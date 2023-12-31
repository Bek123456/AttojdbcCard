package org.example.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Profile;
import org.example.scan.Scaner;
@Setter

public class AdminService {
//    private static CardService cardService=new CardService();
//    private static TerminalService terminalService=new TerminalService();
//    private static ProfileService profileService=new ProfileService();
//    private static TransactionService transactionService=new TransactionService();
//    private static StatisticService statisticService=new StatisticService();
    private CardService  cardService;
    private TerminalService terminalService;
    private ProfileService profileService;
    private TransactionService transactionService;
    private StatisticService statisticService;

    public void start(Profile profile){
        System.out.println("******Admin Menu******");
        int select;
        while (true){
            System.out.println("1.Card\n" +
                               "2.Terminal\n" +
                               "3.Profile\n" +
                               "4.Transaction" +
                               "\n5.Statistic\n" +
                                "0.Orqaga");
            try {
                select= Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<<<<");
                    return;
                }
                if (select>0&&select<6){
                    menu(select,profile);
                }
                else {
                    System.out.println("Qayta urinib kuring");
                }
            }catch (Exception e){
                System.out.println("Qayta urinib kuring");
            }
        }
    }

    private void menu(int select,Profile profile) {
        switch (select){
            case 1:
                cardService.start();
                break;
            case 2:
                terminalService.start(profile);
                break;
            case 3:
                profileService.start(profile);
                break;
            case 4:
                transactionService.start();
                break;
            case 5:
                statisticService.start();
                break;
        }
    }
}
