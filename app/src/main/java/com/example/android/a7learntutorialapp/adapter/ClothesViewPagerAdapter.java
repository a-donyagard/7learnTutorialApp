package com.example.android.a7learntutorialapp.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.a7learntutorialapp.view.fragment.ClothesFragment;

/**
 * Created by Saeed shahini on 7/30/2016.
 */
public class ClothesViewPagerAdapter extends FragmentPagerAdapter {

    public ClothesViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ClothesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "مشاهده شده ها";
            case 1:
                return "پربازدیدترین ها";
            case 2:
                return "جدیدترین ها";
            default:
                return "";
        }
    }
}
