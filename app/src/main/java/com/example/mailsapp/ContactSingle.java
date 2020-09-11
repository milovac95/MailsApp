package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactSingle extends AppCompatActivity {

    Cursor contact;
    DatabaseHelper myDb;
    Button btnReplyContact, btnEditContact, btnDeleteContact;
    String email;
    String lastname;
    String name;
    String contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_single);

        final String mailId = getIntent().getStringExtra("contactId");

        myDb = new DatabaseHelper(this);
        contact = myDb.getContactById(mailId);
        FillMailSingleScreen(contact);

        btnReplyContact = (Button) findViewById(R.id.btn_reply_single_contact);
        btnEditContact = (Button) findViewById(R.id.btn_edit_single_contact);
        btnDeleteContact = (Button) findViewById(R.id.btn_delete_single_contact);

        btnReplyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactSingle.this, Emails.class);
                intent.putExtra("mailTo", email);
                intent.putExtra("mailSubject", "");
                intent.putExtra("mailContent", "");
                startActivity(intent);
            }
        });

        //edit treba jos da se uradi
        btnEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactSingle.this, Emails.class);
                intent.putExtra("mailTo", email);
                intent.putExtra("mailSubject", "");
                intent.putExtra("mailContent", "");
                startActivity(intent);
            }
        });

        btnDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactSingle.this, ContactsActivity.class);
                myDb.deleteContactById(contactId);
                startActivity(intent);
            }
        });

    }

    public void FillMailSingleScreen(Cursor contact){

        contactId = contact.getString(0);
        name = contact.getString(1);
        lastname = contact.getString(2);
        String fullname = name + ' ' + lastname;
        TextView tvName = (TextView) this.findViewById(R.id.contact_single_name);
        tvName.setText(fullname);

        email = contact.getString(3);
        TextView tvEmail = (TextView) this.findViewById(R.id.contact_single_email);
        tvEmail.setText(email);

    }
}