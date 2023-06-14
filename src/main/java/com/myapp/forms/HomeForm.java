package com.myapp.forms;

import com.myapp.Task;
import com.myapp.databases.CategoriesDatabase;
import com.myapp.databases.TaskDatabase;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class HomeForm extends JPanel {
    JTextField inputTask;
    JButton addTask;
    JTextField inputSymptom;
    JButton addSymptom;
    public JPanel homePanel;
    JComboBox taskCategory;
    JComboBox taskDuration;
    JCheckBox checkBox;
    JScrollPane toDoList;

    private JTable table;

    public HomeForm(){

        CategoriesDatabase categoriesDatabase = new CategoriesDatabase();
        categoriesDatabase.insertCategory("self-care");
        int categoryId = categoriesDatabase.getCategoryIdByName("self-care");

        String[] categoryFieldText = new String[]{"SELF-CARE"};

        TaskDatabase taskDatabase = new TaskDatabase();

        taskCategory.addItem("CATEGORY");

        taskDuration.addItem(30);

        Object cmboitem = taskCategory.getSelectedItem();
        System.out.println(cmboitem);


        addTask.addActionListener(e->{
            // Current date
            Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            Task task = new Task(inputTask.getText(), currentDate);
            try {
                task.setTaskCategoryId(categoryId);
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

        taskCategory.addActionListener(e->{

        });

        checkBox.addItemListener(e->{
            System.out.println(checkBox.getText());
        });
    }

    private List<Task> getTasks(TaskDatabase taskDatabase) {
        List<Task> tasksList = taskDatabase.getTaskNames();
        return tasksList;
    }

    private void updateCompleteTask(String taskName) throws SQLException {
        TaskDatabase taskDatabase = new TaskDatabase();
        Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        // Fetch task by taskName
        Task task = taskDatabase.getTaskByName(taskName);
        // Set the task as completed
        task.setCompletedDate(currentDate);
        // Update task in the database
        taskDatabase.updateTask(task);
    }
}
