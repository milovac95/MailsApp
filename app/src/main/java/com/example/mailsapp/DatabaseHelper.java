package com.example.mailsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "email.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user_table");
        db.execSQL("DROP TABLE IF EXISTS email_table");
        db.execSQL("DROP TABLE IF EXISTS folder_table");
        db.execSQL("DROP TABLE IF EXISTS sent_email_table");
        onCreate(db);
    }

    public boolean sendEmail(String emailTo, String emailSubject, String emailContent, String userId){
        Date currentTime = Calendar.getInstance().getTime();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailTo", emailTo);
        contentValues.put("subject", emailSubject);
        contentValues.put("content", emailContent);
        contentValues.put("datetime", currentTime.toString());
        contentValues.put("userid", userId);
        long result = db.insert("sent_email_table",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllSentMails(String userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM SENT_EMAIL_TABLE WHERE USERID =" + userId, null);
        return res;
    }

    public Cursor getAllMails(String userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mails = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE USERID =" +userId, null);
        return mails;
    }

    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor users = db.rawQuery("SELECT * FROM USER_TABLE", null);
        return users;
    }

    public Cursor getMailById(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE _id = " + id, null);
        res.moveToFirst();
        return res;
    }

    public Cursor getSentMailById(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM SENT_EMAIL_TABLE WHERE _id = " + id, null);
        res.moveToFirst();
        return res;
    }

    private void initDB(SQLiteDatabase db){

        db.execSQL("create table user_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LASTNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("create table email_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, EMAILFROM TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME, USERID TEXT)");
        db.execSQL("create table sent_email_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, EMAILTO TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME, USERID TEXT)");
        db.execSQL("create table folder_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");

        db.execSQL("INSERT INTO email_table (emailfrom, subject, content, datetime, userid) " +
                "values ('marko@gmail.com', 'Pozdrav', 'Javljam se u vezi oglasa bla bla bla bla bla bla bla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla bla', 'Sun Aug 01 14:58:44 2020', '1')," +
                "('facebook@facebook.com', 'Notification', 'Bla bla bla', 'Sun Aug 04 14:58:44 2020', '1')," +
                "('petar@gmail.com', 'Pozdrav', 'Zovem povodom polovnog...', 'Fri Aug 16 11:58:44 2020','1')," +
                "('someone@gmail.com', 'Pozdrav', 'Caoo, sta ima', 'Sun Aug 18 13:58:44 2020','1')," +
                "('linkedin@linkedin.com', 'Pozdrav', 'Something important bla bla', 'Mon Sep 01 04:58:44 2020','1')," +
                "('facebook@gmail.com', 'Hahha', 'Hahha, i woild like to..', 'Sat Sep 05 23:58:44 2020','2')," +
                "('johndoe@gmail.com', 'Something', 'Would you like to buy..', 'Mon Sep 06 13:58:44 2020','2')," +
                "('silvester@stalone.com', 'Hello', 'I am Balboa!', 'Sat Sep 07 12:58:44 2020','3')");

        db.execSQL("INSERT INTO user_table (name, lastname, email, password) " +
                "values ('Marko', 'Milovac', 'marko@mail.com', '1234')," +
                "('Silvester', 'Stalone', 'stalone@mail.com', '1234')," +
                "('Arnold', 'Schwarzenegger', 'arnold@mail.com', '1234')");
    }
}
