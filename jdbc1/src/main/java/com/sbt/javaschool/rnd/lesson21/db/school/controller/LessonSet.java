package com.sbt.javaschool.rnd.lesson21.db.school.controller;

import com.sbt.javaschool.rnd.lesson21.db.controller.DBController;
import com.sbt.javaschool.rnd.lesson21.db.school.connection.SchoolConnection;
import com.sbt.javaschool.rnd.lesson21.db.school.entities.Lesson;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonSet implements DBController<Lesson, Integer> {

    private static final String SQL_CREATE_LESSON = "INSERT INTO lessons (name, date) VALUES (?, to_date(?,'YYYY-MM-DD'))";
    private static final String SQL_READ_LESSON_BY_ID = "SELECT * FROM lessons WHERE id = ?";
    private static final String SQL_READ_LESSON_ALL = "SELECT * FROM lessons";
    private static final String SQL_UPDATE_LESSON = "UPDATE lessons SET name = ?, date = to_date(?, 'YYYY-MM-DD') WHERE id = ?";
    private static final String SQL_DELETE_LESSON_BY_ID = "DELETE FROM lessons WHERE id = ?";

    @Override
    public void create(Lesson entity) {
        try (PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_CREATE_LESSON)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setObject(2, entity.getDate().toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Lesson read(Integer id) {
        Lesson lesson = new Lesson();
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_READ_LESSON_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            lesson = readFromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lesson;
    }

    @Override
    public List<Lesson> readAll() {
        List<Lesson> lessons = new ArrayList<>();
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_READ_LESSON_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            lessons = readFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lessons;
    }

    @Override
    public void update(Lesson entity) {
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_UPDATE_LESSON)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setObject(2, entity.getDate().toString());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement preparedStatement = SchoolConnection.getConnection().prepareStatement(SQL_DELETE_LESSON_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Lesson> readFromResultSet(ResultSet resultSet) throws SQLException {
        List<Lesson> lessons = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setName(resultSet.getString("name"));
                lesson.setDate(resultSet.getDate("date").toLocalDate());
                Field idField = lesson.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(lesson, resultSet.getInt("id"));
                lessons.add(lesson);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return lessons;
    }
}
