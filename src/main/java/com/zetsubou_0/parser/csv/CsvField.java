package com.zetsubou_0.parser.csv;

import com.zetsubou_0.parser.model.type.CharacteristicsType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that could be applied to the field that will be mapped as a column in the SCV file
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface CsvField {
    /**
     * Field header
     * @return field header
     */
    CharacteristicsType value() default CharacteristicsType.EMPTY;

    /**
     * Is multiple
     * @return true if multiple
     */
    boolean multiple() default false;
}
