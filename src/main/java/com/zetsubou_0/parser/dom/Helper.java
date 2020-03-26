package com.zetsubou_0.parser.dom;

import com.zetsubou_0.parser.model.type.CharacteristicsType;
import org.jsoup.nodes.Element;

import java.util.Optional;

public interface Helper {

    String HREF = "href";
    String SRC = "src";

    /**
     * Find first element in the provided element by selector
     * @param element {@link Element}
     * @param selector DOM selector
     * @return optional of the element
     */
    Optional<Element> getFirstBySelector(Element element, String selector);

    /**
     * Extract data from data-attr of element on the specification tab
     * @param element {@link Element}
     * @param type {@link CharacteristicsType}
     * @return data from data-attr of element on the specification tab
     */
    String getSpecificationData(Element element, CharacteristicsType type);

    /**
     * Extract text from first occurrence
     * @param element {@link Element}
     * @param selector DOM selector
     * @return find and return text from the element by selector
     */
    String extractText(Element element, String selector);

    /**
     * Extract image from first occurrence
     * @param element {@link Element}
     * @param selector DOM selector
     * @return find and return image from the element by selector
     */
    String extractImage(Element element, String selector);

    /**
     * Extract link from first occurrence
     * @param element {@link Element}
     * @param selector DOM selector
     * @return find and return link from the element by selector
     */
    String extractLink(Element element, String selector);
}
