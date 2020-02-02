package com.sbt.javaschool.rnd.lesson21.db.school.controller;

import com.sbt.javaschool.rnd.lesson21.db.controller.DBController;
import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolConnection;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Student;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentSet implements DBController<Student, Integer> {
    private static final String SQL_CREATE_STUDENT = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
    private static final String SQL_READ_STUDENT_BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String SQL_READ_STUDENT_ALL = "SELECT * FROM students";
    private static final String SQL_UPDATE_STUDENT = "UPDATE students SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String SQL_DELETE_STUDENT_BY_ID = "DELETE FROM students WHERE id = ?";

    @Override
    public void create(Student entity) {
        try (PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_CREATE_STUDENT)){
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student read(Integer id) {
        Student student = new Student();
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_READ_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            student = readFromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> readAll() {
        List<Student> students = new ArrayList<>();
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_READ_STUDENT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            students = readFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public void update(Student entity) {
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_UPDATE_STUDENT)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_DELETE_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Student> readFromResultSet(ResultSet resultSet) throws SQLException {
        List<Student> students = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                Field idField = student.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(student, resultSet.getInt("id"));
                students.add(student);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }
}
