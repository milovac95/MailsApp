package com.example.mailsapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.mailsapp.R;

public class UserCursorAdapter extends ResourceCursorAdapter{

    public UserCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String name = cursor.getString(1) + ' ' + cursor.getString(2);
        TextView nameTv = (TextView) view.findViewById(R.id.profile_list_item_name);
        nameTv.setText(name);

        TextView emailTv = (TextView) view.findViewById(R.id.profile_list_item_email);
        emailTv.setText(cursor.getString(3));

    }
}
