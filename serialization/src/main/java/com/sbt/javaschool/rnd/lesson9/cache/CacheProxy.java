package com.sbt.javaschool.rnd.lesson9.cache;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheProxy<T> {

    private final Map<String, List<Map.Entry<Class[], ResultMethod>>> resultsMethod = new HashMap<>();

    public T makeCache(T instance) {
        return (T)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                instance.getClass().getInterfaces(),
                new CacheHandler(instance, this));
    }

    public Map<String, List<Map.Entry<Class[], ResultMethod>>> getResultsMethod() {
        return resultsMethod;
    }
}
