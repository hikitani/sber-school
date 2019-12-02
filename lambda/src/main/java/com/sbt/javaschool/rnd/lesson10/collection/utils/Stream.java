package com.sbt.javaschool.rnd.lesson10.collection.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Stream<T> {

    private Collection<T> collection;
    private Iterator<T> iterator;

    private Stream(Collection<T> collection) {
        this.collection = collection;
        this.iterator = collection.iterator();
    }

    public static <T> Stream<T> of(Collection<T> collection) {
        return new Stream<>(collection);
    }

    public Stream<T> filter(Predicate<T> predicate) {
        iterator = new IteratorWithFilter<>(collection, iterator, predicate);
        return this;
    }

    public List<T> toList() {
        List<T> list = new LinkedList<>();
        this.forEach(list::add);
        return list;
    }

    public void forEach(Consumer<T> action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }
}
