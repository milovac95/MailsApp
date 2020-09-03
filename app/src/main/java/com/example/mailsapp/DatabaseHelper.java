package com.example.mailsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "email.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LASTNAME TEXT, EMAIL TEXT)");
        db.execSQL("create table email_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAILFROM TEXT, EMAILTO TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME)");
        db.execSQL("create table folder_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user_table");
        db.execSQL("DROP TABLE IF EXISTS email_table");
        db.execSQL("DROP TABLE IF EXISTS folder_table");
        onCreate(db);
    }
}
