package com.zetsubou_0.parser.dom.impl;

import com.zetsubou_0.parser.dom.DataItemProcessor;
import com.zetsubou_0.parser.dom.Helper;
import com.zetsubou_0.parser.model.DataItem;
import com.zetsubou_0.parser.model.type.CharacteristicsType;
import com.zetsubou_0.parser.model.type.PageType;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractDataItemProcessor<T extends DataItem> implements DataItemProcessor {

    private final Helper helper;
    private final PageType pageType;
    private final Class<T> itemClass;

    public AbstractDataItemProcessor(Helper helper, PageType pageType, Class<T> itemClass) {
        this.helper = helper;
        this.pageType = pageType;
        this.itemClass = itemClass;
    }

    @Override
    public DataItem processDomElement(Element element) {
        try {
            final T item = itemClass.getConstructor(String.class, String.class, String.class, String.class, String.class)
                    .newInstance(
                            pageType.getType(),
                            helper.extractText(element, CharacteristicsType.TITLE.getSelector()),
                            helper.extractText(element, CharacteristicsType.ARTICLE.getSelector()).replaceAll("[\\D\\s]", StringUtils.EMPTY),
                            helper.extractImage(element, CharacteristicsType.IMAGE.getSelector()),
                            element.select(CharacteristicsType.PRICE.getSelector()).attr(CONTENT)
                    );
            return setupSpecificationsData(element, item);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    private DataItem setupSpecificationsData(Element element, T itemData) {
        getFields(itemClass)
                .forEach(setupValue(element, itemData));
        return itemData;
    }

    private Stream<Field> getFields(Class<?> cl) {
        final Field[] fields = cl.getDeclaredFields();
        final Class<?> parentClass = cl.getSuperclass();
        return parentClass == null
                ? Arrays.stream(fields)
                : Stream.concat(
                        Arrays.stream(fields),
                        getFields(parentClass)
                );
    }

    private Consumer<Field> setupValue(Element element, T itemData) {
        return field -> {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                final CharacteristicsType type = CharacteristicsType.of(field.getName());
                if (type == CharacteristicsType.EMPTY) {
                    return;
                }
                field.set(itemData, helper.getSpecificationData(element, type));
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.err);
            } finally {
                field.setAccessible(accessible);
            }
        };
    }
}
