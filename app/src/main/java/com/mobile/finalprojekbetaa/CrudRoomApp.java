package com.mobile.finalprojekbetaa;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class CrudRoomApp extends Application {

    private static CrudRoomApp INSTANCE;
    private Databaseapp dataBase;
    private Context context;

    public static CrudRoomApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(context.getApplicationContext(), Databaseapp.class, "database_log.db")
                .addMigrations(DataBaseMigrations.MIGRATION_1_TO_2)
                .allowMainThreadQueries()
                .build();

        INSTANCE = this;
    }

    public Databaseapp getDataBase() {
        return dataBase;
    }

}
