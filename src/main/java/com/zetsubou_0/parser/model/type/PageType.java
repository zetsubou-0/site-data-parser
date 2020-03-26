package com.zetsubou_0.parser.model.type;

import org.apache.commons.lang3.StringUtils;

public enum PageType {
    UNKNOWN(StringUtils.EMPTY),
    LED_LINE("Светодиодные ленты"),
    POWER_BLOCK("Блок питания");

    private final String type;

    PageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
