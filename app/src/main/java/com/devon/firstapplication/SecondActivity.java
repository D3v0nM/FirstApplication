package com.devon.firstapplication;

import android.content.Intent;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.devon.firstapplication.models.EachMatch;
import com.devon.firstapplication.viewmodels.MatchesViewModel;


public class SecondActivity extends AppCompatActivity implements OnListFragmentInteractionListener, SettingsContentFragment.SendMiles{
  public static final String TAG = SecondActivity.class.getSimpleName();






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





       // need to change to store location values
//        latValueGPS = findViewById(R.id.latValueGPS);
//        lonValueGPS = findViewById(R.id.lonValueGPS);
//        latValueNet = findViewById(R.id.latValueNet);
//        lonValueNet = findViewById(R.id.lonValueNet);


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


        // only testing GPS for now
        /*    public void toggleNetWorkUpdates (View view){
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
*/
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

    @Override
    public void getMaxDist(int maxDist){
        String tag = "android:switcher" + R.id.viewPager + ":" + 1;
        MatchesContentFragment matches = (MatchesContentFragment)
                getSupportFragmentManager().findFragmentByTag(tag);
        matches.getRange(maxDist);



    }

    @Override //I have a feeling save and restore are wrong
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Log.d(TAG, "onRestoreInstanceState: Restore stated");

//        if(saveInstanceState.containsKey(Constraints.KEY_LAT)){
//            latGPS.setText((String) saveInstanceState.get(Constraints.KEY_LAT));
//       }
//        if(saveInstanceState.containsKey(Constraints.KEY_LON)){
//            lonGPS.setText((String) saveInstanceState.get(Constraints.KEY_LON));
//        }
//////        if(savedInstanceState.containsKey(Constraints.KEY_AGE)){
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

//    @Override
//    protected void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//        Log.d(TAG, "onSaveInstanceState: saving state");
//
//        outState.putDouble(Constraints.KEY_LAT, latGPS);
//        outState.putDouble(Constraints.KEY_LON, lonGPS);
//        outState.putAll(outState);
//
//    }

//    public void addMatchItem(View view) {
//        String name = newMatchText.getText().toString();
//        String uid = newMatchText.getText().toString();
//        String url = newMatchText.getText().toString();
//        Boolean like = false;
//
//        EachMatch item = new EachMatch(uid, name, url, like);
//        viewModel.addMatch(item);
//    }



   /* private final LocationListener locationListenerNetwork = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latNet = location.getLatitude();
            lonNet = location.getLongitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  //  latValueNet.setText(String.format("%s", latNet)); //set Lat/Lon?
                    //lonValueNet.setText(String.format("%s", lonNet));
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

*/



    @Override
    public void onListFragmentInteraction(EachMatch item) {

        if(!item.liked){
            item.liked = true;
        }

        viewModel.updateById(item);
    }

    @Override
    public void onListFragmentInteraction(LocationListener locationListener) {

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
