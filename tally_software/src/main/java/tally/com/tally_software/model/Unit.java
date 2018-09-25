package tally.com.tally_software.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String symbolName;
    private String formalName;

    private String created_by;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    @ManyToOne
    private Company company;

    public Unit() {
    }

    public Unit(String symbolName, String formalName, String created_by, Date created_time, Company company) {
        this.symbolName = symbolName;
        this.formalName = formalName;
        this.created_by = created_by;
        this.created_time = created_time;
        this.company = company;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", symbolName='" + symbolName + '\'' +
                ", formalName='" + formalName + '\'' +
                ", created_by='" + created_by + '\'' +
                ", created_time=" + created_time +
                ", company=" + company +
                '}';
    }
}
