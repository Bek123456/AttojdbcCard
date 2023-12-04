package org.example.repository;

import org.example.enums.StatusType;
import org.example.enums.Type;
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

    public boolean update(String code, String address) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="update terminal set address=? where code=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,address);
        preparedStatement.setString(2,code);
        int executeUpdate = preparedStatement.executeUpdate();
        return executeUpdate!=0;

    }

    public Terminal changeStatus(String code) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select *from terminal where code=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,code);
        ResultSet resultSet = preparedStatement.executeQuery();
        Terminal terminal;
        while (resultSet.next()){
            terminal=new Terminal(resultSet.getInt("id"),
                    resultSet.getString("code"),
                    resultSet.getString("address"),
                    StatusType.valueOf(resultSet.getString("status")),
                    resultSet.getTimestamp("ereated_date").toLocalDateTime());
            return terminal;
        }
        return new Terminal();
    }

    public boolean deleted(String code) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="delete from terminal where code=?;";
        PreparedStatement preparedStatement=
                connection.prepareStatement(sql);
        preparedStatement.setString(1,code);
        int executed = preparedStatement.executeUpdate();
        connection.close();
        return executed!=0;
    }

    public Terminal checkTerminal(String terminalNumber) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select *from terminal where code=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,terminalNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        Terminal terminal=new Terminal();
        while (resultSet.next()){
           terminal.setStatus(StatusType.valueOf(resultSet.getString("status")));
           terminal.setId(resultSet.getInt("id"));
           terminal.setCode(resultSet.getString("code"));
           terminal.setAddress(resultSet.getString("address"));
           terminal.setCreated_date(resultSet.getTimestamp("created_date").toLocalDateTime());
           return terminal;
        }
        return terminal;
    }
}
