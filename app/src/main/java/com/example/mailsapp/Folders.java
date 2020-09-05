package com.example.mailsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mailsapp.ui.folders.FoldersFragment;

public class Folders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folders_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FoldersFragment.newInstance())
                    .commitNow();
        }
    }
}