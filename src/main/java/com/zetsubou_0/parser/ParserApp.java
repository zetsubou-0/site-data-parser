package com.zetsubou_0.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zetsubou_0.parser.di.modules.ParserModules;
import com.zetsubou_0.parser.model.ApplicationConfiguration;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ParserApp {

    public static void main(String[] args) throws IOException {
        final ApplicationConfiguration configuration = new ApplicationConfiguration(args);
        if (!configuration.isValid()) {
            System.err.println("Please specify file name");
            return;
        }
        FileUtils.deleteDirectory(new File(configuration.getFileRootPath()));
        System.out.println("Output csv files will be saved under " + configuration.getFileRootPath() + " folder");
        final Injector injector = Guice.createInjector(new ParserModules(configuration));
        final ParserRunner parserRunner = injector.getInstance(ParserRunner.class);
        new Thread(parserRunner).start();
    }
}
