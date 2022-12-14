package com.example.mytodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.templates.ControlButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RegisterForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String newString;

    String[] county={"Country","India","USA","Japan","Morroco","Other"};
    /*String[] city;
    String[] indiaCity = {"City","Bombay","Jaipur","Bangalore","Calcutta","Hyderbad"};
    String[] usaCity = {"City","New york","Chicago","Boston","Austin","San Angeles"};
    String[] japanCity = {"City","Tokyo","Osaka","Kobe","Kyoto","Sendai"};
    String[] morrocoCity = {"City","Oujda","Rabat","Fes","Marrakech","Sahara"};*/
    String[] allCitys = {"City","Bombay","Jaipur","Bangalore","Calcutta","Hyderbad","New york","Chicago","Boston","Austin","San Angeles","Tokyo","Osaka","Kobe","Kyoto","Sendai","Oujda","Rabat","Fes","Marakech","Sahara"};

    Spinner countySpinner;
    Spinner citySpinner;
    EditText formation;
    EditText birthDay;
    EditText userName;
    EditText userEmail;
    EditText phoneNum;
    CheckBox agree;
    Button fomrBtn;

    final Calendar myCalendar = Calendar.getInstance();
    ArrayAdapter<String> countries_adapter;

    UserApp user;
    String phone;
    ArrayList<Tutorial> tuto = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        formation = findViewById(R.id.formation);
        userName = findViewById(R.id.formusername);
        userEmail = findViewById(R.id.formuseremail);
        countySpinner =findViewById(R.id.countyspinner);
        citySpinner = findViewById(R.id.cityspinner);
        birthDay = findViewById(R.id.birthday);
        phoneNum = findViewById(R.id.formphonenum);
        agree = findViewById(R.id.agree);
        fomrBtn = findViewById(R.id.formregisterbtn);

        // get the user
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            newString = extras.getString("FORMATION_TYPE");
            user = (UserApp) extras.getSerializable("user");

        } else {
            newString = (String) savedInstanceState.getSerializable("FORMATION_TYPE");
            user = (UserApp) savedInstanceState.getSerializable("user");
        }




        formation.setText(newString);
        userName.setText(user.getUserName());
        userEmail.setText(user.getUserEmail());


        phone = phoneNum.getText().toString();

        // country && city adapter ==>

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, county);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(aa);
        countySpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> bb = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, allCitys);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(bb);
        citySpinner.setOnItemSelectedListener(this);

        // birthDay traitement ==>
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR,i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateEdit();
            }
        };

        birthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterForm.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        fomrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(birthDay.getText().toString().equals("") || countySpinner.getSelectedItem().toString().equals("Country") || citySpinner.getSelectedItem().toString().equals("City") || phoneNum.getText().toString().isEmpty() || !agree.isChecked()) {
                        Toast.makeText(RegisterForm.this, "All items are required!" , Toast.LENGTH_SHORT).show();
                    }
                   else {
                        user.setCity(citySpinner.getSelectedItem().toString());
                        user.setCountry(countySpinner.getSelectedItem().toString());
                        user.setPhoneNum(phoneNum.getText().toString());
                        user.setBirthDay(birthDay.getText().toString());
                        MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(RegisterForm.this);

                        if (formation.getText().toString().equals("Lightroom")) {
                            dataBaseHelper.registerUser(user.getId(),user.getPhoneNum(),user.getBirthDay(),"Lightroom");

                        }
                        else if (formation.getText().toString().equals("Photoshop")) {
                            dataBaseHelper.registerUser(user.getId(),user.getPhoneNum(),user.getBirthDay(),"Photoshop");

                        }
                        else if (formation.getText().toString().equals("Premiere Pro")) {
                            dataBaseHelper.registerUser(user.getId(),user.getPhoneNum(),user.getBirthDay(),"Premiere Pro");

                        }
                        else if (formation.getText().toString().equals("Adobe Stock")) {
                            dataBaseHelper.registerUser(user.getId(),user.getPhoneNum(),user.getBirthDay(),"Adobe Stock");

                        }
                        else {
                            dataBaseHelper.registerUser(user.getId(),user.getPhoneNum(),user.getBirthDay(),"Illustrator");

                        }

                        if(dataBaseHelper.userTutos(user.getId()) != null){
                            Toast.makeText(RegisterForm.this, "Tutorial added Successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterForm.this, "Tutorial cannot be added!", Toast.LENGTH_SHORT).show();
                        }



                        Intent intent = new Intent(RegisterForm.this, UserBag.class);
                        intent.putExtra("user", user);
                        overridePendingTransition(0,0);
                        startActivity(intent);
                        overridePendingTransition(0,0);


                    }


            }
        });




    }
    private void updateEdit(){
        String format = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        birthDay.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Spinner spin = (Spinner)adapterView;
        Spinner spin2 = (Spinner)adapterView;

        if(spin.getId() == R.id.countyspinner)
        {
            //Toast.makeText(this, "Your choose :" + county[i],Toast.LENGTH_SHORT).show();
        }
        if(spin2.getId() == R.id.cityspinner)
        {
            //Toast.makeText(this, "Your choose :" + allCitys[i],Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // nothing to do!
    }

}