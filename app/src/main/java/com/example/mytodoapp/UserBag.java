package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserBag extends AppCompatActivity {

    ListView usertutovList;
    ImageView home;
    ImageView profile;
    ArrayList<Tutorial> tutoArray = new ArrayList<Tutorial>();
    UserApp user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bag);

        usertutovList = (ListView) findViewById(R.id.usertutolist);
        home = findViewById(R.id.bagHomeBtn);
        profile = findViewById(R.id.bagProfileBtn);

        MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(UserBag.this);
        // get the user
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            user = (UserApp) extras.getSerializable("user");

        } else {
            user = (UserApp) savedInstanceState.getSerializable("user");
        }



        tutoArray = dataBaseHelper.userTutos(user.getId());
        if(tutoArray == null){
            Toast.makeText(this, "null!", Toast.LENGTH_SHORT).show();
        }
        else {
            UserBagAddapter bagAddapter = new UserBagAddapter(this, tutoArray);
            usertutovList.setAdapter(bagAddapter);
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBag.this,UserHome.class);
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserBag.this,UserProfile.class);
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }
}