package com.devon.firstapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class AgePicker extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Dialog d = new  DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);

        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLUE));


        return d ;

    }

    //    //age verification helper methods....Maybe needs to be it's own class/interface


}


//    IllegalArgumentException(String message){
//
//        message = "You're not 18..or your math sucks";
//    }


