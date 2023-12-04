package org.example.repository;

import org.example.dto.TransactionDto;
import org.example.enums.StatusType;
import org.example.enums.Type;
import org.example.model.Card;
import org.example.model.Transaction;
import org.example.util1.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public List<Transaction> readsTransaction() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "select *from transaction";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transaction> transactionList = new ArrayList<>();
        while (resultSet.next()) {
            transactionList.add(
                    new Transaction(resultSet.getInt("id"),
                            resultSet.getString("card_number"),
                            resultSet.getDouble("amount"),
                            resultSet.getString("terminal_code"),
                            Type.valueOf(resultSet.getString("type")),
                            resultSet.getTimestamp("created_date").
                                    toLocalDateTime()));
        }
        return transactionList;
    }

    public Card companyBalance() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "select *from card where number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "77777");
        ResultSet resultSet = preparedStatement.executeQuery();
        Card card;
        while (resultSet.next()) {
            card = new Card(resultSet.getInt("id"),
                    resultSet.getString("number"),
                    resultSet.getString("exp_date"),
                    resultSet.getDouble("balance"),
                    StatusType.valueOf(resultSet.getString("status")),
                    resultSet.getString("phone"),
                    resultSet.getTimestamp("created_date").toLocalDateTime());
            return card;
        }
        return null;
    }

    public List<Transaction> historyAll() throws SQLException {
        Connection connection = Util.getConnection();
        String sql = "select *from transaction";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transaction> transactionList = new ArrayList<>();
        while (resultSet.next()) {
            transactionList.add(new Transaction(resultSet.getInt("id"),
                    resultSet.getString("card_number"),
                    resultSet.getDouble("amount"),
                    resultSet.getString("terminal_code"),
                    Type.valueOf(resultSet.getString("type")),
                    resultSet.getTimestamp("created_date").toLocalDateTime()));
        }
        return transactionList;
    }

    public TransactionDto chekCard(String number) throws SQLException {
        Connection connection = Util.getConnection();
        String sql1 = "select *from card where number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.setString(1, number);
        ResultSet resultSet = preparedStatement.executeQuery();
        double balance = 0;
        while (resultSet.next()) {
            if (resultSet.getDouble("balance") >= 1700) {
                return new TransactionDto(resultSet.getDouble("balance"), true);
            }
            return new TransactionDto(resultSet.getDouble("balance"), false);
        }
    return null;
    }



    public boolean tolash(String number) throws SQLException {
        Connection connection=Util.getConnection();
        String sql="select *from card where number=?;";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,number);
        ResultSet resultSet = preparedStatement.executeQuery();

        double balance=0;
        while (resultSet.next()){
             balance=resultSet.getDouble("balance");
        }
        balance=balance-1700;
        String sql1="update card set balance=? where number=?";
        PreparedStatement preparedStatement1= connection.prepareStatement(sql1);
        preparedStatement1.setDouble(1,balance);
        preparedStatement1.setString(2,number);
        preparedStatement1.executeUpdate();
        String sql2="select *from card where number=?";
        PreparedStatement preparedStatement2= connection.prepareStatement(sql2);
        preparedStatement2.setString(1,"77777");
        ResultSet resultSet1 = preparedStatement2.executeQuery();
        double balance2=0;
        while (resultSet1.next()){
            balance2=resultSet1.getDouble("balance");
        }
        balance2=balance2+1700;
        String sql3="update card set balance=? where number=?";
        PreparedStatement preparedStatement3= connection.prepareStatement(sql3);
        preparedStatement3.setDouble(1,balance2);
        preparedStatement3.setString(2,"77777");
        int i = preparedStatement3.executeUpdate();
        if (i!=0){
            return true;
        }
        return false;
    }
}

