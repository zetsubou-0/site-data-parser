package com.zetsubou_0.parser;

import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.Type;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<ItemData> extract(String url, Type type) throws IOException;
}
