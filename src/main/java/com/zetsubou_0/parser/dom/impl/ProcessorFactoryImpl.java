package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DataItemProcessor;
import com.zetsubou_0.parser.dom.ProcessorFactory;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class ProcessorFactoryImpl implements ProcessorFactory {

    @Inject
    private Map<PageType, DataItemProcessor> processors;

    @Override
    public DataItemProcessor create(PageType pageType) {
        return Optional.ofNullable(processors.get(pageType))
                .orElse(processors.get(PageType.UNKNOWN));
    }
}
