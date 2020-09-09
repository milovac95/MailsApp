package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mailsapp.ui.emails.EmailsFragment;

public class MailSingleActivity extends AppCompatActivity {

    Cursor mail;
    DatabaseHelper myDb;
    Button btnReplyMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_single);

        final String mailId = getIntent().getStringExtra("mailId");

        myDb = new DatabaseHelper(this);
        mail = myDb.getMailById(mailId);

        FillMailSingleScreen(mail);

        btnReplyMail = (Button) findViewById(R.id.btn_reply);
        btnReplyMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailTo = mail.getString(1);
                String mailSubject = mail.getString(2);

                Intent intent = new Intent(MailSingleActivity.this, Emails.class);
                intent.putExtra("mailTo", mailTo);
                intent.putExtra("mailSubject", mailSubject);
                startActivity(intent);
            }
        });

    }

    public void FillMailSingleScreen(Cursor mail){

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