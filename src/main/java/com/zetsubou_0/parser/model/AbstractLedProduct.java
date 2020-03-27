package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

public abstract class AbstractLedProduct extends AbstractCommonDataItem {

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
    private String voltage;

    public AbstractLedProduct(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractLedProduct that = (AbstractLedProduct) o;
        return Objects.equals(lightColor, that.lightColor) &&
                Objects.equals(lightTemperature, that.lightTemperature) &&
                Objects.equals(index, that.index) &&
                Objects.equals(angle, that.angle) &&
                Objects.equals(size, that.size) &&
                Objects.equals(voltage, that.voltage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lightColor, lightTemperature, index, angle, size, voltage);
    }
}
