package com.mobile.finalprojekbetaa;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface userDao {
    @Query("SELECT * FROM user")
    List<com.mobile.finalprojekbetaa.userEntity> getAll();
    @Query(" SELECT * FROM  user  where nama=(:nama)and password =(:password)")
    com.mobile.finalprojekbetaa.userEntity login (String nama, String password);

    @Insert
    void registerdao(com.mobile.finalprojekbetaa.userEntity userEntity);
}
