package org.example.service;

import org.example.dto.CardDto;
import org.example.model.Card;
import org.example.model.Profile;
import org.example.repository.CardRepository;
import org.example.scan.Scaner;

import java.sql.SQLException;
import java.util.EventListener;
import java.util.List;

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
                System.out.println("Qayta urinib kuring bu matn");
            }

        }
    }

    private void menu(int select) throws SQLException {
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

    private void deletedCard() throws SQLException {
        System.out.println("Enter your card number:");
        String number=Scaner.getStr();
        boolean deleted = cardRepository.deleted(number);
        if (deleted){
            System.out.println("Deleted card");
            return;
        }
        System.out.println("not found card");
    }

    private void statusCard() throws SQLException {
        System.out.println("Enter your card number:");
        String number=Scaner.getStr();
        String statusCard = cardRepository.statusCard(number);
        if (statusCard!=null){
            System.out.println(statusCard);
        }
    }

    private void editCard() throws SQLException {
        System.out.println("Enter your card number:");
        String number=Scaner.getStr();
        System.out.println("Enter your card exp_date");
        String exp_date=Scaner.getStr();
        cardRepository.edit(number,exp_date);
    }

    private void readCards() throws SQLException {
        List<Card>cardList=cardRepository.reads();
        System.out.println(cardList);
    }

    private void createCard() throws SQLException {
        System.out.println("Enter your card number:");
        String number=Scaner.getStr();
        System.out.println("Enter your card exp_date");
        String exp_date=Scaner.getStr();
        boolean b = cardRepository.create(new CardDto(number, exp_date));
        if (b){
            System.out.println("ADD CARD");
        }
        else {
            System.out.println("not add card");
        }

    }

    public void cardMenu(Profile profile) {
        System.out.println("*******Card Menu*****");
        int select;
        while (true){
            System.out.println("1.Add Card");
            System.out.println("2.Card List");
            System.out.println("3.Card Change status");
            System.out.println("4.Delete Card");
            System.out.println("5.ReFill");
            System.out.println("0.Orqaga");
            try {
                select=Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<<<<<");
                    return;
                }
                if (select>0&&select<6){
                    rabotaCard(select,profile);
                }
                 else {
                    System.out.println("Qayta urinib kuring");
                }
            }catch (Exception e){
                System.out.println("Qayta urinib kuring mant bu");
            }

        }
    }

    private void rabotaCard(int select, Profile profile) {
        switch (select){
            case 1:
                profileCard(profile);
                break;
            case 2:
                profileCardList(profile);
                break;
            case 3:
                profileChangeStatus(profile);
                break;
            case 4:
                profileDeletedCard(profile);
                break;
            case 5:
                profileReFillCard(profile);
                break;
        }
    }

    private void profileReFillCard(Profile profile) {
        
    }

    private void profileDeletedCard(Profile profile) {

    }

    private void profileChangeStatus(Profile profile) {

    }

    private void profileCardList(Profile profile) {

    }

    private void profileCard(Profile profile) {

    }
}
