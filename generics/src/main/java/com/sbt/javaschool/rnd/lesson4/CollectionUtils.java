package com.sbt.javaschool.rnd.lesson4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        List<T> list = new LinkedList<>();
        if (size > source.size()) {
            list.addAll(source);
        } else {
            for (int i = 0; i < size; i++) {
                list.add(source.get(i));
            }
        }
        return list;
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }
}