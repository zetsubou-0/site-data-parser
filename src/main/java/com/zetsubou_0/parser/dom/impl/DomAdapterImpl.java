package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DomAdapter;
import com.zetsubou_0.parser.model.ItemData;
import com.zetsubou_0.parser.model.LedLine;
import com.zetsubou_0.parser.model.Type;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DomAdapterImpl implements DomAdapter {

    private static final String PAGENATION_PARAMETER = "?PAGEN_1=";
    private static final String HREF = "href";
    public static final String SRC = "src";

    @Override
    public List<ItemData> adapt(Document document, Type type) {
        return pageUrlStream(document)
                .flatMap(this::toDocumentElement)
                .filter(Objects::nonNull)
                .flatMap(this::openUrl)
                .filter(Objects::nonNull)
                .map(toItemData(type))
                .distinct()
                .collect(Collectors.toList());
    }

    private Stream<Element> openUrl(Element element) {
        final String url = element.select(".card__img a").get(0).absUrl(HREF);
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
                .map(el -> el.absUrl(HREF))
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

    private Function<Element, ItemData> toItemData(Type type) {
        return element -> {
            final LedLine itemData = new LedLine(
                    type.getType(),
                    extractTitle(element),
                    extractArticle(element),
                    extractImage(element),
                    extractPrice(element)
            );
            return setupSpecificationsData(element, itemData);
        };
    }

    private ItemData setupSpecificationsData(Element element, LedLine itemData) {
        itemData.setProductType(getSpecificationData(element, "T_62"));
        itemData.setLightColor(getSpecificationData(element, "COLOR_HREF"));
        itemData.setLightTemperature(getSpecificationData(element, "T_25"));
        itemData.setIndex(getSpecificationData(element, "T_63"));
        itemData.setAngle(getSpecificationData(element, "T_5"));
        itemData.setSize(getSpecificationData(element, "T_45"));
        itemData.setDensity(getSpecificationData(element, "T_46"));
        itemData.setCount(getSpecificationData(element, "T_23"));
        itemData.setLuminousFlux(getSpecificationData(element, "T_94"));
        itemData.setLuminousFluxCommon(getSpecificationData(element, "T_8"));
        itemData.setPower(getSpecificationData(element, "T_95"));
        itemData.setPowerCommonMax(getSpecificationData(element, "T_35"));
        itemData.setPowerCommon(getSpecificationData(element, "T_75"));
        itemData.setVoltage(getSpecificationData(element, "T_20"));
        itemData.setCurrent(getSpecificationData(element, "T_3"));
        itemData.setIp(getSpecificationData(element, "T_44"));
        itemData.setMinSize(getSpecificationData(element, "T_33"));
        itemData.setLength(getSpecificationData(element, "MY_6"));
        itemData.setWidth(getSpecificationData(element, "MY_7"));
        itemData.setHeight(getSpecificationData(element, "MY_8"));
        itemData.setPacking(getSpecificationData(element, "PACKNORM"));
        itemData.setPackingType(getSpecificationData(element, "PACKAGE_NAME"));
        itemData.setGuaranteePeriod(getSpecificationData(element, "T_89"));
        return itemData;
    }

    private String getSpecificationData(Element element, String property) {
        return extractText(element, ".specifications__table .specifications__table-item[data-property=\"" + property + "\"] .specifications__table-td");
    }

    private String extractTitle(Element element) {
        return extractText(element, ".product__title");
    }

    private String extractArticle(Element element) {
        return extractText(element, ".product__vendor")
                .replaceAll("[\\D\\s]", StringUtils.EMPTY);
    }

    private String extractImage(Element element) {
        return getFirstBySelector(element, ".slide picture img")
                .map(el -> el.absUrl(SRC))
                .orElse(StringUtils.EMPTY);
    }

    private String extractPrice(Element element) {
        return element.select(".card__price-now .price")
                .attr("content");
    }

    private String extractText(Element element, String selector) {
        return getFirstBySelector(element, selector)
                .map(Element::text)
                .orElse(StringUtils.EMPTY);
    }

    private Optional<Element> getFirstBySelector(Element element, String selector) {
        return Optional.of(element.select(selector))
                .filter(elements -> !elements.isEmpty())
                .map(Elements::first);
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
        final String url = document.select(".pagination__item [title=\"Начало\"]").get(0).absUrl(HREF);
        if (StringUtils.isEmpty(url)) {
            return Stream.empty();
        }
        final int lastIndex = Optional.of(document.select(".pagination__item [title=\"Конец\"]"))
                .map(el -> el.get(0))
                .map(el -> el.absUrl(HREF))
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
