package com.devon.firstapplication;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;
//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = MainActivity.class.getSimpleName();


    private TextView textView;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText userEditText;
    private EditText dobEditText;
    private Button  loginBtn;
    private TextView hello_world;
    //private FirebaseAnalytics mFirebaseAnalytics;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Obtain the FirebaseAnalytics instance.
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //setup params
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        userEditText = findViewById(R.id.userEditText);
        //dobEditText = findViewById(R.id.birthday);
        loginBtn = findViewById(R.id.loginBtn);
        hello_world = findViewById(R.id.hello_world);

        final Button button = findViewById(R.id.birthday);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment agePicker = new AgePicker();
                agePicker.show(getSupportFragmentManager(), "age picker");
                button.setVisibility(View.GONE);
            }
        });

        //log onCreate tasks
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar. DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());

        TextView textView = findViewById(R.id.age);
        textView.setText(currentDateString);

    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constraints.KEY_NAME, nameEditText.getText().toString());
        intent.putExtra(Constraints.KEY_EMAIL, emailEditText.getText().toString());
        intent.putExtra(Constraints.KEY_USER, userEditText.getText().toString());
        startActivity(intent);
    }
    public void onLogin(View view){
        loginBtn.setText(R.string.logout); //Logout of secondActivity view?

        //Welcome message on login?
        textView.setText(String.format(getString(R.string.Welcome), nameEditText.getText()));
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //log restart tasks
        Log.i(TAG, "onRestart");

    }

    @Override
    protected void onStart(){
        super.onStart();
        //log onstart tasks
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        //Log Restore state tasks
        Log.i(TAG, "onRestoreInstanceState()");

        if(savedInstanceState.containsKey(Constraints.KEY_NAME)){
            textView.setText((String) savedInstanceState.get(Constraints.KEY_NAME));
        }

        if(savedInstanceState.containsKey(Constraints.KEY_BUTTON_TXT)){
            textView.setText((String) savedInstanceState.get(Constraints.KEY_BUTTON_TXT));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        //Log bundle transfer tasks
        Log.i(TAG, "onSaveInstanceState()");

        outState.putString(Constraints.KEY_NAME, textView.getText().toString());
        outState.putString(Constraints.KEY_BUTTON_TXT, loginBtn.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Log resume tasks
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();

        //Log pause actions
        Log.i(TAG, "onPause()");

    }

    @Override
    protected  void onStop(){
        super.onStop();

        //log stop activity
        Log.i(TAG, "onStop()");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();

        //Log destroy activity
        Log.i(TAG, "onDestroy");

    }


    //Fist homework menu tasks
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.action_bar) {
            this.hello_world.setText(R.string.menu1);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }



//    //age verification helper methods....Maybe needs to be it's own class/interface
//    private int getLinearDate(int month, int day, int, int year){
//
//        int linearDate = (((year * 100)+ month) * 100)+ day;
//
//        return linearDate;
//    }
//
//    // remove 3rd line only needs to be today-dob >= 18
//    public boolean oldEnough(int month, int day, int year){
//        int linearDOB = getLinearDate(monthDOB, dayDOB, yearDOB);
//        int linearQuery = getLinearDate(month, day, year);
//
//        return true;
//    }



}
