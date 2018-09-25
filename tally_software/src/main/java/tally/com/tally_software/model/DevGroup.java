package tally.com.tally_software.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class DevGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int g_id;
    @Column(name = "g_name")
    private String groupName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;
    private String created_by;

    private int uniqueGId;

    private int keyId;

    @OneToMany
    @JsonIgnore
    private List<Groups> groups;

    @OneToMany
    @JsonIgnore
    private List<Ledger> ledgers;

    public DevGroup() {
    }


    public DevGroup(String groupName, Date created_time, String created_by, int uniqueGId, int keyId, List<Groups> groups, List<Ledger> ledgers) {
        this.groupName = groupName;
        this.created_time = created_time;
        this.created_by = created_by;
        this.uniqueGId = uniqueGId;
        this.keyId = keyId;
        this.groups = groups;
        this.ledgers = ledgers;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public List<Ledger> getLedgers() {
        return ledgers;
    }

    public void setLedgers(List<Ledger> ledgers) {
        this.ledgers = ledgers;
    }

    public int getUniqueGId() {
        return uniqueGId;
    }

    public void setUniqueGId(int uniqueGId) {
        this.uniqueGId = uniqueGId;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "DevGroup{" +
                "g_id=" + g_id +
                ", groupName='" + groupName + '\'' +
                ", created_time=" + created_time +
                ", created_by='" + created_by + '\'' +
                ", uniqueGId='" + uniqueGId + '\'' +
                ", groups=" + groups +
                '}';
    }
}
