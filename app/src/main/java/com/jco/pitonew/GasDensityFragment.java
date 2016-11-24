package com.jco.pitonew;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GasDensityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GasDensityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GasDensityFragment extends Fragment {
    public static GasDensityFragment newInstance() {
        return new GasDensityFragment();
    }

    public GasDensityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gas_density, container, false);
    }
}
