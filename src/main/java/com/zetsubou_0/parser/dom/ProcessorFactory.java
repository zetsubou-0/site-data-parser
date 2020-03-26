package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.Type;

public interface ProcessorFactory {

    /**
     * Create data item processor by provided type
     * @param type {@link Type}
     * @return data item processor by provided type or null
     */
    DataItemProcessor create(Type type);
}
