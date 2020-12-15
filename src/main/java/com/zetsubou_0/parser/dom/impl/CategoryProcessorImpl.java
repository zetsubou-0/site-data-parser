package com.zetsubou_0.parser.dom.impl;

import com.google.common.collect.ImmutableMap;
import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.TaskExecutor;
import com.zetsubou_0.parser.adapter.AdapterFactory;
import com.zetsubou_0.parser.adapter.DataItemAdapter;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.dom.CategoryProcessor;
import com.zetsubou_0.parser.model.AbstractDataItem;
import com.zetsubou_0.parser.model.Configuration;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.PriceDataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import java.io.File;
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
    @Inject
    private TaskExecutor taskExecutor;

    @Override
    public void processEachCategory(Configuration configuration) {
        final List<Pair<String, String>> urlNames;
        try {
            urlNames = parser.extractCategoryUtl(configuration.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for (Pair<String, String> urlName : urlNames) {
            taskExecutor.execute(() -> process(urlName, configuration));
        }
    }

    private void process(Pair<String, String> urlName, Configuration conf) {
        try {
            final Configuration configuration = Configuration.of(urlName.getKey(), conf.getPageType(), conf.getName());
            final String path = configuration.getName() + "/"
                    + urlName.getKey().replaceAll(".*?/([^/]+)/?(\\?.*?)?$", "$1");
            System.out.println("Processing sub category " +urlName + " has been started");
            final Set<DataItem> dataItems = updateWithCategory(
                    parser.extract(configuration.getUrl(), configuration.getPageType()),
                    urlName.getValue()
            );
            final Set<DataItem> prices = adaptToPriceItems(dataItems);
            final ImmutableMap.Builder<String, Set<DataItem>> builder = ImmutableMap.builder();
            builder.put(path + EXTENSION, dataItems);
            builder.put(path + "-prices" + EXTENSION, prices);
            if (conf.getPageType() == PageType.EXTERIOR_LIGHTING || conf.getPageType() == PageType.LED_LIGHTS) {
                builder.put(new File(configuration.getName()).getParent() + "/all-prices" + EXTENSION, prices);
            }
            final Map<String, Set<DataItem>> writeData = builder.build();
            csvWriter.write(writeData);
            writeData.values()
                    .forEach(csvWriter::appendToResults);
        } catch (IOException e) {
            e.printStackTrace();
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
}
