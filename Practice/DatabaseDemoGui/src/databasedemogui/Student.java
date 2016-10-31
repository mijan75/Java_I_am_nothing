/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasedemogui;

/**
 *
 * @author mijan
 */
public class Student {
    int id;
    String name;
    double balance;

    public Student(int id, String name, double balance) {
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

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", balance=" + balance + '}';
    }
    
}
