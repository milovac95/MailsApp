package com.example.mailsapp.ui.contacts;

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
import com.example.mailsapp.LoginActivity;
import com.example.mailsapp.MainActivity;
import com.example.mailsapp.NewContact;
import com.example.mailsapp.Preferences;
import com.example.mailsapp.R;
import com.example.mailsapp.adapters.ContactCursorAdapter;
import com.example.mailsapp.adapters.UserCursorAdapter;

public class ContactsFragment extends Fragment implements AdapterView.OnItemClickListener{

    Cursor contacts;
    DatabaseHelper myDb;
    Button btnCreateNew;
    private ContactsViewModel mViewModel;

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.contacts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        ListView contactList = (ListView) getActivity().findViewById(R.id.contact_list);
        String userId = Preferences.getPreferencesUserId(getActivity());
        contacts= myDb.getAllContacts(userId);
        ContactCursorAdapter contactCursorAdapter = new ContactCursorAdapter(getActivity(), R.layout.contact_list_item, contacts, 0);
        contactList.setAdapter(contactCursorAdapter);
        contactList.setOnItemClickListener(this);

        btnCreateNew = (Button) getActivity().findViewById(R.id.btn_new_contact);
        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewContact.class);
                startActivity(intent);
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String contactId = contacts.getString(0);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        Preferences.setPreferencesUserId(getActivity(), contactId);
        startActivity(intent);
    }
}