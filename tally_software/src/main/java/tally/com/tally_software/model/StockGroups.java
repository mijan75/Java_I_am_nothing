package tally.com.tally_software.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StockGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "s_g_name")
    private String stockGroupName;

    private String created_by;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    @ManyToOne
    private Company company;

    public StockGroups() {
    }


    public StockGroups(String stockGroupName, String created_by, Date created_time, Company company) {
        this.stockGroupName = stockGroupName;
        this.created_by = created_by;
        this.created_time = created_time;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockGroupName() {
        return stockGroupName;
    }

    public void setStockGroupName(String stockGroupName) {
        this.stockGroupName = stockGroupName;
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
        return "StockGroups{" +
                "id=" + id +
                ", stockGroupName='" + stockGroupName + '\'' +
                ", created_by='" + created_by + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
