package com.jco.pitonew.Models;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.jco.pitonew.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jco on 2/6/2017.
 */

public class Gas implements Serializable {


    private String DuctUnits;
    private Boolean rectangularDuct;
    private double diameter;
    private double height;
    private double width;
    private double area;
    private int pilotTubeCoeffecient;
    private boolean checkRule;
    private double ductPressure;

    //Air Type Variables
    private boolean isStandardAir;
    private boolean isWetBulbTemperature;

    //Gas Constants
    public final double standardAirMolarWeight=28.96;
    private final double criticalPressureH20 = 166818;
    private final double criticalTemperatureH20=1165.67;


    //AirCompositionVariables
    private double airContentPercentageCO2,airContentPercentageO2,airContentPercentageN2,airContentPercentageAr,airContentPercentageH2O;

    //Molecular Weight
    private double molarWeight;

    //Temperature
    private double dryBulbTemperature,wetBulbTemperature;

    //Pressure
    private double dryBulbWaterSaturationPressurePD,wetBulbWaterSaturationPressurePW,partialWaterPressureDueToDepressionPM,partialPressureOfWaterPA,seaLevelPressure;


    //Relative Humidity
    private double relativeHumidity,Kd,Kw,dryBulbWaterSaturationPressurePD,wetBulbWaterSaturationPressurePW,partialWaterPressureDueToDepressionPM,partialPressureOfWaterPA,dryAirHumidity,wetAFirHumidity;


    //Altitutde
    private double elevationAboveSeaLevel;


    //Dynamic Velocity
    private ArrayList<Double> dynamicVelocityArrayList= new ArrayList<Double>();
    private ArrayList<Double> dynamicPressureArrayList= new ArrayList<Double>();


    //Gas Density
    private double gasDensity;


    //Average Flow/Velocty
    private double averageVelocity,massAirFlow,actualAirFlow, normalAirFlow;

    //Application variables
    Activity currentActivity;

    //TextView
    private TextView gasDensityResultTextView,gasAtmosphericPressureTextView,ductPressureTextView,areaTextView;

    //EditText
    private EditText temperatureEditText, seaLevelPressureEditText, elevationAboveSeaLevelEditText,staticPressureEditText,temperatureWetBulbEditText,dimensionHeightEditText,pitotTubeCoefficientEditText, dynamicPressure, dimensionWidthEditText, dimensionDiameterEditText;

    private Switch standardAirSwitch,WetBulbTemperatureGasDensityFragmentSwitch,unitSwitch,pipeTypeSwitch;
    private double [] gasCompositionInputArry;




    public Gas(Activity activity) {

        currentActivity = activity;


        //Edit Texts
        temperatureEditText = (EditText) activity.findViewById(R.id.temperatureGasDensityFragmentEditText);
        seaLevelPressureEditText = (EditText) activity.findViewById(R.id.seaLevelPressureGasDensityFragmentEditText);
        elevationAboveSeaLevelEditText = (EditText) activity.findViewById(R.id.ElevationAboveSeaLevelFragmentEdiText);
        staticPressureEditText = (EditText) activity.findViewById(R.id.staticPressureFragmentEditText);
        temperatureWetBulbEditText = (EditText) activity.findViewById(R.id.wetBulbTemperatureGasDensityFragmentEditText);
        dimensionHeightEditText = (EditText)activity.findViewById(R.id.dimensionHeightGasFlowFragmentEditText);
        pitotTubeCoefficientEditText =(EditText)activity.findViewById(R.id.pitotTubeCoefficientEditText);
        dimensionWidthEditText =(EditText)activity.findViewById(R.id.dimensionWidthGasFlowFragmentEditText);
        areaTextView = (TextView)activity.findViewById(R.id.ductAreaGasFlowFragmentTextView);


        //TextViews
        gasDensityResultTextView = (TextView) activity.findViewById(R.id.ResultCalculatedGasDensityTextView);
        ductPressureTextView = (TextView) activity.findViewById(R.id.ductPressureFragmentTextView);


        //Switches
        standardAirSwitch = (Switch) activity.findViewById(R.id.standardAirSwitch);
        WetBulbTemperatureGasDensityFragmentSwitch = (Switch) activity.findViewById(R.id.WetBulbTemperatureGasDensityFragmentSwitch);
        unitSwitch = (Switch) activity.findViewById(R.id.unitSwitch);
        pipeTypeSwitch = (Switch) activity.findViewById(R.id.unitSwitch);


        //Retrieval Of Values
        dryBulbTemperature = Integer.valueOf(temperatureEditText.getText().toString());
        wetBulbWaterSaturationPressurePW = Integer.valueOf(temperatureWetBulbEditText.getText().toString());
        seaLevelPressure = Integer.valueOf(seaLevelPressureEditText.getText().toString());
        elevationAboveSeaLevel = Integer.valueOf(elevationAboveSeaLevelEditText.getText().toString());
        staticPressure = Integer.valueOf(staticPressureEditText.getText().toString());
        gasCompositionInputArry = gasDensityFragment.getStandardAirResult();
        gasAtmosphericPressureTextView = (TextView) activity.findViewById(R.id.AtmosphericPressureFragmentTextView);


    }
    //Current settings of calculator


    //Methods to determine status of switches
    public String getPipeType(){
         if(pipeTypeSwitch.isChecked())
             return "circular";
        else
             return "rectangular";
    }
    public String getUnits(){
        if(unitSwitch.isChecked())
            return "SI";
        else
            return "US";

    }



    public double calculateGasDensity() {

        switch (getUnits()) {
            case "SI":
                gasDensity = 1000 * ductPressure / (273.15 + dryBulbTemperature) / (8314.3 / molarWeight);
            case "US":
                gasDensity = 1000 * ductPressure / (273.15 + dryBulbTemperature) / (8314.3 / molarWeight);
        }
        return gasDensity;
    }


    public double calculateRelativeHumidtiy(){
        dryBulbWaterSaturationPressurePD =criticalPressureH20*Math.pow(10,Kd*(1-criticalTemperatureH20)/ dryBulbTemperature);
        wetBulbWaterSaturationPressurePW=criticalPressureH20*Math.pow(10,Kw*(1-criticalTemperatureH20)/wetBulbTemperature);
        partialWaterPressureDueToDepressionPM =0.000367*(1+(wetBulbTemperature-32)/1571)*(754.30-wetBulbWaterSaturationPressurePW)*(dryBulbTemperature -wetBulbTemperature);
        partialPressureOfWaterPA=relativeHumidity*dryBulbWaterSaturationPressurePD;

        /*TODO
        Create an error for when the dialog shows up
         */
        if((wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD>=100 ||(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD<0)
            System.out.println("ERROR");
        else
            .relativeHumidity=(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD;


        switch(getUnits()) {
            case "SI":
                .relativeHumidity = seaLevelPressure + ductPressure * 0.249088;
                break;
            case "US":
                relativeHumidity = seaLevelPressure + ductPressure * 0.07355;
                break;
        }
        ductPressureTextView.setText(String.valueOf(ductPressure));
        return ductPressure;

    }

    public double calculateMolarWeight(){
        if(!isStandardAir) {
            airContentPercentageCO2 = gasCompositionInputArry[0];
            airContentPercentageO2 = gasCompositionInputArry[1];
            airContentPercentageN2 = gasCompositionInputArry[2];
            airContentPercentageAr = gasCompositionInputArry[3];
            airContentPercentageH2O = gasCompositionInputArry[4];
            molarWeight= (airContentPercentageCO2*44.01/100)+(airContentPercentageAr*39.94/100)+(airContentPercentageH2O*18.01528/100)+(airContentPercentageO2*32/100)+(airContentPercentageN2*28.02/100);
        }
        else
        {
            molarWeight=standardAirMolarWeight;
        }
        return molarWeight;
    }


    //Area Calculations
    public double calculateArea(){
        if(getPipeType() == "Circular"){
            diameter = Double.valueOf(dimensionHeightEditText.getText().toString());
            area = Math.PI*diameter /2;
        }
        else if(getPipeType() == "Rectangular"){
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

    //Verify Data Integrity
    public boolean verifyDataPressureRule(){
        double maxPressureValue;
        double currentMax=dynamicPressureArrayList.get(0);
        for(int i=0; i< dynamicPressureArrayList.size();i++)
            if(dynamicPressureArrayList.get(i)>currentMax)
                currentMax=dynamicPressureArrayList.get(i);

        maxPressureValue=currentMax;
        int unacceptablePressureValue=0;
        for(int i=0; i< dynamicPressureArrayList.size();i++)
            if(dynamicPressureArrayList.get(i)<=0.1*maxPressureValue)
                unacceptablePressureValue++;
        return unacceptablePressureValue/dynamicPressureArrayList.size()>0.75;
    }

    public boolean verifyRelativeHumidity(){
        if(relativeHumidity>0)
            return true;
        else
            return false;
    }

    public void convertUnits(){
        switch (getUnits()){
            case "US":
                width = Double.valueOf(dimensionWidthEditText.getText().toString());
            case "SI":;
        }

    }





}
