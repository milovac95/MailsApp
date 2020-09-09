package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MailSingleActivity extends AppCompatActivity {

    Cursor mail;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_single);

        String mailId = getIntent().getStringExtra("mailId");

        myDb = new DatabaseHelper(this);
        mail = myDb.getMailById(mailId);

        TextView emailFrom = (TextView) this.findViewById(R.id.single_received_email_from);
        emailFrom.setText(mail.getString(1));

        TextView emailSubject = (TextView) this.findViewById(R.id.single_received_email_subject);
        emailSubject.setText(mail.getString(2));

        TextView emailContent = (TextView) this.findViewById(R.id.single_received_email_content);
        emailContent.setText(mail.getString(3));

        TextView emailDatetime = (TextView) this.findViewById(R.id.single_received_email_datetime);
        emailDatetime.setText(mail.getString(5));
    }
}