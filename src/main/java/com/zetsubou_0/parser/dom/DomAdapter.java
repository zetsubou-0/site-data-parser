package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Set;

public interface DomAdapter {

    /**
     * Adapt DOM document representation to list of data items according provided type
     * @param document {@link Document}
     * @param pageType type of page
     * @return list of data items
     */
    Set<DataItem> adapt(Document document, PageType pageType);

    List<Pair<String, String>> adaptToLinks(Document document);
}
