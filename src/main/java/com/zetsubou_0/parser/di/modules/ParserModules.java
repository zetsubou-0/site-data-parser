package com.zetsubou_0.parser.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zetsubou_0.parser.Parser;
import com.zetsubou_0.parser.csv.CsvWriter;
import com.zetsubou_0.parser.csv.impl.CsvWriterImpl;
import com.zetsubou_0.parser.impl.SiteParserImpl;

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
    }
}
