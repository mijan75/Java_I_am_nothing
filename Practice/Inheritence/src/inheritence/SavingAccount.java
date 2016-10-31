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
public class SavingAccount extends BankAccount{
    
    public SavingAccount(String name, int id, double balance, String Address) {
        super(name, id, balance, Address);
    }
    @Override
    public void withdraw(double amount){
        super.withdraw(amount + 20);
    }
            
}
