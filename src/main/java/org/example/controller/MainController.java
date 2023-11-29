package org.example.controller;

import org.example.scan.Scaner;
import org.example.service.LoginService;
import org.example.service.RegstrService;

import java.sql.SQLException;

public class MainController {
    private static LoginService loginService=new LoginService();
    private static RegstrService regstrService=new RegstrService();
    public void start() throws SQLException {
        System.out.println("=====Menu=======");
        String select;
        while (true){
            System.out.println("1.Login\n2.Registr\n0.Orqaga");
            select= Scaner.getStr();
            switch (select){
                case "1":
                    loginService.start();
                    break;
                case "2":
                    regstrService.start();
                    break;
                case "0":
                    System.out.println("Bay bay");
                    return;
                default:
                    System.out.println("Qayta urinib kuring");
                    break;
            }
        }
    }
}
