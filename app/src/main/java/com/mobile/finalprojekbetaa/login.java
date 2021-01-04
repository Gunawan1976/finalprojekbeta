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

public class login extends AppCompatActivity {
    EditText idnama, pass;
    TextView daftarr;
    Button login;
    UserDao dao;
    Databaseapp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idnama = findViewById(R.id.idnama);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.loginn);
        daftarr = findViewById(R.id.daftar);

        db = Room.databaseBuilder(this, Databaseapp.class,"databaselog")
                .allowMainThreadQueries().build();
        dao = db.userDao();

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nama = idnama.getText().toString().trim();
                String password = pass.getText().toString().trim();

                UserEntity userEntity = dao.login(nama,password);
                if (userEntity != null){
                    Intent i = new Intent(login.this, bottomnav.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(login.this," salah atau tidak terdaftar ",Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        daftarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(login.this, daftar.class);
                startActivity(a);
            }
        });
    }
}