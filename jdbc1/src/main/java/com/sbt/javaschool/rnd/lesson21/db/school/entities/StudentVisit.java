package com.sbt.javaschool.rnd.lesson21.db.school.entities;

public class StudentVisit {
    private Student student;
    private Lesson lesson;

    public Student getStudent() {
        return student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
