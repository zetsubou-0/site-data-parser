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

import static com.zetsubou_0.parser.model.type.CharacteristicsType.*;

public abstract class AbstractDataItemProcessor<T extends DataItem> implements DataItemProcessor {

    protected abstract PageType getType();
    protected abstract Class<T> getItemClass();
    protected abstract Helper getHelper();

    @Override
    public DataItem processDomElement(Element element) {
        try {
            final T item = getItemClass()
                    .getConstructor(String.class, String.class, String.class, String.class, String.class)
                    .newInstance(
                            getType().getType(),
                            getHelper().extractText(element, TITLE.getSelector()),
                            getHelper().extractText(element, ARTICLE.getSelector()).replaceAll("[\\D\\s]", StringUtils.EMPTY),
                            getHelper().extractImage(element, IMAGE.getSelector()),
                            element.select(PRICE.getSelector()).attr(CONTENT)
                    );
            return setupSpecificationsData(element, item);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    private DataItem setupSpecificationsData(Element element, T itemData) {
        Arrays.stream(getClass().getDeclaredFields())
                .forEach(setupValue(element, itemData));
        return itemData;
    }

    private Consumer<Field> setupValue(Element element, T itemData) {
        return field -> {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                final CharacteristicsType type = CharacteristicsType.of(field.getName());
                if (type == EMPTY) {
                    return;
                }
                field.set(itemData, getHelper().getSpecificationData(element, type));
            } catch (IllegalAccessException e) {
                e.printStackTrace(System.err);
            } finally {
                field.setAccessible(accessible);
            }
        };
    }
}
