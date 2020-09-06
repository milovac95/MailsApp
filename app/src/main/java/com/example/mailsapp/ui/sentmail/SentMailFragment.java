package com.example.mailsapp.ui.sentmail;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.R;
import com.example.mailsapp.SentMailCursorAdapter;

import java.util.List;

public class SentMailFragment extends Fragment {

    DatabaseHelper myDb;
    private SentMailViewModel mViewModel;
    String emailTo[] = {"cao@gmail.com", "brate@ccc.com", "treci@mail.com"};
    String emailSubject[] = {"Pozdrav", "Cao", "Blaaa"};
    String emailContent[] = {"brateuuuu sta radis", "cao burazeee", "desi bre sta ima"};

    public static SentMailFragment newInstance() {
        return new SentMailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.sent_mail_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SentMailViewModel.class);
        ListView sentEmailList = (ListView) getActivity().findViewById(R.id.sent_mail_list);
        Cursor res = myDb.getAllSentMails();
        SentMailCursorAdapter sentMailCursorAdapter = new SentMailCursorAdapter(getActivity(), R.layout.sent_mail_list_item, res, 0);
        sentEmailList.setAdapter(sentMailCursorAdapter);
    }

}