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
        final Injector injector = Guice.createInjector(new ParserModules(args[0]));
        final ParserRunner parserRunner = injector.getInstance(ParserRunner.class);
        new Thread(parserRunner).start();
    }
}
