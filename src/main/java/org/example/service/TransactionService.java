package org.example.service;

import org.example.model.Profile;
import org.example.scan.Scaner;

public class TransactionService {

    public void start() {

    }


    public void transactionCard(Profile profile) {
        System.out.println("*****Transaction Service*****");
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
