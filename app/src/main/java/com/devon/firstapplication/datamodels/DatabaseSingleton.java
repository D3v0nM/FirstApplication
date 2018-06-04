package com.devon.firstapplication.datamodels;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseSingleton {

    public static AppDatabase db;

    public static AppDatabase getDb(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, AppDatabase.class, "local-database").allowMainThreadQueries().build();
            //.addMigrations("Migration name)
        }
        return db;
    }
}

//Add migrations method here