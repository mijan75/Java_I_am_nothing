package tally.com.tally_software.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(name = "user_comment")
    private String userComment;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;


    public UserComment() {
    }

    public UserComment(String name, String userComment, Date created_time) {
        this.name = name;
        this.userComment = userComment;
        this.created_time = created_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userComment='" + userComment + '\'' +
                '}';
    }
}
