package com.ip.ifan.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_storage")
public class UserStorage {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "fact")
    public String fact;


}
