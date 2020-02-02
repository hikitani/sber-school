package com.sbt.javaschool.rnd.lesson18.core;

public interface VehicleBuilder {

    VehicleBuilder buildBody();

    VehicleBuilder buildChassis();

    VehicleBuilder buildWindows();

    VehicleBuilder buildClimateControlSystem();

    VehicleBuilder buildSunroof();

    VehicleBuilder buildEngine();

    Vehicle build();
}
