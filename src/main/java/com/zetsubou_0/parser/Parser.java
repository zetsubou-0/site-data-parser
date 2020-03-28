package com.zetsubou_0.parser;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;

import java.io.IOException;
import java.util.Set;

public interface Parser {

    /**
     * Parse data by URL and extract data items as list by type
     * @param url URL of the page
     * @param pageType {@link PageType}
     * @return data items as list by type
     * @throws IOException
     */
    Set<DataItem> extract(String url, PageType pageType) throws IOException;
}
