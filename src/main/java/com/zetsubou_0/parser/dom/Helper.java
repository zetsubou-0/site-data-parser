package com.zetsubou_0.parser.dom;

import org.jsoup.nodes.Element;

import java.util.Optional;

public interface Helper {

    String HREF = "href";
    String SRC = "src";

    Optional<Element> getFirstBySelector(Element element, String selector);
    String extractText(Element element, String selector);
    String extractImage(Element element, String selector);
    String extractLink(Element element, String selector);
}
