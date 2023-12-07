package org.example.service;

import lombok.Setter;
import org.example.enums.StatusType;
import org.example.enums.UserRole;
import org.example.model.Profile;
import org.example.scan.Scaner;
import org.example.util1.Util;

import java.sql.*;
import java.time.LocalDateTime;
@Setter
public class LoginService {
//   private static AdminService adminService=new AdminService();
//   private static UserService userService=new UserService();
    private AdminService adminService;
    private UserService userService;
    public void start() throws SQLException {
        System.out.println("=====Login=====");
        String phone;
        String password;
        Connection connection= Util.getConnection();
            System.out.println("Enter phone:");
             phone= Scaner.getStr();
            System.out.println("Enter password:");
             password=Scaner.getStr();
        String sql="select *from profile where phone=? and password=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
         preparedStatement.setString(1,phone);
         preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()){
            return;
        }
        Integer id=resultSet.getInt("id");
        String name=resultSet.getString("name");
        String surname=resultSet.getString("surname");
        String phone1=resultSet.getString("phone");
        LocalDateTime created_date=resultSet.getTimestamp("create_date").toLocalDateTime();
        StatusType status=StatusType.valueOf(resultSet.getString("status"));
        UserRole role=UserRole.valueOf(resultSet.getString("role"));
        Profile profile=new Profile(id,name,surname,
                phone1,password,created_date,status,role);
        if (profile.getRole().equals(UserRole.ADMIN)){
            adminService.start(profile);
        }
        else if (profile.getRole().equals(UserRole.USER)){
            userService.start(profile);
        }

    }
}
