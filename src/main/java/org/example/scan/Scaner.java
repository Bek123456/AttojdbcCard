package org.example.scan;

import java.util.Scanner;

public class Scaner {
    public static int getInt(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String getStr(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();
    }
    public static double getDob(){
        Scanner scanner=new Scanner(System.in);
        return scanner.nextDouble();
    }
}
