package com.example.mytodoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserProfile extends AppCompatActivity {

    EditText userName;
    EditText UserEmail;
    EditText UserPhone;
    EditText UserBirth;
    EditText password;
    EditText newPassword;
    Button save;
    ImageView home;
    ImageView bag;
    UserApp user;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        UserBirth = findViewById(R.id.profileuserbirth);
        userName = findViewById(R.id.profileusername);
        UserEmail = findViewById(R.id.profilemail);
        UserPhone = findViewById(R.id.profileuserphone);
        password = findViewById(R.id.profilepasswd);
        newPassword = findViewById(R.id.profilenewpasswd);
        save = findViewById(R.id.saveuserinfosbtn);

        home = (ImageView) findViewById(R.id.profileuserhome);
        bag = (ImageView) findViewById(R.id.profilebagBtn);

        MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(UserProfile.this);


        // get the user
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            user = (UserApp) extras.getSerializable("user");

        } else {
            user = (UserApp) savedInstanceState.getSerializable("user");
        }

        //user = dataBaseHelper.getUserbYiD(user.getId());
        userName.setText(dataBaseHelper.getUserbYiD(user.getId()).getUserName());
        UserEmail.setText(dataBaseHelper.getUserbYiD(user.getId()).getUserEmail());
        UserPhone.setText(dataBaseHelper.getUserbYiD(user.getId()).getPhoneNum());
        UserBirth.setText(dataBaseHelper.getUserbYiD(user.getId()).getBirthDay());




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setUserName(userName.getText().toString());
                user.setUserEmail(UserEmail.getText().toString());
                user.setBirthDay(UserBirth.getText().toString());
                user.setPhoneNum(UserPhone.getText().toString());

                if(!password.getText().toString().equals("") && !newPassword.getText().toString().equals("")){

                    if(!password.getText().toString().equals(dataBaseHelper.getUserbYiD(user.getId()).getUserPasswd())){
                        Toast.makeText(UserProfile.this, "The old password is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        user.setUserPasswd(newPassword.getText().toString());
                        dataBaseHelper.profileModif(user);
                        Toast.makeText(UserProfile.this, "Modify succussul", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    dataBaseHelper.profileModif(user);
                    Toast.makeText(UserProfile.this, user.getBirthDay(), Toast.LENGTH_SHORT).show();
                }

            }
        });



        // birthday calendar
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR,i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateEdit();
            }
        };

        UserBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UserProfile.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        // Home page
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this,UserHome.class);
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        // My bag page
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this,UserBag.class);
                intent.putExtra("user",user);
                overridePendingTransition(0,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(UserProfile.this,LoginActivity.class);
            finish();
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateEdit(){
        String format = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        UserBirth.setText(dateFormat.format(myCalendar.getTime()));
    }
}