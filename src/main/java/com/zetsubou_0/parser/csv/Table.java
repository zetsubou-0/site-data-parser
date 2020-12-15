package com.zetsubou_0.parser.csv;

import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.DataItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

public class Table {

    private static final String SEPARATOR = ";";

    private final List<String> headers = new ArrayList<>();
    private final Map<String, List<Pair<Integer, String>>> cells = new HashMap<>();
    private final String[][] table;

    private int maxRows = 0;
    private int maxColumns = 0;

    public Table(ReflectionService reflectionService, Collection<DataItem> dataItems) {
        int rowIndex = 0;
        for (DataItem dataItem : dataItems) {
            prepareData(reflectionService, rowIndex++).accept(dataItem);
        }
        table = new String[maxRows][maxColumns];
        appendHeaderData(table, headers);
        appendRowsData(table, cells);
        normalizeTable(table);
    }

    public String[][] getCells() {
        return table;
    }

    private void normalizeTable(String[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == null) {
                    table[i][j] = StringUtils.EMPTY;
                }
            }
        }
    }

    private void appendRowsData(String[][] table, Map<String, List<Pair<Integer, String>>> cells) {
        cells.forEach((header, dataList)
                -> dataList.forEach(dataPair -> table[dataPair.getLeft() + 1][headers.indexOf(header)] = dataPair.getRight()));
    }

    private void appendHeaderData(String[][] table, List<String> headers) {
        for (int i = 0; i < headers.size(); i++) {
            table[0][i] = headers.get(i);
        }
    }

    private Consumer<DataItem> prepareData(ReflectionService reflectionService, int rowIndex) {
        return dataItem -> reflectionService.getAllFieldsWithAnnotation(dataItem.getClass(), null, CsvField.class)
                .forEach(appendCell(reflectionService, dataItem, rowIndex));
    }

    private Consumer<Field> appendCell(ReflectionService reflectionService, DataItem dataItem, int rowIndex) {
        return field -> {
            final String header = reflectionService.getCsvFieldName(field);
            if (!headers.contains(header)) {
                headers.add(header);
                cells.put(header, new ArrayList<>());
            }
            maxRows = Math.max(maxRows, rowIndex + 2);
            maxColumns = Math.max(maxColumns, headers.indexOf(header) + 1);
            final String cellText = escape(reflectionService.getFieldValue(field, dataItem, String.class));
            cells.get(header).add(Pair.of(rowIndex, cellText));
        };
    }

    private String escape(String text) {
        if (text == null) {
            return StringUtils.EMPTY;
        }
        return StringUtils.replaceChars(text, SEPARATOR, ",");
    }
}
