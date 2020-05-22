package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

@DataItemModel
public class Control extends AbstractCommonDataItem {

    @CsvField
    private String channel;
    @CsvField
    private String zone;
    @CsvField
    private String controlConnectionType;
    @CsvField
    private String controlInteractionType;
    @CsvField
    private String frequency;
    @CsvField
    private String voltage;
    @CsvField
    private String color;
    @CsvField
    private String sectionShape;
    @CsvField
    private String sizeThreeD;

    public Control(String type, String title, String description, String article, String image, String price) {
        super(type, title, description, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Control control = (Control) o;
        return Objects.equals(channel, control.channel) &&
                Objects.equals(zone, control.zone) &&
                Objects.equals(controlConnectionType, control.controlConnectionType) &&
                Objects.equals(controlInteractionType, control.controlInteractionType) &&
                Objects.equals(frequency, control.frequency) &&
                Objects.equals(voltage, control.voltage) &&
                Objects.equals(color, control.color) &&
                Objects.equals(sectionShape, control.sectionShape) &&
                Objects.equals(sizeThreeD, control.sizeThreeD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), channel, zone, controlConnectionType, controlInteractionType, frequency, voltage, color, sectionShape, sizeThreeD);
    }
}
