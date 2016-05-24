package com.technologies.gimick.stories.adapters;

/*
This class is custom class for login and signup fragments
*/

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.technologies.gimick.stories.fragments.ParentFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<ParentFragment> fragmentList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<ParentFragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList != null ? fragmentList.size() : 0;
    }
}
