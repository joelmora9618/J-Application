package com.example.jmora.webservicesoap.Data.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JMora on 08/08/2017.
 */

public class JemPreferences {
    public static final String KEY_LAST_DATE_SYNC = "KEY_LAST_DATE_SYNC";
    public static final String KEY_USER = "KEY_USER";
    public static final String KEY_PASS = "KEY_PASS";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_URL_HOST = "KEY_URL_HOST";

    private static String KEY_SHARED_PREFERENCE = "KEY_SHARED_PREFERENCE";

    public static void cleanData(Context context){
        SharedPreferences settings = context.getSharedPreferences(KEY_SHARED_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getString(Context context, String key,
                                   String defaultValue) {
        // Restore preferences
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        return settings.getString(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defaultValue) {
        // Restore preferences
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        return settings.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);

        // Commit the edits!
        editor.commit();
    }

    public static long getLong(Context context, String key,
                               long defaultValue) {
        // Restore preferences
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        return settings.getLong(key, defaultValue);
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);

        // Commit the edits!
        editor.commit();
    }

    public static int getInteger(Context context, String key,
                                 int defaultValue) {
        // Restore preferences
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        return settings.getInt(key, defaultValue);
    }

    public static void setInteger(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(
                KEY_SHARED_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);

        // Commit the edits!
        editor.commit();
    }
}
