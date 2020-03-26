package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DataItemProcessor;
import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.LedLine;
import com.zetsubou_0.parser.model.Type;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import javax.inject.Inject;

public class LedLineProcessorData implements DataItemProcessor {

    @Inject
    private Helper helper;

    @Override
    public DataItem processDomElement(Element element) {
        final LedLine itemData = new LedLine(
                Type.LED_LINE.getType(),
                extractTitle(element),
                extractArticle(element),
                helper.extractImage(element, ".slide picture img"),
                extractPrice(element)
        );
        return setupSpecificationsData(element, itemData);
    }

    private DataItem setupSpecificationsData(Element element, LedLine itemData) {
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
        return helper.extractText(element, ".specifications__table .specifications__table-item[data-property=\"" + property + "\"] .specifications__table-td");
    }

    private String extractTitle(Element element) {
        return helper.extractText(element, ".product__title");
    }

    private String extractArticle(Element element) {
        return helper.extractText(element, ".product__vendor")
                .replaceAll("[\\D\\s]", StringUtils.EMPTY);
    }

    private String extractPrice(Element element) {
        return element.select(".card__price-now .price")
                .attr("content");
    }
}
