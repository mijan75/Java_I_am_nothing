package tally.com.tally_software.model;



import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int feedback_id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String cellNumber;
    @NotNull
    private String userText;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_time;

    public Feedback() {
    }

    public Feedback(@NotNull String name, @NotNull String email, @NotNull String cellNumber, @NotNull String userText, Date created_time) {
        this.name = name;
        this.email = email;
        this.cellNumber = cellNumber;
        this.userText = userText;
        this.created_time = created_time;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedback_id=" + feedback_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                ", userText='" + userText + '\'' +
                ", created_time=" + created_time +
                '}';
    }
}
