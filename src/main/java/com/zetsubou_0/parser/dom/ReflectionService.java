package com.zetsubou_0.parser.dom;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

/**
 * Util class that simplify working with Java reflection API
 */
public interface ReflectionService {

    /**
     * Construct new instance of class T
     * @param instanceClass instance class
     * @param parameters constructor parameters
     * @param <T> type of the instance
     * @return new created instance of the object
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    <T> T construct(Class<T> instanceClass, Object ... parameters) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * Construct new instance of class T
     * @param instanceClass instance class
     * @param parameters constructor parameters as pairs of instance class (constructor signature parameter) and parameter (object) itself
     * @param <T> type of the instance
     * @return new created instance of the object
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    <T> T construct(Class<T> instanceClass, Pair<Class<?>, Object>... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    /**
     * Return all fields recursively in the class to parent class (do not including it)
     * @param instanceClass root class
     * @param searchedParentClass the parent class
     * @return all fields recursively in the class to parent class (do not including it)
     */
    Stream<Field> getAllFields(Class<?> instanceClass, Class<?> searchedParentClass);

    /**
     * Setup new value of the field
     * @param field {@link Field}
     * @param object object that contains this field
     * @param value new value
     */
    void setupFieldValue(Field field, Object object, Object value);
}
