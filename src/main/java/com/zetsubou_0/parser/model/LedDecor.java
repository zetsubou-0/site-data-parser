package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.List;
import java.util.Objects;

@DataItemModel
public class LedDecor extends AbstractLedProduct {

    @CsvField
    private String size;
    @CsvField
    private String luminousFlux;
    @CsvField
    private String density;
    @CsvField
    private String minSize;
    @CsvField
    private String voltage;
    @CsvField
    private String power;

    public LedDecor(String type, String title, String description, String article, List<String> images, String price) {
        super(type, title, description, article, images, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LedDecor ledDecor = (LedDecor) o;
        return Objects.equals(size, ledDecor.size) &&
                Objects.equals(luminousFlux, ledDecor.luminousFlux) &&
                Objects.equals(density, ledDecor.density) &&
                Objects.equals(minSize, ledDecor.minSize) &&
                Objects.equals(voltage, ledDecor.voltage) &&
                Objects.equals(power, ledDecor.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, luminousFlux, density, minSize, voltage, power);
    }
}
