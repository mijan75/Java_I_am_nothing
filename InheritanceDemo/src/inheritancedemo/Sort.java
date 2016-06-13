/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritancedemo;

import java.util.Comparator;



/**
 *
 * @author mijan
 */
public class Sort implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        BankAccount s1 = (BankAccount) o1;
        BankAccount s2 = (BankAccount) o2;
        if (s1.getAccountId()<s2.getAccountId())
            return -1;
        else if (s1.getAccountId()>s2.getAccountId())
            return +1;
        else return 0;
    }
    
}
