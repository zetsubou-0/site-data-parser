package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

@DataItemModel
public class LedLine extends AbstractLedProduct {

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
    private String voltage;
    @CsvField
    private String power;
    @CsvField
    private String powerCommonMax;
    @CsvField
    private String powerCommon;
    @CsvField
    private String current;
    @CsvField
    private String minSize;

    public LedLine(String type, String title, String description, String article, String image, String price) {
        super(type, title, description, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LedLine ledLine = (LedLine) o;
        return Objects.equals(size, ledLine.size) &&
                Objects.equals(density, ledLine.density) &&
                Objects.equals(count, ledLine.count) &&
                Objects.equals(luminousFlux, ledLine.luminousFlux) &&
                Objects.equals(luminousFluxCommon, ledLine.luminousFluxCommon) &&
                Objects.equals(voltage, ledLine.voltage) &&
                Objects.equals(power, ledLine.power) &&
                Objects.equals(powerCommonMax, ledLine.powerCommonMax) &&
                Objects.equals(powerCommon, ledLine.powerCommon) &&
                Objects.equals(current, ledLine.current) &&
                Objects.equals(minSize, ledLine.minSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, density, count, luminousFlux, luminousFluxCommon, voltage, power, powerCommonMax, powerCommon, current, minSize);
    }
}
