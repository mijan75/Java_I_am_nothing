/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritancedemo;

/**
 *
 * @author mijan
 */
public class BankAccount implements Comparable{
    private String accountName;
    private int accountId;
    private double balance;

    public BankAccount(String accountName, int accountId, double balance) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }
    
    public void deposit(int amount){
        if (amount >0)
            balance = balance +amount;
    }
    
    public void withdraw(int amount){
        if (amount >0 && amount<balance)
            balance = balance - amount;
    }
    
    public void print(){
        System.out.printf("%d %s %.2f\n",accountId,accountName,balance);
    }

   @Override
    public String toString() {
        return "BankAccount{" + "accountName=" + accountName + ", accountId=" + accountId + ", balance=" + balance + '}';
    }

    @Override
    public int compareTo(Object o) {
        BankAccount b1 = this;
        BankAccount b2 = (BankAccount) o;
        if (b1.getBalance()<b2.getBalance())
            return +1;
        else if (b1.getBalance()>b2.getBalance())
            return -1;
        else return 0;
    }
    
    
}
