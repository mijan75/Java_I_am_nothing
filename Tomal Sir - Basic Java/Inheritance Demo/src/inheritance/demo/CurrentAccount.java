/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inheritance.demo;

/**
 *
 * @author kmhasan
 */
public class CurrentAccount {
    private int accountNumber;
    private String accountName;
    private double balance;
    private String address;

    public CurrentAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    void print() {
        System.out.printf("%d %s %.2f\n", accountNumber, accountName, balance);
    }
    
    public void deposit(double amount) {
        if (amount > 0)
            balance = balance + amount;
    }
    
    public void withdraw(double amount) {
        if (amount < balance && amount > 0)
            balance = balance - amount - 20;
    }
}
