package com.jco.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by jco on 11/23/2016.
 */
public class GasFlowFragment extends Fragment {
    private Switch rectangularOrCircularSwitch;
    private View mView;



    private TextView dimensionHeader, dimension2TextView,dimension1TextView;
    private EditText dimension2EditText;
    public static GasFlowFragment newInstance() {
        return new GasFlowFragment();
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
                break;
            case "Circular":
                dimensionHeader.setText("Circular");
                dimension1TextView.setText("Diameter");
                dimension2TextView.setVisibility(View.INVISIBLE);
                dimension2EditText.setVisibility(View.INVISIBLE);
                break;
        }
    }


    public void clear(){
        Utility.clearAllFields((ViewGroup)this.mView);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gas_flow, container, false);
        this.mView = view;
        dimensionHeader = (TextView)mView.findViewById(R.id.circularOrDiamaterHeader);
        dimension1TextView =(TextView)mView.findViewById(R.id.dimensionHeightOrDiameterGasFlowFragmentTextView);
        dimension2TextView = (TextView)mView.findViewById(R.id.dimensionWidthGasFlowFragmentTextView);
        dimension2EditText = (EditText)mView.findViewById(R.id.dimensionWidthGasFlowFragmentEditText);


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




    public void changeUnits(String units) {
        TextView unitsAverageVelocity = (TextView)mView.findViewById(R.id.UnitsactualAirFlowTextView);
        TextView unitsMassAirFlow =  (TextView)mView.findViewById(R.id.UnitsmassAirFlowTextView);
        TextView unitsActualAirFlow=   (TextView)  mView.findViewById(R.id.UnitsactualAirFlowTextView);
        TextView unitsNormalAirFlow=(TextView)mView.findViewById(R.id.UnitsnormalAirFlowTextView);
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


