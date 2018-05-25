package com.devon.firstapplication.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Settings {

 @NonNull
@PrimaryKey

    private String id = "0";

 @ColumnInfo(name = "reminder_time")
    private String reminderTime;

 @ColumnInfo(name = "gender")
    private String gender;

 @ColumnInfo(name = "age_start")
    private int ageStart;

 @ColumnInfo(name = "age_end")
    private int ageEnd;

 @ColumnInfo(name = "status")
    private boolean status = true;

 @ColumnInfo(name = "max_distance")
    private String maxDist;

 public Settings(){

 }


    public Settings(@NonNull String id, String reminderTime, String gender, int ageStart,

                    int ageEnd, boolean status, String maxDist) {
        this.id = id;
        this.reminderTime = reminderTime;
        this.gender = gender;
        this.ageStart = ageStart;
        this.ageEnd = ageEnd;
        this.status = status;
        this.maxDist = maxDist;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMaxDist() {
        return maxDist;
    }

    public void setMaxDist(String maxDist) {
        this.maxDist = maxDist;
    }

    public int getAgeStart(){
        return ageStart;
    }

    public void setAgeStart(int ageStart){
        this.ageStart = ageStart;
    }

    public int getAgeEnd(){
        return ageEnd;
    }

    public void setAgeEnd(int ageEnd) {
        this.ageEnd = ageEnd;
    }
}
