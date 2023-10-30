package com.example.tomato_robot;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ClassTomato {
    @PrimaryKey(autoGenerate = true)
    public int num;
    @ColumnInfo(name="date")
    public String date;
    @ColumnInfo(name="type")
    public String type;
    @ColumnInfo(name="weight")
    public int weight;

    public ClassTomato(String date, String type, int weight) {
        this.date = date;
        this.type = type;
        this.weight = weight;
    }

    public String getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    public int getWeight() {
        return this.weight;
    }
}


