package org.example.service;

import org.example.model.Profile;
import org.example.model.Terminal;
import org.example.repository.TerminalRepository;
import org.example.scan.Scaner;

import java.sql.SQLException;
import java.util.List;

public class TerminalService {
      private static TerminalRepository terminalRepository=new TerminalRepository();
    public void start(Profile profile){
        System.out.println("******Terminal Service******");
        int select;
        while (true){
            System.out.println("1.Create terminal");
            System.out.println("2.Terminal List");
            System.out.println("3.Update terminal");
            System.out.println("4.Change terminal status");
            System.out.println("5.Deleted terminal");
            System.out.println("0.Orqaga");
            try {
                select= Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<<<");
                    return;
                }
                if (select>0&&select<6){
                  menu(select,profile);
                }
                else {
                    System.out.println("Qayta urinib kuring");
                }
            }catch (Exception e){
                System.out.println("Matn kiritish mumkin emas qayta urinib kuring");
            }
        }
    }
      public void menu(int select,Profile profile) throws SQLException {
          switch (select){
              case 1:
                  created(profile);
                  break;
              case 2:
                  reads(profile);
                  break;
              case 3:
                  update(profile);
                  break;
              case 4:
                  changeStatus(profile);
                  break;
              case 5:
                  deletedTerminal(profile);
                  break;
          }
      }

    private void deletedTerminal(Profile profile) {

    }

    private void changeStatus(Profile profile) {

    }

    private void update(Profile profile) {
        System.out.println();
    }

    private void reads(Profile profile) throws SQLException {
        List<Terminal> reads = terminalRepository.reads();
        for (int i = 0; i < reads.size(); i++) {
            System.out.println(i+1+", "+reads.get(i));
        }
    }

    private void created(Profile profile)  {
        System.out.println("Enter code terminal:");
        String code=Scaner.getStr();
        System.out.println("Enter address terminal");
        String address=Scaner.getStr();

        boolean created = false;
        try {
            created = terminalRepository.created(address, code);
            if (created){
                System.out.println("ADD TERMINAL");
                return;
            }

            System.out.println("Not found add");
        } catch (SQLException e) {
            System.out.println("Terminal codi takrorlanmas bulsin");
        }
    }
}
