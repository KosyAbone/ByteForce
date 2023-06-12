/*
 * *
 *  * Created by Subash Aryc on 6/8/21, 11:05 PM
 *  * Copyright (c) Hamrobazar, Kantipur 2021 . All rights reserved.
 *  * Last modified 5/25/21, 12:37 AM
 *
 */

package com.byteforce.kickash.ui.custom;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomFragmentAdapter extends FragmentPagerAdapter {

    final List<Fragment> mFragmentList = new ArrayList<>();
    final List<String> mFragmentTitleList = new ArrayList<>();

    public CustomFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
