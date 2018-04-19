package com.devon.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        if(b.containsKey(Constraints.KEY_EMAIL)){
            int age = b.getInt(Constraints.KEY_EMAIL);
            msg.append(age).append(" is your email \n");
            //logging age append tasks
            Log.i(TAG, "Age: " + age);

        }

        if (b.containsKey(Constraints.KEY_USER)) {
            String user = b.getString(Constraints.KEY_USER);
            msg.append(user).append(" is your username \n");
            //log username append
            Log.i(TAG, "Username: " + user);
        }

        textView.setText(msg);
        //log second activity onCreate
        Log.i(TAG, "onCreate()");
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
