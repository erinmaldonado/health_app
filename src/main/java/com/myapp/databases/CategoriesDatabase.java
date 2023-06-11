package com.myapp.databases;

import java.sql.*;

public class CategoriesDatabase {

    public int insertCategory(String categoryName) {
        categoryName = categoryName.toLowerCase();  // Convert to lowercase
        int categoryId = getCategoryIdByName(categoryName);

        if (categoryId == -1) {
            String query = "INSERT INTO categories (category_name) VALUES (?)";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, categoryName);

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    categoryId = generatedKeys.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categoryId;
    }

    public int getCategoryIdByName(String categoryName) {
        categoryName = categoryName.toLowerCase();  // Convert to lowercase

        String query = "SELECT category_id FROM categories WHERE category_name = ?";
        int categoryId = -1;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, categoryName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                categoryId = resultSet.getInt("category_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryId;
    }
}
