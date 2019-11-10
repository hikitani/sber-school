package com.sbt.javaschool.rnd.lesson3;

import java.util.ArrayList;
import java.util.Iterator;

public class MyList extends ArrayList<String> implements Iterable<String> {

    @Override
    public Iterator<String> iterator() {
        return new MyListIterator(this);
    }
}
