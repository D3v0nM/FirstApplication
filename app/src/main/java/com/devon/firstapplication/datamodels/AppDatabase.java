package com.devon.firstapplication.datamodels;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.devon.firstapplication.dao.SettingsDao;
import com.devon.firstapplication.entity.Settings;


    @Database(entities = {Settings.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract SettingsDao settingsDao();
    }

