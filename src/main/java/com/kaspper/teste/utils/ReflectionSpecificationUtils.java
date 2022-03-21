package com.kaspper.teste.utils;


import com.kaspper.teste.annotation.FieldEntity;

import java.lang.reflect.Field;

public abstract class ReflectionSpecificationUtils {

    public static String likeAll(String param){
        return "%"+param+"%";
    }

    public static boolean verifyIsNotString(Field f) {
        return f.isAnnotationPresent(FieldEntity.class) && f.getAnnotation(FieldEntity.class).isNotString();
    }

    public static boolean verifyIsForeignKey(Field f) {
        return f.isAnnotationPresent(FieldEntity.class) && (f.getAnnotation(FieldEntity.class).isForeignKey());
    }

    public static Object getFieldValue(Object dto, Field f) {
        try {
            f.setAccessible(true);
            return f.get(dto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyIfFieldIsNull(Object dto, Field f) {
        try {
            f.setAccessible(true);
            return f.get(dto) != null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
