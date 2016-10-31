/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.util.Arrays;

/**
 *
 * @author mijan
 */
public class Interface {
    
    public static Comparable findMax(Comparable []array){
        Comparable max = array[0];
        for(int i=1; i< array.length; i++){
            if(array[i].compareTo(max)<0)
                max = array[i];
        }
        return max;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BankAccount accounts [] = new BankAccount[5];
        accounts[0] = new BankAccount(7,"Mijan",500);
        accounts[1] = new BankAccount(10,"Rezwan",400);
        accounts[2] = new BankAccount(1,"Mim",900);
        accounts[3] = new BankAccount(11,"Rasib",200);
        accounts[4] = new BankAccount(5,"Rokon",300);
        
        //Sort sort = new Sort();
        Arrays.sort(accounts);
        for(BankAccount b : accounts)
            b.print();
        System.out.printf("%s",findMax(accounts).toString());
    }
    
}
