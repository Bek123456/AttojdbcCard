package org.example.service;

import org.example.model.Card;
import org.example.model.Profile;
import org.example.model.Transaction;
import org.example.repository.TransactionRepository;
import org.example.scan.Scaner;

import java.sql.SQLException;
import java.util.List;

public class TransactionService {
private static TransactionRepository transactionRepository=new TransactionRepository();
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
                        break;
                    case 2:
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
}
