package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

public class LedLine extends AbstractCommonDataItem {

    @CsvField
    private String lightColor;
    @CsvField
    private String lightTemperature;
    @CsvField
    private String index;
    @CsvField
    private String angle;
    @CsvField
    private String size;
    @CsvField
    private String density;
    @CsvField
    private String count;
    @CsvField
    private String luminousFlux;
    @CsvField
    private String luminousFluxCommon;
    @CsvField
    private String power;
    @CsvField
    private String powerCommonMax;
    @CsvField
    private String powerCommon;
    @CsvField
    private String voltage;
    @CsvField
    private String current;
    @CsvField
    private String minSize;

    public LedLine(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }
}
