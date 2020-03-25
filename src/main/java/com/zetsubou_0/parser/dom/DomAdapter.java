package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.Type;
import org.jsoup.nodes.Document;

import java.util.List;

public interface DomAdapter {
    List<ItemData> adapt(Document document, Type type);
}
