package com.jco.pitonew.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GasFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    public static GasFragment newInstance() {
        return new GasFragment();
    }



    private double[] standardAirResult;


    //GUI Components
    private TextView unitsGasDensityTemperatureDB, unitsGasDensityTemperatureWB, unitsCalculatedGasDensity, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel, unitsGasDensityAtmosphericPressure, unitsStaticPressure, unitsDuctPressure, dimensionHeader, dimension2TextView,dimension1TextView,dimension2UnitText,        unitsAverageVelocity,unitsMassAirFlow,unitsActualAirFlow,unitsNormalAirFlow,UnitsDimensionHeightGasFlowFragmentTextView,UnitsDimensionWidthGasFlowFragmentTextView;;
    private View layoutDuctPressure,layoutRelativeHumidity,layoutAtmosphericPressure,layoutGasDensityResult,        layoutArea,layoutNormalAir,layoutAverageVelocity,massAirFlowLayout,layoutActualAirFlow;

    public Boolean getStandardAirBoolean() {
        return standardAirBoolean;
    }

    private Boolean standardAirBoolean;
    private Switch standardAirSwitch,rectangularOrCircularSwitch;
    private View mView;
    public void setDimensions(String dimensions){
        switch (dimensions) {
            case "Rectangular":
                dimension1TextView.setText("Height");
                dimension2TextView.setText("Width");
                dimension2TextView.setVisibility(View.VISIBLE);
                dimension2EditText.setVisibility(View.VISIBLE);
                dimension2UnitText.setVisibility(View.VISIBLE);
                dimesnion2LinearLayout.setVisibility(View.VISIBLE);
                dimension2LinearLayoutFull.setVisibility(View.VISIBLE);
                break;
            case "Circular":

                dimension1TextView.setText("Diameter");
                dimension2TextView.setVisibility(View.INVISIBLE);
                dimension2EditText.setVisibility(View.INVISIBLE);
                dimension2UnitText.setVisibility(View.INVISIBLE);
                dimesnion2LinearLayout.setVisibility(View.INVISIBLE);
                dimension2LinearLayoutFull.setVisibility(View.INVISIBLE);
                break;
        }
    }





    public GasFragment() {

        standardAirBoolean=false;

    }


    private EditText dimension2EditText;
    CardView dimension2LinearLayoutFull;
    LinearLayout dimesnion2LinearLayout;




    public void clear(){
        Utility.clearAllFields((ViewGroup)this.mView);

        layoutArea.setVisibility(View.GONE);
        layoutNormalAir.setVisibility(View.GONE);
        layoutAverageVelocity.setVisibility(View.GONE);
        massAirFlowLayout.setVisibility(View.GONE);
        layoutActualAirFlow.setVisibility(View.GONE);
        layoutDuctPressure.setVisibility(View.GONE);
        layoutRelativeHumidity.setVisibility(View.GONE);
        layoutAtmosphericPressure.setVisibility(View.GONE);


    }
    @Nullable
    public void showStandardAirDialog(){
        /*
        Refactor code so you only call the EditText Once.
         */
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

                TextView molecularWeightTextView = (TextView)mView.findViewById(R.id.MolecularWeightTextView);
                molecularWeightTextView.setText(String.valueOf(standardAirResult[0]*44.01/100+standardAirResult[1]*32/100+standardAirResult[2]*28.02/100+standardAirResult[3]*39.94/100+standardAirResult[4]*18.01528/100));










            }
        });


        builder.setNeutralButton("Clear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog standardAirDialog = (Dialog)dialog;
                EditText C02EditText = (EditText)standardAirDialog.findViewById(R.id.C02EditText);
                EditText O2EditText = (EditText)standardAirDialog.findViewById(R.id.O2EditText);
                EditText N2EditText = (EditText)standardAirDialog.findViewById(R.id.N2EditText);
                EditText ArEditText = (EditText)standardAirDialog.findViewById(R.id.ArEditText);
                EditText H20EditText = (EditText)standardAirDialog.findViewById(R.id.H2OEditText);
                C02EditText.setText("");
                O2EditText.setText("");
                N2EditText.setText("");
                ArEditText.setText("");
                H20EditText.setText("");

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog standardAirDialog = (Dialog)dialog;
                standardAirDialog.hide();
                standardAirSwitch.setChecked(false);


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

        //Instantiating Layouts
        layoutDuctPressure = mView.findViewById(R.id.layoutDuctPressure);
        layoutDuctPressure.setVisibility(View.GONE);
        layoutRelativeHumidity = mView.findViewById(R.id.layoutRelativeHumidity);
        layoutRelativeHumidity.setVisibility(View.GONE);
        layoutAtmosphericPressure = mView.findViewById(R.id.layoutAtmosphericPressure);
        layoutAtmosphericPressure.setVisibility(View.GONE);
        layoutGasDensityResult = mView.findViewById(R.id.layoutGasDensity);
        layoutGasDensityResult.setVisibility(View.GONE);


        unitsAverageVelocity = (TextView)mView.findViewById(R.id.UnitsactualAirFlowTextView);
        unitsMassAirFlow =  (TextView)mView.findViewById(R.id.UnitsmassAirFlowTextView);
        unitsActualAirFlow=   (TextView)  mView.findViewById(R.id.UnitsactualAirFlowTextView);
        unitsNormalAirFlow=(TextView)mView.findViewById(R.id.normalAirFlowValueUnits);
        UnitsDimensionHeightGasFlowFragmentTextView = (TextView)mView.findViewById(R.id.UnitsDimensionHeightGasFlowFragmentTextView);
        UnitsDimensionWidthGasFlowFragmentTextView =  (TextView)mView.findViewById(R.id.UnitsDimensionWidthGasFlowFragmentTextView);

        unitsGasDensityTemperatureDB = (TextView) mView.findViewById(R.id.UnitstemperatureGasDensityFragmentTextView);
        unitsGasDensityTemperatureWB = (TextView) mView.findViewById(R.id.UnitsWetBulbtemperatureGasDensityFragmentTextView);
        unitsGasDensitySeaLevelPressure = (TextView) mView.findViewById(R.id.UnitsseaLevelPressureGasDensityFragmentTextView);
        unitsGasDensityElevationAboveSeaLevel = (TextView) mView.findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityAtmosphericPressure = (TextView) mView.findViewById(R.id.UnitsAtmosphericPressureFragmentTextViewUnits);
        unitsDuctPressure = (TextView) mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity = (TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);



        dimensionHeader = (TextView)mView.findViewById(R.id.rectangularCircularGasFlowFragmentTextView);
        dimension1TextView =(TextView)mView.findViewById(R.id.dimensionHeightOrDiameterGasFlowFragmentTextView);
        dimension2TextView = (TextView)mView.findViewById(R.id.dimensionWidthGasFlowFragmentTextView);
        dimension2EditText = (EditText)mView.findViewById(R.id.dimensionWidthGasFlowFragmentEditText);
        dimension2UnitText = (TextView)mView.findViewById(R.id.UnitsactualAirFlowTextView);
        dimesnion2LinearLayout = (LinearLayout)mView.findViewById(R.id.dimension2LinearLayout);
        dimension2LinearLayoutFull = (CardView)mView.findViewById(R.id.dimension2LinearLayoutFull);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.numbers_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        layoutArea = mView.findViewById(R.id.layoutArea);
        layoutArea.setVisibility(View.GONE);
        layoutNormalAir = mView.findViewById(R.id.normalAirFlowLayout);
        layoutNormalAir.setVisibility(View.GONE);
        layoutAverageVelocity = mView.findViewById(R.id.layoutAverageVelocity);
        layoutAverageVelocity.setVisibility(View.GONE);
        massAirFlowLayout = mView.findViewById(R.id.massAirFlowLayout);
        massAirFlowLayout.setVisibility(View.GONE);
        layoutActualAirFlow = mView.findViewById(R.id.layoutActualAirFlow);
        layoutActualAirFlow.setVisibility(View.GONE);


        rectangularOrCircularSwitch= (Switch) mView.findViewById(R.id.rectangularCircularGasFlowFragmentSwitch);
        rectangularOrCircularSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDimensions("Circular");
                }
                else{
                    setDimensions("Rectangular");

                }
            }
        });



        return view;
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)


    }

    public void changeUnits(String units) {



        switch (units) {
            case "Metric":
                unitsAverageVelocity.setText("m/s");
                unitsMassAirFlow.setText("kg/s");
                unitsNormalAirFlow.setText("Nm³/h");
                unitsActualAirFlow.setText("m³/h");
                UnitsDimensionHeightGasFlowFragmentTextView.setText("m");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("m");
                unitsGasDensityTemperatureDB.setText("°C");
                unitsGasDensityTemperatureWB.setText("°C");
                unitsGasDensitySeaLevelPressure.setText("kPa");
                unitsGasDensityElevationAboveSeaLevel.setText("m");
                unitsGasDensityAtmosphericPressure.setText("kPa");
                unitsDuctPressure.setText("kPa");
                unitsCalculatedGasDensity.setText("kg/m³");

                break;
            case "Imperial":
                unitsAverageVelocity.setText("ft/s");
                unitsMassAirFlow.setText("lb/min");
                unitsNormalAirFlow.setText("ACFM");
                unitsActualAirFlow.setText("SCFM");
                UnitsDimensionHeightGasFlowFragmentTextView.setText("inches");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("inches");
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

    public void showResultView(){

        layoutDuctPressure.setVisibility(View.VISIBLE);
        layoutRelativeHumidity.setVisibility(View.VISIBLE);
        layoutAtmosphericPressure.setVisibility(View.VISIBLE);
        layoutGasDensityResult.setVisibility(View.VISIBLE);
        layoutArea.setVisibility(View.VISIBLE);
        layoutNormalAir.setVisibility(View.VISIBLE);
        layoutAverageVelocity.setVisibility(View.VISIBLE);
        massAirFlowLayout.setVisibility(View.VISIBLE);
        layoutActualAirFlow.setVisibility(View.VISIBLE);



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