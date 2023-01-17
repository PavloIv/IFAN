package com.ip.ifan.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "facts")
public  class Facts {
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name =  "fact")
    private String fact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
