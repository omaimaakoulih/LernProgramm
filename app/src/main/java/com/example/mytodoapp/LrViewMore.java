package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LrViewMore extends AppCompatActivity {

    Button lregisterBtn;
    UserApp user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr_view_more);

        lregisterBtn = findViewById(R.id.lrregisterbtn);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            user = (UserApp) extras.getSerializable("user");

        } else {
            user = (UserApp) savedInstanceState.getSerializable("user");
        }


        lregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(LrViewMore.this, RegisterForm.class);
                    intent.putExtra("FORMATION_TYPE", "Lightroom");
                    intent.putExtra("user", user);
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    overridePendingTransition(0,0);

            }
        });
    }
}