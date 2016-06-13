/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountdemo;

/**
 *
 * @author mijan
 */
public class BankAccount {
    private final int accountNumber;
    private final String accountName;
    private double balance;
    
    public BankAccount(int Number,String Name,double amount){
        accountName = Name;
        accountNumber = Number;
        balance = amount;
    }
    public double getBalance(){
        return balance;
    }
    
    public void withdraw(int amount){
        if (amount<=balance && amount>0)
        balance=balance-amount;
    }
    public void deposit(int amount){
        if (amount>0)
        balance=balance+amount;
    }
    public void print(){
         System.out.printf("%d %s %f\n",accountNumber,accountName,balance);
    }
}
