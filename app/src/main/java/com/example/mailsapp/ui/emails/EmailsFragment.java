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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mailsapp.DatabaseHelper;
import com.example.mailsapp.MainActivity;
import com.example.mailsapp.R;

public class EmailsFragment extends Fragment {

    DatabaseHelper myDb;
    EditText emailTo, emailSubject, emailContent;
    Button btnSend;
    private EmailsViewModel mViewModel;

    public static EmailsFragment newInstance() {
        return new EmailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.emails_fragment, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            EditText emailTo = (EditText) rootView.findViewById(R.id.emailToInput);
            emailTo.setText(bundle.getString("mailTo"));
            EditText emailSubject = (EditText) rootView.findViewById(R.id.emailSubjectInput);
            emailSubject.setText(bundle.getString("mailSubject"));
        }
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EmailsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDb = new DatabaseHelper(getActivity());
        emailTo = (EditText) getView().findViewById(R.id.emailToInput);
        emailSubject = (EditText) getView().findViewById(R.id.emailSubjectInput);
        emailContent = (EditText) getView().findViewById(R.id.emailContentInput);
        btnSend = (Button) getView().findViewById(R.id.btnSendEmail);
        AddData();
    }

    public void AddData(){
        btnSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.sendEmail(emailTo.getText().toString(), emailSubject.getText().toString(),
                                emailContent.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(getActivity(), "Data inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getActivity(), "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}