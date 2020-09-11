package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactEdit extends AppCompatActivity {

    EditText tvName, tvLastname, tvEmail;
    Button btnEdit;
    DatabaseHelper myDb;
    Cursor contact;
    String newName, newLastname, newEmail;
    String contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        final String name = getIntent().getStringExtra("name");
        final String lastname = getIntent().getStringExtra("lastname");
        final String email = getIntent().getStringExtra("email");
        contactId = getIntent().getStringExtra("contactId");

        myDb = new DatabaseHelper(this);

        tvName = (EditText) findViewById(R.id.contact_edit_name);
        tvName.setText(name);
        tvLastname = (EditText) findViewById(R.id.contact_edit_lastname);
        tvLastname.setText(lastname);
        tvEmail = (EditText) findViewById(R.id.contact_edit_email);
        tvEmail.setText(email);
        btnEdit = (Button) findViewById(R.id.btn_edit_contact_button);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editContact()){
                    startActivity(new Intent(ContactEdit.this, ContactsActivity.class));
                    finish();
                }
            }
        });
    }

    public boolean editContact() {
        myDb = new DatabaseHelper(this);
        boolean editedContact = false;
        try {
            myDb.editContactById(tvName.getText().toString(), tvLastname.getText().toString(),tvEmail.getText().toString(), contactId);
            editedContact = true;
        } catch (Exception e){
            editedContact = false;
        }

        return editedContact;
    }
}