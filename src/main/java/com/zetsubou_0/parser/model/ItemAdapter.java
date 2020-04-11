package com.zetsubou_0.parser.model;

public interface ItemAdapter {

    /**
     * Adapt current object to T class
     * @param adaptableClass adaptable class
     * @param <T> adaptable type
     * @return instance of T class or null
     */
    <T extends DataItem> T adaptTo(Class<T> adaptableClass);
}
