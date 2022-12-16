package com.example.mytodoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_Complet_Table";
    public MyDataBaseHelper(@Nullable Context context) {
        super(context, "UserAccounts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE "+ USER_TABLE +
                " (ID INTEGER primary key autoincrement,USER_Name TEXT UNIQUE, Email TEXT UNIQUE , Password TEXT ," +
                " PhoneNumber TEXT , Birthday TEXT , Psformation TEXT , Lrformation TEXT , Aiformation TEXT , Prformation TEXT ," +
                " Stfrmation TEXT , Anformation TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addUser(UserApp user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("USER_Name", user.getUserName());
        cv.put("Email", user.getUserEmail());
        cv.put("Password", user.getUserPasswd());

        long insert = db.insert(USER_TABLE, null, cv);

        if (insert < 0) {
            return false;
        } else {
            return true;
        }
    }



    // this fct ==> to get the user from the database after login
    public UserApp getUser(String email,String password){
        UserApp returnUser = null;
        String query = "SELECT * FROM "+USER_TABLE+" WHERE Email='"+email+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                String userEmail= cursor.getString(2);
                if(userEmail.equals(email)){
                    String dbPassword = cursor.getString(3);
                    if(dbPassword.equals(password)){
                        String userName = cursor.getString(1);
                        int userId = cursor.getInt(0);
                        returnUser = new UserApp(userId,userName,userEmail,password);
                        break;
                    }
                }
            }while(cursor.moveToNext());
        }
        db.close();
        return returnUser;
    }

    // get the user information using the user id
    public UserApp getUserbYiD(int id){
        UserApp returnUser = null;
        String query = "SELECT * FROM "+USER_TABLE+" WHERE ID="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                int ident = cursor.getInt(0);
                if(ident == id){
                    String dbPassword = cursor.getString(3);
                    String userEmail = cursor.getString(2);
                    String userName = cursor.getString(1);

                        returnUser = new UserApp(id,userName,userEmail,dbPassword);

                        returnUser.setPhoneNum(cursor.getString(4));
                        returnUser.setBirthDay(cursor.getString(5));
                        break;
                }
            }while(cursor.moveToNext());
        }

        db.close();
        return returnUser;
    }

    // to add tutos to the user ==> registration page
    public void registerUser(int id,String phone, String date,String formation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("PhoneNumber",phone);
        cv.put("Birthday",date);
        if(formation.equals("Lightroom")){
            cv.put("Lrformation","ok");
        }
        else if(formation.equals("Photoshop")){
            cv.put("Psformation","ok");
        }else if(formation.equals("Adobe Stock")){
            cv.put("Stfrmation","ok");
        }
        else if(formation.equals("Premiere Pro")){
            cv.put("Prformation","ok");
        }
        else{
            cv.put("Aiformation","ok");
        }

        db.update(USER_TABLE, cv, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // to modify the user information ==> Profile page
    public void profileModif(UserApp user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("USER_Name",user.getUserName());
        cv.put("Email",user.getUserEmail());
        cv.put("Password",user.getUserPasswd());
        cv.put("PhoneNumber",user.getPhoneNum());
        cv.put("Birthday",user.getBirthDay());

        db.update(USER_TABLE, cv, "ID=?", new String[]{String.valueOf(user.getId())});
        db.close();
    }

    // this fct ==> to get the tutorials of a specific user
    public ArrayList<Tutorial> userTutos(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Tutorial> tutos = new ArrayList<Tutorial>();

        String query = "SELECT * from " + USER_TABLE;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getInt(0) == id) {
                    if (cursor.getString(7)!=null) {
                        tutos.add(new Tutorial("Lightroom", "Learn Lightroom", R.drawable.lr));
                    }
                    if (cursor.getString(6)!=null) {
                        tutos.add(new Tutorial("Photoshop", "Learn Photoshop", R.drawable.ps));
                    }
                    if (cursor.getString(8)!=null) {
                        tutos.add(new Tutorial("Illustrator", "Learn illustrator", R.drawable.ill));
                    }
                    if (cursor.getString(9)!=null) {
                        tutos.add(new Tutorial("Premiere Pro", "Learn Premiere Pro", R.drawable.pr));
                    }
                    if (cursor.getString(10)!=null) {
                        tutos.add(new Tutorial("Adobe Stock", "Learn Adobe Stock", R.drawable.st));
                    }
                    break;
                }

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tutos;
    }



    // this fct ==> To verify if a user with the given email and userName exists :
    // if yes ==> don't create a new
    // else create a new user
    public boolean ifExists(String username,String email){

        boolean exists = false;

        String query1= "SELECT * FROM "+USER_TABLE+" WHERE Email='"+email+"'";
        String query2 = "SELECT * FROM "+USER_TABLE+" WHERE USER_Name='"+username+"'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery(query1,null);

        Cursor cursor2 = db.rawQuery(query2,null);

        if(cursor1.moveToFirst()){
            exists = true;
        }
        if(cursor2.moveToFirst()){
            exists = true;
        }

        db.close();
        return exists ;

    }


    // this fct ==> to delete a userTutorial
    public void deleteUserTutorial(int id,Tutorial tuto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(tuto.getTitle().equals("Photoshop")){
            cv.putNull("Psformation");
        }
        else if(tuto.getTitle().equals("Lightroom")){
            cv.putNull("Lrformation");
        }
        else if(tuto.getTitle().equals("Illustrator")){
            cv.putNull("Aiformation");
        }
        else if(tuto.getTitle().equals("Adobe Stock")){
            cv.putNull("Stfrmation");
        }
        else{
            cv.putNull("Prformation");
        }

        db.update(USER_TABLE, cv, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
