package tally.com.tally_software.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class StockItems {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "item_name")
    private  String itemName;
    @ManyToOne
    private Company company;
    @ManyToOne
    private StockGroups stockGroups;
    @ManyToOne
    private Unit unit;

    private double quantity;
    private double rate;
    private double total;

    private String created_by;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    public StockItems() {
    }

    public StockItems(String itemName, Company company, StockGroups stockGroups, Unit unit, double quantity, double rate, double total, String created_by, Date created_time) {
        this.itemName = itemName;
        this.company = company;
        this.stockGroups = stockGroups;
        this.unit = unit;
        this.quantity = quantity;
        this.rate = rate;
        this.total = total;
        this.created_by = created_by;
        this.created_time = created_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public StockGroups getStockGroups() {
        return stockGroups;
    }

    public void setStockGroups(StockGroups stockGroups) {
        this.stockGroups = stockGroups;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        return "StockItems{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", company=" + company +
                ", stockGroups=" + stockGroups +
                ", unit=" + unit +
                ", quantity=" + quantity +
                ", rate=" + rate +
                ", total=" + total +
                ", created_by='" + created_by + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
