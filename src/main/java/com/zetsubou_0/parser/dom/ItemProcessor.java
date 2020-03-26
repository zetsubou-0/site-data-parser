package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.ItemData;
import org.jsoup.nodes.Element;

public interface ItemProcessor {
    ItemData processDomElement(Element element);
}
