package com.devon.firstapplication;

import android.content.Intent;
import android.os.Bundle;
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
    public ProfileContentFragment(){

    }

  private Button logoutBtn;
    TextView profileView;
    TextView nameView;
    TextView jobView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.profile_tab, container, true);
        ImageView profileImage = view.findViewById(R.id.profileImage);

        nameView = view.findViewById(R.id.nameAndAge);
        jobView = view.findViewById(R.id.job);
        profileView = view.findViewById(R.id.profileText);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        profileImage.setImageResource(R.drawable.lowered_expectations);

        //Bundle loadText = new Bundle();

        //Bundle error handling. Hopfully.
        assert getArguments() != null;
        //Get Argument that passed from  second activity in "args" key value
        if (getArguments().containsKey("name")) {
            String getName = getArguments().getString("name");
            nameView.setText(getName);

            Log.i(TAG, "onCreateView: Name = " +  getName);
        }
    else{
            Tools.toastMessage(getActivity(), "Name is Missing");
        }


        if(getArguments().containsKey("job")){
         String getJob = getArguments().getString("job");
            jobView.setText(getJob);
            Log.i(TAG, "onCreateView: Job is " + getJob);
        }else{
            Tools.toastMessage(getActivity(), "Occupation is missing");
        }

        if(getArguments().containsKey("job")) {
            String getProfile = getArguments().getString("profile");
            profileView.setText(getProfile);
            Log.i(TAG, "onCreateView: Profile says " + getProfile);
        }
        else {
            Tools.toastMessage(getActivity(), "Profile Text is missing");
        }

        return view;
    }



    public void goBack(View view) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        logoutBtn.setText(R.string.logout);
        //onBackPressed();
        startActivity(intent);
    }

    @Override
    public void onViewStateRestored(Bundle saveInstanceState){
        super.onViewStateRestored(saveInstanceState);
    }


    @Override
    public void onStart(){
        super.onStart();
        //logging second onStart tasks
        Log.d(TAG, "Profile onStart triggered ");
    }

    @Override
     public void onResume(){
        super.onResume();
        //Logging second onResume tasks
        Log.d(TAG, "Profile onResume triggered");
    }


    @Override
     public void onDestroyView(){
        super.onDestroyView();
        //logging second onDestroy tasks
        Log.d(TAG, "onDestroy init");
    }

}


