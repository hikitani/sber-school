package com.sbt.javaschool.rnd.lesson18;

import com.sbt.javaschool.rnd.lesson18.core.*;

public class App {
    public static void main(String[] args) {

        try {
            // Abstract Fabric
            VehicleFactory vehicleFactory = (VehicleFactory) Class.forName(args[0]).newInstance();

            System.out.println(vehicleFactory.createBody().getBodyParts());
            System.out.println(vehicleFactory.createChassis().getChassisParts());
            System.out.println(vehicleFactory.createWindows().getWindowsParts());
            System.out.println();

            // Builder
            Vehicle carStandart;
            Vehicle carLux;
            VehicleDirector directorStandard = (VehicleDirector) Class.forName(args[1]).newInstance();
            VehicleDirector directorLux = (VehicleDirector) Class.forName(args[2]).newInstance();
            VehicleBuilder vehicleBuilder = (VehicleBuilder) Class.forName(args[3]).newInstance();

            carStandart = directorStandard.build(vehicleBuilder);
            System.out.println();
            carLux = directorLux.build(vehicleBuilder);
            System.out.println();

            // Adapter
            DriverControl carDriverControl = new CarDriverControl(carStandart);

            carDriverControl.ignitionOn();

            carDriverControl.accelerate();
            carDriverControl.accelerate();
            carDriverControl.accelerate();

            carDriverControl.brake();

            System.out.println();

            // Composite
            Panel panel = new Panel("panel1");
            Label label1 = new Label("label1", "text1");
            Label label2 = new Label("label2", "text2");
            Label label3 = new Label("label3", "text3");

            Panel subPanel1 = new Panel("subPanel1");
            Label sub1Label1 = new Label("sub1label1", "sub1text1");
            Label sub1Label2 = new Label("sub1label2", "sub1text2");
            Label sub1Label3 = new Label("sub1label3", "sub1text3");
            subPanel1.add(sub1Label1);
            subPanel1.add(sub1Label2);
            subPanel1.add(sub1Label3);

            Panel subPanel2 = new Panel("subPanel2");
            Label sub2Label1 = new Label("sub2label1", "sub2text1");
            Label sub2Label2 = new Label("sub2label2", "sub2text2");
            Label sub2Label3 = new Label("sub2label3", "sub2text3");
            subPanel2.add(sub2Label1);
            subPanel2.add(sub2Label2);
            subPanel2.add(sub2Label3);

            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(subPanel1);
            panel.add(subPanel2);

            panel.paint();

            System.out.println();

            // Prototype
            panel.clone().paint();

            System.out.println();

            // Decorator

            Graphic panelScroll = new HorizontalScrollDecorator(new VerticalScrollDecorator(panel));
            panelScroll.paint();

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }

    }

}
