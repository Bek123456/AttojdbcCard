package org.example.service;

import org.example.dto.TransactionDto;
import org.example.model.Card;
import org.example.model.Profile;
import org.example.model.Terminal;
import org.example.model.Transaction;
import org.example.repository.TerminalRepository;
import org.example.repository.TransactionRepository;
import org.example.scan.Scaner;
import org.example.util1.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionService {
private static TransactionRepository transactionRepository=
        new TransactionRepository();
private static TerminalRepository terminalRepository=
        new TerminalRepository();
    public void start() {
        System.out.println("*****Transaction Service******");
        int select;
        while (true){
            System.out.println("1.Transaction List");
            System.out.println("2.Company card balance");
            System.out.println("0.Exit");
            try {
                select=Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<<<");
                    return;
                }

                if (select>0&&select<3){
                    switch (select){
                        case 1:
                            readsTransaction();
                            break;
                        case 2:
                            companyCardBalance();
                            break;
                    }
                }
                else {
                    System.out.println("Qayta urinib kuring");
                }
            }catch (Exception e){
                System.out.println("Matn Qayta urinib kuring");
            }

        }
    }

    private void companyCardBalance() throws SQLException {
        Card card = transactionRepository.companyBalance();
        System.out.println(card.getBalance());
    }

    private void readsTransaction() throws SQLException {
        List<Transaction> transactionList = transactionRepository.readsTransaction();
        for (int i = transactionList.size()-1; i>0 ; i--) {
            System.out.println(transactionList.get(i));
        }
    }


    public void transactionCard(Profile profile) {
        System.out.println("*****Transaction Menu*****");
        int select;
        while (true){
            System.out.println("1.Utkazmalar tarixi");
            System.out.println("2.Utkamalar");
            System.out.println("0.Orqaga");
            try {
                select= Scaner.getInt();
                switch (select){
                    case 1:
                        historiyTransaction();
                        break;
                    case 2:
                         transaction(profile);
                        break;
                    case 0:
                        System.out.println("<<<<<<<");
                        return;
                    default:
                        System.out.println("Qayta urinib kuring");
                        break;
                }
            }catch (Exception e){
                System.out.println("Qayta urinib kuring matn bu");
            }
        }
    }

    private void transaction(Profile profile) throws SQLException {
        System.out.println("Enter card number");
        String number=Scaner.getStr();
        TransactionDto transactionDto = transactionRepository.chekCard(number);
        if (transactionDto==null){
            System.out.println("Sizda bunday karta mavjud emas");
            return;
        }
        if (transactionDto.isBor()){

        }
        if (!transactionDto.isBor()){
            System.out.println("kartada mablag yetarli emas");
            return;
        }
        System.out.println("Enter your terminal code");
        String terminalNumber=Scaner.getStr();
        Terminal terminal = terminalRepository.checkTerminal(terminalNumber);
        if (!terminal.getCode().equals(terminalNumber)){

        }
        boolean tolash = transactionRepository.tolash(number);
        if (!tolash){
            System.out.println("Utkazma amalga oshirilmadi");
        }
        Connection connection= Util.getConnection();
        String sql="insert into transaction(card_number,amount,terminal_code)values (?,?,?)";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        preparedStatement.setString(1,number);
        preparedStatement.setDouble(2,1700);
        preparedStatement.setString(3,terminalNumber);
        int i = preparedStatement.executeUpdate();
        if (i!=0){
            System.out.println("Tolov amalga oshirildi");
        }

    }

    private void historiyTransaction() throws SQLException {
        List<Transaction> transactionList = transactionRepository.historyAll();
        for (int i = transactionList.size()-1; i>=0 ; i--) {
            System.out.println("Card Number==>>"+transactionList.get(i).getCard_number());
            System.out.println("Address==>>"+transactionList.get(i).getAmount());
            System.out.println("Amount==>>"+transactionList.get(i).getAmount());
            System.out.println("Amalga oshirilgan vaqti==>>"+transactionList.get(i).getCreated_date());
            System.out.println("Type==>>"+transactionList.get(i).getType());
            System.out.println("==========\n");
        }
    }
}
