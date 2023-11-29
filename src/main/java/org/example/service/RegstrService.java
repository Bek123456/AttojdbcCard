package org.example.service;

import org.example.scan.Scaner;
import org.example.util1.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegstrService {
    public void start() throws SQLException {
        System.out.println("Entr name:");
        String name= Scaner.getStr();
        System.out.println("Entr surname:");
        String surname=Scaner.getStr();
        System.out.println("Entr phone:");
        String phone=Scaner.getStr();
        System.out.println("Entr password:");
        String password=Scaner.getStr();
        Connection connection= Util.getConnection();
        String sql="insert into profile (name,surname,phone,password)values (?,?,?,?);";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,surname);
        preparedStatement.setString(3,phone);
        preparedStatement.setString(4,password);
        preparedStatement.executeUpdate();
        connection.close();
        System.out.println("You have successfully registered");
    }
}
