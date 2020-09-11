package com.example.mailsapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.mailsapp.R;

public class FolderCursorAdapter extends ResourceCursorAdapter{

    public FolderCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvFolder = (TextView) view.findViewById(R.id.folder_name);
        tvFolder.setText(cursor.getString(1));

    }
}
