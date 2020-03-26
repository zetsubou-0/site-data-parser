package com.zetsubou_0.parser.impl;

import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.Type;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class SiteParserImpl implements Parser {

    @Inject
    private DomAdapter domAdapter;

    @Override
    public List<DataItem> extract(String url, Type type) throws IOException {
        final Document doc = Jsoup.connect(url).get();
        return domAdapter.adapt(doc, type);
    }
}
