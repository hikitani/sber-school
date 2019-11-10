package com.sbt.javaschool.rnd.lesson3;

import java.util.Iterator;
import java.util.List;

public class MyListIterator implements Iterator<String> {

    private MyList list;
    private int index;

    public MyListIterator(MyList list) {
        this.list = list;
        index = list.size();
    }

    @Override
    public boolean hasNext() {
        return index > 0;
    }

    @Override
    public String next() {
        return list.get(--index);
    }
}
