package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

public abstract class AbstractDataItem implements DataItem {

    @CsvField
    private final String type;
    @CsvField
    private String category;
    @CsvField
    private final String article;
    @CsvField
    private final String title;
    @CsvField
    private final String image;
    private final String price;

    public AbstractDataItem(String type, String title, String article, String image, String price) {
        this.type = type;
        this.title = title;
        this.article = article;
        this.image = image;
        this.price = price;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getArticleNumber() {
        return article;
    }

    @Override
    public String getImageSource() {
        return image;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public AbstractDataItem setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDataItem that = (AbstractDataItem) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(category, that.category) &&
                Objects.equals(article, that.article) &&
                Objects.equals(title, that.title) &&
                Objects.equals(image, that.image) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, category, article, title, image, price);
    }
}
