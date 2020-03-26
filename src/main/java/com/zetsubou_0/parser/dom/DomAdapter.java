package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.Type;
import org.jsoup.nodes.Document;

import java.util.List;

public interface DomAdapter {

    /**
     * Adapt DOM document representation to list of data items according provided type
     * @param document {@link Document}
     * @param type type of page
     * @return list of data items
     */
    List<DataItem> adapt(Document document, Type type);
}
