package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.type.PageType;

public interface ProcessorFactory {

    /**
     * Create data item processor by provided type
     * @param pageType {@link PageType}
     * @return data item processor by provided type or null
     */
    DataItemProcessor create(PageType pageType);
}
