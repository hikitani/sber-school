package com.sbt.javaschool.rnd.lesson18.core;

public class CarBuilder implements VehicleBuilder{
    private Vehicle car = new Car();

    @Override
    public VehicleBuilder buildBody() {
        System.out.println("Build Car Body");
        return this;
    }

    @Override
    public VehicleBuilder buildChassis() {
        System.out.println("Build Car Chassis");
        return this;
    }

    @Override
    public VehicleBuilder buildWindows() {
        System.out.println("Build Car Windows");
        return this;
    }

    @Override
    public VehicleBuilder buildClimateControlSystem() {
        System.out.println("Build Car Climate Control System");
        return this;
    }

    @Override
    public VehicleBuilder buildSunroof() {
        System.out.println("Build Car Sunroof");
        return this;
    }

    @Override
    public VehicleBuilder buildEngine() {
        car.setEngine(new CarEngine());
        System.out.println("Build Car Engine");
        return this;
    }

    @Override
    public Vehicle build() {
        System.out.println("Build Car");
        return car;
    }
}
