package com.devon.firstapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devon.firstapplication.models.EachMatch;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class MatchesContentFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "MatchesContentFragment";


    //param argument names
    public static final String ARG_DATA = "data-set";
    public static final String ARG_SIZE = "card-count";


    //Parameters
    private List<EachMatch> mDataSet;
    private OnListFragmentInteractionListener mListener;

    public MatchesContentFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        if(getArguments() != null) {
            mDataSet = getArguments().getParcelableArrayList(ARG_DATA);
        }
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
       // Context context = recyclerView.getContext();
        ContentAdapter adapter = new ContentAdapter(mDataSet, mListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG, "onCreateView: Matches started");
        return recyclerView;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageButton likeBtn;
      //  final  String imageUrl;
        public ImageView picture;
        public TextView name;
        public EachMatch mItem;

        //public TextView desc;

        

         public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.matches_tab, parent, false));

            picture = itemView.findViewById(R.id.matches_pic);
            name = itemView.findViewById(R.id.matches_name);
           // desc = itemView.findViewById(R.id.matches_desc);
            likeBtn = itemView.findViewById(R.id.like_button);
            likeBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        String like = name.getText().toString();
                      Tools.toastMessage(itemView.getContext(), "You Liked " + like);
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

    /**
     * Adapter to display RecyclerView
     * Migrate all this it's own class
     *
     */
    public  class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        //Set numbers of List(s) in RecyclerView
      public final int LENGTH = 8; //getResources().getStringArray(R.array.matches).length;
        public final List<EachMatch> mMatches;
        public final OnListFragmentInteractionListener mListener;
        public final String imageUrl;


//        public final String[] mMatchesDesc;       // Params for local data
//        public final Drawable[] mMatchesPic;

        public ContentAdapter(List<EachMatch> items,  OnListFragmentInteractionListener listener) {
            mMatches = items;
            mListener = listener;



            //Local storage with array code
//            mMatches = resources.getStringArray(R.array.matches);
//            mMatchesDesc = resources.getStringArray(R.array.matches_desc);
//            TypedArray a = resources.obtainTypedArray(R.array.matches_pic);
//
//            mMatchesPic = new Drawable[a.length()];
//            for (int i = 0; i < mMatchesPic.length; i++) {
//                mMatchesPic[i] = a.getDrawable(i);
//            }
            //a.recycle();
            Log.i(TAG, "ContentAdapter: Started");
            imageUrl = null;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(TAG, "onCreateViewHolder: Started");

            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);

            }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            //Firebase database values
            holder.mItem = mDataSet.get(position);
            holder.name.setText(String.format("%s", mMatches.get(position)));
            //holder.picture.setImageDrawable(imageUrl);


            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReferenceFromUrl(imageUrl);
            storageReference.getDownloadUrl().addOnCompleteListener(
                    new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                String downloadUrl = task.getResult().toString();
                                Glide.with(holder.picture.getContext())
                                        .load(downloadUrl)
                                        .into(holder.picture);
                            } else {
                                Log.w(TAG, "Getting download url was not successful.",
                                        task.getException());
                            }
                        }
                    });
            Log.i(TAG, "onBindViewHolder: started");

        }

            //local storage values
//               holder.picture.setImageDrawable(mMatchesPic[position % mMatchesPic.length]);
//                holder.name.setText((mMatches[position % mMatches.length]));
//              holder.desc.setText(mMatchesDesc[position % mMatchesDesc.length]);





        @Override
            public int getItemCount(){
                return LENGTH;
        }

    }

    public void onSaveInstanceState(@Nullable Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: Matches save init");



    } @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}


    interface OnListFragmentInteractionListener {
        //Need to update arg type and name
        void onListFragmentInteraction(EachMatch match);
    }





