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
public class InheritanceDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankAccount a = new BankAccount(101, "Monirul Hasan", 5000);
        a.deposit(200);
        a.withdraw(2000);
        a.print();
        
        CurrentAccount b = new CurrentAccount(102, "Shahriar Manzoor", 5000);
        b.deposit(200);
        b.withdraw(2000);
        b.print();

        CheckingAccount c = new CheckingAccount(103, "Md. Ashiqur Rahman", 5000);
        c.deposit(200);
        c.withdraw(2000);
        c.print();
    }
    
}
