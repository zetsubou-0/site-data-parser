package com.zetsubou_0.parser.dom.impl;

import com.google.common.collect.ImmutableMap;
import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.adapter.AdapterFactory;
import com.zetsubou_0.parser.adapter.DataItemAdapter;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.dom.CategoryProcessor;
import com.zetsubou_0.parser.model.AbstractDataItem;
import com.zetsubou_0.parser.model.Configuration;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.PriceDataItem;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryProcessorImpl implements CategoryProcessor {

    private static final String EXTENSION = ".csv";

    @Inject
    private Parser parser;
    @Inject
    private CsvWriter csvWriter;
    @Inject
    private AdapterFactory adapterFactory;

    @Override
    public void processEachCategory(Configuration configuration) {
        try {
            final List<Pair<String, String>> urlNames = parser.extractCategoryUtl(configuration.getUrl());
            for (Pair<String, String> urlName : urlNames) {
                configuration.setUrl(urlName.getKey());
                final String path = configuration.getName() + "/"
                        + urlName.getKey().replaceAll(".*?/([^/]+)/?(\\?.*?)?$", "$1");
                System.out.println("Processing sub category " +urlName + " has been started");
                final Set<DataItem> dataItems = updateWithCategory(processEntry(configuration), urlName.getValue());
                final Map<String, Set<DataItem>> writeData = ImmutableMap.of(
                        path + EXTENSION, dataItems,
                        path + "-prices" + EXTENSION, adaptToPriceItems(dataItems)
                );
                csvWriter.write(writeData);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private Set<DataItem> updateWithCategory(Set<DataItem> processEntry, String category) {
        return processEntry.stream()
                .filter(dataItem -> AbstractDataItem.class.isAssignableFrom(dataItem.getClass()))
                .map(AbstractDataItem.class::cast)
                .map(abstractDataItem -> abstractDataItem.setCategory(category))
                .collect(Collectors.toSet());
    }

    private Set<DataItem> adaptToPriceItems(Set<DataItem> dataItems) {
        final DataItemAdapter<PriceDataItem> adapter = adapterFactory.createAdapter(PriceDataItem.class);
        if (adapter == null) {
            return Collections.emptySet();
        }
        return dataItems.stream()
                .map(adapter::adaptTo)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Set<DataItem> processEntry(Configuration configuration) {
        try {
            return parser.extract(configuration.getUrl(), configuration.getPageType());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return Collections.emptySet();
    }
}
