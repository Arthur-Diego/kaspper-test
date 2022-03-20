package com.kaspper.teste.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldEntity {

    boolean isEmbbedable() default false;

    boolean isEntity() default false;

    boolean isNotString() default false;

    boolean isForeignKey() default false;
}
