package com.myapp;

import com.formdev.flatlaf.FlatDarkLaf;
import com.myapp.databases.CreateTables;
import com.myapp.tabbed.TabJFrame;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
            UIManager.put( "Component.arrowType", "chevron" );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        CreateTables createTables = new CreateTables();
        createTables.createDateCategories();
        createTables.createAllOtherTables();

        /* Create and display the form */
        EventQueue.invokeLater(() -> new TabJFrame().setVisible(true));
    }

}
