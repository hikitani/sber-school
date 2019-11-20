package com.sbt.javaschool.rnd.lesson6;

import java.lang.reflect.Proxy;

public class ProxyUtils {
    public static <T> T makeCached(T instance) {
        Class instanceClass = instance.getClass();
        return (T) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                instanceClass.getInterfaces(),
                new CalculateHandler(instance));
    }
}
