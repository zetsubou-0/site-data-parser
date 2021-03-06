package com.zetsubou_0.parser.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.MapBinder;
import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.TaskExecutor;
import com.zetsubou_0.parser.adapter.AdapterFactory;
import com.zetsubou_0.parser.adapter.DataItemAdapter;
import com.zetsubou_0.parser.adapter.impl.AdapterFactoryImpl;
import com.zetsubou_0.parser.adapter.impl.ImageAdapter;
import com.zetsubou_0.parser.adapter.impl.PriceAdapter;
import com.zetsubou_0.parser.backoff.BackOff;
import com.zetsubou_0.parser.backoff.impl.BackOffImpl;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.csv.impl.CsvWriterImpl;
import com.zetsubou_0.parser.dom.*;
import com.zetsubou_0.parser.dom.impl.*;
import com.zetsubou_0.parser.impl.FixedTaskExecutor;
import com.zetsubou_0.parser.impl.SiteParserImpl;
import com.zetsubou_0.parser.model.ApplicationConfiguration;
import com.zetsubou_0.parser.model.ImageDataItem;
import com.zetsubou_0.parser.model.PriceDataItem;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Singleton;

public class ParserModules extends AbstractModule {

    private final ApplicationConfiguration configuration;

    public ParserModules(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Provides
    public ApplicationConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    protected void configure() {
        super.configure();

        this.bind(Parser.class).to(SiteParserImpl.class).in(Singleton.class);
        this.bind(CsvWriter.class).to(CsvWriterImpl.class).in(Singleton.class);
        this.bind(DomAdapter.class).to(DomAdapterImpl.class).in(Singleton.class);
        this.bind(Helper.class).to(HelperImpl.class).in(Singleton.class);
        this.bind(ReflectionService.class).to(ReflectionServiceImpl.class).in(Singleton.class);
        this.bind(CategoryProcessor.class).to(CategoryProcessorImpl.class).in(Singleton.class);
        this.bind(BackOff.class).to(BackOffImpl.class).in(Singleton.class);
        this.bind(TaskExecutor.class).to(FixedTaskExecutor.class).in(Singleton.class);

        this.bind(ProcessorFactory.class).to(ProcessorFactoryImpl.class).in(Singleton.class);
        this.bind(AdapterFactory.class).to(AdapterFactoryImpl.class).in(Singleton.class);

        final MapBinder<PageType, DataItemProcessor> processors = MapBinder.newMapBinder(this.binder(), PageType.class, DataItemProcessor.class);
        processors.addBinding(PageType.UNKNOWN).to(MockDataItemProcessor.class);
        processors.addBinding(PageType.LED_LINE).to(LedLineDataProcessor.class);
        processors.addBinding(PageType.POWER_BLOCK).to(PowerBlockDataProcessor.class);
        processors.addBinding(PageType.ALUMINIUM_CONSTRUCTION).to(AluminiumConstructionDataProcessor.class);
        processors.addBinding(PageType.LED_LIGHTS).to(LedLightsDataProcessor.class);
        processors.addBinding(PageType.LED_DECOR).to(LedDecorDataProcessor.class);
        processors.addBinding(PageType.CONTROL).to(ControlDataProcessor.class);
        processors.addBinding(PageType.EXTERIOR_LIGHTING).to(ExteriorLightingProcessor.class);

        final MapBinder<Class, DataItemAdapter> categoryProcessors =
                MapBinder.newMapBinder(this.binder(), Class.class, DataItemAdapter.class);
        categoryProcessors.addBinding(PriceDataItem.class).to(PriceAdapter.class);
        categoryProcessors.addBinding(ImageDataItem.class).to(ImageAdapter.class);
    }
}
