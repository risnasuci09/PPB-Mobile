package com.myapplication.reportapps.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myapplication.reportapps.model.ModelDatabase;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM tbl_laporan")
    LiveData<List<ModelDatabase>> getAllReport();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(ModelDatabase... modelDatabases);

    @Query("DELETE FROM tbl_laporan")
    void deleteAllReport();

    @Query("DELETE FROM tbl_laporan WHERE uid= :uid")
    void deleteSingleReport(int uid);

}
