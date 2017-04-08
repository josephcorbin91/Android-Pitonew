package com.jco.pitonew.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.jco.pitonew.Activities.DisplayActivity;
import com.jco.pitonew.ButtonRectangle;
import com.jco.pitonew.Models.Gas;
import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.DecimalDigitsInputFilter;
import com.jco.pitonew.Utilities.Utility;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {
    public static InputFragment newInstance() {
        return new InputFragment();
    }

    public static InputFragment newInstance(Double[] previousResults,String currentUnits) {
        InputFragment.previousResults=previousResults;
        InputFragment.currentUnits=currentUnits;
        return new InputFragment();
    }


    private Gas gas;

    private ButtonRectangle dynamicVelocityButton;
    private ArrayList<String> stringInputValues = new ArrayList<String>();
    boolean clickedReturn=false;



    private double[] standardAirResult;


    public static Double[] previousResults;
    public static String currentUnits;
    //GUI Components
    private TextView switchTextViewStandardAir, switchTextViewPipeShape, switchTextViewWetBulb,dynamicVelocityResultTextView;
    private ImageView wetBulbIcon, standardAirIcon, pipeShapeIcon;
    private double airContentPercentageCO2, airContentPercentageO2, airContentPercentageN2, airContentPercentageAr, airContentPercentageH2O;
    private ImageButton dynamicVelocityDeleteButton;

    private LinearLayout parentLinearLayout;
    private TextView unitsGasDensityTemperatureDB,dynamicVelocityResultTextViewUnits, unitsGasDensityTemperatureWB, unitsCalculatedGasDensity, unitsGasDensitySeaLevelPressure, unitsGasDensityElevationAboveSeaLevel, unitsGasDensityAtmosphericPressure, unitsStaticPressure, unitsDuctPressure, dimensionHeader, dimension2TextView, dimension1TextView, dimension2UnitText, unitsAverageVelocity, unitsMassAirFlow, unitsActualAirFlow, unitsNormalAirFlow, UnitsDimensionHeightGasFlowFragmentTextView, UnitsDimensionWidthGasFlowFragmentTextView;
    private ArrayList<Double> dynamicPressureArrayList = new ArrayList<>();

    private TextView wetBulbTemperatureTextView;
    private EditText dimension_1_WidthGasEditText,DynamicVelocityEditText, dimension_2_HeightGasEditText, pitotTubeCoefficientEditText, staticPressureFragmentEditText, temperatureGasFragmentEditText, ElevationAboveSeaLevelFragmentEdiText, wetBulbTemperatureGasFragmentEditText, seaLevelPressureGasFragmentEditText;
    private TableRow dimension_1_table_row, dimension_2_table_row, wetBulbTemperatureTableRow;

    public Boolean getStandardAirBoolean() {
        return standardAirBoolean;
    }

    private View first_separator, wetBulbTemperatureDivider;
    private Boolean standardAirBoolean;
    private Switch standardAirSwitch, rectangularOrCircularSwitch, wetBulbTemperatureSwitch;
    private View mView;

    public void setDimensions(String dimensions) {
        switch (dimensions) {
            case "Rectangular":
                dimension1TextView.setText("Width");
                dimension2TextView.setText("Height");
                dimension2TextView.setVisibility(View.VISIBLE);
                first_separator.setVisibility(View.VISIBLE);
                dimension_2_HeightGasEditText.setVisibility(View.VISIBLE);
                UnitsDimensionHeightGasFlowFragmentTextView.setVisibility(View.VISIBLE);
                dimension_2_table_row.setVisibility(View.VISIBLE);
                break;
            case "Circular":
                first_separator.setVisibility(View.GONE);
                dimension1TextView.setText("Diameter");
                dimension2TextView.setVisibility(View.GONE);
                dimension_2_HeightGasEditText.setVisibility(View.GONE);
                UnitsDimensionHeightGasFlowFragmentTextView.setVisibility(View.GONE);
                dimension_2_table_row.setVisibility(View.GONE);

                break;
        }
    }

    public boolean pipeType() {
        return rectangularOrCircularSwitch.isChecked();
    }

    public InputFragment() {

        standardAirBoolean = false;

    }


    public void clickedReturn(boolean clickedReturn){

        clickedReturn=true;
    }

    public void instantiateViews() {


        System.out.println("instantiate view called");
        airContentPercentageCO2 = 0.03;
        airContentPercentageO2 = 20.95;
        airContentPercentageN2 = 78.09;
        airContentPercentageAr = 0.93;
        airContentPercentageH2O = 0.00;

        dynamicVelocityDeleteButton = (ImageButton) mView.findViewById(R.id.dynamicVelocityDeleteButton);


        parentLinearLayout = (LinearLayout) mView.findViewById(R.id.parentLinearLayout);
        wetBulbIcon = (ImageView) mView.findViewById(R.id.wetBulbIcon);
        standardAirIcon = (ImageView) mView.findViewById(R.id.standardAirIcon);
        pipeShapeIcon = (ImageView) mView.findViewById(R.id.pipeShapeIcon);

        first_separator = (View) mView.findViewById(R.id.first_separator);
        UnitsDimensionHeightGasFlowFragmentTextView = (TextView) mView.findViewById(R.id.UnitsDimension_2_HeightGasFragmentTextView);
        UnitsDimensionWidthGasFlowFragmentTextView = (TextView) mView.findViewById(R.id.UnitsDimension_1_WidthGasFragmentTextView);

        switchTextViewStandardAir = (TextView) mView.findViewById(R.id.switchTextViewStandardAir);
        switchTextViewPipeShape = (TextView) mView.findViewById(R.id.switchTextViewPipeShape);
        switchTextViewWetBulb = (TextView) mView.findViewById(R.id.switchTextViewWetBulb);
        dynamicVelocityResultTextViewUnits = (TextView)mView.findViewById(R.id.dynamicVelocityResultTextViewUnits);
        dimension1TextView = (TextView) mView.findViewById(R.id.dimension1TextView);
        dimension2TextView = (TextView) mView.findViewById(R.id.dimension2TextView);
        dimension_1_table_row = (TableRow) mView.findViewById(R.id.dimension_1_table_row);
        dimension_2_table_row = (TableRow) mView.findViewById(R.id.dimension_2_table_row);
        unitsGasDensityTemperatureDB = (TextView) mView.findViewById(R.id.UnitstemperatureGasDensityFragmentTextView);
        unitsGasDensityTemperatureWB = (TextView) mView.findViewById(R.id.UnitsWetBulbtemperatureGasDensityFragmentTextView);
        unitsGasDensitySeaLevelPressure = (TextView) mView.findViewById(R.id.UnitsseaLevelPressureGasDensityFragmentTextView);
        unitsGasDensityElevationAboveSeaLevel = (TextView) mView.findViewById(R.id.UnitsElevationAboveSeaLevelFragmentTextView);
        unitsGasDensityAtmosphericPressure = (TextView) mView.findViewById(R.id.UnitsAtmosphericPressureFragmentTextViewUnits);
        unitsDuctPressure = (TextView) mView.findViewById(R.id.UnitsDuctPressureFragmentTextView);
        unitsCalculatedGasDensity = (TextView) mView.findViewById(R.id.UnitsCalculatedGasDensityTextView);


        DynamicVelocityEditText = (EditText)mView.findViewById(R.id.DynamicVelocityEditText);


        DynamicVelocityEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                                                              @Override
                                                              public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                                  if (actionId == EditorInfo.IME_ACTION_NEXT) {

                                                                      if (!DynamicVelocityEditText.getText().toString().equals("")) {

                                                                         // System.out.println("SIZE"+dynamicPressureArrayList.size());
                                                                          dynamicPressureArrayList.add(Double.valueOf(DynamicVelocityEditText.getText().toString()));
                                                                          DynamicVelocityEditText.setText("");
                                                                          String currentString = "";
                                                                          for (int i = 0; i < dynamicPressureArrayList.size(); i++) {
                                                                              if (i % 10 == 0)
                                                                                  currentString += "\n";
                                                                              currentString += String.valueOf(dynamicPressureArrayList.get(i)) + "  ";

                                                                              System.out.println("Current" + dynamicPressureArrayList.get(i));
                                                                          }
                                                                          dynamicVelocityResultTextView.setText(currentString);

                                                                          return true;
                                                                      }
                                                                      return true;
                                                                  }

                                                                  return true;
                                                              }

                                                              });
        dimension_1_WidthGasEditText = (EditText) mView.findViewById(R.id.dimension_1_WidthGasEditText);
        dimension_2_HeightGasEditText = (EditText) mView.findViewById(R.id.dimension_2_HeightGasEditText);
        pitotTubeCoefficientEditText = (EditText) mView.findViewById(R.id.pitotTubeCoefficientEditText);
        staticPressureFragmentEditText = (EditText) mView.findViewById(R.id.staticPressureFragmentEditText);
        temperatureGasFragmentEditText = (EditText) mView.findViewById(R.id.temperatureGasFragmentEditText);
        ElevationAboveSeaLevelFragmentEdiText = (EditText) mView.findViewById(R.id.ElevationAboveSeaLevelFragmentEdiText);
        wetBulbTemperatureGasFragmentEditText = (EditText) mView.findViewById(R.id.wetBulbTemperatureGasFragmentEditText);
        seaLevelPressureGasFragmentEditText = (EditText) mView.findViewById(R.id.seaLevelPressureGasFragmentEditText);
        InputFilter[] decimalInputFilter = new InputFilter[]{new DecimalDigitsInputFilter(8, 3)};

        dimension_1_WidthGasEditText.setFilters(decimalInputFilter);
        dimension_2_HeightGasEditText.setFilters(decimalInputFilter);
        pitotTubeCoefficientEditText.setFilters(decimalInputFilter);
        staticPressureFragmentEditText.setFilters(decimalInputFilter);
        temperatureGasFragmentEditText.setFilters(decimalInputFilter);
        ElevationAboveSeaLevelFragmentEdiText.setFilters(decimalInputFilter);
        wetBulbTemperatureGasFragmentEditText.setFilters(decimalInputFilter);
        seaLevelPressureGasFragmentEditText.setFilters(decimalInputFilter);


        wetBulbTemperatureDivider = (View) mView.findViewById(R.id.wetBulbTemperatureDivider);
        wetBulbTemperatureTableRow = (TableRow) mView.findViewById(R.id.wetBulbTemperatureTableRow);
        wetBulbTemperatureTextView = (TextView) mView.findViewById(R.id.wetBulbTemperatureTextView);
        dynamicVelocityResultTextView= (TextView) mView.findViewById(R.id.dynamicVelocityResultTextView);
        wetBulbTemperatureGasFragmentEditText.setText("0");



        rectangularOrCircularSwitch = (Switch) mView.findViewById(R.id.rectangularOrCircularSwitch);
        standardAirSwitch = (Switch) mView.findViewById(R.id.standardAirSwitch);
        wetBulbTemperatureSwitch = (Switch) mView.findViewById(R.id.wetBulbTemperatureSwitch);
        standardAirSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showStandardAirDialog();
                } else {
                    airContentPercentageCO2 = 0.03;
                    airContentPercentageO2 = 20.95;
                    airContentPercentageN2 = 78.09;
                    airContentPercentageAr = 0.93;
                    airContentPercentageH2O = 0.00;


                }

            }
        });


        rectangularOrCircularSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pipeShapeIcon.setImageResource(R.drawable.circular_shape);
                    setDimensions("Circular");
                } else {
                    pipeShapeIcon.setImageResource(R.drawable.rectangular_shape);
                    setDimensions("Rectangular");
                }

            }
        });
        wetBulbTemperatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    wetBulbTemperatureTableRow.setVisibility(View.VISIBLE);
                    wetBulbTemperatureTextView.setVisibility(View.VISIBLE);
                    wetBulbTemperatureGasFragmentEditText.setVisibility(View.VISIBLE);
                    wetBulbTemperatureDivider.setVisibility(View.VISIBLE);
                    unitsGasDensityTemperatureWB.setVisibility(View.VISIBLE);
                    wetBulbIcon.setImageResource(R.drawable.wet_bulb_enabled);

                } else {
                    wetBulbIcon.setImageResource(R.drawable.wet_bulb_disabled);
                    wetBulbTemperatureTableRow.setVisibility(View.GONE);
                    wetBulbTemperatureTextView.setVisibility(View.GONE);
                    wetBulbTemperatureGasFragmentEditText.setVisibility(View.GONE);
                    unitsGasDensityTemperatureWB.setVisibility(View.GONE);
                    wetBulbTemperatureDivider.setVisibility(View.GONE);
                    wetBulbTemperatureGasFragmentEditText.setText("0");


                }

            }
        });

        dynamicVelocityDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DynamicVelocityEditText.setError(null);
                boolean cancel = false;
                View focusView = null;

                if(dynamicPressureArrayList.isEmpty()) {

                    DynamicVelocityEditText.setError(getString(R.string.dynamic_velocity_required));
                    focusView = dynamicVelocityResultTextView;
                    cancel = true;
                }

                if (cancel) {
                    Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                    focusView.requestFocus();
                }
                else {  dynamicPressureArrayList.remove(dynamicPressureArrayList.size() - 1);
                    String currentString = "";

                    for (int i = 0; i < dynamicPressureArrayList.size(); i++) {
                        if (i % 10 == 0)
                            currentString += "\n";
                        currentString += String.valueOf(dynamicPressureArrayList.get(i)) + "  ";

                    }

                    dynamicVelocityResultTextView.setText(currentString);



                }

            }
        });
        if(!wetBulbTemperatureSwitch.isChecked()) {

            wetBulbIcon.setImageResource(R.drawable.wet_bulb_disabled);
            wetBulbTemperatureTableRow.setVisibility(View.GONE);
            wetBulbTemperatureTextView.setVisibility(View.GONE);
            wetBulbTemperatureGasFragmentEditText.setVisibility(View.GONE);
            unitsGasDensityTemperatureWB.setVisibility(View.GONE);
            wetBulbTemperatureDivider.setVisibility(View.GONE);
            wetBulbTemperatureGasFragmentEditText.setText("0");
        }


       //testApps();
    }


    public void changeStandardAirSwitch(boolean checked){
        standardAirSwitch.setChecked(checked);
    }
    public void changeWetBulbSwitch(boolean checked){
        wetBulbTemperatureSwitch.setChecked(checked);
    }

    public void changePipeTypeSwitch(boolean checked){
        rectangularOrCircularSwitch.setChecked(checked);
    }


    public void setVelocities(ArrayList<Double> dynamicPressureArrayList){
        this.dynamicPressureArrayList = dynamicPressureArrayList;
    }

    public  void updateVelocity(ArrayList<Double> dynamicPressureArrayList){
        String currentString = "";
        for (int i = 0; i < dynamicPressureArrayList.size(); i++) {
            if (i % 10 == 0)
                currentString += "\n";
            currentString += String.valueOf(dynamicPressureArrayList.get(i)) + "  ";
            System.out.println("PRESSURES "+dynamicPressureArrayList.get(i));

        }
        dynamicVelocityResultTextView.setText(currentString);

    }
    private double units;
    public Double[] getResults() {

        if(!wetBulbTemperatureSwitch.isChecked())
            wetBulbTemperatureGasFragmentEditText.setText("0");
        if(rectangularOrCircularSwitch.isChecked())
            dimension_2_HeightGasEditText.setText("0");


        Double[] inputArray = new Double[]{Double.valueOf(dimension_1_WidthGasEditText.getText().toString()), Double.valueOf(dimension_2_HeightGasEditText.getText().toString())
                , Double.valueOf(pitotTubeCoefficientEditText.getText().toString()), Double.valueOf(staticPressureFragmentEditText.getText().toString())
                , Double.valueOf(temperatureGasFragmentEditText.getText().toString()), Double.valueOf(ElevationAboveSeaLevelFragmentEdiText.getText().toString())
                , wetBulbTemperatureSwitch.isChecked()? Double.valueOf(wetBulbTemperatureGasFragmentEditText.getText().toString()) : 0, Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString())
                , airContentPercentageCO2, airContentPercentageO2, airContentPercentageN2, airContentPercentageAr, airContentPercentageH2O};
        return inputArray;
    }

    public void testApps(){
        wetBulbTemperatureSwitch.setChecked(true);

        standardAirSwitch.setChecked(true);
        airContentPercentageCO2 = 0.99;
        airContentPercentageO2 = 0.1;
        airContentPercentageN2 = 0;
        airContentPercentageAr = 0;
        airContentPercentageH2O = 0.00;

        dimension_1_WidthGasEditText.setText("1");
        dimension_2_HeightGasEditText.setText("1");
        pitotTubeCoefficientEditText.setText("0.9");
        staticPressureFragmentEditText.setText("2");
        temperatureGasFragmentEditText.setText("1");
        ElevationAboveSeaLevelFragmentEdiText.setText("2");
        wetBulbTemperatureGasFragmentEditText.setText("6");
        seaLevelPressureGasFragmentEditText.setText("10.01");


    }
    private boolean previousUnits;
    public void setPreviousUnits(boolean previousUnits, ArrayList<Double> dynamicPressureArrayList)
    {
        this.dynamicPressureArrayList = dynamicPressureArrayList;
        this.previousUnits= previousUnits;
    }


    @Override
    public void onStart() {

        System.out.println("UNS ON START CALLED INPUT");
        System.out.println("UNS CURRENT"+((DisplayActivity)getActivity()).UnitSwitch().isChecked());
        System.out.println("UNS PREVIOUS"+previousUnits);
//        updateVelocity(this.dynamicPressureArrayList);
        if(((DisplayActivity)getActivity()).UnitSwitch().isChecked() &&((DisplayActivity)getActivity()).UnitSwitch().isChecked()!=previousUnits) {
            System.out.println("UNS CURRENT"+((DisplayActivity)getActivity()).UnitSwitch().isChecked());
            System.out.println("UNS PREVIOUS"+previousUnits);


            changeUnits("US");
        }
        else if(!((DisplayActivity)getActivity()).UnitSwitch().isChecked() &&((DisplayActivity)getActivity()).UnitSwitch().isChecked()!=previousUnits) {
            System.out.println("UNS CURRENT" + ((DisplayActivity) getActivity()).UnitSwitch().isChecked());
            System.out.println("UNS PREVIOUS" + previousUnits);

            changeUnits("SI");
        }

        changeUnitString(((DisplayActivity)getActivity()).UnitSwitch().isChecked());

        if(standardAirSwitch.isChecked()) {
            standardAirDialog.hide();
        }

        standardAirSwitch.setChecked(false);
           super.onStart();

    }

    public void clear() {
        Utility.clearAllFields((ViewGroup) this.mView);
        rectangularOrCircularSwitch.setChecked(false);
        standardAirSwitch.setChecked(false);
        wetBulbTemperatureSwitch.setChecked(false);
        wetBulbTemperatureGasFragmentEditText.setText("0");
        dynamicPressureArrayList.clear();
        dynamicVelocityResultTextView.setText("");

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        super.onCreate(savedInstanceState);
    }

    private Dialog standardAirDialog;

    @Nullable
    public void showStandardAirDialog() {
        /*
        Refactor code so you only call the EditText Once.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Air Composition");
        builder.setMessage("Enter volumetric %.");

        builder.setView(R.layout.dialog_standardair);
        builder.setPositiveButton("Confirm", null); //Set to null. We override the onclick
        builder.setNegativeButton("Cancel", null);
        builder.setNeutralButton("Clear", null);

        standardAirDialog = builder.create();

        standardAirDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button buttonPositive = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Dialog standardAirDialog = InputFragment.this.standardAirDialog;

                        EditText C02EditText = (EditText) standardAirDialog.findViewById(R.id.C02EditText);
                        EditText O2EditText = (EditText) standardAirDialog.findViewById(R.id.O2EditText);
                        EditText N2EditText = (EditText) standardAirDialog.findViewById(R.id.N2EditText);
                        EditText ArEditText = (EditText) standardAirDialog.findViewById(R.id.ArEditText);
                        EditText H20EditText = (EditText) standardAirDialog.findViewById(R.id.H2OEditText);
                        try {
                            InputFragment.this.airContentPercentageCO2 = Double.valueOf(C02EditText.getText().toString());
                            InputFragment.this.airContentPercentageO2 = Double.valueOf(O2EditText.getText().toString());
                            InputFragment.this.airContentPercentageN2 = Double.valueOf(N2EditText.getText().toString());
                            InputFragment.this.airContentPercentageAr = Double.valueOf(ArEditText.getText().toString());
                            InputFragment.this.airContentPercentageH2O = Double.valueOf(H20EditText.getText().toString());
                        } catch (NumberFormatException ex) {
                            System.out.print(ex.getMessage());
                        }

                        System.out.println("CO2"+InputFragment.this.airContentPercentageCO2);
                        System.out.println("O2"+InputFragment.this.airContentPercentageO2);
                        System.out.println("N2"+InputFragment.this.airContentPercentageN2);
                        System.out.println("Ar"+InputFragment.this.airContentPercentageAr);
                        System.out.println("H2O"+InputFragment.this.airContentPercentageH2O);
                        double sum= InputFragment.this.airContentPercentageCO2 + InputFragment.this.airContentPercentageO2 + InputFragment.this.airContentPercentageN2 + InputFragment.this.airContentPercentageAr + InputFragment.this.airContentPercentageH2O;
                        sum =Math.floor(sum);

                        System.out.println(sum);


                        C02EditText.setError(null);
                        O2EditText.setError(null);
                        N2EditText.setError(null);
                        ArEditText.setError(null);
                        H20EditText.setError(null);
                        boolean cancel = false;
                        View focusView = null;

                        // Check for a valid password, if the user entered one.
                        if (TextUtils.isEmpty(C02EditText.getText().toString())) {
                            C02EditText.setError(getString(R.string.empty_field));
                            focusView = C02EditText;
                            cancel = true;
                        }
                        if (TextUtils.isEmpty(O2EditText.getText().toString())) {
                            O2EditText.setError(getString(R.string.empty_field));
                            focusView = O2EditText;
                            cancel = true;
                        }
                        if (TextUtils.isEmpty(N2EditText.getText().toString())) {
                            N2EditText.setError(getString(R.string.empty_field));
                            focusView = N2EditText;
                            cancel = true;
                        }
                        if (TextUtils.isEmpty(ArEditText.getText().toString())) {
                            ArEditText.setError(getString(R.string.empty_field));
                            focusView = ArEditText;
                            cancel = true;
                        }
                        if (TextUtils.isEmpty(H20EditText.getText().toString())) {
                            H20EditText.setError(getString(R.string.empty_field));
                            focusView = H20EditText;
                            cancel = true;
                        }

                        if (cancel) {
                            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                            focusView.requestFocus();
                        }

                        else if(sum != 100.0) {

                            Toast.makeText(getContext(), "Summation of Air Composition must be 100%. " + sum, Toast.LENGTH_LONG).show();
                            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                        } else {
                            standardAirDialog.dismiss();
                        }
                    }
                });
                Button buttonNeutral = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEUTRAL);
                buttonNeutral.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Dialog standardAirDialog = InputFragment.this.standardAirDialog;
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
                    }
                });
                Button buttonNegative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                buttonNegative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Dialog standardAirDialog = InputFragment.this.standardAirDialog;

                        InputFragment.this.standardAirSwitch.setChecked(false);
                        standardAirDialog.hide();
                    }
                });
            }
        });

        standardAirDialog.setCancelable(false);
        standardAirDialog.setCanceledOnTouchOutside(false);
        standardAirDialog.show();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        System.out.println("On Create View");
        this.mView = view;
        instantiateViews();
        if(previousResults!=null)
            setInput(previousResults,currentUnits);
        return view;
    }


    public void setInput(Double[] previousResults,String currentUnits){


        System.out.println("previousResultsCalled");
        dimension_1_WidthGasEditText.setText(String.valueOf(previousResults[0]));
        dimension_2_HeightGasEditText.setText(String.valueOf(previousResults[1]));
        pitotTubeCoefficientEditText.setText(String.valueOf(previousResults[2]));
        staticPressureFragmentEditText.setText(String.valueOf(previousResults[3]));
        temperatureGasFragmentEditText.setText(String.valueOf(previousResults[4]));
        wetBulbTemperatureGasFragmentEditText.setText(String.valueOf(previousResults[5]));

        ElevationAboveSeaLevelFragmentEdiText.setText(String.valueOf(previousResults[6]));
        seaLevelPressureGasFragmentEditText.setText(String.valueOf(previousResults[7]));
        wetBulbTemperatureSwitch.setChecked(false);
        standardAirSwitch.setChecked(false);
        rectangularOrCircularSwitch.setChecked(false);
        changeUnitString(((DisplayActivity)getActivity()).UnitSwitch().isChecked());
    }
    public boolean wetBulbEnabled() {
        return wetBulbTemperatureSwitch.isChecked();
    }


    public void changeUnitString(boolean units){
    if(!units) {
        UnitsDimensionHeightGasFlowFragmentTextView.setText("m");
        UnitsDimensionWidthGasFlowFragmentTextView.setText("m");
        unitsGasDensityTemperatureDB.setText("°C");
        unitsGasDensityTemperatureWB.setText("°C");
        unitsGasDensitySeaLevelPressure.setText("kPa");
    }
    else {
                UnitsDimensionHeightGasFlowFragmentTextView.setText("inches");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("inches");
                unitsGasDensityTemperatureDB.setText("°F");
                unitsGasDensityTemperatureWB.setText("°F");
                unitsGasDensitySeaLevelPressure.setText("in. Hg");


        }
    }
    public void changeUnits(String units) {
        DecimalFormat doubleTwoDigitsDecimalFormat = new DecimalFormat("#.00");
        System.out.println("UNS :changing input to"+units);
        DecimalFormat doubleThreeDigitsDecimalFormat = new DecimalFormat("#.000");

        switch (units) {
            case "SI":
                UnitsDimensionHeightGasFlowFragmentTextView.setText("m");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("m");
                unitsGasDensityTemperatureDB.setText("°C");
                unitsGasDensityTemperatureWB.setText("°C");
                unitsGasDensitySeaLevelPressure.setText("kPa");

                if (dimension_1_WidthGasEditText.getText().toString().length() > 0)
                    dimension_1_WidthGasEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(Double.valueOf(dimension_1_WidthGasEditText.getText().toString()) * 0.0254)));
                if (dimension_2_HeightGasEditText.getText().toString().length() > 0)
                    dimension_2_HeightGasEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(Double.valueOf(dimension_2_HeightGasEditText.getText().toString()) * 0.0254)));
                 if (seaLevelPressureGasFragmentEditText.getText().toString().length() > 0) {
                     seaLevelPressureGasFragmentEditText.setText(String.valueOf(Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString())));

                     System.out.println("SWITCH TO C"+ seaLevelPressureGasFragmentEditText.getText());
                     seaLevelPressureGasFragmentEditText.setText(String.valueOf(doubleThreeDigitsDecimalFormat.format(Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString()) / 0.2952998751)));
                     System.out.println("SWITCH TO C"+ seaLevelPressureGasFragmentEditText.getText().toString());

                 }
                if (temperatureGasFragmentEditText.getText().toString().length() > 0)
                    temperatureGasFragmentEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format((Double.valueOf(temperatureGasFragmentEditText.getText().toString()) - 32) / 1.8)));
                if (wetBulbTemperatureGasFragmentEditText.getText().toString().length() > 0)
                    wetBulbTemperatureGasFragmentEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format((Double.valueOf(wetBulbTemperatureGasFragmentEditText.getText().toString()) - 32) / 1.8)));



                break;
            case "US":
                UnitsDimensionHeightGasFlowFragmentTextView.setText("inches");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("inches");
                unitsGasDensityTemperatureDB.setText("°F");

                unitsGasDensityTemperatureWB.setText("°F");
                unitsGasDensitySeaLevelPressure.setText("in. Hg");
                if (dimension_1_WidthGasEditText.getText().toString().length() > 0)
                    dimension_1_WidthGasEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(Double.valueOf(dimension_1_WidthGasEditText.getText().toString()) / 0.0254)));
                if (dimension_2_HeightGasEditText.getText().toString().length() > 0)
                    dimension_2_HeightGasEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(Double.valueOf(dimension_2_HeightGasEditText.getText().toString()) / 0.0254)));
                if (seaLevelPressureGasFragmentEditText.getText().toString().length() > 0) {
                    seaLevelPressureGasFragmentEditText.setText(String.valueOf(Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString())));
                    System.out.println("SWITCH TO F"+ seaLevelPressureGasFragmentEditText.getText().toString());
                    seaLevelPressureGasFragmentEditText.setText(String.valueOf(doubleThreeDigitsDecimalFormat.format(Double.valueOf(seaLevelPressureGasFragmentEditText.getText().toString()) * 0.2952998751)));

                    System.out.println("SWITCH TO F"+ seaLevelPressureGasFragmentEditText.getText().toString());


                }
                if (temperatureGasFragmentEditText.getText().toString().length() > 0)
                    temperatureGasFragmentEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(((Double.valueOf(temperatureGasFragmentEditText.getText().toString()) * 1.8) + 32))));
                if (wetBulbTemperatureGasFragmentEditText.getText().toString().length() > 0)
                    wetBulbTemperatureGasFragmentEditText.setText(String.valueOf(doubleTwoDigitsDecimalFormat.format(((Double.valueOf(wetBulbTemperatureGasFragmentEditText.getText().toString()) * 1.8) + 32))));


                break;
        }
    }


    public boolean validInput() {

        dimension_1_WidthGasEditText.setError(null);
        dimension_2_HeightGasEditText.setError(null);
        pitotTubeCoefficientEditText.setError(null);
        staticPressureFragmentEditText.setError(null);
        temperatureGasFragmentEditText.setError(null);
        ElevationAboveSeaLevelFragmentEdiText.setError(null);
        wetBulbTemperatureGasFragmentEditText.setError(null);
        seaLevelPressureGasFragmentEditText.setError(null);
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(dimension_1_WidthGasEditText.getText().toString())) {
            dimension_1_WidthGasEditText.setError(getString(R.string.empty_field));
            focusView = dimension_1_WidthGasEditText;
            cancel = true;
        }
        if (dimension_2_HeightGasEditText.getVisibility() == View.VISIBLE && TextUtils.isEmpty(dimension_2_HeightGasEditText.getText().toString())) {
            dimension_2_HeightGasEditText.setError(getString(R.string.empty_field));
            focusView = dimension_2_HeightGasEditText;
            cancel = true;
        }
        if (TextUtils.isEmpty(pitotTubeCoefficientEditText.getText().toString())) {
            pitotTubeCoefficientEditText.setError(getString(R.string.empty_field));
            focusView = pitotTubeCoefficientEditText;
            cancel = true;
        }
        if (TextUtils.isEmpty(staticPressureFragmentEditText.getText().toString())) {
            staticPressureFragmentEditText.setError(getString(R.string.empty_field));
            focusView = staticPressureFragmentEditText;
            cancel = true;
        }
        if (TextUtils.isEmpty(temperatureGasFragmentEditText.getText().toString())) {
            temperatureGasFragmentEditText.setError(getString(R.string.empty_field));
            focusView = temperatureGasFragmentEditText;
            cancel = true;
        }
        if (TextUtils.isEmpty(ElevationAboveSeaLevelFragmentEdiText.getText().toString())) {
            ElevationAboveSeaLevelFragmentEdiText.setError(getString(R.string.empty_field));
            focusView = ElevationAboveSeaLevelFragmentEdiText;
            cancel = true;
        }

        if (wetBulbTemperatureSwitch.isChecked()&& TextUtils.isEmpty(wetBulbTemperatureGasFragmentEditText.getText().toString())) {
            wetBulbTemperatureGasFragmentEditText.setError(getString(R.string.empty_field));
            focusView = wetBulbTemperatureGasFragmentEditText;
            cancel = true;
        }
        if (TextUtils.isEmpty(seaLevelPressureGasFragmentEditText.getText().toString())) {
            seaLevelPressureGasFragmentEditText.setError(getString(R.string.empty_field));
            focusView = seaLevelPressureGasFragmentEditText;
            cancel = true;
        }
        if(dynamicVelocityResultTextView.getText().equals(""))
        {
            DynamicVelocityEditText.setError(getString(R.string.empty_field));
            focusView = DynamicVelocityEditText;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else
            return true;
    }

    public  ArrayList<Double> getDynamicPressureArray(){
        return this.dynamicPressureArrayList;
    }
    public boolean verifyDataPressureRule(){
        double maxPressureValue;
        double currentMax=dynamicPressureArrayList.get(0);
        for(int i=0; i< dynamicPressureArrayList.size();i++)
            if(dynamicPressureArrayList.get(i)>currentMax)
                currentMax=dynamicPressureArrayList.get(i);

        maxPressureValue=currentMax;
        double acceptablePressureValue=0;
        for(int i=0; i< dynamicPressureArrayList.size();i++) {
            if (dynamicPressureArrayList.get(i) > 0.1 * maxPressureValue)
                acceptablePressureValue++;
        }

        double percentageOfAcceptableValues = acceptablePressureValue/(double)dynamicPressureArrayList.size();

        return percentageOfAcceptableValues>=0.75;
    }
}




