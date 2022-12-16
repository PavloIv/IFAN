package com.ip.ifan.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserStorage.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserStorageDao userStorageDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"user_storage")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
