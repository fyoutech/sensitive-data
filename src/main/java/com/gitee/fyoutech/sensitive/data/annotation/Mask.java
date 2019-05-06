package com.gitee.fyoutech.sensitive.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ipanocloud
 * @since 2019-05-06 11:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Mask {
    int prefixNoMaskLen() default 0;

    int suffixNoMaskLen() default 0;

    String maskStr() default "*";
}
