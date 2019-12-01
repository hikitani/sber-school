package com.sbt.javaschool.rnd.lesson9.cache.storage;

import com.sbt.javaschool.rnd.lesson9.cache.ResultMethod;

import java.util.Map;

public interface Storage {
    Map<String, Map.Entry<Class[], ResultMethod>> getResultsMethod();
}
