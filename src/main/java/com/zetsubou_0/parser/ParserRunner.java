package com.zetsubou_0.parser;

import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.csv.impl.CsvWriterImpl;
import com.zetsubou_0.parser.impl.SiteParserImpl;
import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.Type;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.List;

public class ParserRunner {

    public static void main(String[] args) throws IOException {
        final Parser parser = new SiteParserImpl();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final List<ItemData> dataList = parser.extract("https://arlight.by/catalog/svetodiodnye-lenty-100002", Type.LED_LINE);
        stopWatch.stop();
        System.out.println("Size: " + dataList.size());
        System.out.println("Time: " + stopWatch);
        System.out.println("Items: " + dataList);

        final CsvWriter csvWriter = new CsvWriterImpl();
        csvWriter.write("/root/test.csv", dataList);
    }
}
