package com.zetsubou_0.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zetsubou_0.parser.di.modules.ParserModules;
import org.apache.commons.lang3.ArrayUtils;

public class ParserApp {

    public static void main(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
            System.err.println("Please specify file name");
            return;
        }
        final String path = args[0];
        System.out.println("Output csv files will be saved under " + path + " folder");
        final Injector injector = Guice.createInjector(new ParserModules(path));
        final ParserRunner parserRunner = injector.getInstance(ParserRunner.class);
        new Thread(parserRunner).start();
    }
}
