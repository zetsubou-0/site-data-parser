package com.zetsubou_0.parser.csv;

import com.zetsubou_0.parser.model.DataItem;

import java.io.IOException;
import java.util.List;

public interface CsvWriter {

    /**
     * Write data into file
     * @param path path to file
     * @param data list of data items
     * @throws IOException
     */
    void write(String path, List<DataItem> data) throws IOException;
}
