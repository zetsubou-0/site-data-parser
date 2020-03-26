package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.Type;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DomAdapterImpl implements DomAdapter {

    private static final String PAGENATION_PARAMETER = "?PAGEN_1=";

    @Override
    public List<ItemData> adapt(Document document, Type type) {
        return pageUrlStream(document)
                .flatMap(this::toDocumentElement)
                .filter(Objects::nonNull)
                .flatMap(this::openUrl)
                .filter(Objects::nonNull)
                .map(ProcessorFactory.create(type)::processDomElement)
                .distinct()
                .collect(Collectors.toList());
    }

    private Stream<Element> openUrl(Element element) {
        final String url = element.select(".card__img a").get(0).absUrl(Helper.HREF);
        if (StringUtils.isEmpty(url)) {
            return Stream.empty();
        }
        final Element pageElement = loadPage(url);
        if (pageElement == null) {
            return Stream.empty();
        }
        Stream<Element> elementStream = pageElement.select(".product__other-list a")
                .stream()
                .parallel()
                .map(el -> el.absUrl(Helper.HREF))
                .map(this::loadPage);
        return Stream.concat(Stream.of(pageElement), elementStream)
                .parallel()
                .distinct();
    }

    private Element loadPage(String url) {
        try {
            return Jsoup.connect(url)
                    .get()
                    .select(".container")
                    .get(0);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    private Stream<Element> toDocumentElement(String url) {
        try {
            return Jsoup.connect(url)
                    .get()
                    .select(".catalog-block__item.card")
                    .stream()
                    .parallel();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return Stream.empty();
        }
    }

    private Stream<String> pageUrlStream(Document document) {
        final String url = document.select(".pagination__item [title=\"Начало\"]").get(0).absUrl(Helper.HREF);
        if (StringUtils.isEmpty(url)) {
            return Stream.empty();
        }
        final int lastIndex = Optional.of(document.select(".pagination__item [title=\"Конец\"]"))
                .map(el -> el.get(0))
                .map(el -> el.absUrl(Helper.HREF))
                .map(fullUrl -> StringUtils.substringAfterLast(fullUrl, PAGENATION_PARAMETER))
                .map(page -> NumberUtils.toInt(page, -1))
                .orElse(-1);
        if (lastIndex < 0) {
            return Stream.empty();
        }
        return IntStream.range(1, lastIndex)
                .mapToObj(index -> url + PAGENATION_PARAMETER + index)
                .parallel();
    }
}
