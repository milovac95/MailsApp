package com.example.mailsapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FolderDialogClass extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btnImportant, btnSpam, btnTrash;
    String emailId;
    DatabaseHelper myDb;

    public FolderDialogClass(Activity a, String emailId) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.emailId = emailId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.folder_dialog);
        myDb = new DatabaseHelper(c);
        btnImportant = (Button) findViewById(R.id.btn_folder_important);
        btnSpam = (Button) findViewById(R.id.btn_folder_spam);
        btnTrash = (Button) findViewById(R.id.btn_folder_trash);
        btnImportant.setOnClickListener(this);
        btnSpam.setOnClickListener(this);
        btnTrash.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_folder_important:
                String folderIdImportant = "1";
                myDb.updateEmailFolderById(emailId, folderIdImportant);
                dismiss();
                break;
            case R.id.btn_folder_spam:
                String folderIdSpam = "2";
                myDb.updateEmailFolderById(emailId, folderIdSpam);
                dismiss();
                break;
            case R.id.btn_folder_trash:
                String folderIdTrash = "3";
                myDb.updateEmailFolderById(emailId, folderIdTrash);
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
