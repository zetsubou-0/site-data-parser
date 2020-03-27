package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

@DataItemModel
public class AluminiumConstruction extends AbstractCommonDataItem {

    @CsvField
    private String function;
    @CsvField
    private String series;
    @CsvField
    private String color;
    @CsvField
    private String mountingMethod;
    @CsvField
    private String areaWidth;
    @CsvField
    private String lightsLineWidth;
    @CsvField
    private String sectionShape;

    public AluminiumConstruction(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }
}
