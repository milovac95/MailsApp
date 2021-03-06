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
    Button btnReplyMail, btnForwardMail, btnDeleteMail;
    String textMailFrom;
    String textMailTo;
    Integer isSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_single);

        final String mailId = getIntent().getStringExtra("mailId");
        final int sent = getIntent().getIntExtra("sentmail", 0);

        myDb = new DatabaseHelper(this);

        if(sent == 1){
            isSent = 1;
        }else{
            isSent = 0;
        }
        mail = myDb.getMailById(mailId);



        FillMailSingleScreen(mail);

        btnReplyMail = (Button) findViewById(R.id.btn_reply);
        btnForwardMail = (Button) findViewById(R.id.btn_forward_mail);
        btnDeleteMail = (Button) findViewById(R.id.btn_delete_single_mail);
        btnReplyMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isSent == 1){
                    textMailTo = mail.getString(6);
                }else {
                    textMailTo = mail.getString(1);
                }

                String mailSubject = mail.getString(2);

                Intent intent = new Intent(MailSingleActivity.this, Emails.class);
                intent.putExtra("mailTo", textMailTo);
                intent.putExtra("mailSubject", mailSubject);
                intent.putExtra("mailContent", "");
                startActivity(intent);
            }
        });

        btnForwardMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailContent = mail.getString(3);
                String mailSubject = mail.getString(2);

                Intent intent = new Intent(MailSingleActivity.this, Emails.class);
                intent.putExtra("mailContent", mailContent);
                intent.putExtra("mailSubject", mailSubject);
                intent.putExtra("mailTo", "");
                startActivity(intent);
            }
        });

        btnDeleteMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailId = mail.getString(0);
                myDb.deleteMailById(mailId);
                Intent intent = new Intent(MailSingleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void FillMailSingleScreen(Cursor mail){

        if(isSent == 1){
            textMailFrom = mail.getString(6);
        }else {
            textMailFrom = mail.getString(1);
        }

        TextView emailFrom = (TextView) this.findViewById(R.id.single_received_email_from);
        emailFrom.setText(textMailFrom);

        TextView emailSubject = (TextView) this.findViewById(R.id.single_received_email_subject);
        emailSubject.setText(mail.getString(2));

        TextView emailContent = (TextView) this.findViewById(R.id.single_received_email_content);
        emailContent.setText(mail.getString(3));

        TextView emailDatetime = (TextView) this.findViewById(R.id.single_received_email_datetime);
        emailDatetime.setText(mail.getString(5));
    }
}