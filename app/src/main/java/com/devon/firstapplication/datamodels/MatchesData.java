package com.devon.firstapplication.datamodels;

import com.devon.firstapplication.models.EachMatch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

public class    MatchesData {

    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;

    public MatchesData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }

    public void addMatch(EachMatch match){
        DatabaseReference matchesRef = mDatabase.child("matches");
        matchesRef.push().setValue(match);
    }

    public void getMatches(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        // This is where we can construct our path
        DatabaseReference matchesRef = mDatabase.child("matches");
        ValueEventListener matchesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);
            }
        };
        matchesRef.addValueEventListener(matchesListener);
        listeners.put(matchesRef, matchesListener);
    }

    public void updateMatchById(EachMatch match){
        DatabaseReference matchesRef = mDatabase.child("matches");
        matchesRef.child(match.uid).setValue(match);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }
}
