package com.sbt.javaschool.rnd.lesson18.core;

public class CarEngine implements Engine {

    @Override
    public void start() {
        System.out.println("Start Car Engine");
    }

    @Override
    public void stop() {
        System.out.println("Stop Car Engine");
    }

    @Override
    public void increasePower() {
        System.out.println("Increase Car Power");
    }

    @Override
    public void decreasePower() {
        System.out.println("Decrease Car Power");
    }
}
