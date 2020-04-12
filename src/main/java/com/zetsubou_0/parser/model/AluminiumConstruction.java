package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AluminiumConstruction that = (AluminiumConstruction) o;
        return Objects.equals(function, that.function) &&
                Objects.equals(series, that.series) &&
                Objects.equals(color, that.color) &&
                Objects.equals(mountingMethod, that.mountingMethod) &&
                Objects.equals(areaWidth, that.areaWidth) &&
                Objects.equals(lightsLineWidth, that.lightsLineWidth) &&
                Objects.equals(sectionShape, that.sectionShape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), function, series, color, mountingMethod, areaWidth, lightsLineWidth, sectionShape);
    }
}
