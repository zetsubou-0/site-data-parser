package com.zetsubou_0.parser;

import com.google.common.collect.ImmutableMap;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.Type;
import org.apache.commons.lang3.time.StopWatch;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserRunner implements Runnable {

    private static final Map<String, Type> SITES = ImmutableMap.<String, Type>builder()
            .put("https://arlight.by/catalog/svetodiodnye-lenty-100002", Type.LED_LINE)
            .build();

    private final String fileName;

    @Inject
    private Parser parser;
    @Inject
    private CsvWriter csvWriter;

    @Inject
    public ParserRunner(String file) {
        this.fileName = file;
    }

    @Override
    public void run() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final List<ItemData> dataList = SITES.entrySet()
                .stream()
                .flatMap(this::processEntry)
                .distinct()
                .collect(Collectors.toList());
        stopWatch.stop();

        System.out.println("Total size: " + dataList.size());
        System.out.println("Total time: " + stopWatch);

        try {
            csvWriter.write(fileName, dataList);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    private Stream<ItemData> processEntry(Map.Entry<String, Type> entry) {
        try {
            System.out.println("Processing of " + entry.getKey() + " has been started");
            return parser.extract(entry.getKey(), entry.getValue())
                    .stream();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return Stream.empty();
    }
}
