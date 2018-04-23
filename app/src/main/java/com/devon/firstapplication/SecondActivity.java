package com.devon.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private Button logoutBtn;

    public static final String TAG = SecondActivity.class.getSimpleName();
    TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);
        logoutBtn = findViewById(R.id.loginBtn);

        StringBuilder msg =  new StringBuilder("Welcome \n");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        ImageView profileImage = findViewById(R.id.profileImage);
        int imageResource = getResources().getIdentifier("@drawable/lowered-expectations",
                null, this.getPackageName());
        profileImage.setImageResource(imageResource);

        // this needs to be converted to a layout
        assert b != null;
        if(b.containsKey(Constraints.KEY_NAME)){
            String name = b.getString(Constraints.KEY_NAME);
            msg.append(name).append("\n");
            //log KeY_name changes
            Log.i(TAG, "Name: " + name );

        }
        if (b.containsKey(Constraints.KEY_USER)) {
            String user = b.getString(Constraints.KEY_USER);
            msg.append(user).append(" is your handle for our site \n");
            //log username append
            Log.i(TAG, "Username: " + user);
        }

        if(b.containsKey(Constraints.KEY_AGE)){
            String age = b.getString(Constraints.KEY_AGE);
            msg.append(age).append("yrs \n");
            //logging age append tasks
            Log.i(TAG, "Age: " + age);

        }
        if(b.containsKey(Constraints.KEY_JOB)){
            String job = b.getString(Constraints.KEY_JOB);
            msg.append(job).append("\n");
            Log.i(TAG, "Job: " + job);
        }
        if(b.containsKey(Constraints.KEY_PROFILE)){
            String profile = b.getString(Constraints.KEY_PROFILE);
            msg.append(profile).append("What dates will know about you: \n");
        }



        textView.setText(msg);
        //log second activity onCreate
        Log.d(TAG, "onCreate() Started");
    }
    public void goBack(View view){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
       logoutBtn.setText(R.string.logout); //Logout of secondActivity view?
        startActivity(intent);

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //logging second onRestart tasks
        Log.d(TAG, "onRestart() init");
    }

    @Override
    protected void onStart(){
        super.onStart();
        //logging second onStart tasks
        Log.d(TAG, "onStart init");
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Logging second onResume tasks
        Log.d(TAG, "onResume init");
    }

    @Override
    protected void onPause(){
        super.onPause();
        //logging second onPause() tasks
        Log.d(TAG, "onPause init");
    }

    @Override
    protected void onStop(){
        super.onStop();
        //logging second onStop tasks
        Log.d(TAG, "onStop() init");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //logging second onDestroy tasks
        Log.d(TAG, "onDestroy init");
    }






}
