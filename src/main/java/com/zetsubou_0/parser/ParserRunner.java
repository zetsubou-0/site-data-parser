package com.zetsubou_0.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zetsubou_0.parser.di.modules.ParserModules;
import org.apache.commons.lang3.ArrayUtils;

public class ParserRunner {

    public static void main(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
            System.err.println("Please specify file name");
            return;
        }
        final Injector injector = Guice.createInjector(new ParserModules(args[0]));
        final DocumentParser documentParser = injector.getInstance(DocumentParser.class);
        new Thread(documentParser).start();
    }
}
