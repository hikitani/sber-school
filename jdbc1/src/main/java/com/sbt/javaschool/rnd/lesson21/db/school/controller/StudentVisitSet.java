package com.sbt.javaschool.rnd.lesson21.db.school.controller;

import com.sbt.javaschool.rnd.lesson21.db.constraint.PrimaryKey2;
import com.sbt.javaschool.rnd.lesson21.db.controller.DBController;
import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolConnection;
import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolDB;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Lesson;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Student;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.StudentVisit;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentVisitSet implements DBController<StudentVisit, PrimaryKey2<Student, Lesson>> {
    private static final String SQL_CREATE_STUDENT_VISITS = "INSERT INTO student_visits (student_id, lesson_id) VALUES (?, ?)";
    private static final String SQL_READ_STUDENT_VISITS_BY_ID = "SELECT * FROM student_visits WHERE student_id == ? AND lesson_id == ?";
    private static final String SQL_READ_STUDENT_VISITS_ALL = "SELECT * FROM student_visits";

    @Override
    public void create(StudentVisit entity) {
        try (PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_CREATE_STUDENT_VISITS)){
            preparedStatement.setInt(1, entity.getStudent().getId());
            preparedStatement.setInt(2, entity.getLesson().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public StudentVisit read(PrimaryKey2<Student, Lesson> id) {
        StudentVisit studentVisit = new StudentVisit();
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_READ_STUDENT_VISITS_BY_ID)) {
            preparedStatement.setInt(1, id.key1.getId());
            preparedStatement.setInt(2, id.key2.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            studentVisit = readFromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentVisit;
    }

    @Override
    public List<StudentVisit> readAll() {
        List<StudentVisit> studentVisits = new ArrayList<>();
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_READ_STUDENT_VISITS_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            studentVisits = readFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentVisits;
    }

    @Override
    public void update(StudentVisit entity) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(PrimaryKey2<Student, Lesson> id) {
        throw new NotImplementedException();
    }

    private List<StudentVisit> readFromResultSet(ResultSet resultSet) throws SQLException {
        List<StudentVisit> studentVisits = new ArrayList<>();
        SchoolDB schoolDB = new SchoolDB();
        try {
            while (resultSet.next()) {
                StudentVisit studentVisit = new StudentVisit();

                Student student = schoolDB.getStudents().read(resultSet.getInt("student_id"));
                Lesson lesson = schoolDB.getLessons().read(resultSet.getInt("lesson_id"));

                Field studentField = studentVisit.getClass().getDeclaredField("student");
                Field lessonField = studentVisit.getClass().getDeclaredField("lesson");

                studentField.setAccessible(true);
                lessonField.setAccessible(true);

                studentField.set(studentVisit, student);
                lessonField.set(studentVisit, lesson);

                studentVisits.add(studentVisit);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return studentVisits;
    }
}
