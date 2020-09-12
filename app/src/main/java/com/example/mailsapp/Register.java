package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name, lastname, email, password;
    String userId;
    Button btnRegisterNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.register_new_name);
        lastname = (EditText) findViewById(R.id.register_new_lastname);
        email = (EditText) findViewById(R.id.register_new_email);
        password = (EditText) findViewById(R.id.register_new_password);
        btnRegisterNew = findViewById(R.id.btn_register_new);

        btnRegisterNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(register()){
                    startActivity(new Intent(Register.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    public boolean register() {
        myDb = new DatabaseHelper(this);
        boolean registred = false;
        try {
            boolean isInserted = myDb.addUser(name.getText().toString(), lastname.getText().toString(),
                    email.getText().toString(), password.getText().toString());
            if(isInserted == true)
                registred = true;
            else
                registred = false;
        } catch (Exception e){
            registred = false;
        }

        return registred;
    }
}