package com.jco.pitonew.Models;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.jco.pitonew.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by jco on 2/6/2017.
 */

public class Gas implements Serializable {


    public Double[] getResults() {
        return new Double[]{averageVelocity, massAirFlow, normalAirFlow, ductPressure, gasDensity, molecularWeight};
    }

    private String DuctUnits;
    private Boolean rectangularDuct;
    private double diameter;
    private double height;

    private String pipeType;
    private String units;

    private double width;
    private double area;
    private double pilotTubeCoeffecient;
    private boolean checkRule;
    private double ductPressure;
    private double molecularWeight;

    //Air Type Variables
    private boolean isStandardAir;
    private boolean isWetBulbTemperature;

    //Gas Constants
    public final double standardAirMolarWeight = 28.96;
    private final double criticalPressureH20 = 166818;
    private final double criticalTemperatureH20 = 1165.67;


    //AirCompositionVariables
    private double airContentPercentageCO2, airContentPercentageO2, airContentPercentageN2, airContentPercentageAr, airContentPercentageH2O;


    //Temperature
    private double dryBulbTemperature, wetBulbTemperature;

    //Pressure
    private double seaLevelPressure;
    private Double staticPressure;


    //Relative Humidity
    private double relativeHumidity, Kd, Kw, dryBulbWaterSaturationPressurePD, wetBulbWaterSaturationPressurePW, partialWaterPressureDueToDepressionPM, partialPressureOfWaterPA, dryAirHumidity, wetAFirHumidity;


    //Altitutde
    private double elevationAboveSeaLevel;


    //Dynamic Velocity
    private ArrayList<Double> dynamicVelocityArrayList = new ArrayList<Double>();
    private ArrayList<Double> dynamicPressureArrayList = new ArrayList<Double>();


    //Gas Density
    private double gasDensity;


    //Average Flow/Velocty
    private double averageVelocity, massAirFlow, actualAirFlow, normalAirFlow;


    private double[] gasCompositionInputArry;


    private Double[] inputResults;
    private Double[] dynamicResults;


    public Gas(Double[] inputResults, Double[] dynamicResults, boolean unitsSwitch, boolean pipeTypeSwitch) {

        if (unitsSwitch)
            this.units = "SI";
        else
            this.units = "US";

        if (pipeTypeSwitch)
            this.pipeType = "CIRCULAR";
        else
            this.pipeType = "RECTANGULAR";

        this.inputResults = inputResults;
        width = inputResults[0];
        height = inputResults[1];
        pilotTubeCoeffecient = inputResults[2];
        staticPressure = inputResults[3];
        dryBulbTemperature = inputResults[4];
        elevationAboveSeaLevel = inputResults[5];
        wetBulbTemperature = inputResults[6];
        seaLevelPressure = inputResults[7];
        molecularWeight = inputResults[8];


        System.out.println("width=inputResults" + inputResults[0]);
        System.out.println("height=inputResults" + inputResults[1]);
        System.out.println("pilotTubeCoeffecient" + inputResults[2]);
        System.out.println("staticPressure" + inputResults[3]);
        System.out.println("dryBulbTemperature" + inputResults[4]);
        System.out.println("elevationAboveSeaLevel" + inputResults[5]);
        System.out.println("wetBulbTemperature" + inputResults[6]);
        System.out.println("seaLevelPressure" + inputResults[7]);
        System.out.println("molecularWeight" + inputResults[8]);

        calculateResults();

    }

    public void calculateResults() {
        calculateAverageVelocity();
        calculateMassAirFlow();
        calculateNormalAirFlow();
        calculateActualAirFlow();
        calculateAtmosphericPressure();
        calculateDuctPressure();
        calculateGasDensity();

    }

    //Methods to determine status of switches
    public String getPipeType() {
        return pipeType;
    }

    public String getUnits() {
        return units;

    }

    private Double atmosphericPressure;

    public double calculateAtmosphericPressure() {
        switch (getUnits()) {
            case "SI":
                this.atmosphericPressure = seaLevelPressure * (Math.pow(10, -0.00001696 * elevationAboveSeaLevel));
                break;
            case "US":
                this.atmosphericPressure = seaLevelPressure / 0.2952998 * (Math.pow(10, -0.00001696 * elevationAboveSeaLevel));
                break;
        }
        return this.atmosphericPressure;
    }

    public double calculateDuctPressure(){
        switch (getUnits())
        {
            case "SI" :  this.ductPressure= atmosphericPressure + staticPressure*0.249088;break;
            case "US" : this.ductPressure= atmosphericPressure + staticPressure*0.07355;break;
        }
        return this.ductPressure;
    }


    public double calculateGasDensity() {

        switch (getUnits()) {
            case "SI":
                gasDensity = 1000 * ductPressure / (273.15 + dryBulbTemperature) / (8314.3 / molecularWeight);
            case "US":
                gasDensity = 1000 * ductPressure / (273.15 + dryBulbTemperature) / (8314.3 / molecularWeight);
        }
        return gasDensity;
    }


    public double calculateRelativeHumidtiy(){
        dryBulbWaterSaturationPressurePD =criticalPressureH20*Math.pow(10,Kd*(1-criticalTemperatureH20)/ dryBulbTemperature);
        wetBulbWaterSaturationPressurePW=criticalPressureH20*Math.pow(10,Kw*(1-criticalTemperatureH20)/wetBulbTemperature);
        partialWaterPressureDueToDepressionPM =0.000367*(1+(wetBulbTemperature-32)/1571)*(754.30-wetBulbWaterSaturationPressurePW)*(dryBulbTemperature -wetBulbTemperature);
        partialPressureOfWaterPA=relativeHumidity*dryBulbWaterSaturationPressurePD;


        if((wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD>=100 ||(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD<0)
            System.out.println("ERROR");
        else
            relativeHumidity=(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD;


        switch(getUnits()) {
            case "SI":
                relativeHumidity = seaLevelPressure + ductPressure * 0.249088;
                break;
            case "US":
                relativeHumidity = seaLevelPressure + ductPressure * 0.07355;
                break;
        }
        return relativeHumidity;

    }
    public double calculateArea(){
        if(pipeType.equals("Circular")){
            area = Math.PI*diameter /2;
        }
        else if(pipeType.equals("Rectangular")){
            area = height *width;
        }
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

    }






