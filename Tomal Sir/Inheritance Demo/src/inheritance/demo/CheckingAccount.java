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
public class CheckingAccount extends BankAccount {

    public CheckingAccount(int accountNumber, String accountName, double balance) {
        super(accountNumber, accountName, balance);
    }
    
    @Override
    public void withdraw(double amount) {
//        if (amount < balance && amount > 0)
//            balance = balance - amount - 20;
        super.withdraw(amount + 20);
    }
}
