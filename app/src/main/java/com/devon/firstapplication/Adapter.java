package com.devon.firstapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mMatchesList = new ArrayList<>();
    private final List<String>   mMatchesTitleList = new ArrayList<>();

    public Adapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mMatchesList.get(position);
    }

    public int getCount(){
        return mMatchesList.size();
    }

    public void addFragment(Fragment fragment, String title){  //title or name of Match??
        mMatchesList.add(fragment);
        mMatchesTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mMatchesTitleList.get(position);
    }
}
