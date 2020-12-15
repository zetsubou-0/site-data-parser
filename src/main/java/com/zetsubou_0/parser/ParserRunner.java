package com.zetsubou_0.parser;

import com.google.common.collect.ImmutableList;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.dom.CategoryProcessor;
import com.zetsubou_0.parser.model.ApplicationConfiguration;
import com.zetsubou_0.parser.model.Configuration;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class ParserRunner implements Runnable {

    private static final List<Configuration> CONFIGURATIONS = ImmutableList.<Configuration>builder()
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnye-lenty-100002", PageType.LED_LINE, "led-lines"))
            .add(Configuration.of("https://arlight.by/catalog/bloki-pitaniya-100006", PageType.POWER_BLOCK, "power-blocks"))
            .add(Configuration.of("https://arlight.by/catalog/alyuminievye-profili-100011", PageType.ALUMINIUM_CONSTRUCTION, "aluminium-construction"))
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnye-svetilniki-100010", PageType.LED_LIGHTS, "led-lights"))
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnyy-dekor-100019", PageType.LED_DECOR, "led-decor"))
            .add(Configuration.of("https://arlight.by/catalog/upravlenie-svetom-100008/", PageType.CONTROL, "control"))
            .add(Configuration.of("https://arlight.by/catalog/svetodiodnye-prozhektory-100018/", PageType.EXTERIOR_LIGHTING, "exterior-lightning"))
            .build();

    private final String fileRootPath;

    @Inject
    private CategoryProcessor categoryProcessor;
    @Inject
    private CsvWriter csvWriter;

    @Inject
    public ParserRunner(ApplicationConfiguration configuration) {
        this.fileRootPath = configuration.getFileRootPath();
    }

    @Override
    public void run() {
        csvWriter.clearResults();
        for (Configuration configuration : CONFIGURATIONS) {
            configuration.setName(fileRootPath + "/" + configuration.getName());
            categoryProcessor.processEachCategory(configuration);
        }
        try {
            Thread.currentThread().join(60 * 60 * 1000);
            csvWriter.flushResults(fileRootPath + "/all.csv");
        } catch (IOException | InterruptedException e) {
            csvWriter.clearResults();
            e.printStackTrace();
        }
    }
}
