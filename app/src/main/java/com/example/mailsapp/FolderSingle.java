package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mailsapp.adapters.FolderCursorAdapter;
import com.example.mailsapp.adapters.MailCursorAdapter;

public class FolderSingle extends AppCompatActivity {

    Cursor folder_emails;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_folder_single);

        final String folderId = getIntent().getStringExtra("folderId");
        ListView folderEmailList = (ListView) this.findViewById(R.id.folder_email_list);
        String userEmail = Preferences.getPreferencesUserEmail(this);
        folder_emails = myDb.getAllFolderEmails(folderId, userEmail);
        MailCursorAdapter mailCursorAdapter = new MailCursorAdapter(this, R.layout.mail_list_item, folder_emails, 0);
        folderEmailList.setAdapter(mailCursorAdapter);
    }
}