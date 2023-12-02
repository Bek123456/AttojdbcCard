package org.example.service;

import org.example.scan.Scaner;

public class StatisticService {

    public void start() {
        System.out.println("******Statistic Service*********");
        int select;
        while (true){
            System.out.println("1.Bugungi tulovlar");
            System.out.println("2.Kunlik tulovlar");
            System.out.println("3.Oraliq tulovlar");
            System.out.println("4.Companiyaning umumiy balance");
            System.out.println("5.Transaction by Terminal");
            System.out.println("6.Transaction By Card");
            System.out.println("0.Orqaga");
            try {
                select= Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<<<<");
                    return;
                }
                if (select>0&&select<7){
                    menu(select-1);
                }
                else {
                    System.out.println("Qayta urinib kuring");
                }

            }catch (Exception e){
                System.out.println("Mant bu Qayta urinib kuring");
            }


        }
    }

    private void menu(int i) {
        switch (i){
            case 1:
                bugungiTulov();
                break;
            case 2:
                kunlikTulov();
                break;
            case 3:
                oraliqTulovlar();
                break;
            case 4:
                companyTulov();
                break;
            case 5:
                transactionTerninal();
                break;
            case 6:
                transactionByCard();
                break;
        }
    }

    private void transactionByCard() {

    }

    private void transactionTerninal() {

    }

    private void companyTulov() {

    }

    private void oraliqTulovlar() {

    }

    private void kunlikTulov() {

    }

    private void bugungiTulov() {

    }
}
