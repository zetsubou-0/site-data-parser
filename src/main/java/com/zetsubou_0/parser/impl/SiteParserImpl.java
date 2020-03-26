package com.zetsubou_0.parser.impl;

import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Set;

public class SiteParserImpl implements Parser {

    @Inject
    private DomAdapter domAdapter;

    @Override
    public Set<DataItem> extract(String url, PageType pageType) throws IOException {
        final Document doc = Jsoup.connect(url).get();
        return domAdapter.adapt(doc, pageType);
    }
}
