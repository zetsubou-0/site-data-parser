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
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnye-lenty-100002", PageType.LED_LINE, "led-lines.csv"))
            .add(Configuration.of("https://arlight.by/catalog/bloki-pitaniya-100006", PageType.POWER_BLOCK, "power-blocks.csv"))
            .add(Configuration.of("https://arlight.by/catalog/alyuminievye-profili-100011", PageType.ALUMINIUM_CONSTRUCTION, "aluminium-construction.csv"))
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnye-svetilniki-100010", PageType.LED_LIGHTS, "led-lights.csv"))
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnyy-dekor-100019", PageType.LED_DECOR, "led-decor.csv"))
            .build();

    private final String path;
    private final Integer delay;

    @Inject
    private Parser parser;
    @Inject
    private CsvWriter csvWriter;

    @Inject
    public ParserRunner(String path, Integer delay) {
        this.path = path;
        this.delay = delay;
    }

    @Override
    public void run() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (Configuration configuration : CONFIGURATIONS) {
            final Set<DataItem> dataItems = processEntry(configuration);
            try {
                csvWriter.write(path + "/" + configuration.getName(), dataItems);
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
            return parser.extract(configuration.getUrl(), configuration.getPageType(), delay);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return Collections.emptySet();
    }
}
