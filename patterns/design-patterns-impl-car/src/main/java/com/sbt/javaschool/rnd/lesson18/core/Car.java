package com.sbt.javaschool.rnd.lesson18.core;

public class Car implements Vehicle {
    private CarEngine engine;

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = (CarEngine) engine;
    }
}
