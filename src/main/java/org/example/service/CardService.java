package org.example.service;

import org.example.dto.CardDto;
import org.example.repository.CardRepository;
import org.example.scan.Scaner;

public class CardService {
    private static CardRepository cardRepository=new CardRepository();

    public void start() {
        System.out.println("*******Card Menu*******");
        int select;
        while (true) {
            System.out.println("1.Create Card");
            System.out.println("2.Card List");
            System.out.println("3.Update Card");
            System.out.println("4.Change Card status");
            System.out.println("5.Deleted Card");
            System.out.println("0.Orqaga");
            try {
                select= Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<");
                    return;
                }
                if (select>0&&select<6){
                    menu(select);
                }
                else {
                    System.out.println("Qayta urinib kuring");

                }
            }catch (Exception e){

            }

        }
    }

    private void menu(int select) {
        switch (select){
            case 1:
                createCard();
                break;
            case 2:
                readCards();
                break;
            case 3:
                editCard();
                break;
            case 4:
                statusCard();
                break;
            case 5:
                deletedCard();
                break;
        }
    }

    private void deletedCard() {

    }

    private void statusCard() {

    }

    private void editCard() {

    }

    private void readCards() {

    }

    private void createCard() {
        System.out.println("Enter your card number:");
        String number=Scaner.getStr();
        System.out.println("Enter your card exp_date");
        String exp_date=Scaner.getStr();
        cardRepository.create(new CardDto(number,exp_date));
    }
}
