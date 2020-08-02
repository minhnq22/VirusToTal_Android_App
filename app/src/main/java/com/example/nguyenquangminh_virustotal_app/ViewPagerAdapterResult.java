package com.example.nguyenquangminh_virustotal_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class ViewPagerAdapterResult extends FragmentPagerAdapter {

    String data;

    public ViewPagerAdapterResult(@NonNull FragmentManager fm, int behavior, String data) {
        super(fm, behavior);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentSummary();

                break;
            case 1:
                fragment = new FragmentDetection();
                break;
            case 2:
                fragment = new FragmentDetails();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Summary";
            case 1:
                return "Detection";
            case 2:
                return "Details";
        };
        return null;
    }
}
