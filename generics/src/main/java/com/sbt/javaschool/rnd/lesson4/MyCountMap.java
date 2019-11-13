package com.sbt.javaschool.rnd.lesson4;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MyCountMap<T> implements CountMap<T> {

    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {

    }

    @Override
    public int getCount(T o) {
        return 0;
    }

    @Override
    public int remove(T o) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void addAll(CountMap source) {

    }

    @Override
    public Map toMap() {
        return null;
    }

    @Override
    public void toMap(Map destination) {

    }
}
