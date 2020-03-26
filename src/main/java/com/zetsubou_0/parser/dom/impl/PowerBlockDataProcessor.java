package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.model.PowerBlock;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class PowerBlockDataProcessor extends AbstractDataItemProcessor<PowerBlock> {

    @Inject
    private Helper helper;

    @Override
    protected PageType getType() {
        return PageType.POWER_BLOCK;
    }

    @Override
    protected Class<PowerBlock> getItemClass() {
        return PowerBlock.class;
    }

    @Override
    protected Helper getHelper() {
        return helper;
    }
}
