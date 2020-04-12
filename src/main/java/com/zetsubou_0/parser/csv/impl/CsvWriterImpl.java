package com.zetsubou_0.parser.csv.impl;

import com.zetsubou_0.parser.csv.CsvField;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.CharacteristicsType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvWriterImpl implements CsvWriter {

    private static final String SEPARATOR = ";";
    private static final BinaryOperator<List<String>> MERGER = (oldVal, newVal) -> {
        oldVal.addAll(newVal);
        return oldVal;
    };

    @Override
    public void write(String path, Set<DataItem> data) throws IOException {
        final Map<String, List<String>> titleValues = data.stream()
                .flatMap(this::prepareData)
                .collect(Collectors.toMap(
                        Pair::getKey,
                        Pair::getValue,
                        MERGER,
                        LinkedHashMap::new
                ));
        final List<String> titles = new ArrayList<>(titleValues.keySet());
        final String titlesLine = String.join(SEPARATOR, titles);
        if (titles.isEmpty()) {
            return;
        }
        final String firstTitle = titles.get(0);
        if (firstTitle == null) {
            return;
        }

        System.out.println("Saving into " + path);
        writeToFile(path, titleValues, titles, titlesLine, firstTitle);
    }

    @Override
    public void write(Map<String, Set<DataItem>> writeData) throws IOException {
        for (Map.Entry<String, Set<DataItem>> item : writeData.entrySet()) {
            write(item.getKey(), item.getValue());
        }
    }

    private void writeToFile(String path, Map<String, List<String>> titleValues, List<String> titles,
                             String titlesLine, String firstTitle) throws IOException {
        if (!createPath(path)) {
            System.err.println("Cannot create file: " + path);
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

    private synchronized boolean createPath(String path) throws IOException {
        final File file = new File(path);
        final File folder = file.getParentFile();
        return (folder.exists() || folder.mkdirs()) && (file.exists() || file.createNewFile());
    }

    private String escape(String text) {
        return StringUtils.replaceChars(text, SEPARATOR, ",");
    }

    private Stream<Pair<String, List<String>>> prepareData(DataItem dataItem) {
        return getAllCsvFields(dataItem.getClass())
                .map(getFiledTitleAndValue(dataItem))
                .filter(Objects::nonNull);
    }

    private Function<Field ,Pair<String, List<String>>> getFiledTitleAndValue(DataItem dataItem) {
        return field -> {
            final CsvField annotation = field.getAnnotation(CsvField.class);
            if (annotation == null) {
                return null;
            }
            String value = getFieldValue(field, dataItem);
            if (value == null) {
                value = StringUtils.EMPTY;
            }
            final List<String> list = new ArrayList<>();
            list.add(value);
            if (annotation.value() == CharacteristicsType.EMPTY) {
                return Pair.of(CharacteristicsType.of(field.getName()).getHeader(), list);
            }
            return Pair.of(annotation.value().getHeader(), list);
        };
    }

    private String getFieldValue(Field field, DataItem dataItem) {
        final boolean access = field.isAccessible();
        if (!access) {
            field.setAccessible(true);
        }
        try {
            final Object val = field.get(dataItem);
            if (val instanceof String) {
                return (String) val;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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
