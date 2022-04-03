package com.myapplication.reportapps.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient dcInstance;
    AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "laporan_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (dcInstance == null) {
            dcInstance = new DatabaseClient(context);
        }
        return dcInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
