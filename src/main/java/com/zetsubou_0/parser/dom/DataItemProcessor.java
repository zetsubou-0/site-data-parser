package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.DataItem;
import org.jsoup.nodes.Element;

public interface DataItemProcessor {
    DataItem processDomElement(Element element);
}
