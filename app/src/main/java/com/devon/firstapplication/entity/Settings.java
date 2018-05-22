package com.devon.firstapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Settings {

 @NonNull
@PrimaryKey

    private int id =1;

 @ColumnInfo(name = "reminder_time")
    private String reminderTime;

 @ColumnInfo(name = "gender")
    private String gender;

 @ColumnInfo(name = "status")
    private String status;

 @ColumnInfo(name = "max_distance")
    private int maxDist;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxDist() {
        return maxDist;
    }

    public void setMaxDist(int maxDist) {
        this.maxDist = maxDist;
    }
}
