package com.zetsubou_0.parser.csv.impl;

import com.zetsubou_0.parser.csv.CsvField;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.model.ItemData;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvWriterImpl implements CsvWriter {

    private static final String SEPARATOR = ";";

    @Override
    public void write(String path, List<ItemData> data) throws IOException {
        final Map<String, List<String>> titleValues = data.stream()
                .flatMap(this::prepareData)
                .collect(Collectors.toMap(
                        Pair::getKey,
                        Pair::getValue,
                        (oldVal, newVal) -> {
                            oldVal.addAll(newVal);
                            return oldVal;
                        },
                        LinkedHashMap::new
                ));
        final List<String> titles = new ArrayList<>(titleValues.keySet());
        final String titlesLine = String.join(SEPARATOR, titles);
        final String firstTitle = titles.get(0);
        if (firstTitle == null) {
            return;
        }

        try (final FileWriter writer = new FileWriter(path)) {
            writer.append(titlesLine);
            writer.append("\n");
            final int size = titleValues.get(firstTitle).size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < titles.size(); j++) {
                    writer.append(escape(titleValues.get(titles.get(j)).get(i)));
                    if (j < titles.size() -1) {
                        writer.append(SEPARATOR);
                    }
                }
                writer.append("\n");
            }
            writer.flush();
        }
    }

    private String escape(String text) {
        return StringUtils.replaceChars(text, SEPARATOR, ",");
    }

    private Stream<Pair<String, List<String>>> prepareData(ItemData itemData) {
        return getAllCsvFields(itemData.getClass())
                .map(getFiledTitleAndValue(itemData))
                .filter(Objects::nonNull);
    }

    private Function<Field ,Pair<String, List<String>>> getFiledTitleAndValue(ItemData itemData) {
        return field -> {
            final CsvField annotation = field.getAnnotation(CsvField.class);
            if (annotation == null) {
                return null;
            }
            String value = getFieldValue(field, itemData);
            if (value == null) {
                value = StringUtils.EMPTY;
            }
            final List<String> list = new ArrayList<>();
            list.add(value);
            return Pair.of(annotation.value(), list);
        };
    }

    private String getFieldValue(Field field, ItemData itemData) {
        final boolean access = field.isAccessible();
        if (!access) {
            field.setAccessible(true);
        }
        try {
            final Object val = field.get(itemData);
            if (val instanceof String) {
                return (String) val;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace(System.err);
        } finally {
            field.setAccessible(access);
        }
        return null;
    }

    private Stream<Field> getAllCsvFields(Class<?> cl) {
        final Class<?> parentClass = cl.getSuperclass();
        final Stream<Field> parentFields = parentClass != null
                ? getAllCsvFields(parentClass)
                : Stream.empty();
        final Stream<Field> currentClassFields = Arrays.stream(cl.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(CsvField.class));
        return Stream.concat(parentFields, currentClassFields);
    }
}
