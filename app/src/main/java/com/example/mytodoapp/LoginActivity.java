package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn ;
    TextView createBtn;
    EditText userEmail;
    EditText userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = this.findViewById(R.id.loginbtn);
        createBtn = this.findViewById(R.id.signupbtn);
        userEmail = this.findViewById(R.id.edituseremail);
        userPassword = this.findViewById(R.id.edituserpassword);

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(userEmail.getText().toString().equals("") || userPassword.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter your email and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserApp user;
                    MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(LoginActivity.this);
                    user = dataBaseHelper.getUser(userEmail.getText().toString(),userPassword.getText().toString());

                    if(user==null){
                        Toast.makeText(LoginActivity.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(LoginActivity.this,UserHome.class);
                        intent.putExtra("user",user);
                        startActivity(intent);

                    }
                }
            }
        });

        this.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,CreateAccout.class);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }
}