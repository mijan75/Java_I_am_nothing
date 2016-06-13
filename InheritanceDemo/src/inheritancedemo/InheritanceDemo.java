/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritancedemo;

import java.util.Arrays;

/**
 *
 * @author mijan
 */
public class InheritanceDemo {
    public static Comparable findMax(Comparable Array[]){
        Comparable max = Array[0];
        for (int i=1;i<Array.length;i++)
        if (Array[i].compareTo(max)<0)
            max = Array[i];
        return max;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BankAccount account[] = new BankAccount[5];
       
        account[0] = new BankAccount("Mijanur Rahaman",500,5000);
        account[1] = new BankAccount("Mim Mosaddeak",400,4000);
        account[2] = new BankAccount("Rasib Sarkar",300,3000);
        account[3] = new BankAccount("Rokon Jahan Noor",200,2000);
        account[4] = new BankAccount("Atik Ahmed Anik",100,1000);
        
        Sort sort = new Sort();
        
        Arrays.sort(account,sort);
        for (BankAccount s : account)
            s.print();
        System.out.printf("MAX : %s\n",findMax(account));
       
        
    }
    
}
