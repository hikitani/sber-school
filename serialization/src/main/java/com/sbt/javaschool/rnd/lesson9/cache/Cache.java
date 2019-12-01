package com.sbt.javaschool.rnd.lesson9.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    StorageType storage() default StorageType.JVM_MEMORY;
    int listLimit() default -1;
    Class[] signature() default {};
    String fileName() default "";
    String key() default "";
}
