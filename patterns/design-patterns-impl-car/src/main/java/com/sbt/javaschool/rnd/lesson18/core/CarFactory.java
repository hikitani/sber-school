package com.sbt.javaschool.rnd.lesson18.core;

public class CarFactory implements VehicleFactory {
    @Override
    public Body createBody() {
        return new CarBody();
    }

    @Override
    public Chassis createChassis() {
        return new CarChassis();
    }

    @Override
    public Windows createWindows() {
        return new CarWindows();
    }
}
