package com.devon.firstapplication.viewmodels;

import com.devon.firstapplication.datamodels.MatchesData;
import com.devon.firstapplication.models.EachMatch;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {

    private MatchesData dataModel;

    public MatchesViewModel(){
        dataModel = new MatchesData();

    }

    public void addMatch(EachMatch match) {
        dataModel.addMatch(match);
    }

    public void getEachMatch(Consumer<ArrayList<EachMatch>> responseCallback) {
       dataModel.getMatches(
               (DataSnapshot dataSnapshot) -> {
           ArrayList<EachMatch> mEachMatch = new ArrayList<>();
           for(DataSnapshot matchSnapshot : dataSnapshot.getChildren()){
               EachMatch match = matchSnapshot.getValue(EachMatch.class);
               assert match != null;
               match.uid = matchSnapshot.getKey();
               mEachMatch.add(match);
            }
            responseCallback.accept(mEachMatch);
        },
                (databaseError -> System.out.println("Error reading Matches: " + databaseError))
       );
    }

    public void updateById(EachMatch match){
        dataModel.updateMatchById(match);
    }

    public void clear(){

        dataModel.clear();
    }
}
