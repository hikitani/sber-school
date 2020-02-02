package com.sbt.javaschool.rnd.lesson21.db.school.connection;

import com.sbt.javaschool.rnd.lesson21.db.school.controller.LessonSet;
import com.sbt.javaschool.rnd.lesson21.db.school.controller.StudentSet;
import com.sbt.javaschool.rnd.lesson21.db.school.controller.StudentVisitSet;

public class SchoolDB {

    private LessonSet lessons;
    private StudentSet students;
    private StudentVisitSet studentVisits;

    public SchoolDB() {
        this.lessons = new LessonSet();
        this.students = new StudentSet();
        this.studentVisits = new StudentVisitSet();
    }


    public LessonSet getLessons() {
        return lessons;
    }

    public StudentSet getStudents() {
        return students;
    }

    public StudentVisitSet getStudentVisits() {
        return studentVisits;
    }
}
