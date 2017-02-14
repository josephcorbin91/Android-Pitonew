package com.jco.pitonew.Models;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.jco.pitonew.R;
import com.jco.pitonew.Utilities.Utility;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jco on 11/17/2016.
 */
public class GasFlow implements Serializable{
    private String DuctUnits;
    private Boolean rectangularDuct;
    private double diameter;
    private double height;
    private double width;
    private double area;
    private int pilotTubeCoeffecient;
    private boolean checkRule;
    private double ductPressure;

    private double dryBulbTemperature;
    private String pipeType;
    private EditText dimensionHeightEditText,pitotTubeCoefficientEditText, dynamicPressure, dimensionWidthEditText, dimensionDiameterEditText;
    private TextView areaTextView;

    //Dynamic Velocity
    private ArrayList<Double> dynamicVelocityArrayList= new ArrayList<Double>();
    private ArrayList<Double> dynamicPressureArrayList= new ArrayList<Double>();

    //Gas Density
    private double gasDensity;

    //Average Flow/Velocty
    private double averageVelocity;
    private double massAirFlow;
    private double actualAirFlow;
    private double normalAirFlow;






    public GasFlow(Activity activity, String pipeShape){

        this.pipeType=pipeShape;
        dimensionHeightEditText = (EditText)activity.findViewById(R.id.dimensionHeightGasFlowFragmentEditText);
        pitotTubeCoefficientEditText =(EditText)activity.findViewById(R.id.pitotTubeCoefficientEditText);
        dimensionWidthEditText =(EditText)activity.findViewById(R.id.dimensionWidthGasFlowFragmentEditText);
        areaTextView = (TextView)activity.findViewById(R.id.ductAreaGasFlowFragmentTextView);
    }

    //Area Calculations
    public double calculateArea(){
        if(pipeType == "Circular"){
            diameter = Double.valueOf(dimensionHeightEditText.getText().toString());
            area = Math.PI*diameter /2;
        }
        else if(pipeType == "Rectangular"){
            height = Double.valueOf(dimensionHeightEditText.getText().toString());
            width = Double.valueOf(dimensionWidthEditText.getText().toString());
            area = height *width;
        }
        areaTextView.setText(String.valueOf(area));
        return area;
    }

    //Dyanmic Velocity
    public void calculateDynamicVelocity(){
       for(int i=0; i<dynamicPressureArrayList.size();i++)
       {
           dynamicVelocityArrayList.set(i,pilotTubeCoeffecient*Math.pow(2*dynamicPressureArrayList.get(i)*1000/4.01864/gasDensity,0.5));
       }
    }

    //Average Velocity

    public double calculateAverageVelocity(){
        int sum=0;
        for(Double velocity: dynamicVelocityArrayList)
            sum+=velocity;
        averageVelocity=sum;
        return averageVelocity;
    }

    public double calculateMassAirFlow(){
        massAirFlow=actualAirFlow*gasDensity/3600;
        return massAirFlow;
    }

    public double calculateActualAirFlow(){
        actualAirFlow= averageVelocity*area*3600;
        return actualAirFlow;

    }
    public double calculateNormalAirFlow(){
        normalAirFlow=actualAirFlow*ductPressure/101.325*273.15/(273.15+((dryBulbTemperature-32)/1.8));
        return normalAirFlow;
    }



    public boolean verifyDataIntegrity() {
        return Utility.containsText(dimensionDiameterEditText)&&Utility.containsText(dimensionHeightEditText)&&Utility.containsText(dimensionWidthEditText);

    }

    }
