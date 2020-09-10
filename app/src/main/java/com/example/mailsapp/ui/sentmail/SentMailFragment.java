package com.example.mailsapp.ui.sentmail;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.MailSingleActivity;
import com.example.mailsapp.Preferences;
import com.example.mailsapp.R;
import com.example.mailsapp.adapters.SentMailCursorAdapter;

public class SentMailFragment extends Fragment implements AdapterView.OnItemClickListener {

    Cursor mails;
    DatabaseHelper myDb;
    private SentMailViewModel mViewModel;

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
        String userId = Preferences.getPreferencesUserId(getActivity());
        mails= myDb.getAllSentMails(userId);
        SentMailCursorAdapter sentMailCursorAdapter = new SentMailCursorAdapter(getActivity(), R.layout.sent_mail_list_item, mails, 0);
        sentEmailList.setAdapter(sentMailCursorAdapter);
        sentEmailList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemId = mails.getString(0);

        Intent intent = new Intent(getActivity(), MailSingleActivity.class);
        intent.putExtra("mailId", itemId);
        intent.putExtra("sentmail", 1);
        startActivity(intent);
    }

}