package tally.com.tally_software.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DevVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int v_id;
    @Column(name = "v_name")
    private String voucherName;
    private int unique_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;
    private String created_by;

    public DevVoucher() {
    }

    public DevVoucher(String voucherName, int unique_id, Date created_time, String created_by) {
        this.voucherName = voucherName;
        this.unique_id = unique_id;
        this.created_time = created_time;
        this.created_by = created_by;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
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
}
