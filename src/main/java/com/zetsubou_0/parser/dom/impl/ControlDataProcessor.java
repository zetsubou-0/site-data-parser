package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.Control;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class ControlDataProcessor extends AbstractDataItemProcessor<Control> {

    @Inject
    public ControlDataProcessor(Helper helper, ReflectionService reflectionService) {
        super(helper, reflectionService, PageType.CONTROL, Control.class);
    }
}
