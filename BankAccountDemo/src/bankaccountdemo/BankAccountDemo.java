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
public class BankAccountDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BankAccount a = new BankAccount(7500,"Md.Mijanur Rahaman",2000);
        BankAccount b =new BankAccount (1000,"Md.Rezwanul Haque",3000);
        a.deposit(7300);
        b.withdraw(100);
        a.print();
        b.print();
    }
    
}
