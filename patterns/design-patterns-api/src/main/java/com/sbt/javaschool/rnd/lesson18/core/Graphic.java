package com.sbt.javaschool.rnd.lesson18.core;

public interface Graphic {

    void paint();

    void add(Graphic graphic);

    void remove(int index);

    Graphic getChild(int index);

    String getName();

    Graphic clone();
}
