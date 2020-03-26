package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.DataItem;
import org.jsoup.nodes.Element;

public interface DataItemProcessor {

    /**
     * Convert DOM element into {@link DataItem}
     * @param element {@link Element}
     * @return data item from DOM element
     */
    DataItem processDomElement(Element element);
}
