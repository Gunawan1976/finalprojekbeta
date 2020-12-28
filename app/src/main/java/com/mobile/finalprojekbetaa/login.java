package com.mobile.finalprojekbetaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mobile.finalprojekbetaa.ui.home.HomeFragment;

public class login extends AppCompatActivity {
    EditText idnama, pass;
    TextView daftarr;
    Button login;
    userDao dao;
    com.mobile.finalprojekbetaa.Databaseapp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idnama = findViewById(R.id.idnama);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.loginn);
        daftarr = findViewById(R.id.daftar);

        db = Room.databaseBuilder(this, com.mobile.finalprojekbetaa.Databaseapp.class,"databaselog")
                .allowMainThreadQueries().build();
        dao = db.userDao();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nama = idnama.getText().toString().trim();
                String password = pass.getText().toString().trim();

                com.mobile.finalprojekbetaa.userEntity userEntity = dao.login(nama,password);
                if (userEntity != null){
                    Intent i = new Intent(com.mobile.finalprojekbetaa.login.this, bottomnav.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(com.mobile.finalprojekbetaa.login.this," salah atau tidak terdaftar ",Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        daftarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(com.mobile.finalprojekbetaa.login.this, com.mobile.finalprojekbetaa.daftar.class);
                startActivity(a);
            }
        });
    }
}