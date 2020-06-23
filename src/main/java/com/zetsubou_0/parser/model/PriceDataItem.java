package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;

public class PriceDataItem implements DataItem {

    @CsvField
    private final String article;
    @CsvField
    private final String price;
    @CsvField
    private final String brand = "Arlight";

    public PriceDataItem(String article, String price) {
        this.article = article;
        this.price = correctPrice(price);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getArticleNumber() {
        return article;
    }

    @Override
    public String getImageSource() {
        return null;
    }

    @Override
    public String getPrice() {
        return price;
    }

    private String correctPrice(String price) {
        return String.format("%.2f", Math.round(NumberUtils.createDouble(price) * 10) / 10.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDataItem that = (PriceDataItem) o;
        return Objects.equals(article, that.article) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, price, brand);
    }
}
