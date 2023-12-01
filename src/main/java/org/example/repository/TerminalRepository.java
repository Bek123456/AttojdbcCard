package org.example.repository;

import org.example.enums.StatusType;
import org.example.model.Terminal;
import org.example.util1.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TerminalRepository {

    public boolean created(String address, String code) throws SQLException {
        Connection connection= Util.getConnection();
        String sql="insert into terminal (code,address)values (?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,code);
        preparedStatement.setString(2,address);
        int i = preparedStatement.executeUpdate();
        connection.close();
        return i!=0;
    }

    public List<Terminal> reads() throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select *from terminal";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Terminal>terminalList=new ArrayList<>();
        while (resultSet.next()){
            terminalList.add(new Terminal(resultSet.getInt("id"),
                    resultSet.getString("code"),
                    resultSet.getString("address"),
                   StatusType.valueOf(resultSet.getString("status")),
                    resultSet.getTimestamp("created_date").toLocalDateTime()));
        }
        return terminalList;
    }
}
