package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DataItemProcessor;
import com.zetsubou_0.parser.dom.ProcessorFactory;
import com.zetsubou_0.parser.model.Type;

import javax.inject.Inject;
import java.util.Map;

public class ProcessorFactoryImpl implements ProcessorFactory {

    @Inject
    private Map<Type, DataItemProcessor> processors;

    @Override
    public DataItemProcessor create(Type type) {
        return processors.get(type);
    }
}
