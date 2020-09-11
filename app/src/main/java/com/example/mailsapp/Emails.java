package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mailsapp.ui.emails.EmailsFragment;

public class Emails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emails_activity);
        final String mailTo = getIntent().getStringExtra("mailTo");
        final String mailSubject = getIntent().getStringExtra("mailSubject");
        final String mailContent = getIntent().getStringExtra("mailContent");

        if(getIntent() != null){

            Bundle bundle = new Bundle();
            bundle.putString("mailTo", mailTo);
            bundle.putString("mailSubject", mailSubject);
            bundle.putString("mailContent", mailContent);

            EmailsFragment emailsFragment = new EmailsFragment();
            emailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, emailsFragment).commit();


        }else{
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, EmailsFragment.newInstance())
                        .commitNow();
            }
        }

    }
}