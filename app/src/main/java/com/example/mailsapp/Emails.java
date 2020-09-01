package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mailsapp.ui.emails.EmailsFragment;

public class Emails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emails_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EmailsFragment.newInstance())
                    .commitNow();
        }
    }
}