package com.mobile.finalprojekbetaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class daftar extends AppCompatActivity {
    EditText etnama ,etemail, etpassword , etkonpassword;
    Button daftar;
    Button masuk;

    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        etnama = findViewById(R.id.etnama);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        etkonpassword = findViewById(R.id.etkonpassword);
        daftar = findViewById(R.id.daftar);

        dao = Room.databaseBuilder(this, Databaseapp.class,"databaselog")
                .allowMainThreadQueries().build().userDao();
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etnama.getText().toString().trim();
                String email = etemail.getText().toString().trim();
                String pass = etpassword.getText().toString().trim();
                String konpass = etkonpassword.getText().toString().trim();

                if (pass.equals(konpass)){
                    UserEntity userEntity = new UserEntity(nama,email,pass);
                    dao.registerdao(userEntity);
                    Intent pindahlogin = new Intent(daftar.this, login.class);
                    startActivity(pindahlogin);
                }else {
                    Toast.makeText(daftar.this,"password tidak sama",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}