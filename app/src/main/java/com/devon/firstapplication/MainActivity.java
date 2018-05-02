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

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Calendar;
import java.util.regex.Pattern;





public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAnalytics mFirebaseAnalytics; //Future Enhancement

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText userEditText;
    private EditText passEditText;
    private EditText jobEditText;
    private EditText profileEditText;
    private Button  loginBtn;
    private TextView hello_world;
    public TextView age;
    String Age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Obtain the FirebaseAnalytics instance..... in the Future
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //setup params
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        userEditText = findViewById(R.id.userEditText);
        passEditText = findViewById(R.id.passEditText);
        jobEditText = findViewById(R.id.jobEditText);
        profileEditText = findViewById(R.id.profileEditText);
        loginBtn = findViewById(R.id.loginBtn);
        hello_world = findViewById(R.id.hello_world);

        // set-up config for DatePicker Dailog
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
                int bday = c.get(Calendar.DAY_OF_YEAR);

                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);

                if(!oldEnough(year,month,day)) {
                    Tools.toastMessage(MainActivity.this, "You're not 18...or Your math sucks");
                    loginBtn.setVisibility(View.GONE);
                    Age = (bday -(setAge(year,month,day))  + " days till you are 18! \n Visit us then");
                    TextView textView = findViewById(R.id.age);
                    textView.setText(Age);

                }else

                {
                    Age = (setAge(year,month,day)) + " yrs";
                    TextView textView = findViewById(R.id.age);
                    textView.setText(Age);
                }

    }

    public void createProfile(View view) {
        int errors = 0;
        if (nameEditText.getText().toString().length() == 0) {
            nameEditText.setError("Name is required");

            errors += 1;
        }

        if (emailEditText.getText().toString().length() == 0) {
            emailEditText.setError("Email is required");
            errors += 1;
        }

        if (!checkEmail(emailEditText.getText().toString())) {
            emailEditText.setError("Not an Email Address");
            errors += 1;
        }

        if (userEditText.getText().toString().length() == 0) {
            userEditText.setError("Username is required");
            errors += 1;
        }

        if (passEditText.getText().toString().length() == 0 || passEditText.getText().toString().length() < 6) {
            passEditText.setError("Password is required and must be at least 6 characters");
        }

        if (errors < 1) {

            Intent intent = new Intent(MainActivity.this, ProfileContentFragment.class);
            intent.putExtra(Constraints.KEY_NAME, nameEditText.getText().toString());
            intent.putExtra(Constraints.KEY_EMAIL, emailEditText.getText().toString());
            intent.putExtra(Constraints.KEY_USER, userEditText.getText().toString());
            intent.putExtra(Constraints.KEY_AGE, Age.toString());
            intent.putExtra(Constraints.KEY_PASS, passEditText.getText().toString());
            intent.putExtra(Constraints.KEY_JOB, jobEditText.getText().toString());
            intent.putExtra(Constraints.KEY_PROFILE, profileEditText.getText().toString());

            // Create a new Fragment to be placed in the activity layout
            ProfileContentFragment firstFragment = new ProfileContentFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the Layout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.coordinator_profile, firstFragment).commit();


        }

     else
        Tools.toastMessage(this, "Errors found. Fix errors and try again");
    }



    @Override
    protected void onRestart(){
        super.onRestart();
        //log restart tasks
        Log.i(TAG, "onRestart triggered");

    }

    @Override
    protected void onStart(){
        super.onStart();
        //log onStart tasks
        Log.i(TAG, "onStart() triggered");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        //Log Restore state tasks
        Log.i(TAG, "onRestoreInstanceState() triggered");

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
//        if(savedInstanceState.containsKey(Constraints.KEY_AGE)){
//            age.setText((String) savedInstanceState.get(Age));
//        }
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
        Log.i(TAG, "onSaveInstanceState() triggered");

        outState.putString(Constraints.KEY_NAME, nameEditText.getText().toString());
        outState.putString(Constraints.KEY_EMAIL, emailEditText.getText().toString());
        outState.putString(Constraints.KEY_USER, userEditText.getText().toString());
       //outState.putString(Constraints.KEY_AGE,  Age.toString());
        outState.putString(Constraints.KEY_BUTTON_TXT, loginBtn.getText().toString());
        outState.putString(Constraints.KEY_PASS , passEditText.getText().toString());
        outState.putString(Constraints.KEY_JOB, jobEditText.getText().toString());
        outState.putString(Constraints.KEY_PROFILE, profileEditText.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Log resume tasks
        Log.i(TAG, "onResume() triggered");
    }

    @Override
    protected void onPause(){
        super.onPause();

        //Log pause actions
        Log.i(TAG, "onPause() triggered");

    }

    @Override
    protected  void onStop(){
        super.onStop();

        //log stop activity
        Log.i(TAG, "onStop() triggered");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();

        //Log destroy activity
        Log.i(TAG, "onDestroy triggered");

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



        if (curryear - year == 18 && currmonth <= month && currday <= day) {
            return now.get(Calendar.DAY_OF_YEAR);
        }

        return  curryear - year;

    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


}
