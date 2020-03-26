package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

public abstract class AbstractCommonDataItem extends AbstractDataItem {

    @CsvField
    private String productType;
    @CsvField
    private String ip;
    @CsvField
    private String length;
    @CsvField
    private String width;
    @CsvField
    private String height;
    @CsvField
    private String packing;
    @CsvField
    private String packingType;
    @CsvField
    private String weight;
    @CsvField
    private String guaranteePeriod;

    public AbstractCommonDataItem(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    public String getProductType() {
        return productType;
    }

    public AbstractCommonDataItem setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AbstractCommonDataItem setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getLength() {
        return length;
    }

    public AbstractCommonDataItem setLength(String length) {
        this.length = length;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public AbstractCommonDataItem setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public AbstractCommonDataItem setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getPacking() {
        return packing;
    }

    public AbstractCommonDataItem setPacking(String packing) {
        this.packing = packing;
        return this;
    }

    public String getPackingType() {
        return packingType;
    }

    public AbstractCommonDataItem setPackingType(String packingType) {
        this.packingType = packingType;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public AbstractCommonDataItem setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public AbstractCommonDataItem setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
        return this;
    }
}
