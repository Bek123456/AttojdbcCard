package org.example.repository;

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
        Connection connection= Util.getConnection();
        String sql="select from transaction";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Transaction>transactionList=new ArrayList<>();
        while (resultSet.next()){
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
        Connection connection=Util.getConnection();
        String sql="select *from card where number=?";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,"77777");
        ResultSet resultSet = preparedStatement.executeQuery();
        Card card;
        while (resultSet.next()){
                     card= new Card(resultSet.getInt("id"),
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
}
