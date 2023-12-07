package org.example;

import org.example.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
//    private static MainController mainController=new MainController();
    public static void main(String[] args) throws SQLException {
        AbstractApplicationContext context=new ClassPathXmlApplicationContext("spring-beans.xml");
        MainController mainController = (MainController) context.getBean("mainController");
        mainController.start();
    }
}