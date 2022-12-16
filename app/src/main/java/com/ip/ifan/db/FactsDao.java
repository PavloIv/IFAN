package com.ip.ifan.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserStorageDao {

    @Query("SELECT * FROM user_storage")
    List<UserStorage> getUserStorage();

    @Insert
    void insertFactToStorage(UserStorage... userStorages);
}
