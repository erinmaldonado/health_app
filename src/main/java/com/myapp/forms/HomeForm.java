package com.myapp.forms;
import com.myapp.Task;
import com.myapp.databases.CategoriesDatabase;
import com.myapp.databases.TaskDatabase;

import javax.swing.*;
import java.sql.SQLException;

public class HomeForm {
    JTextField inputTask;
    JButton addTask;
    JTextField inputSymptom;
    JButton addSymptom;
    public JPanel homePanel;
    JComboBox comboBox;

    public HomeForm(){
        CategoriesDatabase categoriesDatabase = new CategoriesDatabase();
        categoriesDatabase.insertCategory("self-care");

        String[] categoryFieldText = new String[]{"SELF-CARE"};

        TaskDatabase taskDatabase = new TaskDatabase();

        addTask.addActionListener(e->{
            Task task = new Task(inputTask.getText());
            try {
                int taskId = taskDatabase.insertTask(task);
                taskDatabase.insertItemCategories(taskId, categoryFieldText);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        addSymptom.addActionListener(e->{
            String symptom = inputSymptom.getText();
            System.out.println(symptom);
        });

        comboBox.addActionListener(e->{

        });
    }

}
