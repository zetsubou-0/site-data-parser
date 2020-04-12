package com.zetsubou_0.parser;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.List;
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

    /**
     * Parse data end extract a list with list of pairs with category URL and name
     * @param url parent URL
     * @return list of pairs with category URL and name
     */
    List<Pair<String, String>> extractCategoryUtl(String url) throws IOException;;
}
