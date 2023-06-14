package com.myapp.databases;

import com.myapp.Task;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TaskDatabase {

    public int insertTask(Task task) throws SQLException {
        String query = "INSERT INTO task (task_name, task_date_added, category_id, task_complete) VALUES (?, ?, ?, ?)";
        int taskId = -1;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setDate(2, task.getTaskDateAdded());
            preparedStatement.setInt(3, task.getTaskCategoryId());
            preparedStatement.setBoolean(4, task.getTaskComplete());

            JOptionPane.showMessageDialog(null, "Task: " +  task.getTaskName() + " posted.");
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                taskId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskId;
    }

    public void insertItemCategories(int taskId, String[] categoryNames) {
        String query = "INSERT INTO task_categories (task_id, category_id) VALUES (?, ?)";
        CategoriesDatabase categoriesDatabase = new CategoriesDatabase();

        for (String categoryName : categoryNames) {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                int categoryId = categoriesDatabase.insertCategory(categoryName);

                preparedStatement.setInt(1, taskId);
                preparedStatement.setInt(2, categoryId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Task getTaskByName(String name) throws SQLException {
        String query = "SELECT * FROM task WHERE task_name = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("task_id");
                Date date = resultSet.getDate("task_date_added");
                Task task = new Task(name, date);
                task.setId(id);
                return task;
            } else {
                return null;
            }
        }

    }

    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE task SET task_complete = ? WHERE task_id = ?";


        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, task.getTaskId());
            preparedStatement.executeUpdate();
        }

    }

    public List<Task> getTaskNames() {
        List<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM task";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String taskName = resultSet.getString("task_name");
                // Current date
                Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                Task task = new Task(taskName, currentDate);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public boolean isTableEmpty() throws SQLException {
        String query = "SELECT COUNT(*) FROM task";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            } else {
                return true;
            }
        }
    }
}
