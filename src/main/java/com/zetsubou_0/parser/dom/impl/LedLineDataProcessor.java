package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.LedLine;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class LedLineDataProcessor extends AbstractDataItemProcessor<LedLine> {

    @Inject
    public LedLineDataProcessor(Helper helper, ReflectionService reflectionService) {
        super(helper, reflectionService, PageType.LED_LINE, LedLine.class);
    }
}
