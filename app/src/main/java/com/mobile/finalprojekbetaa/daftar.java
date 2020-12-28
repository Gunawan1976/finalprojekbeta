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

    private userDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        etnama = findViewById(R.id.etnama);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        etkonpassword = findViewById(R.id.etkonpassword);
        daftar = findViewById(R.id.daftar);

        dao = Room.databaseBuilder(this, com.mobile.finalprojekbetaa.Databaseapp.class,"databaselog")
                .allowMainThreadQueries().build().userDao();
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etnama.getText().toString().trim();
                String email = etemail.getText().toString().trim();
                String pass = etpassword.getText().toString().trim();
                String konpass = etkonpassword.getText().toString().trim();

                if (pass.equals(konpass)){
                    userEntity userEntity = new userEntity(nama,email,pass);
                    dao.registerdao(userEntity);
                    Intent pindahlogin = new Intent(com.mobile.finalprojekbetaa.daftar.this, com.mobile.finalprojekbetaa.login.class);
                    startActivity(pindahlogin);
                }else {
                    Toast.makeText(com.mobile.finalprojekbetaa.daftar.this," salah lur",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}