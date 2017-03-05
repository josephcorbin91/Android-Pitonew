package com.jco.pitonew.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jco.pitonew.ButtonRectangle;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.Utility;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment  {
    public static InputFragment newInstance() {
        return new InputFragment();
    }

    private Gas gas;

    private ButtonRectangle dynamicVelocityButton;
    private ArrayList<String > stringInputValues = new ArrayList<String>();


    private double[] standardAirResult;
    public ArrayList<Double> dynamicVelocityArrayList;


    //GUI Components
    private TextView unitsGasDensityTemperatureDB, unitsGasDensityTemperatureWB, unitsCalculatedGasDensity, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel, unitsGasDensityAtmosphericPressure, unitsStaticPressure, unitsDuctPressure, dimensionHeader, dimension2TextView,dimension1TextView,dimension2UnitText,        unitsAverageVelocity,unitsMassAirFlow,unitsActualAirFlow,unitsNormalAirFlow,UnitsDimensionHeightGasFlowFragmentTextView,UnitsDimensionWidthGasFlowFragmentTextView;;
    private TextView molecularWeightTextView ;
    private EditText dimension_1_WidthGasEditText,dimension_2_HeightGasEditText,pitotTubeCoefficientEditText, staticPressureFragmentEditText, temperatureGasFragmentEditText, ElevationAboveSeaLevelFragmentEdiText, wetBulbTemperatureGasFragmentEditText, seaLevelPressureGasFragmentEditText;
    public Boolean getStandardAirBoolean() {
        return standardAirBoolean;
    }
    private Boolean standardAirBoolean;
    private Switch standardAirSwitch,rectangularOrCircularSwitch;
    private View mView;
    private double molecularWeight=28.96;

    public void setDimensions(String dimensions){
        switch (dimensions) {
            case "Rectangular":
                dimension1TextView.setText("Height");
                dimension2TextView.setText("Width");
                dimension2TextView.setVisibility(View.VISIBLE);
                dimension2EditText.setVisibility(View.VISIBLE);
                dimension2UnitText.setVisibility(View.VISIBLE);
                break;
            case "Circular":

                dimension1TextView.setText("Diameter");
                dimension2TextView.setVisibility(View.INVISIBLE);
                dimension2EditText.setVisibility(View.INVISIBLE);
                dimension2UnitText.setVisibility(View.INVISIBLE);
                break;
        }
    }
    public InputFragment() {

        standardAirBoolean=false;

    }
    private EditText dimension2EditText;

    public void instantiateViews() {



        UnitsDimensionHeightGasFlowFragmentTextView = (TextView) mView.findViewById(R.id.UnitsDimension_2_HeightGasFragmentTextView);
        UnitsDimensionWidthGasFlowFragmentTextView = (TextView) mView.findViewById(R.id.UnitsDimension_1_WidthGasFragmentTextView);

        unitsGasDensityTemperatureDB = (TextView) mView.findViewById(R.id.UnitstemperatureGasDensityFragmentTextView);
        unitsGasDensityTemperatureWB = (TextView) mView.findViewById(R.id.UnitsWetBulbtemperatureGasDensityFragmentTextView);
        unitsGasDensitySeaLevelPressure = (TextView) mView.findViewById(R.id.UnitsseaLevelPressureGasDensityFragmentTextView);
        unitsGasDensityElevationAboveSeaLevel = (TextView) mView.findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityAtmosphericPressure = (TextView) mView.findViewById(R.id.UnitsAtmosphericPressureFragmentTextViewUnits);
        unitsDuctPressure = (TextView) mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity = (TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);


        dimension_1_WidthGasEditText= (EditText) mView.findViewById(R.id.dimension_1_WidthGasEditText);
        dimension_2_HeightGasEditText= (EditText) mView.findViewById(R.id.dimension_2_HeightGasEditText);
        pitotTubeCoefficientEditText= (EditText) mView.findViewById(R.id.pitotTubeCoefficientEditText);
        staticPressureFragmentEditText= (EditText) mView.findViewById(R.id.staticPressureFragmentEditText);
        temperatureGasFragmentEditText= (EditText) mView.findViewById(R.id.temperatureGasFragmentEditText);
        ElevationAboveSeaLevelFragmentEdiText= (EditText) mView.findViewById(R.id.ElevationAboveSeaLevelFragmentEdiText);
        wetBulbTemperatureGasFragmentEditText= (EditText) mView.findViewById(R.id.wetBulbTemperatureGasFragmentEditText);
        seaLevelPressureGasFragmentEditText= (EditText) mView.findViewById(R.id.seaLevelPressureGasFragmentEditText);

        dimension_1_WidthGasEditText.setText("1");
        dimension_2_HeightGasEditText.setText("1");
        pitotTubeCoefficientEditText.setText("1");
        staticPressureFragmentEditText.setText("1");
        temperatureGasFragmentEditText.setText("1");
        ElevationAboveSeaLevelFragmentEdiText.setText("2000");
        wetBulbTemperatureGasFragmentEditText.setText("1");
        seaLevelPressureGasFragmentEditText.setText("1");
        ;



        rectangularOrCircularSwitch= (Switch) mView.findViewById(R.id.rectangularCircularGasFlowFragmentSwitch);
        standardAirSwitch = (Switch)mView.findViewById(R.id.standardAirSwitch);

        standardAirSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    showStandardAirDialog();

            }
        });


    }

    private Double[] dynamicPressure;
    public Double[] getDynamicPressure(){
        return this.dynamicPressure;
    }

    public Double[] getResults(){
        Double[] inputArray = new Double[]{Double.valueOf(dimension_1_WidthGasEditText.getText().toString()),Double.valueOf(dimension_2_HeightGasEditText.getText().toString())
                ,Double.valueOf(pitotTubeCoefficientEditText.getText().toString()),Double.valueOf(staticPressureFragmentEditText.getText().toString())
                ,Double.valueOf(temperatureGasFragmentEditText.getText().toString()),Double.valueOf(ElevationAboveSeaLevelFragmentEdiText.getText().toString())
                ,Double.valueOf(wetBulbTemperatureGasFragmentEditText.getText().toString()),Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString()),
                molecularWeight
        };
        return inputArray;
    }
    public void clear(){
        Utility.clearAllFields((ViewGroup)this.mView);


    }
    @Nullable
    public void showStandardAirDialog() {
        /*
        Refactor code so you only call the EditText Once.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Standard Air Input");
        builder.setView(R.layout.dialog_standardair);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog standardAirDialog = (Dialog) dialog;
                EditText C02EditText = (EditText) standardAirDialog.findViewById(R.id.C02EditText);
                EditText O2EditText = (EditText) standardAirDialog.findViewById(R.id.O2EditText);
                EditText N2EditText = (EditText) standardAirDialog.findViewById(R.id.N2EditText);
                EditText ArEditText = (EditText) standardAirDialog.findViewById(R.id.ArEditText);
                EditText H20EditText = (EditText) standardAirDialog.findViewById(R.id.H2OEditText);
                standardAirResult = new double[5];
                standardAirResult[0] = Double.valueOf(C02EditText.getText().toString());
                standardAirResult[1] = Double.valueOf(O2EditText.getText().toString());
                standardAirResult[2] = Double.valueOf(N2EditText.getText().toString());
                standardAirResult[3] = Double.valueOf(ArEditText.getText().toString());
                standardAirResult[4] = Double.valueOf(H20EditText.getText().toString());
                for (int i = 0; i < standardAirResult.length; i++)
                    System.out.print(standardAirResult[i]);
                Toast.makeText(getActivity(), "Molecular Weight =" + String.valueOf(standardAirResult[0] * 44.01 / 100 + standardAirResult[1] * 32 / 100 + standardAirResult[2] * 28.02 / 100 + standardAirResult[3] * 39.94 / 100 + standardAirResult[4] * 18.01528 / 100), Toast.LENGTH_LONG).show();

                InputFragment.this.molecularWeight=standardAirResult[0] * 44.01 / 100 + standardAirResult[1] * 32 / 100 + standardAirResult[2] * 28.02 / 100 + standardAirResult[3] * 39.94 / 100 + standardAirResult[4] * 18.01528 / 100;


            }
        });


        builder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog standardAirDialog = (Dialog) dialog;
                EditText C02EditText = (EditText) standardAirDialog.findViewById(R.id.C02EditText);
                EditText O2EditText = (EditText) standardAirDialog.findViewById(R.id.O2EditText);
                EditText N2EditText = (EditText) standardAirDialog.findViewById(R.id.N2EditText);
                EditText ArEditText = (EditText) standardAirDialog.findViewById(R.id.ArEditText);
                EditText H20EditText = (EditText) standardAirDialog.findViewById(R.id.H2OEditText);
                C02EditText.setText("");
                O2EditText.setText("");
                N2EditText.setText("");
                ArEditText.setText("");
                H20EditText.setText("");
                InputFragment.this.molecularWeight=28.96;


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog standardAirDialog = (Dialog) dialog;
                InputFragment.this.molecularWeight=28.96;
                standardAirDialog.hide();
                InputFragment.this.standardAirSwitch.setChecked(false);


            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showDyanamicVelocityInputDialog(){

        dynamicVelocityArrayList = new ArrayList<Double>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Dynamic Velocity Input");
        builder.setView(R.layout.dialog_dynamic_velocity);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dynamicVelocityDiaog = (Dialog)dialog;
                dynamicVelocityDiaog.hide();
            }
        });
        builder.setNeutralButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dynamicVelocityDiaog = (Dialog)dialog;

                TextView dynamicVelocityTextView = (TextView)dynamicVelocityDiaog.findViewById(R.id.listOfDynamicVelocities);
                for(Double dynamicVelocity : dynamicVelocityArrayList)
                    dynamicVelocityTextView.setText(dynamicVelocityTextView.getText().toString()+ ", "+String.valueOf(dynamicVelocity));

                EditText dynamicVelocityInputEditText = (EditText)dynamicVelocityDiaog.findViewById(R.id.dynamicVelocityInput);
                dynamicVelocityArrayList.add(Double.valueOf(dynamicVelocityInputEditText.getText().toString()));
                dynamicVelocityInputEditText.setText("");

                           }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dynamicVelocityDialog = (Dialog)dialog;
                dynamicVelocityArrayList.clear();
                TextView dynamicVelocityTextView = (TextView)dynamicVelocityDialog.findViewById(R.id.listOfDynamicVelocities);

                EditText dynamicVelocityInputEditText = (EditText)dynamicVelocityDialog.findViewById(R.id.dynamicVelocityInput);
                dynamicVelocityInputEditText.setText("");
                dynamicVelocityTextView.setText("");
                dynamicVelocityDialog.hide();
                     }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        this.mView = view;

        instantiateViews();
        //For test purposes
        dynamicPressure = new Double[]{0.18,0.3,	0.29,	0.34,	0.16,	0.15	,0.27	,0.34,	0.26,	0.25, 0.16	,0.14};
        return view;
    }



    public void changeUnits(String units) {



        switch (units) {
            case "SI":
                UnitsDimensionHeightGasFlowFragmentTextView.setText("m");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("m");
                unitsGasDensityTemperatureDB.setText("°C");
                unitsGasDensityTemperatureWB.setText("°C");
                unitsGasDensitySeaLevelPressure.setText("kPa");
                unitsGasDensityElevationAboveSeaLevel.setText("m");
                if(dimension_1_WidthGasEditText.getText().toString().length()>0)
                    dimension_1_WidthGasEditText.setText(String.valueOf(Double.valueOf(dimension_1_WidthGasEditText.getText().toString())*0.0254));
                if(dimension_2_HeightGasEditText.getText().toString().length()>0)
                    dimension_2_HeightGasEditText.setText(String.valueOf(Double.valueOf(dimension_2_HeightGasEditText.getText().toString())*0.0254));
                if(staticPressureFragmentEditText.getText().toString().length()>0)
                    staticPressureFragmentEditText.setText(String.valueOf(Double.valueOf(staticPressureFragmentEditText.getText().toString())*3.38639));
                if(seaLevelPressureGasFragmentEditText.getText().toString().length()>0)
                    seaLevelPressureGasFragmentEditText.setText(String.valueOf(Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString())*3.38639));
                if(ElevationAboveSeaLevelFragmentEdiText.getText().toString().length()>0)
                    ElevationAboveSeaLevelFragmentEdiText.setText(String.valueOf(Double.valueOf(ElevationAboveSeaLevelFragmentEdiText.getText().toString())*0.3048));
                if(temperatureGasFragmentEditText.getText().toString().length()>0)
                    temperatureGasFragmentEditText.setText(String.valueOf(((Double.valueOf(temperatureGasFragmentEditText.getText().toString())-32)/1.8)));
                if(wetBulbTemperatureGasFragmentEditText.getText().toString().length()>0)
                    wetBulbTemperatureGasFragmentEditText.setText(String.valueOf(((Double.valueOf(wetBulbTemperatureGasFragmentEditText.getText().toString())-32)/1.8)));

                break;
            case "US":
                UnitsDimensionHeightGasFlowFragmentTextView.setText("inches");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("inches");
                unitsGasDensityTemperatureDB.setText("°F");
                unitsGasDensityTemperatureWB.setText("°F");
                unitsGasDensitySeaLevelPressure.setText("in. Hg");
                unitsGasDensityElevationAboveSeaLevel.setText("ft");

                if(dimension_1_WidthGasEditText.getText().toString().length()>0)
                    dimension_1_WidthGasEditText.setText(String.valueOf(Double.valueOf(dimension_1_WidthGasEditText.getText().toString())/0.0254));
                if(dimension_2_HeightGasEditText.getText().toString().length()>0)
                    dimension_2_HeightGasEditText.setText(String.valueOf(Double.valueOf(dimension_2_HeightGasEditText.getText().toString())/0.0254));
                if(staticPressureFragmentEditText.getText().toString().length()>0)
                    staticPressureFragmentEditText.setText(String.valueOf(Double.valueOf(staticPressureFragmentEditText.getText().toString())*0.2952998751));
                if(seaLevelPressureGasFragmentEditText.getText().toString().length()>0)
                    seaLevelPressureGasFragmentEditText.setText(String.valueOf(Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString())*0.2952998751));
                if(ElevationAboveSeaLevelFragmentEdiText.getText().toString().length()>0)
                    ElevationAboveSeaLevelFragmentEdiText.setText(String.valueOf(Double.valueOf(ElevationAboveSeaLevelFragmentEdiText.getText().toString())/0.3048));
                if(temperatureGasFragmentEditText.getText().toString().length()>0)
                    temperatureGasFragmentEditText.setText(String.valueOf(((Double.valueOf(temperatureGasFragmentEditText.getText().toString())*1.8)+32)));
                if(wetBulbTemperatureGasFragmentEditText.getText().toString().length()>0)
                    wetBulbTemperatureGasFragmentEditText.setText(String.valueOf(((Double.valueOf(wetBulbTemperatureGasFragmentEditText.getText().toString())*1.8)+32)));




                break;
        }
    }


    public boolean verifyInput(){
return true;
        //return !emptyEditText(dimensionWidthGasEditText)&& !emptyEditText(dimensionHeightGasEditText) && !emptyEditText(pitotTubeCoefficientEditText) && !emptyEditText( staticPressureFragmentEditText) &&!emptyEditText( temperatureGasFragmentEditText) && !emptyEditText(ElevationAboveSeaLevelFragmentEdiText) && !emptyEditText( wetBulbTemperatureGasFragmentEditText) && !emptyEditText(seaLevelPressureGasFragmentEditText);


    }

    public boolean emptyEditText(EditText editText){
        return editText.getText().equals("");
    }

    public void setDynamicInputVaues(ArrayList<Double> dynamicInputVaues){

    }

    public double[] getStandardAirResult() {
        return standardAirResult;
    }
    public void setStandardAirResult(double[] standardAirResult) {
        this.standardAirResult = standardAirResult;
    }
    public void setStandardAirBoolean(Boolean standardAirBoolean) {
        this.standardAirBoolean = standardAirBoolean;
    }
}
