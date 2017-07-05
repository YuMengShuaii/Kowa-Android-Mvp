package com.mobile.app.javashop.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * pageadapter
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Fragment[] fragments ;
    Context mContext;
    public MyPagerAdapter(FragmentManager fm,Fragment[] fragments,Context context,String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return  fragments[position] ;
    }

    @Override
    public int getCount() {

        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
