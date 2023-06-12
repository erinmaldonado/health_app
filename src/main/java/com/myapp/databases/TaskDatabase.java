package com.myapp.databases;

import com.myapp.Task;

import javax.swing.*;
import java.sql.*;

public class TaskDatabase {

    //private Task task = new Task();

    public int insertTask(Task task) throws SQLException {
        String query = "INSERT INTO task (task_name) VALUES (?)";
        int taskId = -1;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getTaskName());

            JOptionPane.showMessageDialog(null, "Item posted.");
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
