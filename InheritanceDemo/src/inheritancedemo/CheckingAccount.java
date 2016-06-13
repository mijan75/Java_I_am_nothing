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
public class CheckingAccount extends BankAccount{
    
    public CheckingAccount(String accountName, int accountId, double balance) {
        super(accountName, accountId, balance);
    }
    @Override
    public void withdraw(int amount){
        super.withdraw(amount+ 20);
    }
}
