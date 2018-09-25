package tally.com.tally_software.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int company_id;
    private String company_name;
    private String address;
    private String phone_number;
    private String c_email;
    private String startdate;
    private String enddate;

    @Lob
    private byte[] file;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "name")
    private List<User> user;
    private String created_by;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    @OneToMany
    @JsonIgnore
    private List<Groups> groups;


    @OneToMany
    @JsonIgnore
    private List<Ledger> ledgers;

    private String username;

    public Company() {
    }

    public List<Ledger> getLedgers() {
        return ledgers;
    }

    public void setLedgers(List<Ledger> ledgers) {
        this.ledgers = ledgers;
    }

    public Company(String company_name, String address, String phone_number, String c_email, String startdate, String enddate, byte[] file, List<User> user, String created_by, Date created_time, List<Groups> groups, List<Ledger> ledgers, String username) {
        this.company_name = company_name;
        this.address = address;
        this.phone_number = phone_number;
        this.c_email = c_email;
        this.startdate = startdate;
        this.enddate = enddate;
        this.file = file;
        this.user = user;
        this.created_by = created_by;
        this.created_time = created_time;
        this.groups = groups;
        this.ledgers = ledgers;
        this.username = username;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<User> getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", c_email='" + c_email + '\'' +
                ", user=" + user +
                ", created_by='" + created_by + '\'' +
                ", created_time=" + created_time +
                ", ledgers=" + ledgers +
                ", username='" + username + '\'' +
                '}';
    }
}
