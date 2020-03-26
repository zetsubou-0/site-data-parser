package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class HelperImpl implements Helper {

    @Override
    public Optional<Element> getFirstBySelector(Element element, String selector) {
        return Optional.of(element.select(selector))
                .filter(elements -> !elements.isEmpty())
                .map(Elements::first);
    }

    @Override
    public String extractText(Element element, String selector) {
        return getFirstBySelector(element, selector)
                .map(Element::text)
                .orElse(StringUtils.EMPTY);
    }

    @Override
    public String extractImage(Element element, String selector) {
        return getFirstBySelector(element, selector)
                .map(el -> el.absUrl(SRC))
                .orElse(StringUtils.EMPTY);
    }

    @Override
    public String extractLink(Element element, String selector) {
        return getFirstBySelector(element, selector)
                .map(el -> el.absUrl(Helper.HREF))
                .orElse(StringUtils.EMPTY);
    }
}
