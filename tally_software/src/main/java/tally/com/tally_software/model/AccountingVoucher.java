package tally.com.tally_software.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AccountingVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

//    @ManyToOne
//    private DevVoucher devVoucher;
//    @ManyToOne
//    private Ledger ledger;
    public int ref;

    private String voucherName;
    @ManyToOne
    private StockItems stockItems;

    private double quantity;
    private double rate;
    @ManyToOne
    private Unit unit;

    public int invoice;

    @ManyToOne
    public Ledger debitLedger;
    @ManyToOne
    public Ledger creditLedger;

    public String creditId;
    public String debitId;

    private double debit;
    private double credit;
    private String narration;
    private double total;
    private String created_time;

    private String created_by;
    @ManyToOne
    private Company company;

    public AccountingVoucher(int ref, String voucherName, StockItems stockItems, double quantity, double rate, Unit unit, int invoice, Ledger debitLedger, Ledger creditLedger, String creditId, String debitId, double debit, double credit, String narration, double total, String created_time, String created_by, Company company) {
        this.ref = ref;
        this.voucherName = voucherName;
        this.stockItems = stockItems;
        this.quantity = quantity;
        this.rate = rate;
        this.unit = unit;
        this.invoice = invoice;
        this.debitLedger = debitLedger;
        this.creditLedger = creditLedger;
        this.creditId = creditId;
        this.debitId = debitId;
        this.debit = debit;
        this.credit = credit;
        this.narration = narration;
        this.total = total;
        this.created_time = created_time;
        this.created_by = created_by;
        this.company = company;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDebitId() {
        return debitId;
    }

    public void setDebitId(String debitId) {
        this.debitId = debitId;
    }

    public AccountingVoucher(int creditLedger) {
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public AccountingVoucher() {
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public StockItems getStockItems() {
        return stockItems;
    }

    public void setStockItems(StockItems stockItems) {
        this.stockItems = stockItems;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "AccountingVoucher{" +
                "id=" + id +
                ", ref=" + ref +
                ", voucherName='" + voucherName + '\'' +
                ", stockItems=" + stockItems +
                ", quantity=" + quantity +
                ", rate=" + rate +
                ", unit=" + unit +
                ", invoice=" + invoice +
                ", debitLedger=" + debitLedger +
                ", creditLedger=" + creditLedger +
                ", creditId='" + creditId + '\'' +
                ", debitId='" + debitId + '\'' +
                ", debit=" + debit +
                ", credit=" + credit +
                ", narration='" + narration + '\'' +
                ", total=" + total +
                ", created_time='" + created_time + '\'' +
                ", created_by='" + created_by + '\'' +
                ", company=" + company +
                '}';
    }

    public Ledger getDebitLedger() {
        return debitLedger;
    }

    public void setDebitLedger(Ledger debitLedger) {
        this.debitLedger = debitLedger;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ledger getCreditLedger() {
        return creditLedger;
    }

    public void setCreditLedger(Ledger creditLedger) {
        this.creditLedger = creditLedger;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountingVoucher that = (AccountingVoucher) o;
        return Objects.equals(creditId, that.creditId) &&
                Objects.equals(debitId, that.debitId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(creditId, debitId);
    }
}
