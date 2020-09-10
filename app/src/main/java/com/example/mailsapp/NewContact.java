package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name, lastname, email;
    String userId;
    Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        myDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.contact_new_name);
        lastname = (EditText) findViewById(R.id.contact_new_lastname);
        email = (EditText) findViewById(R.id.contact_new_email);
        userId = Preferences.getPreferencesUserId(this);
        btnAddContact = findViewById(R.id.btn_add_contact);

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addContact()){
                    startActivity(new Intent(NewContact.this, ContactsActivity.class));
                    finish();
                }
            }
        });
    }

    public boolean addContact() {
        myDb = new DatabaseHelper(this);
        boolean addedContact = false;
        try {
            boolean isInserted = myDb.addContact(name.getText().toString(), lastname.getText().toString(),
                    email.getText().toString(), userId);
            if(isInserted == true)
                addedContact = true;
            else
                addedContact = false;
        } catch (Exception e){
            addedContact = false;
        }

        return addedContact;
    }

}
