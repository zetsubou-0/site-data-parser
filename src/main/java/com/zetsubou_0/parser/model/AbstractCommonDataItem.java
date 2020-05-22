package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

public abstract class AbstractCommonDataItem extends AbstractDataItem {

    @CsvField
    private String productType;
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

    public AbstractCommonDataItem(String type, String title, String description, String article, String image, String price) {
        super(type, title, description, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractCommonDataItem that = (AbstractCommonDataItem) o;
        return Objects.equals(productType, that.productType) &&
                Objects.equals(length, that.length) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(packing, that.packing) &&
                Objects.equals(packingType, that.packingType) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(guaranteePeriod, that.guaranteePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productType, length, width, height, packing, packingType, weight, guaranteePeriod);
    }
}
