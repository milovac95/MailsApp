package com.example.mailsapp.ui.folders;

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

import com.example.mailsapp.ContactSingle;
import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.NewContact;
import com.example.mailsapp.Preferences;
import com.example.mailsapp.R;
import com.example.mailsapp.adapters.ContactCursorAdapter;
import com.example.mailsapp.adapters.FolderCursorAdapter;

public class FoldersFragment extends Fragment implements AdapterView.OnItemClickListener {

    Cursor folders;
    DatabaseHelper myDb;
    Button btnCreateNew;
    private FoldersViewModel mViewModel;

    public static FoldersFragment newInstance() {
        return new FoldersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.folders_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FoldersViewModel.class);
        ListView folderList = (ListView) getActivity().findViewById(R.id.folders_list);
        String userId = Preferences.getPreferencesUserId(getActivity());
        folders= myDb.getAllFolders(userId);
        FolderCursorAdapter folderCursorAdapter = new FolderCursorAdapter(getActivity(), R.layout.folder_list_item, folders, 0);
        folderList.setAdapter(folderCursorAdapter);
        folderList.setOnItemClickListener(this);

        btnCreateNew = (Button) getActivity().findViewById(R.id.btn_new_folder);
        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewContact.class);
                startActivity(intent);
            }
        });

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String folderId = folders.getString(0);

        Intent intent = new Intent(getActivity(), ContactSingle.class);
        intent.putExtra("folderId", folderId);
        startActivity(intent);
    }

}