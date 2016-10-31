/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritence;

/**
 *
 * @author mijan
 */
public class BankAccount {
    String name;
    int id;
    double balance;
    String Address;

    public BankAccount(String name, int id, double balance, String Address) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.Address = Address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    public void print(){
        System.out.println(name + " "+id + " "+ balance +" "+ Address);
    }
    public void withdraw(double amount){
        balance = balance - amount;
    }
            
}
