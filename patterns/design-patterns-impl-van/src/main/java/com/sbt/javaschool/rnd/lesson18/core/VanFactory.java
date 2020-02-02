package com.sbt.javaschool.rnd.lesson18.core;

public class VanFactory implements VehicleFactory {
    @Override
    public Body createBody() {
        return new VanBody();
    }

    @Override
    public Chassis createChassis() {
        return new VanChassis();
    }

    @Override
    public Windows createWindows() {
        return new VanWindows();
    }
}
