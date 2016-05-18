package com.example.yalantis.y1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.yalantis.y1.fragment.TabRecyclerFragment;

public class TaskStatusPagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    public TaskStatusPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TabRecyclerFragment.newInstance(position);
            case 1:
                return TabRecyclerFragment.newInstance(position);
            case 2:
                return TabRecyclerFragment.newInstance(position);
            default:
                return null;
        }
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
