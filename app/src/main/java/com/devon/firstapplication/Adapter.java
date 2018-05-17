package com.devon.firstapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class   Adapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mTabsList = new ArrayList<>();
    private final List<String>   mTabTitleList = new ArrayList<>();

    public Adapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mTabsList.get(position);
    }

    public int getCount(){
        return mTabsList.size();
    }

    public void addFragment(Fragment fragment, String title){
        mTabsList.add(fragment);
        mTabTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mTabTitleList.get(position);
    }
}
