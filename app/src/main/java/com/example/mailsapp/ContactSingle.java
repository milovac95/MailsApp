package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ContactSingle extends AppCompatActivity {

    Cursor contact;
    DatabaseHelper myDb;
    Button btnReplyContact, btnEditContact, btnDeleteContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_single);

        final String mailId = getIntent().getStringExtra("contactId");

        myDb = new DatabaseHelper(this);
        contact = myDb.getContactById(mailId);
        FillMailSingleScreen(contact);
    }

    public void FillMailSingleScreen(Cursor contact){

        String name = contact.getString(1) + ' ' + contact.getString(2);
        TextView tvName = (TextView) this.findViewById(R.id.contact_single_name);
        tvName.setText(name);

        TextView tvEmail = (TextView) this.findViewById(R.id.contact_single_email);
        tvEmail.setText(contact.getString(3));

    }
}