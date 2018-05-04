package com.devon.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.devon.firstapplication.SecondActivity.TAG;

/**
 * Provies UI for the View Profile
 */
public class ProfileContentFragment extends android.support.v4.app.Fragment {
    public ProfileContentFragment() {

    }

    private Button logoutBtn;
    TextView profileView;
    TextView nameView;
    TextView jobView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.profile_tab, container, false);

        ImageView profileImage = view.findViewById(R.id.profileImage);

        nameView = view.findViewById(R.id.nameAndAge);
        jobView = view.findViewById(R.id.job);
        profileView = view.findViewById(R.id.profileText);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        profileImage.setImageResource(R.drawable.lowered_expectations);

        StringBuilder nameAgeMsg = new StringBuilder("");
        Intent nameAge = getActivity().getIntent();
        Bundle b1 = nameAge.getExtras();

        //Bundle to profile page handling
        assert b1 != null;
        if (b1.containsKey(Constraints.KEY_USER)) {
            String name = b1.getString(Constraints.KEY_USER);
            nameAgeMsg.append(name).append("\n");
            //log KeY_name changes

            Log.i(TAG, "onCreateView: Name = " + name);

            if(b1.containsKey(Constraints.KEY_AGE)){
                String age = b1.getString(Constraints.KEY_AGE);
                nameAgeMsg.append(age);
                //logging age append tasks
                Log.i(TAG, "Age: " + age);

            }
        } else {
            Tools.toastMessage(getActivity(), "Name or or age is Missing");
            }


        StringBuilder jobMsg = new StringBuilder("Occupation:\n");
        Intent JOBS = getActivity().getIntent();
        Bundle b2 = JOBS.getExtras();

        //Bundle to profile page handling
        assert b2 != null;
        if (b2.containsKey(Constraints.KEY_JOB)) {
            String jobs = b2.getString(Constraints.KEY_JOB);
            jobMsg.append(jobs);
            Log.i(TAG, "onCreateView: Job is " + jobs);

        } else {
            Tools.toastMessage(getActivity(), "Occupation is missing");
        }

        //maybe set the "Details" as it's own string to enlarge and BOLD
        StringBuilder profileMsg = new StringBuilder("Details you need to know: \n");
        Intent profiles = getActivity().getIntent();
        Bundle b3 = profiles.getExtras();

        //Bundle to profile page handling
        assert b3 != null;
        if (b3.containsKey(Constraints.KEY_PROFILE)) {
            String profile = b3.getString(Constraints.KEY_PROFILE);
            profileMsg.append(profile);

            Log.i(TAG, "onCreateView: Profile says " + profileMsg);
        } else {
            Tools.toastMessage(getActivity(), "Profile Text is missing");
        }

        //Add to TextViews
        nameView.setText(nameAgeMsg);
        jobView.setText(jobMsg);
        profileView.setText(profileMsg);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void goBack (View view){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        logoutBtn.setText(R.string.logout);
        startActivity(intent);
    }


    @Override
    public void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: Saving View State");

        outState.putString(Constraints.KEY_USER, nameView.getText().toString());
        //outState.putString(Constraints.KEY_AGE,  Age.toString());
        outState.putString(Constraints.KEY_BUTTON_TXT, logoutBtn.getText().toString());
        outState.putString(Constraints.KEY_JOB, jobView.getText().toString());
        outState.putString(Constraints.KEY_PROFILE, profileView.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach()");
    }
}

