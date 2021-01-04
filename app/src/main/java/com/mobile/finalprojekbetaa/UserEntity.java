package com.mobile.finalprojekbetaa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nama;
    private String password;
    private String email;

    public UserEntity(String nama, String email, String password) {
        this.nama = nama;
        this.email = email;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ColumnInfo(name = "userid")
    String Userid;

    public void setUsernameid() {
    }
}
