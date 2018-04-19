package com.devon.firstapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
        oldEnough(month, day, year);
        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }

    //    //age verification helper methods....Maybe needs to be it's own class/interface

    public boolean oldEnough(int month, int day, int year) {
        Calendar now = Calendar.getInstance();

        int curryear = now.get(Calendar.YEAR);
        int currmonth = now.get(Calendar.MONTH) + 1;
        int currday = now.get(Calendar.DAY_OF_MONTH);

        if (curryear - year <= 18 && currmonth < month && currday < day) {

            throw new IllegalArgumentException("You're not 18..or your math sucks");
        }
        if (curryear - year < 18) {

            throw new IllegalArgumentException("You're not 18..or your math sucks");
        }
        return true;

    }
}
