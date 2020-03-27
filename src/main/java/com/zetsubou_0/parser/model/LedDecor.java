package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

@DataItemModel
public class LedDecor extends AbstractLedProduct {

    @CsvField
    private String luminousFlux;
    @CsvField
    private String density;
    @CsvField
    private String minSize;
    @CsvField
    private String power;

    public LedDecor(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LedDecor ledDecor = (LedDecor) o;
        return Objects.equals(luminousFlux, ledDecor.luminousFlux) &&
                Objects.equals(density, ledDecor.density) &&
                Objects.equals(minSize, ledDecor.minSize) &&
                Objects.equals(power, ledDecor.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), luminousFlux, density, minSize, power);
    }
}
