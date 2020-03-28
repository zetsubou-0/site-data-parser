package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

@DataItemModel
public class LedLights extends AbstractLedProduct {

    @CsvField
    private String series;
    @CsvField
    private String luminousFluxCommon;
    @CsvField
    private String powerCommonMax;
    @CsvField
    private String color;
    @CsvField
    private String sectionShape;
    @CsvField
    private String mountingMethod;
    @CsvField
    private String caseType;

    public LedLights(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LedLights ledLights = (LedLights) o;
        return Objects.equals(series, ledLights.series) &&
                Objects.equals(luminousFluxCommon, ledLights.luminousFluxCommon) &&
                Objects.equals(powerCommonMax, ledLights.powerCommonMax) &&
                Objects.equals(color, ledLights.color) &&
                Objects.equals(sectionShape, ledLights.sectionShape) &&
                Objects.equals(mountingMethod, ledLights.mountingMethod) &&
                Objects.equals(caseType, ledLights.caseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), series, luminousFluxCommon, powerCommonMax, color, sectionShape, mountingMethod, caseType);
    }
}
