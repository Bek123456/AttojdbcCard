package org.example.repository;

import org.example.dto.CardDto;
import org.example.dto.CardDtoUser;
import org.example.dto.CuseCard;
import org.example.enums.StatusType;
import org.example.model.Card;
import org.example.model.Profile;
import org.example.model.Transaction;
import org.example.scan.Scaner;
import org.example.util1.Util;

import java.sql.*;
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

    public boolean profileCardCreate(Profile profile, String number) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="update card set phone=? where number=?;";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,profile.getPhone());
        preparedStatement.setString(2,number);
        int i = preparedStatement.executeUpdate();
        connection.close();
        return i==1;
    }

    public List<CardDtoUser> readProfileCardList(Profile profile) throws SQLException {
        List<CardDtoUser>cardDtoUsers=new ArrayList<>();
        Connection connection=Util.getConnection();
        String sql="select number,exp_date,balance from card where phone=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,profile.getPhone());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            CardDtoUser cardDtoUser=new CardDtoUser();
            cardDtoUser.setBalance(resultSet.getDouble("balance"));
            cardDtoUser.setNumber(resultSet.getString("number"));
            cardDtoUser.setExp_date(resultSet.getString("exp_date"));
            cardDtoUsers.add(cardDtoUser);
        }
     return cardDtoUsers;
    }

    public List<String> profileChangeStatus(Profile profile) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select status from card where phone=?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,profile.getPhone());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String>stringList=new ArrayList<>();
        while (resultSet.next()){
            stringList.add(new String(resultSet.getString("status")));
        }
        return stringList;
    }
    public boolean profileDeletedCard(Profile profile) throws SQLException {
     Connection connection=Util.getConnection();
     String sql="delete from card where phone=?";
     PreparedStatement preparedStatement=connection.prepareStatement(sql);
     preparedStatement.setString(1,profile.getPhone());
        int executedUpdate = preparedStatement.executeUpdate();
        connection.close();
        return executedUpdate==1;
    }

    public CuseCard chuseCard(String cardNumber, Profile profile) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select *from card where number=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            if (resultSet.getString("phone").equals(profile.getPhone())){
                Card card=new Card(resultSet.getInt("id"),
                        resultSet.getString("number"),
                        resultSet.getString("exp_date"),
                        resultSet.getDouble("balance"),
                        StatusType.valueOf(resultSet.getString("status")),
                        resultSet.getString("phone"),resultSet.getTimestamp("created_date").toLocalDateTime()
                        );
                return new CuseCard(true,card);
            }
        }
        return new CuseCard();
    }

    public void profileReFillCard(Profile profile,Card card) throws SQLException {
        System.out.println("Qancha pul tashlamoqchisiz");
        double balance=Scaner.getDob();
        Connection connection=Util.getConnection();
        card.setBalance(card.getBalance()+balance);
        String sql="update card set balance=? where number=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setDouble(1,card.getBalance());
        preparedStatement.setString(2,card.getNumber());
        preparedStatement.executeUpdate();
        String sql1="insert into transaction(card_number,amount,type)values (?,?,?)";
        PreparedStatement preparedStatement1=connection.prepareStatement(sql1);
        preparedStatement1.setString(1,card.getNumber());
        preparedStatement1.
    }

    private void profileReFillCard1(double balance, Card card) throws SQLException {

    }


}
