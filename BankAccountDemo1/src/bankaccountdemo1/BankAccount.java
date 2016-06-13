/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountdemo1;

/**
 *
 * @author mijan
 */
public class BankAccount {
    private String accountName;
    private int accountID;
    private double balance;

    public BankAccount(String name,int id,double startingbalance){
        accountName=name;
        accountID=id;
        balance=startingbalance;
    }
    
    public double getBalnce(){
        return balance;
    }
    
    public void withdraw(int balanceAmount){
        if (balance<=balanceAmount && balanceAmount>0)
        balance=balance-balanceAmount;
    }
    
    public void deposit(int balanceAmount){
        if(balanceAmount>0)
            balance=balance+balanceAmount;
    }
    
    public void print(){
        System.out.printf("%s %d %.2f\n",accountName,accountID,balance);
    }
    
    public void name(String newName){
        accountName=newName;
    }
}
