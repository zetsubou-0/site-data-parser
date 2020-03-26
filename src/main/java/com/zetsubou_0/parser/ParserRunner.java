package com.zetsubou_0.parser;

import com.google.common.collect.ImmutableList;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.model.Configuration;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.time.StopWatch;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ParserRunner implements Runnable {

    private static final List<Configuration> CONFIGURATIONS = ImmutableList.<Configuration>builder()
//            .add(Configuration.of("https://arlight.by/catalog/svetodiodnye-lenty-100002", PageType.LED_LINE, "led.csv"))
            .add(Configuration.of("https://arlight.by/catalog/bloki-pitaniya-100006/", PageType.POWER_BLOCK, "power.csv"))
            .build();

    private final String filePath;

    @Inject
    private Parser parser;
    @Inject
    private CsvWriter csvWriter;

    @Inject
    public ParserRunner(String file) {
        this.filePath = file;
    }

    @Override
    public void run() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (Configuration configuration : CONFIGURATIONS) {
            final Set<DataItem> dataItems = processEntry(configuration);
            try {
                csvWriter.write(filePath + "/" + configuration.getName(), dataItems);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
            System.out.println("Total size: " + dataItems.size());
        }
        stopWatch.stop();

        System.out.println("Total time: " + stopWatch);

    }

    private Set<DataItem> processEntry(Configuration configuration) {
        try {
            System.out.println("Processing of " + configuration.getUrl() + " has been started");
            return parser.extract(configuration.getUrl(), configuration.getPageType());
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return Collections.emptySet();
    }
}
