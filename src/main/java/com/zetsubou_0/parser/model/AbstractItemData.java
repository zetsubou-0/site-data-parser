package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

public class AbstractItemData implements ItemData {

    @CsvField("Тип")
    private final String type;
    @CsvField("Артикул")
    private final String article;
    @CsvField("Заголовок")
    private final String title;
    @CsvField("Изображение")
    private final String image;
    @CsvField("Цена")
    private final String price;

    public AbstractItemData(String type, String title, String article, String image, String price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItemData that = (AbstractItemData) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(article, that.article) &&
                Objects.equals(image, that.image) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, article, image, price);
    }
}
