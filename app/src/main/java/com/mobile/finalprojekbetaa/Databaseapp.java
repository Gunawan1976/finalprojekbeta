package com.mobile.finalprojekbetaa;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {com.mobile.finalprojekbetaa.userEntity.class},version = 1)
public abstract class Databaseapp extends RoomDatabase {
    private static final String dbname = "user";
    private static com.mobile.finalprojekbetaa.Databaseapp databaseapp;

    public static synchronized com.mobile.finalprojekbetaa.Databaseapp getDatabaseapp(Context context){
        if (databaseapp==null){
            databaseapp= Room.databaseBuilder(context, com.mobile.finalprojekbetaa.Databaseapp.class, dbname)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseapp;
    }
    public abstract userDao userDao();
}
