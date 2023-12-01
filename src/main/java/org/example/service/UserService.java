package org.example.service;

import org.example.model.Profile;
import org.example.scan.Scaner;

public class UserService {
    private static CardService cardService=new CardService();
    private static TransactionService transactionService=new TransactionService();
    public void start(Profile profile){
        System.out.println("********User Menu********");
        int select;
        while (true){
            System.out.println("1.Car Menu");
            System.out.println("2.Transaction");
            System.out.println("0.Orqaga");
            try {
                select= Scaner.getInt();
                  switch (select){
                      case 1:
                          cardService.cardMenu(profile);
                          break;
                      case 2:
                          transactionService.transactionCard(profile);
                          break;
                      case 0:
                          System.out.println("<<<<<<");
                         return;
                      default:
                          System.out.println("Qayta urinib kuring");
                          break;
                  }

            }catch (Exception e){
                System.out.println("Qayta urinib kuring bu matn");
            }

        }
      }
    }





