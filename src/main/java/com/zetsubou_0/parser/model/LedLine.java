package com.zetsubou_0.parser.model;

import com.zetsubou_0.parser.csv.CsvField;

public class LedLine extends AbstractDataItem {

    @CsvField("Тип товара")
    private String productType;
    @CsvField("Цвет свечения")
    private String lightColor;
    @CsvField("Цветовая температура")
    private String lightTemperature;
    @CsvField("Индекс цветопередачи")
    private String index;
    @CsvField("Угол обзора")
    private String angle;
    @CsvField("Размер светодиода")
    private String size;
    @CsvField("Плотность светодиодов")
    private String density;
    @CsvField("Кол-во светодиодов")
    private String count;
    @CsvField("Световой поток 1м")
    private String luminousFlux;
    @CsvField("Световой поток (общий)")
    private String luminousFluxCommon;
    @CsvField("Мощность 1м")
    private String power;
    @CsvField("Мощность (общая), макс.")
    private String powerCommonMax;
    @CsvField("Мощность (общая)")
    private String powerCommon;
    @CsvField("Напряжение питания")
    private String voltage;
    @CsvField("Ток (общий)")
    private String current;
    @CsvField("Класс пылевлагозащиты")
    private String ip;
    @CsvField("Минимальный отрезок")
    private String minSize;
    @CsvField("Длина, мм")
    private String length;
    @CsvField("Ширина, мм")
    private String width;
    @CsvField("Высота,мм")
    private String height;
    @CsvField("Норма упаковки")
    private String packing;
    @CsvField("Вид упаковки")
    private String packingType;
    @CsvField("Вес")
    private String weight;
    @CsvField("Гарантийный срок")
    private String guaranteePeriod;

    public LedLine(String type, String title, String article, String image, String price) {
        super(type, title, article, image, price);
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getLightColor() {
        return lightColor;
    }

    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
    }

    public String getLightTemperature() {
        return lightTemperature;
    }

    public void setLightTemperature(String lightTemperature) {
        this.lightTemperature = lightTemperature;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLuminousFlux() {
        return luminousFlux;
    }

    public void setLuminousFlux(String luminousFlux) {
        this.luminousFlux = luminousFlux;
    }

    public String getLuminousFluxCommon() {
        return luminousFluxCommon;
    }

    public void setLuminousFluxCommon(String luminousFluxCommon) {
        this.luminousFluxCommon = luminousFluxCommon;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPowerCommonMax() {
        return powerCommonMax;
    }

    public void setPowerCommonMax(String powerCommonMax) {
        this.powerCommonMax = powerCommonMax;
    }

    public String getPowerCommon() {
        return powerCommon;
    }

    public void setPowerCommon(String powerCommon) {
        this.powerCommon = powerCommon;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMinSize() {
        return minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }
}
