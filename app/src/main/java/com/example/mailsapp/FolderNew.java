package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FolderNew extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name;
    String userId;
    Button btnAddFolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_new);

        myDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.folder_new_name);
        userId = Preferences.getPreferencesUserId(this);
        btnAddFolder = findViewById(R.id.btn_add_folder_new);

        btnAddFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addFolder()){
                    startActivity(new Intent(FolderNew.this, Folders.class));
                    finish();
                }
            }
        });
    }

    public boolean addFolder() {
        myDb = new DatabaseHelper(this);
        boolean addedFolder = false;
        try {
            boolean isInserted = myDb.addFolder(name.getText().toString(), userId);
            if(isInserted == true)
                addedFolder = true;
            else
                addedFolder = false;
        } catch (Exception e){
            addedFolder = false;
        }

        return addedFolder;
    }
}