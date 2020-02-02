package com.sbt.javaschool.rnd.lesson21.db.controller;

import java.util.List;

public interface DBController<E, K> {

    void create(E entity);
    E read(K id);
    List<E> readAll();
    void update(E entity);
    void delete(K id);

}
