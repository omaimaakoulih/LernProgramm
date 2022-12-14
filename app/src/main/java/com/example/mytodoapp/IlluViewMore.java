package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IlluViewMore extends AppCompatActivity {

    Button egisterBtn;
    UserApp user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illu_view_more);

        egisterBtn = findViewById(R.id.illregisterbtn);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            user = (UserApp) extras.getSerializable("user");

        } else {
            user = (UserApp) savedInstanceState.getSerializable("user");
        }

        egisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IlluViewMore.this,RegisterForm.class);
                intent.putExtra("FORMATION_TYPE","Illustartor");
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}