package com.mobile.finalprojekbetaa;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class,SampahEntity.class},version = 2)
public abstract class Databaseapp extends RoomDatabase {

    public abstract UserDao userDao();

}
