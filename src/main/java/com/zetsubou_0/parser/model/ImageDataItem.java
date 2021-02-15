package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;
import com.zetsubou_0.parser.model.type.CharacteristicsType;

import java.util.List;

public class ImageDataItem implements DataItem {

    @CsvField
    private final String brand = "Arlight";
    @CsvField
    private final String article;
    @CsvField(value = CharacteristicsType.IMAGE, multiple = true)
    private final List<String> images;

    public ImageDataItem(String article, List<String> images) {
        this.article = article;
        this.images = images;
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
        return null;
    }
}
