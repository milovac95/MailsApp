package com.example.mailsapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.mailsapp.R;

public class MailCursorAdapter extends ResourceCursorAdapter {

    public MailCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView emailFrom = (TextView) view.findViewById(R.id.list_item_received_email_from);
        emailFrom.setText(cursor.getString(1));

        TextView emailSubject = (TextView) view.findViewById(R.id.list_item_received_email_subject);
        emailSubject.setText(cursor.getString(2));

        TextView emailContent = (TextView) view.findViewById(R.id.list_item_received_email_content);
        String fullContent = cursor.getString(3);
        if(fullContent.length() > 100){
            emailContent.setText(fullContent.substring(0,100) + "...");
        }else {
            emailContent.setText(fullContent);
        }

        TextView emailDatetime = (TextView) view.findViewById(R.id.list_item_received_email_datetime);
        emailDatetime.setText(cursor.getString(5));
    }

}
