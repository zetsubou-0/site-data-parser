package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.PowerBlock;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class PowerBlockDataProcessor extends AbstractDataItemProcessor<PowerBlock> {

    @Inject
    public PowerBlockDataProcessor(Helper helper, ReflectionService reflectionService) {
        super(helper, reflectionService, PageType.POWER_BLOCK, PowerBlock.class);
    }
}
