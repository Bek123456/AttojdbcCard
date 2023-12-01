package org.example.repository;

import org.example.enums.StatusType;
import org.example.enums.UserRole;
import org.example.model.Profile;
import org.example.util1.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {

    public String changeProfileStatus(Profile profile,String phone) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select  status from profile where phone=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,phone);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
       while (resultSet.next()){
           return resultSet.getString("status");
       }
        return null;

    }

    public List<Profile> reads(Profile profile) throws SQLException {
        Connection connection= Util.getConnection();
        String sql="select *from profile";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Profile>profileList=new ArrayList<>();
        while (resultSet.next()){
            profileList.add(new Profile(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("phone"),
                    resultSet.getString("password"),
                    resultSet.getTimestamp("create_date").toLocalDateTime(),
                    StatusType.valueOf(resultSet.getString("status")),
                    UserRole.valueOf(resultSet.getString("role"))));
        }
        connection.close();
        return profileList;
    }
}
