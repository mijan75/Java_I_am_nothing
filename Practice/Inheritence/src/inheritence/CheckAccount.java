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
public class CheckAccount extends BankAccount{
    
    public CheckAccount(String name, int id, double balance, String Address) {
        super(name, id, balance, Address);
    }  
}
