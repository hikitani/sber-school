package com.sbt.javaschool.rnd.lesson18.core;

public interface VehicleFactory {

    Body createBody();

    Chassis createChassis();

    Windows createWindows();
}
