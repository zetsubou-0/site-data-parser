package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.LedDecor;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class LedDecorDataProcessor extends AbstractDataItemProcessor<LedDecor> {

    @Inject
    public LedDecorDataProcessor(Helper helper, ReflectionService reflectionService) {
        super(helper, reflectionService, PageType.LED_DECOR, LedDecor.class);
    }
}
