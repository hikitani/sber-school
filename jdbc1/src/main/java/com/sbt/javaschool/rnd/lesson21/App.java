package com.sbt.javaschool.rnd.lesson21;

import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolDB;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Lesson;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Student;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.StudentVisit;

import java.time.LocalDate;

public class App {
    private static final SchoolDB schoolDB = new SchoolDB();

    public static void main(String[] args) {
        Student student = new Student();
        student.setFirstName("Ivan");
        student.setLastName("Ivanov");

        Lesson lesson = new Lesson();
        lesson.setName("Math");
        lesson.setDate(LocalDate.of(2020, 02, 10));

//        schoolDB.getStudents().create(student);
//        schoolDB.getLessons().create(lesson);

        student = schoolDB.getStudents().readAll().get(0);
        lesson = schoolDB.getLessons().readAll().get(0);

        StudentVisit studentVisit = new StudentVisit();
        studentVisit.setLesson(lesson);
        studentVisit.setStudent(student);

//        schoolDB.getStudentVisits().create(studentVisit);
        studentVisit = new StudentVisit();
        studentVisit = schoolDB.getStudentVisits().readAll().get(0);

        System.out.printf("st_name - %s\nles_name - %s", studentVisit.getStudent().getFirstName(), studentVisit.getLesson().getName());
    }
}
