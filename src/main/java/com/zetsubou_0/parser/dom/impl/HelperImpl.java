package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.model.type.CharacteristicsType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelperImpl implements Helper {

    @Override
    public Stream<Element> getAllBySelector(Element element, String selector) {
        return Optional.ofNullable(element.select(selector))
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }

    @Override
    public Optional<Element> getFirstBySelector(Element element, String selector) {
        return Optional.ofNullable(element.selectFirst(selector));
    }

    @Override
    public String getSpecificationData(Element element, CharacteristicsType type) {
        return extractText(
                element,
                ".specifications__table .specifications__table-item[data-property=\"" + type.getSelector() + "\"] .specifications__table-td"
        );
    }

    @Override
    public String extractText(Element element, String selector) {
        return getFirstBySelector(element, selector)
                .map(Element::text)
                .orElse(StringUtils.EMPTY);
    }

    @Override
    public List<String> extractImages(Element element, String selector) {
        return Optional.ofNullable(element.select(selector))
                .map(Elements::stream)
                .orElseGet(Stream::empty)
                .map(el -> el.absUrl(SRC))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
    }

    @Override
    public String extractLink(Element element, String selector) {
        return getFirstBySelector(element, selector)
                .map(el -> el.absUrl(Helper.HREF))
                .orElse(StringUtils.EMPTY);
    }
}
