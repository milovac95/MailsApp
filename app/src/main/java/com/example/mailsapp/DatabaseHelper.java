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
        db.execSQL("create table user_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LASTNAME TEXT, EMAIL TEXT)");
        db.execSQL("create table email_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAILFROM TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("create table sent_email_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAILTO TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("create table folder_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user_table");
        db.execSQL("DROP TABLE IF EXISTS email_table");
        db.execSQL("DROP TABLE IF EXISTS folder_table");
        db.execSQL("DROP TABLE IF EXISTS sent_email_table");
        onCreate(db);
    }

    public boolean sendEmail(String emailTo, String emailSubject, String emailContent){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailTo", emailTo);
        contentValues.put("subject", emailSubject);
        contentValues.put("content", emailContent);
        long result = db.insert("sent_email_table",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllSentMails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM SENT_EMAIL_TABLE", null);
        return res;
    }
}
