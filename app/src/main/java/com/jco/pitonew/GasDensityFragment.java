package com.jco.pitonew;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fenjuly.mylibrary.ToggleExpandLayout;
import com.fenjuly.mylibrary.*;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

    public double[] getStandardAirResult() {
        return standardAirResult;
    }

    public void setStandardAirResult(double[] standardAirResult) {
        this.standardAirResult = standardAirResult;
    }

    private double[] standardAirResult;

    private TextView unitsGasDensityTemperatureDB, unitsGasDensityTemperatureWB, unitsCalculatedGasDensity, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel, unitsGasDensityAtmosphericPressure, unitsStaticPressure, unitsDuctPressure, dimensionHeader, dimension1TextView, dimension2TextView;

    public Boolean getStandardAirBoolean() {
        return standardAirBoolean;
    }

    public void setStandardAirBoolean(Boolean standardAirBoolean) {
        this.standardAirBoolean = standardAirBoolean;
    }

    private Boolean standardAirBoolean;
    private Switch standardAirSwitch;
    private View mView;


    public GasDensityFragment() {

        standardAirBoolean=false;

    }


    public void clear(){
        Utility.clearAllFields((ViewGroup)this.mView);
        View layoutDuctPressure = mView.findViewById(R.id.layoutDuctPressure);
        layoutDuctPressure.setVisibility(View.GONE);
        View layoutRelativeHumidity = mView.findViewById(R.id.layoutRelativeHumidity);
        layoutRelativeHumidity.setVisibility(View.GONE);
        View layoutAtmosphericPressure = mView.findViewById(R.id.layoutAtmosphericPressure);
        layoutAtmosphericPressure.setVisibility(View.GONE);


    }
    @Nullable
    public void showStandardAirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Standard Air Input");
        builder.setView(R.layout.dialog_standardair);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog standardAirDialog = (Dialog)dialog;
                EditText C02EditText = (EditText)standardAirDialog.findViewById(R.id.C02EditText);
                EditText O2EditText = (EditText)standardAirDialog.findViewById(R.id.O2EditText);
                EditText N2EditText = (EditText)standardAirDialog.findViewById(R.id.N2EditText);
                EditText ArEditText = (EditText)standardAirDialog.findViewById(R.id.ArEditText);
                EditText H20EditText = (EditText)standardAirDialog.findViewById(R.id.H2OEditText);
                standardAirResult= new double[5];
                standardAirResult[0]= Double.valueOf(C02EditText.getText().toString());
                standardAirResult[1]= Double.valueOf(O2EditText.getText().toString());
                standardAirResult[2]= Double.valueOf(N2EditText.getText().toString());
                standardAirResult[3]= Double.valueOf(ArEditText.getText().toString());
                standardAirResult[4]= Double.valueOf(H20EditText.getText().toString());
                for(int i=0; i<standardAirResult.length; i++)
                    System.out.print(standardAirResult[i]);
                Toast.makeText(getActivity(), "Molecular Weight ="+String.valueOf(standardAirResult[0]*44.01/100+standardAirResult[1]*32/100+standardAirResult[2]*28.02/100+standardAirResult[3]*39.94/100+standardAirResult[4]*18.01528/100),Toast.LENGTH_LONG).show();








            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog standardAirDialog = (Dialog)dialog;
                standardAirDialog.hide();
                standardAirSwitch.setChecked(false);
                Toast.makeText(getActivity(), "Molecular Weight = 28.96",Toast.LENGTH_LONG).show();


            }
        });
        AlertDialog dialog = builder.create();
dialog.show();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gas_density, container, false);
        this.mView = view;
        standardAirSwitch = (Switch)mView.findViewById(R.id.standardAirSwitch);
        standardAirSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    standardAirBoolean=true;
                    showStandardAirDialog();
                } else {
                    Toast.makeText(getActivity(), "Molecular Weight = 28.96",
                            Toast.LENGTH_LONG).show();
                    standardAirBoolean=false;
                }
            }
        });
        //standardAirSwitch = (Switch)mView.findViewById(R.id.);


        View layoutDuctPressure = mView.findViewById(R.id.layoutDuctPressure);
        layoutDuctPressure.setVisibility(View.GONE);
        View layoutRelativeHumidity = mView.findViewById(R.id.layoutRelativeHumidity);
        layoutRelativeHumidity.setVisibility(View.GONE);
        View layoutAtmosphericPressure = mView.findViewById(R.id.layoutAtmosphericPressure);
        layoutAtmosphericPressure.setVisibility(View.GONE);



        return view;
    }



    public void changeUnits(String units) {
        unitsGasDensityTemperatureDB = (TextView) mView.findViewById(R.id.UnitstemperatureGasDensityFragmentTextView);
        unitsGasDensityTemperatureWB = (TextView) mView.findViewById(R.id.UnitsWetBulbtemperatureGasDensityFragmentTextView);
        unitsGasDensitySeaLevelPressure = (TextView) mView.findViewById(R.id.UnitsseaLevelPressureGasDensityFragmentTextView);
        unitsGasDensityElevationAboveSeaLevel = (TextView) mView.findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityAtmosphericPressure = (TextView) mView.findViewById(R.id.UnitsAtmosphericPressureFragmentTextViewUnits);
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