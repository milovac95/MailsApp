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
        db.execSQL("DROP TABLE IF EXISTS contact_table");
        //db.execSQL("DROP TABLE IF EXISTS sent_email_table");
        onCreate(db);
    }

    public boolean sendEmail(String emailFrom, String emailSubject, String emailContent, String emailTo){
        Date currentTime = Calendar.getInstance().getTime();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("emailFrom", emailFrom);
        contentValues.put("subject", emailSubject);
        contentValues.put("content", emailContent);
        contentValues.put("datetime", currentTime.toString());
        contentValues.put("emailto", emailTo);
        long result = db.insert("email_table",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean addContact(String name, String lastname, String email, String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("userid", userId);
        long result = db.insert("contact_table",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean addUser(String name, String lastname, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("user_table",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean addFolder(String name, String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("userid", userId);
        long result = db.insert("folder_table",null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllSentMails(String emailFrom){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE EMAILFROM ='" + emailFrom + "'ORDER BY _id desc ", null);
        return res;
    }

    public Cursor getAllMails(String emailTo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mails = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE EMAILTO = '" + emailTo + "' ORDER BY _id desc " , null);
        return mails;
    }

    public Cursor getAllFolderEmails(String folderId, String userEmail){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor folderMails = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE (EMAILTO = '" + userEmail + "' AND FOLDERID = " + folderId + ")" , null);
        return folderMails;
    }

    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor users = db.rawQuery("SELECT * FROM USER_TABLE", null);
        return users;
    }

    public Cursor getAllContacts(String userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor users = db.rawQuery("SELECT * FROM CONTACT_TABLE WHERE USERID =" + userId, null);
        return users;
    }

    public Cursor getAllFolders(String userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor folders = db.rawQuery("SELECT * FROM FOLDER_TABLE WHERE USERID =" + userId, null);
        return folders;
    }

    public Cursor getMailById(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE _id = " + id, null);
        res.moveToFirst();
        return res;
    }

    public Cursor getSentMailById(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM EMAIL_TABLE WHERE _id = " + id, null);
        res.moveToFirst();
        return res;
    }

    public Cursor getContactById(String id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor contact = db.rawQuery("SELECT * FROM CONTACT_TABLE WHERE _id = " + id, null);
        contact.moveToFirst();
        return contact;
    }

    public Cursor editContactById(String name, String lastname, String email, String id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor contact = db.rawQuery("UPDATE CONTACT_TABLE SET NAME = '" + name + "', " +
                "LASTNAME = '" + lastname + "', EMAIL = '" + email + "'  WHERE _id = " + id, null);
        contact.moveToFirst();
        return contact;
    }

    public Cursor updateEmailFolderById(String emailId, String folderId){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor emailFolder = db.rawQuery("UPDATE EMAIL_TABLE SET FOLDERID = '" + folderId + "' WHERE _id = " + emailId, null);
        emailFolder.moveToFirst();
        return emailFolder;
    }

    public Cursor deleteContactById(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor contactDeleted = db.rawQuery("DELETE FROM CONTACT_TABLE WHERE _id = " + id, null);
        contactDeleted.moveToFirst();
        return contactDeleted;
    }

    public Cursor deleteMailById(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor emailDeleted = db.rawQuery("DELETE FROM EMAIL_TABLE WHERE _id = " + id, null);
        emailDeleted.moveToFirst();
        return emailDeleted;
    }

    private void initDB(SQLiteDatabase db){

        db.execSQL("create table user_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LASTNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("create table email_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, EMAILFROM TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME, EMAILTO TEXT, FOLDERID TEXT)");
        //db.execSQL("create table sent_email_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, EMAILTO TEXT,SUBJECT TEXT, CONTENT TEXT, EMAILCC TEXT, DATETIME DATETIME, USERID TEXT)");
        db.execSQL("create table folder_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USERID TEXT)");
        db.execSQL("create table contact_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LASTNAME TEXT, EMAIL TEXT, USERID TEXT)");

        db.execSQL("INSERT INTO email_table (emailfrom, subject, content, datetime, emailto, folderid) " +
                "values ('marko@mail.com', 'Pozdrav', 'Javljam se u vezi oglasa bla bla bla bla bla bla bla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla blabla bla bla', 'Sun Aug 01 14:58:44 2020', 'stalone@mail.com', '1')," +
                "('facebook@facebook.com', 'Notification', 'Bla bla bla', 'Sun Aug 04 14:58:44 2020', 'marko@mail.com', '1')," +
                "('petar@gmail.com', 'Pozdrav', 'Zovem povodom polovnog...', 'Fri Aug 16 11:58:44 2020','marko@mail.com', '1')," +
                "('someone@gmail.com', 'Pozdrav', 'Caoo, sta ima', 'Sun Aug 18 13:58:44 2020','marko@mail.com', '2')," +
                "('linkedin@linkedin.com', 'Pozdrav', 'Something important bla bla', 'Mon Sep 01 04:58:44 2020','marko@mail.com', '2')," +
                "('facebook@gmail.com', 'Hahha', 'Hahha, i woild like to..', 'Sat Sep 05 23:58:44 2020','stalone@mail.com', '1')," +
                "('johndoe@gmail.com', 'Something', 'Would you like to buy..', 'Mon Sep 06 13:58:44 2020','stalone@mail.com', '1')," +
                "('silvester@stalone.com', 'Hello', 'I am Balboa!', 'Sat Sep 07 12:58:44 2020','arnold@mail.com', '1')");

        db.execSQL("INSERT INTO user_table (name, lastname, email, password) " +
                "values ('Marko', 'Milovac', 'marko@mail.com', '1234')," +
                "('Silvester', 'Stalone', 'stalone@mail.com', '1234')," +
                "('Arnold', 'Schwarzenegger', 'arnold@mail.com', '1234')");

        db.execSQL("INSERT INTO contact_table (name, lastname, email, userid) " +
                "values ('John', 'Doe', 'johndoe@mail.com', '1')," +
                "('Novak', 'Djokovic', 'nole@mail.com', '1')," +
                "('FTN', 'Sluzba', 'ssluzbaftn@uns.ac.rs', '1')");

        db.execSQL("INSERT INTO folder_table (name, userid) values ('Important', '1'), ('Spam', '1'), ('Trash', '1')");
    }
}
