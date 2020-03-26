package com.zetsubou_0.parser;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.Type;

import java.io.IOException;
import java.util.List;

public interface Parser {

    /**
     * Parse data by URL and extract data items as list by type
     * @param url URL of the page
     * @param type {@link Type}
     * @return data items as list by type
     * @throws IOException
     */
    List<DataItem> extract(String url, Type type) throws IOException;
}
