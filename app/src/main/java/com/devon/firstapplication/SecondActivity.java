package com.devon.firstapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devon.firstapplication.models.EachMatch;
import com.devon.firstapplication.viewmodels.MatchesViewModel;


public class SecondActivity extends AppCompatActivity implements OnListFragmentInteractionListener{
  public static final String TAG = SecondActivity.class.getSimpleName();

  //location params
  LocationManager locationManager;
  double latGPS, lonGPS;
  double latNet,  lonNet;
    TextView latValueGPS, lonValueGPS;
    TextView latValueNet, lonValueNet;


    //Firebase vals
    private MatchesViewModel viewModel;
    private EditText newMatchText;
    private Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        viewModel = new MatchesViewModel();
        mAdapter = new Adapter(getSupportFragmentManager());
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);



       // latValueBest = findViewById(R.id.latValueBest);
       // lonValueBest = findViewById(R.id.lonValueBest);
        latValueGPS = findViewById(R.id.latValueGPS);
        lonValueGPS = findViewById(R.id.lonValueGPS);
        latValueNet = findViewById(R.id.latValueNet);
        lonValueNet = findViewById(R.id.lonValueNet);


        //Adding toolbar to Main Screen
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null){ setSupportActionBar(toolbar);}

        //add tabs
        TabLayout tabs = findViewById(R.id.tabs);

        //Setting ViewPager for each Tab
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        //Set Tabs inside ViewPager
        tabs.setupWithViewPager(viewPager);



        Log.d(TAG, "Second Activity onCreate() Started");
    }

        private boolean checkLocation() {
            if (!isLocationEnabled()){
                showAlert();
            }
            Log.i(TAG, "checkLocation: returned");
            return isLocationEnabled();

        }

        private boolean isLocationEnabled() {
            Log.i(TAG, "isLocationEnabled: returned");
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        }

        private void showAlert() {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.enable_location)
                    .setMessage(getString(R.string.location_message))
                    .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    })
                    .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
            dialog.show();
        }

        public void toggleGPSUpdates(View view) {
            if (!checkLocation()){
                return;

            }

            Button button = (Button) view;
            if (button.getText().equals(getResources().getString(R.string.pause))) {
                locationManager.removeUpdates(locationListenerGPS);
                button.setText(R.string.resume);
                Log.i(TAG, "toggleGPSUpdates: remove updates");

            } else {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerGPS);
                    Tools.toastMessage(getApplicationContext(), "GPS provider started running");
                    button.setText(R.string.pause);
                    Log.i(TAG, "toggleGPSUpdates: GPS listening");

                }
            }
        }

            public void toggleNetWorkUpdates (View view){
                if (!checkLocation()){
                    return;
                }

                Button button = (Button) view;
                if (button.getText().equals(getResources().getString(R.string.pause))) {
                    locationManager.removeUpdates(locationListenerNetwork);
                    button.setText(R.string.resume);

                } else {

                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED ||
                            ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED){

                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
                    Tools.toastMessage(this, "Network provider location services running");
                    button.setText(R.string.pause);
                        Log.i(TAG, "toggleNetWorkUpdates: Network location listening");

                }
            }

        }

    //Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileContentFragment(), "Profile");
        adapter.addFragment(new MatchesContentFragment(), "Matches");
        adapter.addFragment(new SettingsContentFragment(), "Settings");
        viewPager.setAdapter(adapter);

        Log.d(TAG, "setupViewPager: start created");



    }

    /**
     * Logout/ back button handling
     * @param view is current activity view
     */
    public void goBack(View view){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
//       logoutBtn.setText(R.string.logout);
       //onBackPressed();
        startActivity(intent);

    }

    @Override //I have a feeling save and restore are wrong
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Log.d(TAG, "onRestoreInstanceState: Restore stated");

        if(saveInstanceState.containsKey(Constraints.KEY_LAT)){
            latValueGPS.setText((String) saveInstanceState.get(Constraints.KEY_LAT));
       }
        if(saveInstanceState.containsKey(Constraints.KEY_LON)){
            lonValueGPS.setText((String) saveInstanceState.get(Constraints.KEY_LON));
        }
////        if(savedInstanceState.containsKey(Constraints.KEY_AGE)){
////            age.setText((String) savedInstanceState.get(Age));
////        }
//
//        if(saveInstanceState.containsKey(Constraints.KEY_JOB)){
//            jobView.setText((String) saveInstanceState.get(Constraints.KEY_JOB));
//
//        }
//        if (saveInstanceState.containsKey(Constraints.KEY_PROFILE)) {
//            profileView.setText((String) saveInstanceState.get(Constraints.KEY_PROFILE));
//        }

  }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: saving state");

        outState.putDouble(Constraints.KEY_LAT, latGPS);
        outState.putDouble(Constraints.KEY_LON, lonGPS);

    }

//    public void addMatchItem(View view) {
//        String name = newMatchText.getText().toString();
//        String uid = newMatchText.getText().toString();
//        String url = newMatchText.getText().toString();
//        Boolean like = false;
//
//        EachMatch item = new EachMatch(uid, name, url, like);
//        viewModel.addMatch(item);
//    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latGPS = location.getLatitude();
            lonGPS = location.getLongitude();
            Log.i(TAG, "onLocationChanged: pulling new lat and lon");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    latValueGPS.setText(String.format("%s", latGPS)); //what do with this data besides print
                    lonValueGPS.setText(String.format("%s", lonGPS));
                   Tools.toastMessage(getApplicationContext(), "GPS location updating");
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private final LocationListener locationListenerNetwork = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latNet = location.getLatitude();
            lonNet = location.getLongitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    latValueNet.setText(String.format("%s", latNet)); //set Lat/Lon?
                    lonValueNet.setText(String.format("%s", lonNet));
                    Tools.toastMessage(getApplicationContext(), "Network location services updating");
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };



    @Override
    public void onListFragmentInteraction(EachMatch item) {

        if(!item.like){
            item.like = true;
        }

        viewModel.updateById(item);
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
        viewModel.clear();
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
