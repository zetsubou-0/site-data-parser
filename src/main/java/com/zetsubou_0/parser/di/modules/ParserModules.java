package com.zetsubou_0.parser.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.csv.impl.CsvWriterImpl;
import com.zetsubou_0.parser.dom.*;
import com.zetsubou_0.parser.dom.impl.*;
import com.zetsubou_0.parser.impl.SiteParserImpl;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Singleton;

public class ParserModules extends AbstractModule {

    private final String fileName;

    public ParserModules(String fileName) {
        this.fileName = fileName;
    }

    @Provides
    public String getFileName() {
        return fileName;
    }

    @Override
    protected void configure() {
        super.configure();

        this.bind(Parser.class).to(SiteParserImpl.class).in(Singleton.class);
        this.bind(CsvWriter.class).to(CsvWriterImpl.class).in(Singleton.class);
        this.bind(DomAdapter.class).to(DomAdapterImpl.class).in(Singleton.class);
        this.bind(Helper.class).to(HelperImpl.class).in(Singleton.class);

        this.bind(ProcessorFactory.class).to(ProcessorFactoryImpl.class).in(Singleton.class);

        final MapBinder<PageType, DataItemProcessor> processors = MapBinder.newMapBinder(this.binder(), PageType.class, DataItemProcessor.class);
        processors.addBinding(PageType.UNKNOWN).to(MockDataItemProcessor.class);
        processors.addBinding(PageType.LED_LINE).to(LedLineDataProcessor.class);
        processors.addBinding(PageType.POWER_BLOCK).to(PowerBlockDataProcessor.class);
    }
}
