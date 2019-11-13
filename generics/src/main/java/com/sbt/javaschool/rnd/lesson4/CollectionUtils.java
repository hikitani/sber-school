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
}