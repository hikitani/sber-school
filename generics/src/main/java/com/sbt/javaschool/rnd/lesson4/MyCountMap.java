package com.sbt.javaschool.rnd.lesson4;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MyCountMap<T> implements CountMap<T> {

    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        map.put(o, map.containsKey(o) ? map.get(o) + 1 : 1);
    }

    @Override
    public int getCount(T o) {
        return map.get(o);
    }

    @Override
    public int remove(T o) {
        int count = map.get(o);
        map.remove(o);
        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        source.toMap().forEach((key, value) -> {
            map.put(key, map.containsKey(key) ? map.get(key) + value : 1);
        });
    }

    @Override
    public Map toMap() {
        return map;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }
}
