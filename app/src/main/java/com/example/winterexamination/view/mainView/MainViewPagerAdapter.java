package com.example.winterexamination.view.mainView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.winterexamination.MyApplication;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    public MyApplication application;

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                FavorFragment fragment = new FavorFragment();
                fragment.application=this.application;
                return fragment;
            }
            case 1:{
                SurfFragment fragment = new SurfFragment();
                fragment.application=this.application;
                return fragment;
            }
            case 2:{
                PensonFragment fragment = new PensonFragment();
                fragment.application=this.application;
                return fragment;
            }
        }
        return new Fragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "收藏";
            case 1:return "浏览";
            case 2:return "个人";
        }
        return "错乱";
    }
}
