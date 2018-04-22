package com.devon.firstapplication;

import android.content.Context;
import android.widget.Toast;

public class Tools {
    private Context applicationContext;

    public static void exceptionToast(Context context, String error) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();


    }

//    public Context getApplicationContext() {
//        return applicationContext;
    }


