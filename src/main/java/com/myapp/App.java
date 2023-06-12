package com.myapp;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.myapp.databases.*;
import com.myapp.forms.*;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
            UIManager.put( "Component.arrowType", "chevron" );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

// create UI here...

        CreateTables createTables = new CreateTables();
        createTables.createDateCategories();
        createTables.createAllOtherTables();

        JFrame frame = new JFrame("Home");
        frame.setContentPane(new HomeForm().homePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Automatically adjust the frame size based on its contents
        frame.pack();
        frame.setVisible(true);
    }

}
