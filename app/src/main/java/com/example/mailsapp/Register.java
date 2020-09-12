package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText name, lastname, email, password;
    String userId;
    Button btnRegisterNew;
    Cursor users;
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
                }else {
                    Toast.makeText(getApplication(), "Email already exists", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean register() {
        myDb = new DatabaseHelper(this);
        boolean registred = false;
        boolean emailExists = false;
        try {
            users = myDb.getAllUsers();
            while (users.moveToNext()) {
                String inputEmail = email.getText().toString();
                String userEmail = users.getString(3);
                if (inputEmail.equals(userEmail)) {
                    emailExists = true;
                }
            }

            if(!emailExists){
                boolean isInserted = myDb.addUser(name.getText().toString(), lastname.getText().toString(),
                        email.getText().toString(), password.getText().toString());
                if(isInserted)
                    registred = true;
                else
                    registred = false;
            }

        } catch (Exception e){
            registred = false;
        }

        return registred;
    }
}