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
public class Inheritence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CheckAccount c = new CheckAccount("Mijan",1234,500,"Dhaka");
        SavingAccount s = new SavingAccount("Rashel",1478,500,"Bangladesh");
        c.withdraw(100);
        s.withdraw(100);
        c.print();
        s.print();
        
    }   
}
