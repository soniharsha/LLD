package AnnotationOperation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface StringAnalyzer {

    public Analyze[] value() default {Analyze.NULL};
    enum Analyze {
        NULL,
        EMPTY,
        CAPITAL_CASE,
        SMALL_CASE;
    }
}
