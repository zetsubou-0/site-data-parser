package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DataItemProcessor;
import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.dom.ReflectionService;
import com.zetsubou_0.parser.model.AbstractDataItem;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.DataItemModel;
import com.zetsubou_0.parser.model.type.CharacteristicsType;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractDataItemProcessor<T extends DataItem> implements DataItemProcessor {

    private final Helper helper;
    private final ReflectionService reflectionService;
    private final PageType pageType;
    private final Class<T> itemClass;

    public AbstractDataItemProcessor(Helper helper, ReflectionService reflectionService,
                                     PageType pageType, Class<T> itemClass) {
        this.helper = helper;
        this.reflectionService = reflectionService;
        this.pageType = pageType;
        this.itemClass = itemClass;
    }

    @Override
    public DataItem processDomElement(Element element) {
        if (!itemClass.isAnnotationPresent(DataItemModel.class)) {
            return null;
        }
        try {
            final T item = reflectionService.construct(
                    itemClass,
                    Pair.of(String.class, pageType.getType()),
                    Pair.of(String.class, helper.extractText(element, CharacteristicsType.TITLE.getSelector())),
                    Pair.of(String.class, helper.extractText(element, CharacteristicsType.DESCRIPTION.getSelector())),
                    Pair.of(String.class, helper.extractText(element, CharacteristicsType.ARTICLE.getSelector()).replaceAll(".*?\\s*:\\s*(.*?)\\s*", "$1")),
                    Pair.of(List.class, helper.extractImages(element, CharacteristicsType.IMAGE.getSelector())),
                    Pair.of(String.class, element.select(CharacteristicsType.PRICE.getSelector()).attr(CONTENT))
            );
            return setupSpecificationsData(element, item);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DataItem setupSpecificationsData(Element element, T itemData) {
        reflectionService.getAllFields(itemClass, AbstractDataItem.class)
                .forEach(setupValue(element, itemData));
        return itemData;
    }

    private Consumer<Field> setupValue(Element element, T itemData) {
        return field -> {
            final CharacteristicsType type = CharacteristicsType.of(field.getName());
            if (type != CharacteristicsType.EMPTY) {
                reflectionService.setupFieldValue(field, itemData, helper.getSpecificationData(element, type));
            }
        };
    }
}
