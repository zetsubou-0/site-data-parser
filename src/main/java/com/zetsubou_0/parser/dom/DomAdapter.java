package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.Type;
import org.jsoup.nodes.Document;

import java.util.List;

public interface DomAdapter {
    List<DataItem> adapt(Document document, Type type);
}
