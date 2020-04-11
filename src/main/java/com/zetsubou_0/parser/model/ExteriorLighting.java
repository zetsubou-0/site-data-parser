package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

@DataItemModel
public class ExteriorLighting extends AbstractLedProduct {

    @CsvField
    private String series;
    @CsvField
    private String luminousFluxCommon;
    @CsvField
    private String powerCommonMax;
    @CsvField
    private String color;
    @CsvField
    private String mountingMethod;

    public ExteriorLighting(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExteriorLighting that = (ExteriorLighting) o;
        return Objects.equals(series, that.series) &&
                Objects.equals(luminousFluxCommon, that.luminousFluxCommon) &&
                Objects.equals(powerCommonMax, that.powerCommonMax) &&
                Objects.equals(color, that.color) &&
                Objects.equals(mountingMethod, that.mountingMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), series, luminousFluxCommon, powerCommonMax, color, mountingMethod);
    }
}
