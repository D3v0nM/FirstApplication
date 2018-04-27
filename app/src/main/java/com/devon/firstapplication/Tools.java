package com.devon.firstapplication;

import android.content.Context;
import android.widget.Toast;



public class Tools {
    private Context applicationContext;

    /**
     * Toast helper class
     * @param activity Context field for activity class toast message will display
     * @param message message to be displayed
     */
    public static void toastMessage(Context activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();


    }

//    public Context getApplicationContext() {
//        return applicationContext;
    }


