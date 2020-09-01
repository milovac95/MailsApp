package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mailsapp.ui.contacts.ContactsFragment;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ContactsFragment.newInstance())
                    .commitNow();
        }
    }
}