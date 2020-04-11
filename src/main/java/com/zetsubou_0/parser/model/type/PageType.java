package com.zetsubou_0.parser.model.type;

import org.apache.commons.lang3.StringUtils;

public enum PageType {
    UNKNOWN(StringUtils.EMPTY),
    LED_LINE("Светодиодные ленты"),
    POWER_BLOCK("Блок питания"),
    ALUMINIUM_CONSTRUCTION("Алюминиевые профили"),
    LED_LIGHTS("Светодиодные светильники"),
    LED_DECOR("Светодиодный декор"),
    CONTROL("Управление светом"),
    EXTERIOR_LIGHTING("Наружное освещение"),
    ;

    private final String type;

    PageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
