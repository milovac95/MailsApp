package com.example.mailsapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String PREFERENCES_USER_ID = "preferences_user_id";
    private static final String PREFERENCES_USER_EMAIL = "preferences_user_email";

    private static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_USER_ID, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void setPreferencesUserId(Context context, String userId){
        getEditor(context).putString(PREFERENCES_USER_ID, userId).commit();
    }

    public static String getPreferencesUserId(Context context){
        return getSharedPreferences(context).getString(PREFERENCES_USER_ID, null);
    }

    public static void setPreferencesUserEmail(Context context, String userEmail){
        getEditor(context).putString(PREFERENCES_USER_EMAIL, userEmail).commit();
    }

    public static String getPreferencesUserEmail(Context context){
        return getSharedPreferences(context).getString(PREFERENCES_USER_EMAIL, null);
    }

    public static void clearUser(Context context)
    {
        setPreferencesUserId(context, null);
        setPreferencesUserEmail(context, null);
    }
}
