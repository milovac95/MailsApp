package com.example.mailsapp.ui.profile;

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
import android.widget.Button;
import android.widget.ListView;

import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.Emails;
import com.example.mailsapp.LoginActivity;
import com.example.mailsapp.MailSingleActivity;
import com.example.mailsapp.MainActivity;
import com.example.mailsapp.Preferences;
import com.example.mailsapp.R;
import com.example.mailsapp.adapters.SentMailCursorAdapter;
import com.example.mailsapp.adapters.UserCursorAdapter;

public class ProfileFragment extends Fragment implements AdapterView.OnItemClickListener{
    Cursor users;
    DatabaseHelper myDb;
    Button btnLogout;
    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        ListView profileList = (ListView) getActivity().findViewById(R.id.profile_list);
        users= myDb.getAllUsers();
        UserCursorAdapter userCursorAdapter = new UserCursorAdapter(getActivity(), R.layout.profile_list_item, users, 0);
        profileList.setAdapter(userCursorAdapter);
        profileList.setOnItemClickListener(this);

        btnLogout = (Button) getActivity().findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearUser(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String userId = users.getString(0);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        Preferences.setPreferencesUserId(getActivity(), userId);
        startActivity(intent);
    }

}