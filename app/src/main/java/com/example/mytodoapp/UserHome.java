package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserHome extends AppCompatActivity {

    ListView tutovList;
    ImageView bagBtn;
    ImageView profileBtn;
    TextView userName;
    ArrayList<Tutorial> tutoList = new ArrayList<Tutorial>();
    UserApp user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        userName = findViewById(R.id.userName);
        tutovList = findViewById(R.id.tutolist);
        bagBtn = findViewById(R.id.bagBtn);
        profileBtn = findViewById(R.id.userBtn);

        // get the user
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            user = (UserApp) extras.getSerializable("user");
        } else {
            user = (UserApp) savedInstanceState.getSerializable("user");
        }

        // To put the user Name at the top
        userName.setText(user.getUserName());


        tutoList.add(new Tutorial("Photoshop","Learn Photoshop",R.drawable.ps,R.drawable.imageps));
        tutoList.add(new Tutorial("Lightroom","Learn Lightroom",R.drawable.lr,R.drawable.imagelr));
        tutoList.add(new Tutorial("Illustrator","Learn Illustrator",R.drawable.ill,R.drawable.illimage));
        tutoList.add(new Tutorial("Premiere Pro","Learn Premium Pro",R.drawable.pr,R.drawable.pr));
        tutoList.add(new Tutorial("Adobe Stock","Learn Adobe Stock",R.drawable.st,R.drawable.st));

        TutorialAdapter tutoAdapter = new TutorialAdapter(this,tutoList);
        tutovList.setAdapter(tutoAdapter);



        // OnClickListeners ==>

        bagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this,UserBag.class);
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this,UserProfile.class);
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }
}