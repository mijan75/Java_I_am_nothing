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
public class BankAccountDemo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BankAccount a = new BankAccount("Mijanur Rahaman",2014,7500);
        BankAccount b = new BankAccount("Rasib Sarkar",2014,7000);
        a.getBalnce();
        b.getBalnce();
        a.print();
        b.print();
        a.deposit(-100);
        a.print();
        b.name("Mijanur Rahaman Don");
        b.print();
    }
    
}
