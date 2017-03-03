package com.jco.pitonew.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jco.pitonew.Adapters.ViewPagerAdapter;
import com.jco.pitonew.R;

import java.util.List;

/**
 * Created by jco on 12/19/2016.
 */
public class PagerFragment extends Fragment {

    private int mCurrentIndex = 0;
    private View view;
    private ViewPager pager;
    public ViewPagerAdapter mViewPagerAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Pager Fragment", "onCreateView");

        View result = inflater.inflate(R.layout.pager, container, false);
        pager = (ViewPager) result.findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapter(getActivity(), getChildFragmentManager());
        pager.setAdapter(mViewPagerAdapter);
        pager.setCurrentItem(0);


        return result;
    }

    public ViewPagerAdapter getViewPagerAdapter(){
        return this.mViewPagerAdapter;
    }

    public PagerFragment() {


    }
}
