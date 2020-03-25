package com.zetsubou_0.parser.model;

public enum Type {
    LED_LINE("Светодиодные ленты");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
