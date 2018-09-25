package tally.com.tally_software.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ledger_id;
    @Column(name = "ledger_name")
    private String ledgerName;

    private String mailing_name;
    private String address;
    @Email
    private String email;

//    @ManyToOne
//    @JsonIgnore
//    private Groups groups;
    @ManyToOne
    @JsonIgnore
    private DevGroup devGroup;

    @OneToMany
    private List<AccountingVoucher> accountingVoucher;
    @ManyToOne
    @JsonIgnore
    private Company company;

    public Ledger() {
    }

    public Ledger(String ledgerName, String mailing_name, String address, @Email String email, DevGroup devGroup, List<AccountingVoucher> accountingVoucher, Company company) {
        this.ledgerName = ledgerName;
        this.mailing_name = mailing_name;
        this.address = address;
        this.email = email;
        this.devGroup = devGroup;
        this.accountingVoucher = accountingVoucher;
        this.company = company;
    }

    public DevGroup getDevGroup() {
        return devGroup;
    }

    public void setDevGroup(DevGroup devGroup) {
        this.devGroup = devGroup;
    }

    public int getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(int ledger_id) {
        this.ledger_id = ledger_id;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getMailing_name() {
        return mailing_name;
    }

    public void setMailing_name(String mailing_name) {
        this.mailing_name = mailing_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<AccountingVoucher> getAccountingVoucher() {
        return accountingVoucher;
    }

    public void setAccountingVoucher(List<AccountingVoucher> accountingVoucher) {
        this.accountingVoucher = accountingVoucher;
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "ledger_id=" + ledger_id +
                ", ledger_name='" + ledgerName + '\'' +
                ", mailing_name='" + mailing_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +

                ", company=" + company +
                '}';
    }
}
