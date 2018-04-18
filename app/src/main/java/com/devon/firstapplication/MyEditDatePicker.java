package com.devon.firstapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class MyEditDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    public EditText dob;
    private int day;
    private int month;
    private int year;
    private Context context;

    public MyEditDatePicker(Context context, EditText dob) {
        Activity act = Activity(context);
        this.dob = act.findViewById(R.id.birthday);
        this.dob.setOnClickListener(this);
        this.context = context;

    }

    //unnecessary??
    @Override
    public  void onSetDate(DatePicker view, int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        updateDisplay(); //??

    }

    @Override
}
