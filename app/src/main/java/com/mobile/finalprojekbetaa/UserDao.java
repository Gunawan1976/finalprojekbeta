package com.mobile.finalprojekbetaa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<UserEntity> getAll();
    @Query(" SELECT * FROM  user  where nama=(:nama)and password =(:password)")
    UserEntity login (String nama, String password);
    @Query("SELECT * FROM user WHERE id LIKE :laporId LIMIT 1")
    UserEntity findById(int laporId);

    @Query("SELECT * FROM usersampah")
    List<SampahEntity> getall();
    @Query("SELECT * FROM usersampah where id LIKE :sampahid LIMIT 1")
    SampahEntity findbyid(int sampahid);
    @Delete
    void delete(UserEntity userEntity);

    @Insert
    void registerdao(UserEntity userEntity);

    @Query("SELECT * FROM usersampah")
    List<SampahEntity>getsemua();

    @Update
    void update (SampahEntity sampahEntity);

    @Insert
    void insertdata(SampahEntity sampahEntity);

    @Delete
    void delete (SampahEntity sampahEntity);
}
