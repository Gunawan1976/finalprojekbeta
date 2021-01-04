package com.mobile.finalprojekbetaa;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usersampah")
public class SampahEntity {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "judul")
    public String judul;

    @ColumnInfo(name = "deskripsi")
    public String deskripsi;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "gambar")
    private String gambar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
