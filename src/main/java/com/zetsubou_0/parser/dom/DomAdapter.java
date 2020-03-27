package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.jsoup.nodes.Document;

import java.util.Set;

public interface DomAdapter {

    /**
     * Adapt DOM document representation to list of data items according provided type
     * @param document {@link Document}
     * @param pageType type of page
     * @param delay delay between requests
     * @return list of data items
     */
    Set<DataItem> adapt(Document document, PageType pageType, int delay);
}
