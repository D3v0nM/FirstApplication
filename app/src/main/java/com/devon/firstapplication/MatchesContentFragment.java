package com.devon.firstapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devon.firstapplication.models.EachMatch;
import com.devon.firstapplication.viewmodels.MatchesViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;


public class MatchesContentFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "MatchesContentFragment";


    //param argument names
    public static final String ARG_DATA = "data-set";


    //Parameters
    private List<EachMatch> mDataSet;
    private static OnListFragmentInteractionListener mListener;

    //location params
    LocationManager locationManager;
    double latGPS, lonGPS;
    public int distance;
    private Location mlocation;


    public MatchesContentFragment(){

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
       // Context context = recyclerView.getContext();
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);


        MatchesViewModel viewModel = new MatchesViewModel();

        viewModel.getEachMatch(
                (ArrayList<EachMatch> matchArrayList) -> {
                    ContentAdapter adapter = new ContentAdapter(matchArrayList, mListener);



                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    Log.i(TAG, "onCreateView: Matches started, pulling data");
                }
        );



        return recyclerView;



    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final RelativeLayout matchCard;
        final ImageButton likeBtn;
        public ImageView picture;
        public TextView name;
        public EachMatch mItem;
        public TextView lat;
       public TextView lon;
       public TextView dist; // combine lat and long into meter for location some how

        //public TextView desc;

        

         public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_tab, parent, false));

            picture = itemView.findViewById(R.id.matches_pic);
            name = itemView.findViewById(R.id.matches_name);
            dist = itemView.findViewById(R.id.distance);
            likeBtn = itemView.findViewById(R.id.like_button);
            lat = itemView.findViewById(R.id.latValue);
            lon = itemView.findViewById(R.id.lonValue);
            matchCard = itemView.findViewById(R.id.Relative);
            likeBtn.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    try {

                        String like = name.getText().toString();
                        Tools.toastMessage(itemView.getContext(), "You Liked " + like);
                        mListener.onListFragmentInteraction(mItem);
                      likeBtn.setVisibility(View.GONE);

                    }catch (Exception e) {
                         e.printStackTrace();
                        Tools.toastMessage(itemView.getContext(), "Couldn't like, something broken");
                    }
                }
            });

            Log.i(TAG, "ViewHolder: Started");
        }

    }
    private boolean checkLocation() {
        if (!isLocationEnabled()) {
           // showAlert();
        }
        Log.i(TAG, "checkLocation: returned");
        return isLocationEnabled();

    }

    private boolean isLocationEnabled() {
        Log.i(TAG, "isLocationEnabled: returned");
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


    }

//    private void showAlert() {
//        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
//        dialog.setTitle(R.string.enable_location)
//                .setMessage(getString(R.string.location_message))
//                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
//                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    startActivity(myIntent);
//                })
//                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {});
//        dialog.show();
//    }

    public void toggleGPSUpdates(View view) {
        if (!checkLocation()){
            return;

        }


        if (MatchesContentFragment.TAG.contains("onDestroyView()")||
                MatchesContentFragment.TAG.contains("onPause()")||
                MatchesContentFragment.TAG.contains("onStop()")||
                MatchesContentFragment.TAG.contains("onDestroy()")) {
            locationManager.removeUpdates(locationListenerGPS);

            Log.i(TAG, "toggleGPSUpdates: remove updates");

        } else {

            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 60 * 1000,10, locationListenerGPS);
                Tools.toastMessage(getContext(), "GPS provider started running");
                Log.i(TAG, "toggleGPSUpdates: GPS listening");

            }
        }
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latGPS = location.getLatitude();
            lonGPS = location.getLongitude();
            Bundle bundle = new Bundle();
            bundle.putDouble(Constraints.KEY_LAT, latGPS);
            bundle.putDouble(Constraints.KEY_LON, lonGPS);






            Log.i(TAG, "onLocationChanged: pulling new lat and lon");


            Tools.toastMessage(getContext(), "GPS location updating");
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

    /**
     * Adapter to display RecyclerView
     * Migrate all this it's own class
     *
     */
    public  class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        //Set numbers of List(s) in RecyclerView // local array variables/code
  //    public final int LENGTH = 8; //getResources().getStringArray(R.array.matches).length;

//        public final String[] mMatchesDesc;       // Params for local data
//        public final Drawable[] mMatchesPic;

        public ContentAdapter(List<EachMatch> items, OnListFragmentInteractionListener listener){
            mDataSet = items;
            mListener = listener;
            //lat
            //lon
            if(checkLocation()){toggleGPSUpdates(getView());}


            if(mDataSet.isEmpty()){
                Tools.toastMessage(getContext(), "Array is empty");
                Log.i(TAG, "mMatches was null. trying to download again");

            }




            //Local storage with array code
//            mMatches = resources.getStringArray(R.array.matches);
//            mMatchesDesc = resources.getStringArray(R.array.matches_desc);
//            TypedArray a = resources.obtainTypedArray(R.array.matches_pic);
//            mMatchesPic = new Drawable[a.length()];
//            for (int i = 0; i < mMatchesPic.length; i++) {
//                mMatchesPic[i] = a.getDrawable(i);
//            }
            //a.recycle();
            Log.i(TAG, "ContentAdapter: Started");

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(TAG, "onCreateViewHolder: Started");

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);

            }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            Log.i(TAG, "onBindViewHolder: holder called");
             //final String imageUrl;
            //Firebase database values


            try {
                holder.mItem = mDataSet.get(position);
                holder.name.setText(String.format("%s", holder.mItem.name));
                Picasso.get().load(holder.mItem.imageUrl).into(holder.picture);

                if(mlocation != null) {
                    mlocation.getExtras();
                    double  gpsLat = mlocation.getLatitude();
                    double gpsLon = mlocation.getLongitude();


                    float[] result = new float[2];
                    double matchLat = Double.parseDouble(holder.mItem.lat);
                    double matchLon = Double.parseDouble(holder.mItem.longitude);

                    Location.distanceBetween(gpsLat, gpsLon, matchLat, matchLon, result);

                    holder.dist.setText(String.format("%s", (result[0]/(float)1000) + "Kilometers away"));

                    if(distance > (result[0]/(float)1000) ){
                        holder.itemView.setVisibility(View.GONE);
                    }

                }else {
                    holder.lat.setText( holder.mItem.lat);
                    holder.lon.setText( holder.mItem.longitude);
                }


            } catch (UnknownFormatConversionException e) {
                e.printStackTrace();

            }



        }

            //local storage values
//               holder.picture.setImageDrawable(mMatchesPic[position % mMatchesPic.length]);
//                holder.name.setText((mMatches[position % mMatches.length]));
//              holder.desc.setText(mMatchesDesc[position % mMatchesDesc.length]);





        @Override
            public int getItemCount(){
            int count = 0;
                if(mDataSet != null){
                    count = mDataSet.size();

                }
                return count;
        }

    }

    public void onSaveInstanceState(@Nullable Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: Matches save init");



    } @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(getArguments() != null) {
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA);
           // mlocation = getArguments().getParcelable(Constraints.KEY_LOCATION);

        }

        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "Matches Frag onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Mataches Frag onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "Matches onPause()");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "Matches onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "Matches onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Matches onDestroy()");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }




    }
    public void getRange(int maxRange){
           distance = maxRange;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }
}


    interface OnListFragmentInteractionListener {
        //Need to update arg type and name
        void onListFragmentInteraction(EachMatch match);

      void onListFragmentInteraction(LocationListener locationListener);

    }






