package com.sbt.javaschool.rnd.lesson23;

import com.sbt.javaschool.rnd.lesson23.config.SchoolConfig;
import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolDB;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SchoolConfig.class);

        SchoolDB schoolDB = context.getBean(SchoolDB.class);

//        Student ivan = new Student();
//        ivan.setFirstName("Ivan");
//        ivan.setLastName("Ivanov");
//        schoolDB.getStudents().create(ivan);

        List<Student> students = schoolDB.getStudents().readAll();
        System.out.println(students.stream().map(student -> student.getFirstName()).collect(Collectors.joining("\n")));
    }
}
