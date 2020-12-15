package com.zetsubou_0.parser.csv.impl;

import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.csv.Table;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.PriceDataItem;

import javax.inject.Inject;
import java.io.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CsvWriterImpl implements CsvWriter {

    private static final String SEPARATOR = ";";
    private static final BinaryOperator<List<String>> MERGER = (oldVal, newVal) -> {
        oldVal.addAll(newVal);
        return oldVal;
    };

    private Set<DataItem> allDataItems = Collections.synchronizedSet(new HashSet<>());

    @Inject
    private ReflectionService reflectionService;

    @Override
    public void write(String path, Set<DataItem> data) throws IOException {
        System.out.println("Saving into " + path);
        writeToFile(path, new Table(reflectionService, data));
        System.out.println("File has been saved. " + path);
    }

    @Override
    public void write(Map<String, Set<DataItem>> writeData) throws IOException {
        writeData.entrySet()
                .stream()
                .parallel()
                .forEach(item -> {
                    try {
                        write(item.getKey(), item.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public synchronized void clearResults() {
        allDataItems = Collections.synchronizedSet(new HashSet<>());
    }

    @Override
    public void appendToResults(Set<DataItem> dataItems) {
        allDataItems.addAll(dataItems);
    }

    @Override
    public void flushResults(String file) throws IOException {
        final Set<DataItem> dataItems = new HashSet<>(allDataItems)
                .stream()
                .filter(dataItem -> !(dataItem instanceof PriceDataItem))
                .collect(Collectors.toSet());
        System.out.println("Processing of " + dataItems.size() + " records has been started ...");
        write(file, dataItems);
        clearResults();
    }

    private void writeToFile(String path, Table table) throws IOException {
        if (!createPath(path)) {
            System.err.println("Cannot create file: " + path);
            return;
        }
        final String[][] cells = table.getCells();
        try (final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true), "cp1251"))) {
            for (int rowIndex = 0; rowIndex < cells.length; rowIndex++) {
                final String row = String.join(SEPARATOR, cells[rowIndex]);
                writer.append(row)
                        .append("\r\n");
            }
            writer.flush();
        }
    }

    private boolean createPath(String path) throws IOException {
        final File file = new File(path);
        final File folder = file.getParentFile();
        return (folder.exists() || folder.mkdirs()) && (file.exists() || file.createNewFile());
    }
}
