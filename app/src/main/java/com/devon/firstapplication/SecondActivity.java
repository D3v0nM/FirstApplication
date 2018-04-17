package com.devon.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = SecondActivity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        StringBuilder msg =  new StringBuilder("Hello \n");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        assert b != null;
        if(b.containsKey(Constraints.KEY_NAME)){
            String name = b.getString(Constraints.KEY_NAME);
            msg.append(name).append("\n");
            //log KeY_name changes
            Log.i(TAG, "Name: " + name );

        }

        if(b.containsKey(Constraints.KEY_AGE)){
            int age = b.getInt(Constraints.KEY_AGE);
            msg.append(age).append(" years old");
            //logging age append tasks
            Log.i(TAG, "Age: " + age);

        }

        textView.setText(msg);
        //log second activity onCreate
        Log.i(TAG, "onCreate()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //logging second onRestart tasks
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        //logging second onStart tasks
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Logging second onResume tasks
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        //logging second onPause() tasks
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        //logging second onStop tasks
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //logging second onDestroy tasks
        Log.i(TAG, "onDestroy");
    }






}
