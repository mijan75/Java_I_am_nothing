/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

/**
 *
 * @author mijan
 */
public class BankAccount implements Comparable{
    int id;
    String name;
    double balance;

    public BankAccount() {
    }

    public BankAccount(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void print(){
        System.out.println(id+" "+ name+" "+ balance);
    }

    @Override
    public String toString() {
        return "BankAccount{" + "id=" + id + ", name=" + name + ", balance=" + balance + '}';
    }
    
    
    
    @Override
    public int compareTo(Object o) {
        BankAccount b1 = this;
        BankAccount b2 = (BankAccount) o;
        if(b1.getId() < b2.getId())
            return -1;
        else if(b1.getId() > b2.getId()) return +1;
        else return 0;
    }
}
