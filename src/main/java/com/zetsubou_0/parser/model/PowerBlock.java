package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

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
}
