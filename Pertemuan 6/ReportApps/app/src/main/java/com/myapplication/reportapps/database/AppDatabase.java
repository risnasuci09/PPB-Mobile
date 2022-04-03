package com.myapplication.reportapps.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.myapplication.reportapps.dao.DatabaseDao;
import com.myapplication.reportapps.model.ModelDatabase;

@Database(entities = {ModelDatabase.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
