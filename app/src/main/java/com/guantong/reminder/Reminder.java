package com.guantong.reminder;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import static android.os.Parcelable.*;

/**
 * Created by Alien on 4/16/2015.
 */
public class Reminder implements Serializable {
    private String title;
    private String description;
    private Date dueDate;
    private Boolean completed;

    public Reminder() {
        this.completed = false;
        this.dueDate = null;
        this.title = "unknown";
        this.description = "unknown";
    }

    public Reminder(String title, String description, Date dueDate, Boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title;
    }
}
