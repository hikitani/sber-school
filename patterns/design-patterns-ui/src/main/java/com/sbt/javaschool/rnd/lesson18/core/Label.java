package com.sbt.javaschool.rnd.lesson18.core;

public class Label implements Graphic {

    private String text;
    private String labelName;

    public Label(String labelName, String text) {
        this.labelName = labelName;
        this.text = text;
    }

    @Override
    public void paint() {
        System.out.println(text);
    }

    @Override
    public void add(Graphic graphic) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Graphic getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return labelName;
    }

    @Override
    public Graphic clone() {
        Label label = new Label(labelName, text);
        return label;
    }
}
