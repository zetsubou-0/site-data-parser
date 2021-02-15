package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.csv.CsvField;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.type.CharacteristicsType;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ReflectionServiceImpl implements ReflectionService {

    @Override
    public <T> T construct(Class<T> instanceClass, Object... parameters)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return construct(instanceClass, toPairs(parameters));
    }

    @SuppressWarnings("Suppres unchecked")
    private Pair<Class<?>, Object>[] toPairs(Object[] parameters) {
        return Arrays.stream(parameters)
                .map(parameter -> Pair.of(parameter.getClass(), parameter))
                .toArray(Pair[]::new);
    }

    @Override
    public <T> T construct(Class<T> instanceClass, Pair<Class<?>, Object>... parameters)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Pair<Class<?>[], Object[]> constructorParameters = getParametersAsArray(parameters);
        return instanceClass.getConstructor(constructorParameters.getKey())
                .newInstance(constructorParameters.getValue());
    }

    private Pair<Class<?>[], Object[]> getParametersAsArray(Pair<Class<?>, Object>... parameters) {
        if (ArrayUtils.isEmpty(parameters)) {
            return Pair.of(ArrayUtils.EMPTY_CLASS_ARRAY, ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
        final Class<?>[] classes = new Class<?>[parameters.length];
        final Object[] methodParameters = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classes[i] = parameters[i].getKey();
            methodParameters[i] = parameters[i].getValue();
        }
        return Pair.of(classes, methodParameters);
    }

    @Override
    public Stream<Field> getAllFields(Class<?> instanceClass, Class<?> searchedParentClass) {
        final Field[] fields = instanceClass.getDeclaredFields();
        final Class<?> instanceParentClass = instanceClass.getSuperclass();
        return instanceParentClass.isAssignableFrom(searchedParentClass)
                ? Arrays.stream(fields)
                : Stream.concat(
                Arrays.stream(fields),
                getAllFields(instanceParentClass, searchedParentClass)
        );
    }

    @Override
    public Stream<Field> getAllFieldsWithAnnotation(Class<?> instanceClass, Class<?> searchedParentClass, Class<? extends Annotation> annotation) {
        final Class<?> parentClass = instanceClass.getSuperclass();
        final Stream<Field> parentFields = parentClass != null && (searchedParentClass == null || instanceClass.isAssignableFrom(searchedParentClass))
                ? getAllFieldsWithAnnotation(parentClass, searchedParentClass, annotation)
                : Stream.empty();
        final Stream<Field> currentClassFields = Arrays.stream(instanceClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation));
        return Stream.concat(parentFields, currentClassFields);
    }

    @Override
    public void setupFieldValue(Field field, Object object, Object value) {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(accessible);
        }
    }

    @Override
    public <T> T getFieldValue(Field field, Object object, Class<T> returnValueClass) {
        final boolean access = field.isAccessible();
        if (!access) {
            field.setAccessible(true);
        }
        try {
            final Object val = field.get(object);
            if (val == null) {
                return null;
            }
            if (returnValueClass.isAssignableFrom(val.getClass())) {
                return returnValueClass.cast(val);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(access);
        }
        return null;
    }

    @Override
    public String getCsvFieldName(Field field) {
        return Optional.ofNullable(field)
                .map(f -> f.getAnnotation(CsvField.class))
                .map(CsvField::value)
                .map(toHeader(field))
                .map(CharacteristicsType::getHeader)
                .orElse(StringUtils.EMPTY);
    }

    private UnaryOperator<CharacteristicsType> toHeader(Field field) {
        return characteristicsType -> characteristicsType == CharacteristicsType.EMPTY
                ? CharacteristicsType.of(field.getName())
                : characteristicsType;
    }

    @Override
    public boolean isMultiple(Field field) {
        return Optional.ofNullable(field)
                .map(f -> f.getAnnotation(CsvField.class))
                .map(CsvField::multiple)
                .orElse(false);
    }
}
