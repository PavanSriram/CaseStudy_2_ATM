package com.company;


import java.util.ArrayList;
import java.util.Scanner;

//ATM_CS as a class
public class ATM_CS {

    //array list of user accounts
    ArrayList<User_Account> user_acc = new ArrayList<>(1);
    long pwd;
    long total_cash;
    Scanner sc = new Scanner(System.in);

    //constructor for initializing the ATM
    ATM_CS(long pwd, long total_cash){
        this.pwd = pwd;
        this.total_cash = total_cash;

        System.out.println("Enter all the customer details");
        //the ATM starts with atleast one customer
        while(true){
            System.out.println("Enter the Name, Account Number(5-digit only), PIN Number(5-digit only), Balance in the order: ");
            String name = sc.next();

            long acc_no = sc.nextLong();

            //to check whether the account number is five digit or not
            if(!(acc_no >= 10000 && acc_no <= 99999)){
                System.out.println("Invalid Account Number. A valid number must be a 5-digit number");
                continue;
            }
            //to check whether the account number is already issued or not
            boolean flag1=false;
            for(User_Account v : user_acc){
                if(v.getAcc_no() == acc_no){
                    System.out.println("This account number already exists");
                    flag1 = true;
                    break;
                }
            }
            if(flag1)
                continue;


            long pin = sc.nextLong();

            //to check whether the pin number is 5-digit or not
            if(!(pin >= 10000 && pin <= 99999)){
                System.out.println("Invalid PIN number. A valid pin must be a 5-digit number");
                continue;
            }

            //to check whether the pin number is already issued or not
            boolean flag2 = false;
            for(User_Account v : user_acc){
                if(v.check_pin(pin)){
                    System.out.println("This pin cannot be assigned");
                    flag2 = true;
                    break;
                }
            }
            if(flag2)
                continue;


            long Balance = sc.nextLong();

            //customer successfully added to the array list
            user_acc.add(new User_Account(name, acc_no, pin, Balance));

            System.out.println("Customer Successfully added");
            System.out.println("Press 1 to enter new customer information");
            System.out.println("Press 0 to set the ATM in working mode");
            int option = sc.nextInt();
            if(option == 0)
                break;
        }
    }

    //method that keeps the ATM in the working mode
    void working(){
        while(true) {
            System.out.println("press 1 if admin");
            System.out.println("press 2 if customer");
            int option = sc.nextInt();

            //admin : shutdown the ATM , add cash to the ATM, check the total cash
            if (option == 1) {
                System.out.println("Enter the password: ");
                long p = sc.nextLong();

                //checks whether the admin password is correct or not
                if (p == pwd) {

                    //functionalities of the admin
                    System.out.println("press 0 to shutdown the system");
                    System.out.println("press 1 to add cash");
                    System.out.println("press 2 to get the cash inside the ATM");

                    int chose = sc.nextInt();
                    if(chose == 0){
                        return ;
                    }
                    else if(chose == 1){
                        System.out.println("Enter the amount: ");
                        long add = sc.nextLong();
                        total_cash += add;
                        System.out.println("Cash added successfully");
                    }
                    else if(chose == 2){
                        System.out.println("Remaining cash inside the ATM = " + total_cash);
                    }
                    //to check if the option is invalid
                    else{
                        System.out.println("Invalid Option");
                    }
                } else {
                    System.out.println("Wrong Password.... Redirecting to main menu");
                }
            }
            //entering the customer menu
            else if(option == 2){

                boolean login = true;
                User_Account u = new User_Account("nobody", 0, 0, 0);
                while(login){
                    System.out.println("Welcome");
                    System.out.println("Enter your 5-digit Account Number");
                    long acc = sc.nextLong();

                    //checks whether the entered account number is inside the database or not
                    for (User_Account v : user_acc) {
                        if (v.getAcc_no() == acc) {
                            u = v;
                            break;
                        }
                    }
                    //if it is not present
                    if (u.getAcc_no() == 0){
                        System.out.println("The DataBase does not contain the Account Number please try again...");
                        continue;
                    }

                    System.out.println("Enter your 5-digit PIN number");
                    long pin = sc.nextLong();

                    //checks whether the entered pin matches the original pin or not
                    if(!u.check_pin(pin)){
                        System.out.println("This PIN number does not match with the original PIN");
                        continue;
                    }

                    //if matches
                    System.out.println("Welcome " + u.getName());
                    login = false;
                }

                //functionalities of the customer
                while(true) {
                    System.out.println("Press 1 to View Your Balance");
                    System.out.println("Press 2 to Deposit Funds");
                    System.out.println("Press 3 to Withdrawl Amount");
                    int opt = sc.nextInt();

                    //to view balance
                    if(opt == 1){
                        view_bal(u);
                    }
                    //to deposit funds
                    else if(opt == 2){
                        deposit(u);
                    }
                    //to withdraw amount
                    else{
                        withdraw(u);
                    }
                    System.out.println("To Stay logged in to your account press 1");
                    System.out.println("To logout press 0");
                    int sel = sc.nextInt();
                    if(sel == 0)
                        break;
                }
            }
            //to check whether the option is valid or not
            else{
                System.out.println("Invalid Option");
            }
        }
    }

    //method to show the balance of the customer
    void view_bal(User_Account u){
        System.out.println("Your Current Balance : " + u.getBal());
    }

    //method to deposit funds to the customer's account
    void deposit(User_Account u){
        System.out.println("Enter the Amount you need to deposit");
        long Amount = sc.nextLong();
        u.setBal(Amount);
        System.out.println("Transaction Successful :)");
        view_bal(u);
    }

    //method to withdraw amount from the customer's account
    void withdraw(User_Account u){
        System.out.println("Enter the Amount you need to withdraw");
        long Amount = sc.nextLong();

        //checks if the total cash inside the ATM is sufficient for the withdrawl
        if(Amount <= total_cash) {
            //checks if the total cash inside the customer's account is more than the withdrawl amount
            if (u.withdraw(Amount)) {
                System.out.println("Transaction Successful :)");
            } else {
                System.out.println("Sorry Transaction Unsuccessful!.... ");
                System.out.println("Insufficient Funds!!!");
            }
            view_bal(u);
            total_cash -= Amount;
        }
        else{
            System.out.println("Insufficient cash in ATM");
        }
    }
}
