package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Taking the password for ATM admin at the start of the program
        System.out.println("Enter the security password of the admin: ");
        long pwd = sc.nextLong();

        //taking the total cash as input
        System.out.println("Set the initial Cash Amount: ");
        long total_cash = sc.nextLong();

        //instantiating the ATM_CS class
        ATM_CS atm = new ATM_CS(pwd, total_cash);

        //Setting the ATM to the working mode
        atm.working();
    }
}
