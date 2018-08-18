package com.mysoft.university.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * ViewPager Adapter
 * <p>
 * Created by Zourw on 2018/8/12.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private boolean destroy;

    public PageAdapter(FragmentManager fm, List<Fragment> fragments) {
        this(fm, fragments, false);
    }

    public PageAdapter(FragmentManager fm, List<Fragment> fragments, boolean destroy) {
        super(fm);
        this.fragments = fragments;
        this.destroy = destroy;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "标题栏" + (position % 2 == 0 ? "" : "WWW");
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (destroy) {
            super.destroyItem(container, position, object);
        }
    }
}