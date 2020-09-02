package com.example.mailsapp.ui.emails;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mailsapp.R;

public class EmailsFragment extends Fragment {

    private EmailsViewModel mViewModel;

    public static EmailsFragment newInstance() {
        return new EmailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.emails_fragment, container, false);
        String[] menuItems = {"Do something",
                                "Do something else!",
                                "Do yet another thing"};

        ListView listView = (ListView)view.findViewById(R.id.emailsListView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems
        );
        listView.setAdapter(listViewAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EmailsViewModel.class);
        // TODO: Use the ViewModel
    }

}