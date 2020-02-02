package com.sbt.javaschool.rnd.lesson18.core;

public class VanEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Van Start");
    }

    @Override
    public void stop() {
        System.out.println("Van Stop");
    }

    @Override
    public void increasePower() {
        System.out.println("Van Increase Power");
    }

    @Override
    public void decreasePower() {
        System.out.println("Van Decrease Power");
    }
}
