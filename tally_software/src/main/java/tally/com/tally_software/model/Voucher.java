package tally.com.tally_software.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "voucher_name")
    private String voucherName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;
    private String created_by;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private DevVoucher devVoucher;

    public Voucher() {
    }

    public Voucher(String voucherName, Date created_time, String created_by, Company company, DevVoucher devVoucher) {
        this.voucherName = voucherName;
        this.created_time = created_time;
        this.created_by = created_by;
        this.company = company;
        this.devVoucher = devVoucher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DevVoucher getDevVoucher() {
        return devVoucher;
    }

    public void setDevVoucher(DevVoucher devVoucher) {
        this.devVoucher = devVoucher;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", voucherName='" + voucherName + '\'' +
                ", created_time=" + created_time +
                ", created_by='" + created_by + '\'' +
                ", company=" + company +
                ", devVoucher=" + devVoucher +
                '}';
    }
}


