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

import java.util.Calendar;

// import com.google.firebase.analytics.FirebaseAnalytics; Future enhancement





public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText userEditText;
    private EditText passEditText;
    private EditText jobEditText;
    private EditText profileEditText;
    private Button  loginBtn;
    private TextView hello_world;
    public TextView age;
   // private FirebaseAnalytics mFirebaseAnalytics; Future Enhancement



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Obtain the FirebaseAnalytics instance..... in the Future
      //  mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //setup params
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameEditText);
        if(nameEditText.getText().toString().length() == 0)
            nameEditText.setError("Name is required");
        emailEditText = findViewById(R.id.emailEditText);
        if(emailEditText.getText().toString().length() ==0)
            emailEditText.setError("Email is required");
        userEditText = findViewById(R.id.userEditText);
        if(userEditText.getText().toString().length() == 0)
            userEditText.setError("Username is required");
        passEditText = findViewById(R.id.passEditText);
        if(passEditText.getText().toString().length() == 0 || passEditText.getText().toString().length() < 6)
            passEditText.setError("Password is required and must be at least 6 characters");
        jobEditText = findViewById(R.id.jobEditText);
        profileEditText = findViewById(R.id.profileEditText);


        loginBtn = findViewById(R.id.loginBtn);
        hello_world = findViewById(R.id.hello_world);

        final Button birthday = findViewById(R.id.birthday);
        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment agePicker = new AgePicker();
                agePicker.show(getSupportFragmentManager(), "age picker");
                birthday.setVisibility(View.GONE);
            }
        });

        //log onCreate tasks
        Log.i(TAG, "onCreate() Started");
    }



        @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        //logic for age or error message
                Calendar c = Calendar.getInstance();
                Calendar now = Calendar.getInstance();
           // int curryear = now.get(Calendar.YEAR);
            int currday = now.get(Calendar.DAY_OF_YEAR);
            String age;

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);

                if(!oldEnough(year,month,day)) {

                    Tools.toastMessage(MainActivity.this, "You're not 18...or Your math sucks");
                    loginBtn.setVisibility(View.GONE);

                    age = currday - (setAge(year,month,day)) + " days till you are 18!";

                    TextView textView = findViewById(R.id.age);

                    textView.setText(age);
                }else

                {
                    age = (setAge(year,month,day)) + " years old";


                    TextView textView = findViewById(R.id.age);

                    textView.setText(age);
                }

    }


    public void goToSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constraints.KEY_NAME, nameEditText.getText().toString());
        intent.putExtra(Constraints.KEY_EMAIL, emailEditText.getText().toString());
        intent.putExtra(Constraints.KEY_USER, userEditText.getText().toString());
        intent.putExtra(Constraints.KEY_AGE, age.getText().toString());
        intent.putExtra(Constraints.KEY_PASS, passEditText.getText().toString());
        intent.putExtra(Constraints.KEY_JOB, jobEditText.getText().toString());
        intent.putExtra(Constraints.KEY_PROFILE, profileEditText.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //log restart tasks
        Log.i(TAG, "onRestart init");

    }

    @Override
    protected void onStart(){
        super.onStart();
        //log onStart tasks
        Log.i(TAG, "onStart() init");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        //Log Restore state tasks
        Log.i(TAG, "onRestoreInstanceState() init");

        if(savedInstanceState.containsKey(Constraints.KEY_NAME)){
            nameEditText.setText((String) savedInstanceState.get(Constraints.KEY_NAME));
        }
        if(savedInstanceState.containsKey(Constraints.KEY_EMAIL)){
            emailEditText.setText((String) savedInstanceState.get(Constraints.KEY_EMAIL));
        }
        if(savedInstanceState.containsKey(Constraints.KEY_USER)){
            userEditText.setText((String) savedInstanceState.get(Constraints.KEY_USER));
        }
        if(savedInstanceState.containsKey(Constraints.KEY_BUTTON_TXT)){
            loginBtn.setText((String) savedInstanceState.get(Constraints.KEY_BUTTON_TXT));
        }
        if(savedInstanceState.containsKey(Constraints.KEY_AGE)){
            age.setText((String) savedInstanceState.get(Constraints.KEY_AGE));
        }
        if(savedInstanceState.containsKey(Constraints.KEY_PASS)){
            passEditText.setText((String) savedInstanceState.get(Constraints.KEY_PASS));
        }
        if(savedInstanceState.containsKey(Constraints.KEY_JOB)){
            jobEditText.setText((String) savedInstanceState.get(Constraints.KEY_JOB));

        }
        if (savedInstanceState.containsKey(Constraints.KEY_PROFILE)){
            profileEditText.setText((String) savedInstanceState.get(Constraints.KEY_PROFILE));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        //Log bundle transfer tasks
        Log.i(TAG, "onSaveInstanceState() init");

        outState.putString(Constraints.KEY_NAME, nameEditText.getText().toString());
        outState.putString(Constraints.KEY_EMAIL, emailEditText.getText().toString());
        outState.putString(Constraints.KEY_USER, userEditText.getText().toString());
        outState.putString(Constraints.KEY_AGE, age.getText().toString());
        outState.putString(Constraints.KEY_BUTTON_TXT, loginBtn.getText().toString());
        outState.putString(Constraints.KEY_PASS , passEditText.getText().toString());
        outState.putString(Constraints.KEY_JOB, jobEditText.getText().toString());
        outState.putString(Constraints.KEY_PROFILE, profileEditText.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Log resume tasks
        Log.i(TAG, "onResume() init");
    }

    @Override
    protected void onPause(){
        super.onPause();

        //Log pause actions
        Log.i(TAG, "onPause() init");

    }

    @Override
    protected  void onStop(){
        super.onStop();

        //log stop activity
        Log.i(TAG, "onStop() init");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();

        //Log destroy activity
        Log.i(TAG, "onDestroy init");

    }


    //Fist homework menu tasks: Maybe add create task to menu?
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

    /**
     * Helper method to calculate if user is over 18
     * @param year year passed from DatePicker
     * @param month  month passed from DatePicker
     * @param day day passed from DatePicker
     * @return boolean if age entered in DatePicker is over 18
     * @throws IllegalArgumentException
     */
    public boolean oldEnough(int year, int month, int day) throws IllegalArgumentException {

        Calendar now = Calendar.getInstance();
        boolean age = false;

        int curryear = now.get(Calendar.YEAR);
        int currmonth = now.get(Calendar.MONTH) + 1;
        int currday = now.get(Calendar.DAY_OF_MONTH);

        if (curryear - year > 18) {
            age = true;
        }

        if (curryear - year == 17 && currmonth >= month && currday >= day){
            age = true;
        }

        return age;


    }

    /**
     * Takes boolean from oldEnough and if true calculates age to be printed on screen
     * If false calculates num days until 18yr old
     * @param year year int passed from DatePicker
     * @param month month int passed from DatePicker
     * @param day day int passed from date picker
     * @return age in years or days until 18
     */
    public int setAge(int year, int month, int day){
        Calendar now = Calendar.getInstance();
        int curryear = now.get(Calendar.YEAR);
        int currmonth = now.get(Calendar.MONTH) + 1;
        int currday = now.get(Calendar.DAY_OF_MONTH);


        if (curryear - year == 17 && currmonth <= month && currday <= day) {
            return now.get(Calendar.DAY_OF_YEAR);
        }

        return  curryear - year;

    }
}
