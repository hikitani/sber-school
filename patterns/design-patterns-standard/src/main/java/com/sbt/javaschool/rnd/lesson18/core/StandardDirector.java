package com.sbt.javaschool.rnd.lesson18.core;

public class StandardDirector implements VehicleDirector {
    @Override
    public Vehicle build(VehicleBuilder builder) {
        builder.buildBody()
                .buildChassis()
                .buildWindows()
                .buildEngine();

        return builder.build();
    }
}
