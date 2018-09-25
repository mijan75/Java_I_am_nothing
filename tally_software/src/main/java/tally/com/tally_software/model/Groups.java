package tally.com.tally_software.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "group_name")
    private String groupName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;
    private String created_by;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JsonIgnore
    private DevGroup devGroup;

    public Groups() {
    }

    public Groups(String groupName, Date created_time, String created_by, Company company, DevGroup devGroup) {
        this.groupName = groupName;
        this.created_time = created_time;
        this.created_by = created_by;
        this.company = company;
        this.devGroup = devGroup;
    }

    public DevGroup getDevGroup() {
        return devGroup;
    }

    public void setDevGroup(DevGroup devGroup) {
        this.devGroup = devGroup;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
}
