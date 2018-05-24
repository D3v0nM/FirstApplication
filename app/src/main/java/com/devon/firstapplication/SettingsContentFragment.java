package com.devon.firstapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devon.firstapplication.datamodels.AppDatabase;
import com.devon.firstapplication.datamodels.DatabaseSingleton;
import com.devon.firstapplication.entity.Settings;

import java.lang.ref.WeakReference;
import java.util.List;

public class SettingsContentFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "SettingsFragment";
    private int id;

    //Parmeters
    public TextView reminder;
    public TextView maxDist;
    public TextView gender;
    public TextView status;
    public TextView ageRange;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        Log.i(TAG, "onCreateView: started");

        View view = inflater.inflate(R.layout.settings_tab, null);

        reminder = view.findViewById(R.id.reminder);
        maxDist = view.findViewById(R.id.maxSearchDist);
        gender = view.findViewById(R.id.genderSelect);
        status = view.findViewById(R.id.profileStatus);
        ageRange = view.findViewById(R.id.ageRange);

        //SeekBar SeekBar=(SeekBar) findViewById(R.id.SeekBar); // initiate the Seekbar
        //android.widget.SeekBar.setMax(50);// 50 maximum value for the Seek bar



        new GetSettingsTask(this, getActivity(), "0").execute();


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

    /*Add in Oncreate() funtion after setContentView()*/







    private static class GetSettingsTask extends AsyncTask<Void, Void, Settings>{
        private WeakReference<SettingsContentFragment> fragmentWeakReference;
        private String id;

        public GetSettingsTask(SettingsContentFragment fragment, Activity activity, String id){
            fragmentWeakReference = new WeakReference<>(fragment);
            this.id = id;
            // passing in activity??
        }

        @Override
        protected Settings doInBackground(Void...voids){
            SettingsContentFragment fragment = fragmentWeakReference.get();
            if(fragment == null){
                return null;
            }

            AppDatabase db = DatabaseSingleton.getDb(fragment.getContext());


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

            fragment.reminder.setText(settings.getMaxDist());
            fragment.ageRange.setText(settings.getAgeRange());
            fragment.gender.setText(settings.getGender());
            fragment.maxDist.setText(settings.getMaxDist());
            fragment.status.setText(settings.getStatus());

        }
    }

}


