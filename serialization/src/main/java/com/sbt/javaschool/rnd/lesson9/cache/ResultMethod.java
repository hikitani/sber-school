package com.sbt.javaschool.rnd.lesson9.cache;

import java.io.Serializable;

public class ResultMethod implements Serializable {
    private transient Object value;
    private transient Class type;

    public ResultMethod(Object value, Class type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public Class getType() {
        return type;
    }
}
