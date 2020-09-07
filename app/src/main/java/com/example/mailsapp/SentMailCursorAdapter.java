package com.example.mailsapp;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class SentMailCursorAdapter extends ResourceCursorAdapter{

    public SentMailCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView emailTo = (TextView) view.findViewById(R.id.list_item_email_to);
        emailTo.setText(cursor.getString(1));

        TextView emailSubject = (TextView) view.findViewById(R.id.list_item_email_subject);
        emailSubject.setText(cursor.getString(2));

        TextView emailContent = (TextView) view.findViewById(R.id.list_item_email_content);
        emailContent.setText(cursor.getString(3));

        TextView emailDatetime = (TextView) view.findViewById(R.id.list_item_email_datetime);
        emailDatetime.setText(cursor.getString(5));
    }
}
