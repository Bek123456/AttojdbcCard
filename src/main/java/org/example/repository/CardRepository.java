package org.example.repository;

import org.example.dto.CardDto;
import org.example.enums.StatusType;
import org.example.model.Card;
import org.example.util1.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {

    public boolean create(CardDto cardDto) throws SQLException {
        Connection connection= Util.getConnection();
        String sql="insert into card(number,exp_date)values (?,?);";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1, cardDto.getNumber());
        preparedStatement.setString(2, cardDto.getExp_date());
        int i = preparedStatement.executeUpdate();
        return i==1;
    }
    public boolean edit(String number, String expDate) throws SQLException {
      Connection connection=Util.getConnection();
      String sql="update card set number=? where exp_date=?;";
      PreparedStatement preparedStatement= connection.prepareStatement(sql);
      preparedStatement.setString(1,number);
      preparedStatement.setString(2,expDate);
      int i = preparedStatement.executeUpdate();
      connection.close();
      return i==1;
    }

    public List<Card> reads() throws SQLException {
        List<Card>cardList=new ArrayList<>();
        Connection connection=Util.getConnection();
        String sql="select *from card";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Integer id=resultSet.getInt("id");
            String number=resultSet.getString("number");
            String exp_date=resultSet.getString("exp_date");
            double balance=resultSet.getDouble("balance");
            StatusType type=StatusType.valueOf(resultSet.getString("status"));
            String phone=resultSet.getString("phone");
            LocalDateTime created_date=resultSet.getTimestamp("created_date").toLocalDateTime();
            Card card=new Card(id,number,exp_date,balance,type,phone,created_date);
            cardList.add(card);
        }
        return cardList;
    }


    public String statusCard(String number) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select *from card where number=? ";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println( resultSet.getString("status"));
        }
        return null;
    }

    public boolean deleted(String number) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="delete from card where number=?;";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,number);
        int i = preparedStatement.executeUpdate();
        return i==1;
    }
}
