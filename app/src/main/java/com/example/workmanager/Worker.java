package com.example.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkerParameters;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Worker extends androidx.work.Worker {

    Prefs prefs;
    public Worker(@NonNull @NotNull Context context,
                  @NonNull @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        prefs=new Prefs(getApplicationContext());
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {
        prefs.setTime(getTime());
        return Result.success();
    }

    private String getTime(){
        String currentTime = new SimpleDateFormat("HH:mm:ss",
                Locale.getDefault()).format(new Date());
        return currentTime;
    }
}
