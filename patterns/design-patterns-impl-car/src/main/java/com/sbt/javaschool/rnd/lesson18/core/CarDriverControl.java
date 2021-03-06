package com.sbt.javaschool.rnd.lesson18.core;

public class CarDriverControl implements DriverControl {

    private Vehicle vehicle;

    public CarDriverControl(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void ignitionOn() {
        vehicle.getEngine().start();
    }

    @Override
    public void ignitionOff() {
        vehicle.getEngine().stop();
    }

    @Override
    public void accelerate() {
        vehicle.getEngine().increasePower();
    }

    @Override
    public void brake() {
        vehicle.getEngine().decreasePower();
        vehicle.getEngine().stop();
    }
}
