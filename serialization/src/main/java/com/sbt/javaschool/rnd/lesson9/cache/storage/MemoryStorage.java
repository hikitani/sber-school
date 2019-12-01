package com.sbt.javaschool.rnd.lesson9.cache.storage;

import com.sbt.javaschool.rnd.lesson9.cache.ResultMethod;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage implements Storage {
    private final Map<String, Map.Entry<Class[], ResultMethod>> resultsMethod = new HashMap<>();

    @Override
    public Map<String, Map.Entry<Class[], ResultMethod>> getResultsMethod() {
        return resultsMethod;
    }
}
