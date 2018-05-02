package com.devon.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private Button logoutBtn;
    private FragmentManager manager;

    public static final String TAG = SecondActivity.class.getSimpleName();
    TextView profileView;
    TextView nameView;
    TextView jobView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // nameView = findViewById(R.id.nameAndAge);
        //jobView =   findViewById(R.id.job);
        //profileView = findViewById(R.id.profileText);
        //logoutBtn = findViewById(R.id.logoutBtn);



        //Adding toolbar to Main Screen requires V7??
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null){ setSupportActionBar(toolbar);}

        //add tabs
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Profile"));
        tabs.addTab(tabs.newTab().setText("Matches"));
        tabs.addTab(tabs.newTab().setText("Settings"));

        //Setting ViewPager for each Tab
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        //Set Tabs inside ViewPager
        tabs.setupWithViewPager(viewPager);


      //  ImageView profileImage = findViewById(R.id.profileImage);
        int imageResource = getResources().getIdentifier("@drawable/lowered-expectations",
               null, this.getPackageName());
       // profileImage.setImageResource(R.drawable.lowered_expectations);

        Log.i(TAG, "onCreate: SetImage Stated");

        StringBuilder nameAgeMsg = new StringBuilder("");
        Intent nameAge = getIntent();
        Bundle b1 = nameAge.getExtras();

      //Bundle to profile page handling
        assert b1 != null;
        if(b1.containsKey(Constraints.KEY_USER)){
            String name = b1.getString(Constraints.KEY_USER);
            nameAgeMsg.append(name).append("\n");
            //log KeY_name changes
            Log.i(TAG, "handle: " + name );

        }
        if(b1.containsKey(Constraints.KEY_AGE)){
            String age = b1.getString(Constraints.KEY_AGE);
            nameAgeMsg.append(age);
            //logging age append tasks
            Log.i(TAG, "Age: " + age);

        }


        StringBuilder jobMsg =  new StringBuilder("Occupation:\n");
        Intent JOBS = getIntent();
        Bundle b2 = JOBS.getExtras();

        //Bundle to profile page handling
        assert b2 != null;
        if(b2.containsKey(Constraints.KEY_JOB)){
            String jobs = b2.getString(Constraints.KEY_JOB);
            jobMsg.append(jobs);
            Log.i(TAG, "Job: " + jobs);
        }

        //maybe set the "Details" as it's own string to enlarge and BOLD
        StringBuilder profileMsg =  new StringBuilder("Details you need to know: \n");
        Intent profiles = getIntent();
        Bundle b3 = profiles.getExtras();

        //Bundle to profile page handling
        assert b3 != null;
        if(b3.containsKey(Constraints.KEY_PROFILE)){
            String profile = b3.getString(Constraints.KEY_PROFILE);
            profileMsg.append("\n").append(profile);
        }

        Bundle args = new Bundle();
        args.putString("name",  nameAgeMsg.toString());
       args.putString("job", jobMsg.toString());
       args.putString("profile", profileMsg.toString());




        ProfileContentFragment startFrag = new ProfileContentFragment();
        startFrag.setArguments(args);

    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.profileLayout, startFrag);
        fragmentTransaction.commit();

        //log second activity onCreate
        Log.d(TAG, "onCreate() Started");
    }

    //Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager){
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileContentFragment(), "Profile");
        adapter.addFragment(new MatchesContentFragment(), "Matches");
        adapter.addFragment(new SettingsContentFragment(), "Settings");
        viewPager.setAdapter(adapter);

    }

    /**
     * Logout/ back button handling
     * @param view is current activity view
     */
    public void goBack(View view){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
       logoutBtn.setText(R.string.logout);
       //onBackPressed();
        startActivity(intent);

    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Log.d(TAG, "onRestoreInstanceState: Restore stated");

        if(saveInstanceState.containsKey(Constraints.KEY_USER)){
            nameView.setText((String) saveInstanceState.get(Constraints.KEY_USER));
        }
        if(saveInstanceState.containsKey(Constraints.KEY_BUTTON_TXT)){
            logoutBtn.setText((String) saveInstanceState.get(Constraints.KEY_BUTTON_TXT));
        }
//        if(savedInstanceState.containsKey(Constraints.KEY_AGE)){
//            age.setText((String) savedInstanceState.get(Age));
//        }

        if(saveInstanceState.containsKey(Constraints.KEY_JOB)){
            jobView.setText((String) saveInstanceState.get(Constraints.KEY_JOB));

        }
        if (saveInstanceState.containsKey(Constraints.KEY_PROFILE)) {
            profileView.setText((String) saveInstanceState.get(Constraints.KEY_PROFILE));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: saving state");

        outState.putString(Constraints.KEY_USER, nameView.getText().toString());
        //outState.putString(Constraints.KEY_AGE,  Age.toString());
        outState.putString(Constraints.KEY_BUTTON_TXT, logoutBtn.getText().toString());
        outState.putString(Constraints.KEY_JOB, jobView.getText().toString());
        outState.putString(Constraints.KEY_PROFILE, profileView.getText().toString());

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //logging second onRestart tasks
        Log.d(TAG, "onRestart() started");
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
        Log.d(TAG, "onResume started");
    }

    @Override
    protected void onPause(){
        super.onPause();
        //logging second onPause() tasks
        Log.d(TAG, "onPause started");
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
