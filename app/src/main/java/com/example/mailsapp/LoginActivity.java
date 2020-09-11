package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btnLogin;
    DatabaseHelper myDb;
    String userId;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login()){
                    Preferences.setPreferencesUserId(getApplicationContext(), userId);
                    Preferences.setPreferencesUserEmail(getApplicationContext(), userEmail);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }

    public boolean login() {
        myDb = new DatabaseHelper(this);
        Cursor users = myDb.getAllUsers();
        boolean userFound = false;
        try {
            while (users.moveToNext()) {
                if (email.getText().toString().equals(users.getString(3))
                        && password.getText().toString().equals(users.getString(4))) {
                    userFound = true;
                    userId = users.getString(0);
                    userEmail = users.getString(3);
                }
            }
        } finally {
            users.close();
        }

        return userFound;
    }
}