package com.devon.firstapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.devon.firstapplication.datamodels.AppDatabase;
import com.devon.firstapplication.datamodels.DatabaseSingleton;
import com.devon.firstapplication.entity.Settings;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

public class SettingsContentFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "SettingsFragment";
    private int id;

    //Parmeters Need to add TextViews and saved strings for best practices
    public EditText reminder;
    public Spinner maxDist;
    public Spinner gender;
    public Switch status;
    public EditText ageStart;
    public EditText ageEnd;
    public Button apply;

    public String genderPick;
    public String maxDistPick;
    public String statusick;

    ArrayAdapter<CharSequence> distAdapter;
    ArrayAdapter<CharSequence> genderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        Log.i(TAG, "onCreateView: started");

        View view = inflater.inflate(R.layout.settings_tab, null);

        maxDist = view.findViewById(R.id.maxSearchSpin);
        reminder = view.findViewById(R.id.reminder);
        gender = view.findViewById(R.id.gender_group);
        status = view.findViewById(R.id.status_switch);
        ageStart = view.findViewById(R.id.ageStart);
        ageEnd = view.findViewById(R.id.ageEnd);
        apply = view.findViewById(R.id.applyBtn);

        distAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.miles, android.R.layout.simple_spinner_dropdown_item);
        distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxDist.setAdapter(distAdapter);

        genderAdapter = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.genders, android.R.layout.simple_spinner_dropdown_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);



        if(saveInstanceState == null) {
            new GetSettingsTask(this, getActivity(), "0").execute();
        }

        apply.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Settings settings = new Settings();

                 if(status.isChecked()){
                     boolean statusPublic = status.isChecked();
                     settings.setStatus(statusPublic);
                 }else{
                settings.setStatus(false);
            }
             if(!ageStart.getText().toString().isEmpty()){
                 int savedAgeStart = Integer.parseInt(ageStart.getText().toString());
                 settings.setAgeStart(savedAgeStart);
                }

            if (!ageEnd.getText().toString().isEmpty()){
                     int savedAgeEnd = Integer.parseInt(ageEnd.getText().toString());
                     settings.setAgeEnd(savedAgeEnd);
                }

            if (!reminder.getText().toString().isEmpty()){
                     settings.setReminderTime(reminder.getText().toString());
            }

            genderPick = gender.getSelectedItem().toString();
             settings.setGender(genderPick);

             maxDistPick = maxDist.getSelectedItem().toString();
             settings.setMaxDist(maxDistPick);

            new UpdateTask(getActivity(), settings).execute();

            }
        });

//        if(saveInstanceState != null){
//
//
//        }

        return view;
    }

//    //this whole bit needs to updated or could be unnecessary
//    private void updateDatabase(View view) {
//        Settings fakeNewUser = new Settings();
//        int id = this.id; // how to call this since not visible
//        if (id == (0)) {
//            id += 1;
//        }
//        fakeNewUser.setReminderTime(reminder);
//        fakeNewUser.setGender(gender);
//        fakeNewUser.setMaxDist(maxDist);
//        fakeNewUser.setStatus(status);
//        fakeNewUser.setAgeRange(ageRange);
//

//    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("reminder", reminder.getText().toString());
        outState.putInt("MaxDistance", maxDist.getSelectedItemPosition());
        outState.putInt("Gender", gender.getSelectedItemPosition());
        outState.putString("StartAge", ageStart.getText().toString());
        outState.putString("EndAge", ageEnd.getText().toString());
        Log.i(TAG, "onSaveInstanceState: Setting tab saved bundle started");
    }

    public String RadioButtonClicked(View view) {

//This variable will store whether the user was male or female
        String selected = "";
// Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Was complaining that this was required
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
      //  complaining this was required.

    }


    private class GetSettingsTask extends AsyncTask<Void, Void, Settings>{
        private WeakReference<SettingsContentFragment> fragmentWeakReference;
        private WeakReference<Activity> weakActivity;
        private SettingsContentFragment fragment;
        private String id;

        public GetSettingsTask(SettingsContentFragment fragment, Activity activity, String id){
            fragmentWeakReference = new WeakReference<>(fragment);
            weakActivity = new WeakReference<>(activity);
            this.id = id;
            // passing in activity??
        }

        @Override
        protected Settings doInBackground(Void...voids){
            Activity activity = weakActivity.get();
            if(activity == null){
                return null;
            }

            AppDatabase db = DatabaseSingleton.getDb(activity.getApplicationContext());


            List<Settings> settings = db.settingsDao().loadAllByIds(id);

            if(settings.size()<= 0 || settings.get(0) == null) {
                return null;
            }
            return settings.get(0);
        }

        @Override
        protected void onPostExecute(Settings settings){
            SettingsContentFragment fragment = fragmentWeakReference.get();
            if(settings == null || fragment == null){
                return;
            }

            fragment.reminder.setText(settings.getReminderTime());
            fragment.ageStart.setText(Integer.toString(settings.getAgeStart()));
            fragment.ageEnd.setText(Integer.toString(settings.getAgeEnd()));

            String genderCompare = settings.getGender();
            if(settings.getGender() != null){
                int position = fragment.genderAdapter.getPosition(genderCompare);
                fragment.gender.setSelection(position);
            }

            String distCompare = settings.getMaxDist();
            if(distCompare != null){
                int position =  fragment.distAdapter.getPosition(distCompare);
                fragment.maxDist.setSelection(position);
            }

            if(settings.getStatus())
            fragment.status.toggle();

        }
    }

    private static class UpdateTask extends AsyncTask<Void, Void,Settings>{
        private WeakReference<Activity> weakActivity;
        private Settings settings;

        public UpdateTask(Activity activity, Settings settings){
            weakActivity = new WeakReference<>(activity);
            this.settings = settings;

        }

        @Override
        protected Settings doInBackground(Void...voids){
            Activity activity = weakActivity.get();
            if(activity == null){
                return null;
            }
            AppDatabase db = DatabaseSingleton.getDb(activity.getApplicationContext());

            db.settingsDao().insertAll(settings);
            return  settings;
        }

    }

}


