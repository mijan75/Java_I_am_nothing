/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytestsoft;

/**
 *
 * @author mijan
 */
public class UserClass {
    String name;
    String mail;
    String accountType;

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getAccountType() {
        return accountType;
    }

    public UserClass(String name, String mail, String accountType) {
        this.name = name;
        this.mail = mail;
        this.accountType = accountType;
    }
}
