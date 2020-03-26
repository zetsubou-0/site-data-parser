package com.zetsubou_0.parser.model;

/**
 * Data item representation
 */
public interface DataItem {

    /**
     * @return type
     */
    String getType();

    /**
     * @return title
     */
    String getTitle();

    /**
     * @return article number
     */
    String getArticleNumber();

    /**
     * @return image
     */
    String getImageSource();

    /**
     * @return price
     */
    String getPrice();
}
