package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.backoff.BackOff;
import com.zetsubou_0.parser.backoff.impl.BackOffConfig;
import com.zetsubou_0.parser.backoff.impl.BackOffConfigBuilder;
import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ProcessorFactory;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DomAdapterImpl implements DomAdapter {

    private static final String PAGENATION_PARAMETER = "?PAGEN_1=";
    private static final String LINK_SELECTOR = ".categories-list__link";

    @Inject
    private ProcessorFactory processorFactory;
    @Inject
    private Helper helper;
    @Inject
    private BackOff backOff;

    @Override
    public Set<DataItem> adapt(String url, Document document, PageType pageType) {
        return pageUrlStream(url, document)
                .flatMap(toDocumentElement())
                .filter(Objects::nonNull)
                .flatMap(openUrl())
                .filter(Objects::nonNull)
                .map(processorFactory.create(pageType)::processDomElement)
                .filter(Objects::nonNull)
                .filter(dataItem -> dataItem.getPrice().matches("\\d+(\\.\\d+)?"))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Pair<String, String>> adaptToLinks(Document document) {
        return Optional.ofNullable(document)
                .map(doc -> helper.getAllBySelector(doc.body(), ".cat_list_categories_in_filter li.active_el > .categories-list.categories-list--sub > li"))
                .orElseGet(Stream::empty)
                .filter(Objects::nonNull)
                .map(this::createUrlNamePair)
                .filter(pair -> StringUtils.isNotEmpty(pair.getKey()) && StringUtils.isNotEmpty(pair.getValue()))
                .collect(Collectors.toList());
    }

    private Pair<String, String> createUrlNamePair(Element element) {
        return Pair.of(helper.extractLink(element, LINK_SELECTOR), helper.extractText(element, LINK_SELECTOR));
    }

    private Function<Element, Stream<Element>> openUrl() {
        return element -> {
            final String url = helper.extractLink(element, ".card__img a");
            if (StringUtils.isEmpty(url)) {
                return Stream.empty();
            }
            final Element pageElement = loadPage().apply(url);
            if (pageElement == null) {
                return Stream.empty();
            }
            Stream<Element> elementStream = pageElement.select(".product__other-list a")
                    .stream()
                    .map(el -> el.absUrl(Helper.HREF))
                    .filter(Objects::nonNull)
                    .map(loadPage());
            return Stream.concat(Stream.of(pageElement), elementStream)
                    .distinct();
        };
    }

    private Function<String, Element> loadPage() {
        return url -> {
            final BackOffConfig<Element> config = BackOffConfigBuilder.<Element>defaultBuilder()
                    .setMessage("URL: " + url)
                    .setSupplier(() -> {
                        final Elements elements = Jsoup.connect(url)
                                .get()
                                .select(".container");
                        if (elements == null || elements.isEmpty()) {
                            return null;
                        }
                        return elements.get(0);
                    })
                    .build();
            try {
                return backOff.produce(config);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };
    }

    private Function<String, Stream<Element>> toDocumentElement() {
        return url -> {
            final BackOffConfig<Stream<Element>> config = BackOffConfigBuilder.<Stream<Element>>defaultBuilder()
                .setMessage("URL: " + url)
                .setSupplier(() -> Jsoup.connect(url)
                            .get()
                            .select(".catalog-block__item.card")
                            .stream())
                .build();
            try {
                return backOff.produce(config);
            } catch (Exception e) {
                e.printStackTrace();
                return Stream.empty();
            }
        };
    }

    private Stream<String> pageUrlStream(String parentUrl, Document document) {
        final String url = helper.extractLink(document.body(), ".pagination__item [title=\"Начало\"]");
        if (StringUtils.isEmpty(url)) {
            return Stream.of(parentUrl);
        }
        final int lastIndex = Optional.of(helper.extractLink(document.body(), ".pagination__item [title=\"Конец\"]"))
                .map(fullUrl -> StringUtils.substringAfterLast(fullUrl, PAGENATION_PARAMETER))
                .map(page -> NumberUtils.toInt(page, -1))
                .orElse(-1);
        if (lastIndex < 0) {
            return Stream.of(parentUrl);
        }
        return IntStream.range(1, lastIndex)
                .mapToObj(index -> url + PAGENATION_PARAMETER + index)
                .parallel();
    }
}
