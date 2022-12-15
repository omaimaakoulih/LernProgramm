package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccout extends AppCompatActivity {

    EditText userName;
    EditText userEmail;
    EditText userPassword;
    EditText confirm;
    TextView loginBtn;
    Button createBtn;
    UserApp user;
    UserApp userTmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_accout);

        userName = this.findViewById(R.id.userName);
        userEmail = this.findViewById(R.id.userEmail);
        userPassword = this.findViewById(R.id.userpassword);
        confirm = this.findViewById(R.id.confirmpassword);

        createBtn = this.findViewById(R.id.create);
        loginBtn = this.findViewById(R.id.loginTextBtn);

        // create a new user account
        this.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userName.getText().toString().equals("") || userEmail.getText().toString().equals("") || userPassword.getText().toString().equals("") || confirm.getText().toString().equals("")){
                    Toast.makeText(CreateAccout.this, "All Fields are required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!userPassword.getText().toString().equals(confirm.getText().toString())){
                        Toast.makeText(CreateAccout.this, "Please Confirm your password!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        MyDataBaseHelper dbHelper = new MyDataBaseHelper(CreateAccout.this);

                        if(dbHelper.ifExists(userName.getText().toString(),userEmail.getText().toString())){
                            Toast.makeText(CreateAccout.this, "User with the same Username or Email exist!", Toast.LENGTH_SHORT).show();
                        }
                        else {


                            user = new UserApp(userName.getText().toString(), userEmail.getText().toString(), userPassword.getText().toString());
                            dbHelper.addUser(user);


                            Toast.makeText(CreateAccout.this, "user added", Toast.LENGTH_SHORT).show();

                            userTmp = dbHelper.getUser(user.getUserEmail(),user.getUserPasswd());

                            Intent intent = new Intent(CreateAccout.this,UserHome.class);
                            intent.putExtra("user",userTmp);
                            startActivity(intent);

                        }
                    }
                }

            }
        });

        // to start the Login activity
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateAccout.this,LoginActivity.class);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


    }
}