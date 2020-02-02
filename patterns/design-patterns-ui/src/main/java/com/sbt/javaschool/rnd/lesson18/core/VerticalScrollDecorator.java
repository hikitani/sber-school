package com.sbt.javaschool.rnd.lesson18.core;

public class VerticalScrollDecorator implements Graphic {

    private final Graphic decorateGraphic;

    public VerticalScrollDecorator(Graphic decorateGraphic) {
        this.decorateGraphic = decorateGraphic;
    }

    @Override
    public void paint() {
        System.out.printf("%s With Vertical Scroll\n", decorateGraphic.getName());
        this.decorateGraphic.paint();
    }

    @Override
    public void add(Graphic graphic) {
        this.decorateGraphic.add(graphic);
    }

    @Override
    public void remove(int index) {
        this.decorateGraphic.remove(index);
    }

    @Override
    public Graphic getChild(int index) {
        return decorateGraphic.getChild(index);
    }

    @Override
    public String getName() {
        return decorateGraphic.getName();
    }

    @Override
    public Graphic clone() {
        return null;
    }
}
