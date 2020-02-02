package com.sbt.javaschool.rnd.lesson18.core;

import java.util.LinkedList;
import java.util.List;

public class Panel implements Container {

    private final String panelName;
    private List<Graphic> childs = new LinkedList<>();

    public Panel(String panelName) {
        this.panelName = panelName;
    }

    @Override
    public void paint() {
        for (Graphic child : childs) {
            child.paint();
        }
    }

    @Override
    public void add(Graphic graphic) {
        childs.add(graphic);
    }

    @Override
    public void remove(int index) {
        childs.remove(index);
    }

    @Override
    public Graphic getChild(int index) {
        return childs.get(index);
    }

    @Override
    public String getName() {
        return panelName;
    }

    @Override
    public Graphic clone() {
        Panel panel = new Panel(panelName);
        for (Graphic child : childs) {
            panel.add(child);
        }

        return panel;
    }
}
