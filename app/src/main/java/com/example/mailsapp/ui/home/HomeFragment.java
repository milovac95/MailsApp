package com.example.mailsapp.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.MailCursorAdapter;
import com.example.mailsapp.R;
import com.example.mailsapp.SentMailCursorAdapter;
import com.example.mailsapp.ui.sentmail.SentMailViewModel;

public class HomeFragment extends Fragment {

    DatabaseHelper myDb;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//
//        View root = inflater.inflate(R.layout.fragment_home, container, false);

        myDb = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.fragment_home, container, false);

//        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myDb = new DatabaseHelper(getActivity());
        ListView mailList = (ListView) getActivity().findViewById(R.id.received_mail_list);
        Cursor mails = myDb.getAllMails();
        MailCursorAdapter mailCursorAdapter = new MailCursorAdapter(getActivity(), R.layout.mail_list_item, mails, 0);
        mailList.setAdapter(mailCursorAdapter);
    }

}