package com.jco.pitonew.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by jco on 12/19/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context ctxt = null;
    ViewGroup mContainer = null;
    Object mObject = null;

    Fragment inputFragment;
    Fragment resultFragment;
    private int currentPosition;
    public ViewPagerAdapter(Context ctxt, FragmentManager mgr) {

        super(mgr);

        this.inputFragment =inputFragment;
        this.resultFragment= resultFragment;
        this.ctxt = ctxt;
    }

    public int getCurrentPosition(){
        return this.currentPosition;
    }
    @Override
    public int getCount() {
        return 2;

    }
    @Override
    public Fragment getItem(int position) {
        this.currentPosition=position;
        switch (position){
            case 0: return inputFragment;
            case 1: return resultFragment;
        }
return null;
    }

    



}

