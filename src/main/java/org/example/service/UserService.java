package org.example.service;

import lombok.Setter;
import org.example.model.Profile;
import org.example.scan.Scaner;

@Setter
public class UserService {
//    private static CardService cardService=new CardService();
//    private static TransactionService transactionService=new TransactionService();
    private CardService cardService;
    private TransactionService transactionService;
    public void start(Profile profile){
        System.out.println("********User Menu********");
        int select;
        while (true){
            System.out.println("1.Card Menu");
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












