package com.zetsubou_0.parser;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.Type;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<DataItem> extract(String url, Type type) throws IOException;
}
