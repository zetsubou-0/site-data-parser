package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.ItemProcessor;
import com.zetsubou_0.parser.model.Type;

public class ProcessorFactory {
    private ProcessorFactory() {
    }

    public static ItemProcessor create(Type type) {
        return new LinesLedProcessor();
    }
}
