/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.util.Comparator;

/**
 *
 * @author mijan
 */
public class Sort implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        BankAccount a = (BankAccount) o1;
        BankAccount b = (BankAccount) o2;
        
        if(a.getBalance() < b.getBalance())
            return -1;
        else if(a.getBalance() > b.getBalance())
            return +1;
        else return 0;
    }
    
}
