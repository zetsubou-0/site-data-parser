package com.zetsubou_0.parser.csv;

import com.zetsubou_0.parser.model.DataItem;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface CsvWriter {

    /**
     * Write data into file
     * @param path path to file
     * @param data list of data items
     * @throws IOException
     */
    void write(String path, Set<DataItem> data) throws IOException;

    /**
     * Write data into file
     * @param writeData
     * @throws IOException
     */
    void write(Map<String, Set<DataItem>> writeData) throws IOException;
}
