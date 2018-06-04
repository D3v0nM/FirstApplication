package com.devon.firstapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.devon.firstapplication.entity.Settings;

import java.util.List;

    @Dao
    public interface SettingsDao{
        @Query("Select * FROM settings")
        List<Settings> getAll();

        @Query("SELECT * FROM settings WHERE id IN (:ids)")
        List<Settings> loadAllByIds(String ids);

        //extra code
//        @Query("SELECT reminder_time FROM settings")
//        Settings  getReminder(String reminderTime);
//
//        @Query("SELECT gender FROM settings")
//        Settings getGender(String gender);
//
//        @Query("SELECT status FROM settings")
//        Settings getStatus(String status);
//
//   @Query("SELECT max_distance FROM settings")
//       Settings getMaxDist (String maxDist);
//
//        @Update
//        void updateSettings(Settings...settings);

        @Update
        void updateSettings(Settings...settings);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAll(Settings...settings);

        @Delete
        void delete(Settings settings);



    }


