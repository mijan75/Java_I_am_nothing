/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.account.demo;

/**
 *
 * @author kmhasan
 */
public class BankAccountDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankAccount a; // declaring an object "a" of "BankAccount" class
        a = new BankAccount(5542, "Md. Rakibul Hasan", 40000); // we are instantiating the object; the JVM will allocate memory
       
        BankAccount b = new BankAccount(5462, "Hasan Tareque", 25000);
        
        a.withdraw(100000);
        a.withdraw(-100000);
        b.deposit(-500);
        b.withdraw(5000);
        
        a.print();
        b.print();
    }
    
}
