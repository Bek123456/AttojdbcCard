package org.example;

import org.example.controller.MainController;

import java.sql.SQLException;

public class Main {
    private static MainController mainController=new MainController();
    public static void main(String[] args) throws SQLException {
         mainController.start();
    }
}