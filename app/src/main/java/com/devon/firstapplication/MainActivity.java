package com.devon.firstapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;
//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView textView;
    private EditText editText;
    private Button  loginBtn;


    //private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Obtain the FirebaseAnalytics instance.
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //setup params
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.nameEditText);
        loginBtn = findViewById(R.id.loginBtn);

        //log onCreate tasks
        Log.i(TAG, "onCreate()");
    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constraints.KEY_NAME, editText.getText().toString());
        intent.putExtra(Constraints.KEY_AGE, 21);
        startActivity(intent);
    }
    public void onLogin(View view){
        loginBtn.setText(R.string.logout); //Logout of secondActivity view?

        //Welcome message on login?
        textView.setText(String.format(getString(R.string.Welcome), editText.getText()));
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
            this.hello_world.setText(R.string.great);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
