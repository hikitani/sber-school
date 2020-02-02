package com.sbt.javaschool.rnd.lesson18.core;

public class Van implements Vehicle {
    private VanEngine engine;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = (VanEngine) engine;
    }
}
