package com.zetsubou_0.parser.impl;

import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.backoff.BackOff;
import com.zetsubou_0.parser.backoff.impl.BackOffConfig;
import com.zetsubou_0.parser.backoff.impl.BackOffConfigBuilder;
import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

public class SiteParserImpl implements Parser {

    @Inject
    private DomAdapter domAdapter;
    @Inject
    private BackOff backOff;

    @Override
    public Set<DataItem> extract(String url, PageType pageType) throws IOException {
        final BackOffConfig<Set<DataItem>> config = BackOffConfigBuilder.<Set<DataItem>>defaultBuilder()
                .setMessage("URL: " + url)
                .setSupplier(() -> {
                    final Document doc = Jsoup.connect(url).get();
                    return domAdapter.adapt(url, doc, pageType);
                })
                .build();
        try {
            return backOff.produce(config);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public List<Pair<String, String>> extractCategoryUtl(String url) throws IOException {
        final BackOffConfig<List<Pair<String, String>>> config = BackOffConfigBuilder.<List<Pair<String, String>>>defaultBuilder()
                .setMessage("Extracting URLs. Parent URL: " + url)
                .setSupplier(() -> {
                    final Document doc = Jsoup.connect(url).get();
                    return domAdapter.adaptToLinks(doc);
                })
                .build();
        try {
            return backOff.produce(config);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
