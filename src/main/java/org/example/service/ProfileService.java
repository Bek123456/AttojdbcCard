package org.example.service;

import lombok.Setter;
import org.example.model.Profile;
import org.example.repository.ProfileRepository;
import org.example.scan.Scaner;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
@Setter
public class ProfileService {
//     private static ProfileRepository profileRepository=new ProfileRepository();
    private ProfileRepository profileRepository;
    public void start(Profile profile) {
        int select;
        while (true) {
            System.out.println("1.Profile List");
            System.out.println("2.Change profile status (by phone)");
            System.out.println("0.Exit");
            try {
                select= Scaner.getInt();
                if (select==0){
                    System.out.println("<<<<<<<<<<");
                    return;
                }
                if (select>0&&select<3){
                    menu(profile,select);
                }
                else {
                    System.out.println("Qayta urinib kuring");
                }
            }catch (Exception e){
                System.out.println("Matn kiritish mumkin emas");
            }
        }
    }

    private void menu(Profile profile,int select) throws SQLException {
        switch (select){
            case 1:
                reads(profile);
                break;
            case 2:
                changeStatus(profile);
                break;
        }
    }

    private void changeStatus(Profile profile) throws SQLException {
        System.out.println("Enter phone number:");
        String phone=Scaner.getStr();
        String changeProfileStatus = profileRepository.changeProfileStatus(profile, phone);
        System.out.println(changeProfileStatus);

    }

    private void reads(Profile profile) throws SQLException {
        List<Profile> reads = profileRepository.reads(profile);
        for (Profile profile1:reads){
            System.out.println(profile1);
        }
    }
}
