package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ProcessorFactory;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DomAdapterImpl implements DomAdapter {

    private static final String PAGENATION_PARAMETER = "?PAGEN_1=";

    @Inject
    private ProcessorFactory processorFactory;
    @Inject
    private Helper helper;

    @Override
    public Set<DataItem> adapt(Document document, PageType pageType) {
        return pageUrlStream(document)
                .flatMap(this::toDocumentElement)
                .filter(Objects::nonNull)
                .flatMap(this::openUrl)
                .filter(Objects::nonNull)
                .map(processorFactory.create(pageType)::processDomElement)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Stream<Element> openUrl(Element element) {
        final String url = helper.extractLink(element, ".card__img a");
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
                .filter(Objects::nonNull)
                .map(this::loadPage);
        return Stream.concat(Stream.of(pageElement), elementStream)
                .parallel()
                .distinct();
    }

    private Element loadPage(String url) {
        try {
            final Elements elements = Jsoup.connect(url)
                    .get()
                    .select(".container");
            if (elements == null || elements.isEmpty()) {
                return null;
            }
            return elements.get(0);
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
