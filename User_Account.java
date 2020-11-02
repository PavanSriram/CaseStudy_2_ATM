package com.company;

//User_Account as a class where all the information about the user's bank details is stored
public class User_Account {

    //name, account number, pin number, balance are declared private to enhance the security
    private String name;
    private long acc_no;
    private long pin;
    private long bal;

    //constructor for initializing the name, account number, pin and balance
    User_Account(String name, long acc_no, long pin, long bal){
        this.name = name;
        this.acc_no = acc_no;
        this.pin = pin;
        this.bal = bal;
    }

    //method to get name of the customer
    public String getName(){
        return name;
    }

    //method to get the account number of the customer
    public long getAcc_no(){
        return acc_no;
    }

    //method to check whether the pin is correct or not which is checked here itself
    //to maintain the security the getPin() method is not declared
    public boolean check_pin(long p){
        return pin == p;
    }

    //method to get the balance of the customer
    public long getBal(){
        return bal;
    }

    //method to update the balance of the customer after depositing the amount
    public void setBal(long amount){
        bal += amount;
    }

    //method to update the balance after withdrawing the amount
    public boolean withdraw(long amount){
        if(amount <= bal){
            bal -= amount;
            return true;
        }
        else{
            return false;
        }
    }
}
