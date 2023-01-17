package com.ip.ifan.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Facts.class}, version = 1)
public abstract class DatabaseFacts extends RoomDatabase {

    private static DatabaseFacts instance;

    public static DatabaseFacts getDatabase( Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    DatabaseFacts.class, "database_facts.db").build();
        }
        return instance;
    }


    public abstract FactsDao factsDao();
}
