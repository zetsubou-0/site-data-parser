package com.zetsubou_0.parser.impl;

import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.dom.impl.DomAdapterImpl;
import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.Type;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class SiteParserImpl implements Parser {

    private final DomAdapter domAdapter = new DomAdapterImpl();

    @Override
    public List<ItemData> extract(String url, Type type) throws IOException {
        final Document doc = Jsoup.connect(url).get();
        return domAdapter.adapt(doc, type);
    }
}
