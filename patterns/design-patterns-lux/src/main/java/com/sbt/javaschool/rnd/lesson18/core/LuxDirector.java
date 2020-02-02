package com.sbt.javaschool.rnd.lesson18.core;

public class LuxDirector implements VehicleDirector {
    @Override
    public Vehicle build(VehicleBuilder builder) {
        builder.buildWindows()
                .buildChassis()
                .buildBody()
                .buildSunroof()
                .buildClimateControlSystem()
                .buildEngine();

        return builder.build();
    }
}
