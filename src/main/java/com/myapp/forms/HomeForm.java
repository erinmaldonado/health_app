package com.myapp.forms;
import com.myapp.databases.CategoriesDatabase;

import javax.swing.*;

public class HomeForm {
    JTextField inputTask;
    JButton addTask;
    JTextField inputSymptom;
    JButton addSymptom;
    public JPanel homePanel;

    public HomeForm(){
        CategoriesDatabase categoriesDatabase = new CategoriesDatabase();
        categoriesDatabase.insertCategory("self-care");

        addTask.addActionListener(e->{
            String task = inputTask.getText();
            System.out.println(task);
        });

        addSymptom.addActionListener(e->{
            String symptom = inputSymptom.getText();
            System.out.println(symptom);
        });
    }

}
