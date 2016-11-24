package com.jco.pitonew;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by jco on 11/23/2016.
 */
public class GasFlowFragment extends Fragment {

    public static GasFlowFragment newInstance() {
        return new GasFlowFragment();
    }

    public GasFlowFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_gas_flow, container, false);


    }



}


