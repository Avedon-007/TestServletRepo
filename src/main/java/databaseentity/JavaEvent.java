package databaseentity;


import java.io.Serializable;
import java.sql.Date;


public class JavaEvent implements Serializable {
    private int id;
    private String title;
    private String description;
    private Date dateOfEvent;

    public JavaEvent() {
    }

    public JavaEvent(int id) {
        this.id = id;
    }

    public JavaEvent(int id, String title, String description, Date dateOfEvent) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateOfEvent = dateOfEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }
}
