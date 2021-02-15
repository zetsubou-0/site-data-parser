package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.List;
import java.util.Objects;

public abstract class AbstractLedProduct extends AbstractCommonDataItem {

    @CsvField
    private String lightColor;
    @CsvField
    private String lightTemperature;
    @CsvField
    private String ip;
    @CsvField
    private String index;
    @CsvField
    private String angle;

    public AbstractLedProduct(String type, String title, String description, String article, List<String> images, String price) {
        super(type, title, description, article, images, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractLedProduct that = (AbstractLedProduct) o;
        return Objects.equals(lightColor, that.lightColor) &&
                Objects.equals(lightTemperature, that.lightTemperature) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(index, that.index) &&
                Objects.equals(angle, that.angle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lightColor, lightTemperature, ip, index, angle);
    }
}
