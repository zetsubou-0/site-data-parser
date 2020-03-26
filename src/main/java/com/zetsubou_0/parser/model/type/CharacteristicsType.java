package com.zetsubou_0.parser.model.type;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

public enum CharacteristicsType {
    EMPTY,
    TYPE("Тип"),
    ARTICLE("Артикул", ".product__vendor"),
    TITLE("Заголовок", ".product__title"),
    IMAGE("Изображение", ".slide picture img"),
    PRICE("Цена", ".card__price-now .price"),
    PRODUCT_TYPE("Тип товара", "T_62"),
    LIGHT_COLOR("Цвет свечения", "COLOR_HREF"),
    LIGHT_TEMPERATURE("Цветовая температура", "T_25"),
    WORKING_TEMPERATURE("Рабочая температура", "T_87"),
    INDEX("Индекс цветопередачи", "T_63"),
    ANGLE("Угол обзора", "T_5"),
    MATERIAL("Материал", "T_6"),
    SIZE("Размер светодиода", "T_45"),
    DENSITY("Плотность светодиодов", "T_46"),
    COUNT("Кол-во светодиодов", "T_23"),
    LUMINOUS_FLUX("Световой поток 1м", "T_94"),
    LUMINOUS_FLUX_COMMON("Световой поток (общий)", "T_8"),
    EFFICIENCY("Коэффициент мощности", "T_86"),
    CONNECTION_TYPE("Тип подключения", "T_88"),
    CASE_TYPE("Обозначение корпуса", "CASE"),
    CASE_FORM("Форма корпуса", "T_91"),
    PWD("Использование с управлением ШИМ", "T_133"),
    POWER("Мощность 1м", "T_95"),
    POWER_COMMON_MAX("Мощность (общая), макс.", "T_35"),
    POWER_COMMON("Мощность (общая)", "T_75"),
    OUTPUT_POWER("Выходная мощность", "T_13"),
    VOLTAGE("Напряжение питания", "T_20"),
    OUTPUT_VOLTAGE("Выходное напряжение", "T_38"),
    INPUT_VOLTAGE("Входное напряжение", "T_37"),
    CURRENT("Ток (общий)", "T_3"),
    OUTPUT_CURRENT("Выходной ток", "T_43"),
    INPUT_ALTERNATING_CURRENT("Входной переменный ток", "T_76"),
    INRUSH_CURRENT("Пусковой ток", "T_85"),
    IP("Класс пылевлагозащиты", "T_44"),
    MIN_SIZE("Минимальный отрезок", "T_33"),
    LENGTH("Длина, мм", "MY_6"),
    WIDTH("Ширина, мм", "MY_7"),
    HEIGHT("Высота,мм", "MY_8"),
    PACKING("Норма упаковки", "PACKNORM"),
    PACKING_TYPE("Вид упаковки", "PACKAGE_NAME"),
    WEIGHT("Вес", "WEIGHT"),
    GUARANTEE_PERIOD("Гарантийный срок", "T_89");

    private final String header;
    private String selector;

    CharacteristicsType(String header, String selector) {
        this.header = header;
        this.selector = selector;
    }

    CharacteristicsType(String header) {
        this(header, StringUtils.EMPTY);
    }

    CharacteristicsType() {
        this(StringUtils.EMPTY);
    }

    public static CharacteristicsType of(String name) {
        final String upperName = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE).convert(name);
        try {
            return valueOf(upperName);
        } catch (IllegalArgumentException e) {
            return EMPTY;
        }
    }

    public String getHeader() {
        return header;
    }

    public String getSelector() {
        return selector;
    }
}
