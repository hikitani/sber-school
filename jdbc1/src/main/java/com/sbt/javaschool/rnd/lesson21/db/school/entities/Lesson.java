package com.sbt.javaschool.rnd.lesson21.db.school.entities;

import java.time.LocalDate;

public class Lesson {
    private int id;
    private String name;
    private LocalDate date;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
