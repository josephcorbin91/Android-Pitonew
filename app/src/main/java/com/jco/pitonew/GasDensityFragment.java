package com.jco.pitonew;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fenjuly.mylibrary.ToggleExpandLayout;
import com.fenjuly.mylibrary.*;

import android.widget.TextView;

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

    private TextView unitsGasDensityTemperatureDB, unitsGasDensityTemperatureWB, unitsCalculatedGasDensity, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel, unitsGasDensityAtmosphericPressure, unitsStaticPressure, unitsDuctPressure, dimensionHeader, dimension1TextView, dimension2TextView;

    private View mView;

    public GasDensityFragment() {


    }

    public void clear(){
        Utility.clearAllFields((ViewGroup)this.mView);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gas_density, container, false);
        this.mView = view;
        return view;
    }


    public void changeUnits(String units) {
        unitsGasDensityTemperatureDB = (TextView) mView.findViewById(R.id.UnitstemperatureGasDensityFragmentTextView);
        unitsGasDensityTemperatureWB = (TextView) mView.findViewById(R.id.UnitsWetBulbtemperatureGasDensityFragmentTextView);
        unitsGasDensitySeaLevelPressure = (TextView) mView.findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityElevationAboveSeaLevel = (TextView) mView.findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityAtmosphericPressure = (TextView) mView.findViewById(R.id.UnitsAtmosphericPressureFragmentTextView);
        unitsDuctPressure = (TextView) mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity = (TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);
        switch (units) {
            case "Metric":
                unitsGasDensityTemperatureDB.setText("°C");
                unitsGasDensityTemperatureWB.setText("°C");
                unitsGasDensitySeaLevelPressure.setText("kPa");
                unitsGasDensityElevationAboveSeaLevel.setText("m");
                unitsGasDensityAtmosphericPressure.setText("kPa");
                unitsDuctPressure.setText("kPa");
                unitsCalculatedGasDensity.setText("kg/m³");
                break;
            case "Imperial":
                unitsGasDensityTemperatureDB.setText("°F");
                unitsGasDensityTemperatureWB.setText("°F");
                unitsGasDensitySeaLevelPressure.setText("in. Hg");
                unitsGasDensityElevationAboveSeaLevel.setText("ft");
                unitsGasDensityAtmosphericPressure.setText("in. Hg");
                unitsDuctPressure.setText("in. Hg");
                unitsCalculatedGasDensity.setText("lb/ft³");
                break;
        }
    }
}