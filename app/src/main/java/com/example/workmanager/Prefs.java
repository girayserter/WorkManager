package com.example.workmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

public class Prefs {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "TimePref";
    private static final String KEY_TIME = "time";

    public Prefs(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setTime(String time){
        editor.putString(KEY_TIME, time);
        editor.commit();
    }

    public String getTime(){
        return pref.getString(KEY_TIME,null);
    }

}
