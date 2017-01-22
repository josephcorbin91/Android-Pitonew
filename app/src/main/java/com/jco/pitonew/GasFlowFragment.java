package com.jco.pitonew;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by jco on 11/23/2016.
 */
public class GasFlowFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Switch rectangularOrCircularSwitch;
    private View mView;
    private Spinner numberOfGasFlowValuesNumberSpinner;



    private TextView dimensionHeader, dimension2TextView,dimension1TextView,dimension2UnitText;
    private EditText dimension2EditText;
    CardView dimension2LinearLayoutFull;
    LinearLayout dimesnion2LinearLayout;
    public static GasFlowFragment newInstance() {
        return new GasFlowFragment();

    }


    public void showStandardAirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Gas Flow Velocity");
        builder.setTitle("Gas Flow Velocity");
        builder.setIcon(R.drawable.pitonew_logo);

        builder.setView(R.layout.inputfragment);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog standardAirDialog = (Dialog)dialog;
               EditText editText = (EditText)standardAirDialog.findViewById(R.id.input1);






            }
        });
        builder.setNegativeButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Dialog standardAirDialog = (Dialog)dialog;
                EditText editText = (EditText)standardAirDialog.findViewById(R.id.input1);
                  editText.setText("");


            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog standardAirDialog = (Dialog)dialog;
                standardAirDialog.hide();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public GasFlowFragment() {
    }
    public void setDimensions(String dimensions){
        switch (dimensions) {
            case "Rectangular":
                dimensionHeader.setText("Rectangular");
                dimension1TextView.setText("Height");
                dimension2TextView.setText("Width");
                dimension2TextView.setVisibility(View.VISIBLE);
                dimension2EditText.setVisibility(View.VISIBLE);

                dimension2UnitText.setVisibility(View.VISIBLE);
                dimesnion2LinearLayout.setVisibility(View.VISIBLE);
                dimension2LinearLayoutFull.setVisibility(View.VISIBLE);
                break;
            case "Circular":
                dimensionHeader.setText("Circular");
                dimension1TextView.setText("Diameter");
                dimension2TextView.setVisibility(View.INVISIBLE);
                dimension2EditText.setVisibility(View.INVISIBLE);
                dimension2UnitText.setVisibility(View.INVISIBLE);
                dimesnion2LinearLayout.setVisibility(View.INVISIBLE);
                dimension2LinearLayoutFull.setVisibility(View.INVISIBLE);
                break;
        }
    }


    public void showResultView(){

        View layoutArea = mView.findViewById(R.id.layoutArea);
        layoutArea.setVisibility(View.VISIBLE);
        View layoutNormalAir = mView.findViewById(R.id.normalAirFlowLayout);
        layoutNormalAir.setVisibility(View.VISIBLE);
        View layoutAverageVelocity = mView.findViewById(R.id.layoutAverageVelocity);
        layoutAverageVelocity.setVisibility(View.VISIBLE);
        View massAirFlowLayout = mView.findViewById(R.id.massAirFlowLayout);
        massAirFlowLayout.setVisibility(View.VISIBLE);
        View layoutActualAirFlow = mView.findViewById(R.id.layoutActualAirFlow);
        layoutActualAirFlow.setVisibility(View.VISIBLE);

    }
    public void clear(){

        Utility.clearAllFields((ViewGroup)this.mView);
        View layoutArea = mView.findViewById(R.id.layoutArea);
        layoutArea.setVisibility(View.GONE);
        View layoutNormalAir = mView.findViewById(R.id.normalAirFlowLayout);
        layoutNormalAir.setVisibility(View.GONE);
        View layoutAverageVelocity = mView.findViewById(R.id.layoutAverageVelocity);
        layoutAverageVelocity.setVisibility(View.GONE);
        View massAirFlowLayout = mView.findViewById(R.id.massAirFlowLayout);
        massAirFlowLayout.setVisibility(View.GONE);
        View layoutActualAirFlow = mView.findViewById(R.id.layoutActualAirFlow);
        layoutActualAirFlow.setVisibility(View.GONE);




    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gas_flow, container, false);
        this.mView = view;
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
   
        View layoutArea = mView.findViewById(R.id.layoutArea);
        layoutArea.setVisibility(View.GONE);
        View layoutNormalAir = mView.findViewById(R.id.normalAirFlowLayout);
        layoutNormalAir.setVisibility(View.GONE);
        View layoutAverageVelocity = mView.findViewById(R.id.layoutAverageVelocity);
        layoutAverageVelocity.setVisibility(View.GONE);
        View massAirFlowLayout = mView.findViewById(R.id.massAirFlowLayout);
        massAirFlowLayout.setVisibility(View.GONE);
        View layoutActualAirFlow = mView.findViewById(R.id.layoutActualAirFlow);
        layoutActualAirFlow.setVisibility(View.GONE);


        rectangularOrCircularSwitch= (Switch) mView.findViewById(R.id.rectangularCircularGasFlowFragmentSwitch);
        rectangularOrCircularSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDimensions("Rectangular");
                }
                else{
                    setDimensions("Circular");

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
        TextView unitsAverageVelocity = (TextView)mView.findViewById(R.id.UnitsactualAirFlowTextView);
        TextView unitsMassAirFlow =  (TextView)mView.findViewById(R.id.UnitsmassAirFlowTextView);
        TextView unitsActualAirFlow=   (TextView)  mView.findViewById(R.id.UnitsactualAirFlowTextView);
        TextView unitsNormalAirFlow=(TextView)mView.findViewById(R.id.normalAirFlowValueUnits);
        TextView UnitsDimensionHeightGasFlowFragmentTextView = (TextView)mView.findViewById(R.id.UnitsDimensionHeightGasFlowFragmentTextView);
        TextView UnitsDimensionWidthGasFlowFragmentTextView =  (TextView)mView.findViewById(R.id.UnitsDimensionWidthGasFlowFragmentTextView);

        switch (units) {
            case "Metric":
                unitsAverageVelocity.setText("m/s");
                unitsMassAirFlow.setText("kg/s");
                unitsNormalAirFlow.setText("Nm³/h");
                unitsActualAirFlow.setText("m³/h");
                UnitsDimensionHeightGasFlowFragmentTextView.setText("m");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("m");

                break;
            case "Imperial":
                unitsAverageVelocity.setText("ft/s");
                unitsMassAirFlow.setText("lb/min");
                unitsNormalAirFlow.setText("ACFM");
                unitsActualAirFlow.setText("SCFM");
                UnitsDimensionHeightGasFlowFragmentTextView.setText("inches");
                UnitsDimensionWidthGasFlowFragmentTextView.setText("inches");

                break;
        }
    }

}


