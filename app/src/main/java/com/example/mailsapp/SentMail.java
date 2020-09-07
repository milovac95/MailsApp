package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mailsapp.ui.sentmail.SentMailFragment;

public class SentMail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sent_mail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SentMailFragment.newInstance())
                    .commitNow();
        }
    }
}