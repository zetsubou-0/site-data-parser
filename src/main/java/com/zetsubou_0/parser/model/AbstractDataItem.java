package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractDataItem implements DataItem {

    @CsvField
    private final String brand = "Arlight";
    @CsvField
    private final String type;
    @CsvField
    private String category;
    @CsvField
    private final String article;
    @CsvField
    private final String title;
    @CsvField
    private final String description;
    @CsvField
    private final String image;
    private final List<String> images;
    private final String price;

    public AbstractDataItem(String type, String title, String description, String article, List<String> images, String price) {
        this.type = type;
        this.article = article;
        this.title = title;
        this.description = description;
        this.images = images;
        this.image = Optional.ofNullable(images)
                .map(imgs -> imgs.get(0))
                .orElse(null);
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

    public String getDescription() {
        return description;
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

    public List<String> getImages() {
        return images;
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
        return Objects.equals(brand, that.brand) &&
                Objects.equals(type, that.type) &&
                Objects.equals(category, that.category) &&
                Objects.equals(article, that.article) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(image, that.image) &&
                Objects.equals(images, that.images) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, type, category, article, title, description, image, images, price);
    }
}
