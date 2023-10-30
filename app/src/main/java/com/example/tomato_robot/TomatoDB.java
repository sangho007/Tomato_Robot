package com.example.tomato_robot;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ClassTomato.class}, version = 1)
public abstract class TomatoDB extends RoomDatabase {
    private static TomatoDB INSTANCE = null;

    public abstract TomatoDao tomatoDao();

    public static TomatoDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TomatoDB.class, "classtomato.db")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // 여기서 초기 데이터 추가
                            // 새로운 스레드를 만들어서 비동기적으로 작업
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ClassTomato[] tomatoes = new ClassTomato[] {
                                            new ClassTomato("2023-06-07", "토마토", 350),
                                            new ClassTomato("2023-06-08", "토마토", 403),
                                            new ClassTomato("2023-06-10", "토마토", 456),
                                            new ClassTomato("2023-06-11", "토마토", 420),
                                            new ClassTomato("2023-06-13", "토마토", 280),
                                            new ClassTomato("2023-06-15", "토마토", 410),
                                            new ClassTomato("2023-06-19", "토마토", 440),
                                            new ClassTomato("2023-06-20", "토마토", 420)
                                    };
                                    INSTANCE.tomatoDao().insertAll(tomatoes);
                                }
                            }).start();
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

