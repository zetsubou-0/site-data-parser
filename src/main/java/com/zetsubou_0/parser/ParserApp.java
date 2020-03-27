package com.zetsubou_0.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zetsubou_0.parser.di.modules.ParserModules;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ParserApp {

    private static final int DEFAULT_DELAY = 100;

    public static void main(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
            System.err.println("Please specify file name");
            return;
        }
        final int delay = args.length >= 2
                ? NumberUtils.toInt(args[1], DEFAULT_DELAY)
                : DEFAULT_DELAY;
        final Injector injector = Guice.createInjector(new ParserModules(args[0], delay));
        final ParserRunner parserRunner = injector.getInstance(ParserRunner.class);
        new Thread(parserRunner).start();
    }
}
