package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

import java.util.Objects;

@DataItemModel
public class PowerBlock extends AbstractCommonDataItem {

    @CsvField
    private String workingTemperature;
    @CsvField
    private String material;
    @CsvField
    private String efficiency;
    @CsvField
    private String connectionType;
    @CsvField
    private String caseType;
    @CsvField
    private String caseForm;
    @CsvField
    private String pwd;
    @CsvField
    private String outputPower;
    @CsvField
    private String outputVoltage;
    @CsvField
    private String inputVoltage;
    @CsvField
    private String outputCurrent;
    @CsvField
    private String inputAlternatingCurrent;
    @CsvField
    private String inrushCurrent;

    public PowerBlock(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PowerBlock that = (PowerBlock) o;
        return Objects.equals(workingTemperature, that.workingTemperature) &&
                Objects.equals(material, that.material) &&
                Objects.equals(efficiency, that.efficiency) &&
                Objects.equals(connectionType, that.connectionType) &&
                Objects.equals(caseType, that.caseType) &&
                Objects.equals(caseForm, that.caseForm) &&
                Objects.equals(pwd, that.pwd) &&
                Objects.equals(outputPower, that.outputPower) &&
                Objects.equals(outputVoltage, that.outputVoltage) &&
                Objects.equals(inputVoltage, that.inputVoltage) &&
                Objects.equals(outputCurrent, that.outputCurrent) &&
                Objects.equals(inputAlternatingCurrent, that.inputAlternatingCurrent) &&
                Objects.equals(inrushCurrent, that.inrushCurrent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workingTemperature, material, efficiency, connectionType, caseType, caseForm, pwd, outputPower, outputVoltage, inputVoltage, outputCurrent, inputAlternatingCurrent, inrushCurrent);
    }
}
