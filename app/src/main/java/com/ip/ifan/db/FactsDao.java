package com.ip.ifan.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FactsDao {
    @Insert
    void saveFact(Facts fact);

    @Query("SELECT * FROM facts")
    Flowable<List<Facts>> getFacts();
}
