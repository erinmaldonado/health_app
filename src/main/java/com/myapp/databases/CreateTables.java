package com.myapp.databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    public CreateTables(){}

    public void createDateCategories(){
        try {
            DBConnection createSchema = new DBConnection();
            createSchema.create();
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();

            /**
             * Table for storing date information
             */
            String dateTable = "CREATE TABLE IF NOT EXISTS date " +
                    "(date_id INTEGER not NULL AUTO_INCREMENT, " +
                    " date DATE, " +
                    " PRIMARY KEY ( date_id ))";

            stmt.executeUpdate(dateTable);

            /**
             * Table for storing categories
             */
            String categoryTable = "CREATE TABLE IF NOT EXISTS categories " +
                    "(category_id INTEGER not NULL AUTO_INCREMENT, " +
                    " category_name VARCHAR(255), " +
                    " PRIMARY KEY ( category_id ))";

            stmt.executeUpdate(categoryTable);

            System.out.println("Created date and categories table in given database...");

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void createAllOtherTables(){
        try {
            DBConnection createSchema = new DBConnection();
            createSchema.create();
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();

            /**
             * Table for storing period information
             */
        String periodTable = "CREATE TABLE IF NOT EXISTS period " +
                "(period_id INTEGER not NULL AUTO_INCREMENT, " +
                " period_score INTEGER, " +
                " date_id INTEGER, " +
                " category_id INTEGER, " +
                " FOREIGN KEY (period_id) REFERENCES date(date_id), " +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id), " +
                " PRIMARY KEY ( period_id ))";

        stmt.executeUpdate(periodTable);

        /**
         * Table for storing weight
         */
        String weightTable = "CREATE TABLE IF NOT EXISTS weight " +
                "(weight_id INTEGER not NULL AUTO_INCREMENT, " +
                " weight_lb FLOAT, " +
                " date_id INTEGER, " +
                " category_id INTEGER, " +
                " FOREIGN KEY (date_id) REFERENCES date(date_id), " +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id), " +
                " PRIMARY KEY ( weight_id ))";

        stmt.executeUpdate(weightTable);

        /**
         * Table for storing tasks
         */
        String taskTable = "CREATE TABLE IF NOT EXISTS task " +
                "(task_id INTEGER not NULL AUTO_INCREMENT, " +
                " task_name VARCHAR(255), " +
                " task_duration INTEGER," +
                " date_id INTEGER, " +
                " category_id INTEGER, " +
                " FOREIGN KEY (date_id) REFERENCES date(date_id), " +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id), " +
                " PRIMARY KEY ( task_id ))";

        stmt.executeUpdate(taskTable);

        String createTaskCategoriesTable = "CREATE TABLE IF NOT EXISTS task_categories (" +
                "task_id INT NOT NULL," +
                "category_id INT NOT NULL,"+
                "PRIMARY KEY (task_id, category_id)," + "FOREIGN KEY(task_id) REFERENCES task(" +
                "task_id)," +
                "FOREIGN KEY (category_id) REFERENCES categories (category_id)" +
                ")";
        stmt.executeUpdate(createTaskCategoriesTable);

        /**
         * Table for storing symptoms
         */
        String symptomTable = "CREATE TABLE IF NOT EXISTS symptom " +
                "(symptom_id INTEGER not NULL AUTO_INCREMENT, " +
                " symptom_name VARCHAR(255), " +
                " symptom_score INTEGER, " +
                " date_id INTEGER, " +
                " category_id INTEGER, " +
                " FOREIGN KEY (date_id) REFERENCES date(date_id), " +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id), " +
                " PRIMARY KEY ( symptom_id ))";


        stmt.executeUpdate(symptomTable);

        String sleepTable = "CREATE TABLE IF NOT EXISTS sleep " +
                "(sleep_id INTEGER not NULL AUTO_INCREMENT, " +
                " sleep_duration INTEGER, " +
                " sleep_score INTEGER, " +
                " date_id INTEGER, " +
                " category_id INTEGER, " +
                " FOREIGN KEY (date_id) REFERENCES date(date_id), " +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id), " +
                " PRIMARY KEY ( sleep_id ))";

        stmt.executeUpdate(sleepTable);

        /**
         * Table for storing moods
         */
        String moodTable = "CREATE TABLE IF NOT EXISTS mood " +
                "(mood_id INTEGER not NULL AUTO_INCREMENT, " +
                " mood_score INTEGER, " +
                " mood_time TIMESTAMP, " +
                " date_id INTEGER, " +
                " category_id INTEGER, " +
                " FOREIGN KEY (date_id) REFERENCES date(date_id), " +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id), " +
                " PRIMARY KEY ( mood_id ))";

        stmt.executeUpdate(moodTable);
            System.out.println("Created all other tables in given database...");

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
