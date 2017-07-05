package com.nat.android.javashoplib.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LDD on 17/6/28.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> datas;

    public FragmentAdapter(FragmentManager paramFragmentManager, List<Fragment> paramList)
    {
        super(paramFragmentManager);
        this.datas = paramList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    public int getCount()
    {
        return this.datas.size();
    }

    public Fragment getItem(int paramInt)
    {
        return ((Fragment)this.datas.get(paramInt));
    }
}
