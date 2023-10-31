package com.example.tomato_robot;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TomatoDao {
    @Query("SELECT * FROM classtomato ORDER BY date DESC")
    List<ClassTomato> getAll();


    @Query("SELECT * FROM classtomato WHERE date IN (:in_date)")
    List<ClassTomato> loadAllByDate(int[] in_date);

    @Insert
    void insertAll(ClassTomato... classTomatoes);

    @Delete
    void delete(ClassTomato classTomato);
}
