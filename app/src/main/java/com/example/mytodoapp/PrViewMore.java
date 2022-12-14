package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrViewMore extends AppCompatActivity {


    Button pregisterBtn;
    UserApp user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_view_more);

        pregisterBtn = findViewById(R.id.prregisterbtn);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            user = (UserApp) extras.getSerializable("user");

        } else {
            user = (UserApp) savedInstanceState.getSerializable("user");
        }

        pregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrViewMore.this,RegisterForm.class);
                intent.putExtra("FORMATION_TYPE", "Premiere Pro");
                intent.putExtra("user", user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}