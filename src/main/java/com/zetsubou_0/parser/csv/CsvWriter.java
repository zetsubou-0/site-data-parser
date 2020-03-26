package com.zetsubou_0.parser.csv;

import com.zetsubou_0.parser.model.DataItem;

import java.io.IOException;
import java.util.List;

public interface CsvWriter {
    void write(String path, List<DataItem> data) throws IOException;
}
