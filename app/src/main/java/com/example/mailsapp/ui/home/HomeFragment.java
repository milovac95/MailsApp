package com.example.mailsapp.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.MailSingleActivity;
import com.example.mailsapp.adapters.MailCursorAdapter;
import com.example.mailsapp.R;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener{

    Cursor mails;
    ListView mailList;
    DatabaseHelper myDb;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        myDb = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myDb = new DatabaseHelper(getActivity());
        mailList = (ListView) getActivity().findViewById(R.id.received_mail_list);
        mails = myDb.getAllMails();
        MailCursorAdapter mailCursorAdapter = new MailCursorAdapter(getActivity(), R.layout.mail_list_item, mails, 0);
        mailList.setAdapter(mailCursorAdapter);
        mailList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemId = mails.getString(0);

        Intent intent = new Intent(getActivity(), MailSingleActivity.class);
        intent.putExtra("mailId", itemId);
        startActivity(intent);


    }

}