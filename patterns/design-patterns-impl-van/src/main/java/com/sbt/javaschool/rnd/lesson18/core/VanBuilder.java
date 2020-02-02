package com.sbt.javaschool.rnd.lesson18.core;

public class VanBuilder implements VehicleBuilder {

    private Vehicle van = new Van();

    @Override
    public VehicleBuilder buildBody() {
        return null;
    }

    @Override
    public VehicleBuilder buildChassis() {
        return null;
    }

    @Override
    public VehicleBuilder buildWindows() {
        return null;
    }

    @Override
    public VehicleBuilder buildClimateControlSystem() {
        return null;
    }

    @Override
    public VehicleBuilder buildSunroof() {
        return null;
    }

    @Override
    public VehicleBuilder buildEngine() {
        van.setEngine(new VanEngine());
        System.out.println("Build Van Engine");
        return this;
    }

    @Override
    public Vehicle build() {
        return van;
    }
}
