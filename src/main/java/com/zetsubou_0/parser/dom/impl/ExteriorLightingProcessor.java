package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.ExteriorLighting;
import com.zetsubou_0.parser.model.type.PageType;

import javax.inject.Inject;

public class ExteriorLightingProcessor extends AbstractDataItemProcessor<ExteriorLighting> {

    @Inject
    public ExteriorLightingProcessor(Helper helper, ReflectionService reflectionService) {
        super(helper, reflectionService, PageType.EXTERIOR_LIGHTING, ExteriorLighting.class);
    }
}
