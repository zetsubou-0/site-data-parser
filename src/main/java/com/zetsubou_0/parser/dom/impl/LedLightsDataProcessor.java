package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.LedLights;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class LedLightsDataProcessor extends AbstractDataItemProcessor<LedLights> {

    @Inject
    public LedLightsDataProcessor(Helper helper, ReflectionService reflectionService) {
        super(helper, reflectionService, PageType.LED_LIGHTS, LedLights.class);
    }
}
