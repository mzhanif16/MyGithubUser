package com.example.mygithubuser.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.mygithubuser.R;
import com.example.mygithubuser.fragment.Followers;
import com.example.mygithubuser.fragment.Following;


public class SectionPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.title_follower,
            R.string.title_following
    };

    public SectionPagerAdapter(Context context, FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Followers();
                break;
            case 1:
                fragment = new Following();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
