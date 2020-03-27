package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.model.AluminiumConstruction;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class AluminiumConstructionDataProcessor extends AbstractDataItemProcessor<AluminiumConstruction> {

    @Inject
    public AluminiumConstructionDataProcessor(Helper helper) {
        super(helper, PageType.ALUMINIUM_CONSTRUCTION, AluminiumConstruction.class);
    }
}
