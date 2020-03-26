package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DataItemProcessor;
import com.zetsubou_0.parser.model.DataItem;
import org.jsoup.nodes.Element;

public class MockDataItemProcessor implements DataItemProcessor {

    @Override
    public DataItem processDomElement(Element element) {
        return null;
    }
}
