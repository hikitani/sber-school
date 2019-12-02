package com.sbt.javaschool.rnd.lesson10.collection.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public class IteratorWithFilter<T> implements Iterator<T> {
    private Collection<T> collection;
    private Iterator<T> iterator;
    private T current;
    private boolean elemExist;
    private Predicate<T> predicate;

    public IteratorWithFilter(Collection<T> collection, Iterator<T> iterator, Predicate<T> predicate) {
        this.collection = collection;
        this.iterator = iterator;
        this.elemExist = iterator.hasNext();
        this.current = this.elemExist ? iterator.next() : null;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        if (elemExist) {
            if (predicate.test(current)) {
                return true;
            } else {
                elemExist = iterator.hasNext();
                if (elemExist) {
                    current = iterator.next();
                    return this.hasNext();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        T res = current;
        elemExist = iterator.hasNext();
        if (elemExist) {
            current = iterator.next();
        }
        return res;
    }

    @Override
    public void remove() {

    }
}
