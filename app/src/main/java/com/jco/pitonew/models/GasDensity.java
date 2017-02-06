package com.jco.pitonew.models;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.jco.pitonew.GasDensityFragment;
import com.jco.pitonew.R;
import com.jco.pitonew.Utility;

import java.io.Serializable;


/**
 * Created by jco on 11/17/2016.
 */
public class GasDensity implements Serializable{
    private Boolean standardAir;
    private Boolean wetBulbTemperatureBoolean;
    private String TemperatureUnit;
    private double dryBulbTemperature;
    private double wetBulbTemperature;
    private String PressureUnit;
    private int SealevelPressure;
    private double ductPressure;
    private int ElevationAboveSeaLevel;
    private int StaticPressure;
    private double airContentPercentageCO2;
    private double airContentPercentageO2;
    private double airContentPercentageN2;
    private double airContentPercentageAr;
    private double airContentPercentageH2O;
    private double density;
    private double pressure;
    private double molecularWeight;
    private double gasConstantImperial;
    private double gasConstantMetric;
    private double absolutePressure;
    private double absoluteTemperature;
    private String currentUnits;
    private double molarMass;
    private double individualGasConstant;
    private double atmosphericPressure;
    private double gasDensity;
    private Activity activity;


    //Relative Humidty Variables
    private double relativeHumidity;
    private double Kd;
    private double Kw;
    private double dryBulbWaterSaturationPressurePD;
    private double wetBulbWaterSaturationPressurePW;
    private double partialWaterPressureDueToDepressionPM;
    private double partialPressureOfWaterPA;
    private double dryAirHumidity;
    private double wetAirHumidity;


    //Gas Constants
    private static final double pressureHG = 754.30;
    private static final double universalGasConstant =8.3144598;
    private static final double standardAirMolecularWeight = 28.97;
    private static final double boltzmannConstant =1.38064852E10-23; // Units m2 kg s-2 K-1
    private static final double gravitationalConstant = 9.8;// m * s^-2
    private static final double criticalPressureH20 = 166818;
    private static final double criticalTemperatureH20=1165.67;
    private TextView gasDensityResultTextView,gasAtmosphericPressureTextView,ductPressureTextView;
    private EditText temperatureEditText, seaLevelPressureEditText, elevationAboveSeaLevelEditText,staticPressureEditText,temperatureWetBulbEditText;
    private Switch standardAirSwitch,WetBulbTemperatureGasDensityFragmentSwitch,unitSwitch;
    private double [] gasCompositionInputArry;
private double pressureCurrentElevation;


public GasDensity(Activity activity, GasDensityFragment gasDensityFragment, String units, Boolean standardAirBoolean){

    this.activity =activity;
    //Edit Texts
    temperatureEditText= (EditText)activity.findViewById(R.id.temperatureGasDensityFragmentEditText);
    seaLevelPressureEditText = (EditText)activity.findViewById(R.id.seaLevelPressureGasDensityFragmentEditText);
    elevationAboveSeaLevelEditText = (EditText)activity.findViewById(R.id.ElevationAboveSeaLevelFragmentEdiText);
    staticPressureEditText = (EditText)activity.findViewById(R.id.staticPressureFragmentEditText);
    temperatureWetBulbEditText = (EditText)activity.findViewById(R.id.wetBulbTemperatureGasDensityFragmentEditText);
    //TextViews
    gasDensityResultTextView= (TextView)activity.findViewById(R.id.ResultCalculatedGasDensityTextView);
    ductPressureTextView = (TextView)activity.findViewById(R.id.ductPressureFragmentTextView);
    //Switches
    standardAirSwitch = (Switch)activity.findViewById(R.id.standardAirSwitch);
    WetBulbTemperatureGasDensityFragmentSwitch = (Switch)activity.findViewById(R.id.WetBulbTemperatureGasDensityFragmentSwitch);
    unitSwitch = (Switch)activity.findViewById(R.id.unitSwitch);


    //
    this.dryBulbTemperature = Integer.valueOf(temperatureEditText.getText().toString());
    this.wetBulbWaterSaturationPressurePW = Integer.valueOf(temperatureWetBulbEditText.getText().toString());
    this.SealevelPressure = Integer.valueOf(seaLevelPressureEditText.getText().toString());
    this.ElevationAboveSeaLevel =Integer.valueOf(elevationAboveSeaLevelEditText.getText().toString());
    this.StaticPressure =Integer.valueOf(staticPressureEditText.getText().toString());
    this.gasCompositionInputArry = gasDensityFragment.getStandardAirResult();
    this.gasAtmosphericPressureTextView = (TextView) activity.findViewById(R.id.AtmosphericPressureFragmentTextView);


    if(!standardAirBoolean) {
        this.airContentPercentageCO2 = this.gasCompositionInputArry[0];
        this.airContentPercentageO2 = this.gasCompositionInputArry[1];
        this.airContentPercentageN2 = this.gasCompositionInputArry[2];
        this.airContentPercentageAr = this.gasCompositionInputArry[3];
        this.airContentPercentageH2O = this.gasCompositionInputArry[4];
        this.molecularWeight= (this.airContentPercentageCO2*44.01/100)+(this.airContentPercentageAr*39.94/100)+(this.airContentPercentageH2O*18.01528/100)+(this.airContentPercentageO2*32/100)+(this.airContentPercentageN2*28.02/100);
    }
    else
    {
        this.molarMass=standardAirMolecularWeight;
    }
    System.out.println(dryBulbTemperature + wetBulbWaterSaturationPressurePW +SealevelPressure+ airContentPercentageAr);

}
    public double calculateAtmosphericPressure(){
        switch(getCurrentUnits()) {
            case "SI":
                this.atmosphericPressure = this.SealevelPressure * (Math.pow(10, -0.00001696 * this.ElevationAboveSeaLevel));break;
            case "US":
                this.atmosphericPressure = (this.SealevelPressure / 0.2952998) * (Math.pow(10, -0.00001696 * this.ElevationAboveSeaLevel));break;
        }

        this.gasAtmosphericPressureTextView.setText(String.valueOf(this.atmosphericPressure));
        return this.atmosphericPressure;
    }

    public double calculateDuctPressure(){
        switch(getCurrentUnits()) {
            case "SI":
                this.ductPressure = this.SealevelPressure + this.ductPressure * 0.249088;
                break;
            case "US":
                this.ductPressure = this.SealevelPressure + this.ductPressure * 0.07355;
                break;
        }
        ductPressureTextView.setText(String.valueOf(this.ductPressure));
        return this.ductPressure;
    }

    public double calculateWetBulbTemperature(){
        switch(getCurrentUnits()) {
            case "SI":
                this.wetBulbWaterSaturationPressurePW = this.SealevelPressure + this.ductPressure * 0.249088;
                break;
            case "US":
                this.wetBulbWaterSaturationPressurePW = this.SealevelPressure + this.ductPressure * 0.07355;
                break;
        }
        ductPressureTextView.setText(String.valueOf(this.ductPressure));
        return this.ductPressure;

    }

    /*TODO
    Not Done
     */
    /*
    public double calculateHumidity(){
       dryAirHumidity=18.02/molecularWeight*(partialPressureOfWaterPA/(pressureHG-partialPressureOfWaterPA));
        if(wetBulbTemperature ==0)
            wetAirHumidity=0;
        else if()

        this.wetAirHumidity=IF(D20="value?",0,IF(D8="Y",P12/P5,0))


    }

    */
    public double calculateRelativeHumidtiy(){
        this.dryBulbWaterSaturationPressurePD = criticalPressureH20*Math.pow(10,Kd*(1-criticalTemperatureH20)/this.dryBulbTemperature);
        this.wetBulbWaterSaturationPressurePW=criticalPressureH20*Math.pow(10,Kw*(1-criticalTemperatureH20)/this.wetBulbTemperature);
        this.partialWaterPressureDueToDepressionPM =0.000367*(1+(wetBulbTemperature-32)/1571)*(754.30-wetBulbWaterSaturationPressurePW)*(dryBulbTemperature-wetBulbTemperature);
        this.partialPressureOfWaterPA=relativeHumidity*dryBulbWaterSaturationPressurePD;

        /*TODO
        Create an error for when the dialog shows up
         */
        if((wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD>=100 ||(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD<0)
            System.out.println("ERROR");
        else
            this.relativeHumidity=(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD;


        switch(getCurrentUnits()) {
            case "SI":
                this.relativeHumidity = this.SealevelPressure + this.ductPressure * 0.249088;
                break;
            case "US":
                this.relativeHumidity = this.SealevelPressure + this.ductPressure * 0.07355;
                break;
        }
        ductPressureTextView.setText(String.valueOf(this.ductPressure));
        return this.ductPressure;

    }

     public double calculateGasDensity() {
        if (!standardAir) {
            this.absoluteTemperature =   273.15;
            this.individualGasConstant = this.universalGasConstant / this.molecularWeight;
            this.gasDensity = this.atmosphericPressure * this.molecularWeight / this.absoluteTemperature;
            return this.gasDensity;
        }
        else{
            this.absoluteTemperature =  273.15;
            this.individualGasConstant = this.universalGasConstant / standardAirMolecularWeight;
            this.gasDensity = (this.atmosphericPressure * this.molecularWeight) / (this.absoluteTemperature *this.universalGasConstant);
            return this.gasDensity;        }
    }

    public String getCurrentUnits(){
        return unitSwitch.isChecked() ? "SI": "US";
    }




    public double getAirContentPercentageCO2() {
        return airContentPercentageCO2;
    }

    public void setAirContentPercentageCO2(double airContentPercentageCO2) {
        this.airContentPercentageCO2 = airContentPercentageCO2;
    }

    public double getAirContentPercentageO2() {
        return airContentPercentageO2;
    }

    public void setAirContentPercentageO2(double airContentPercentageO2) {
        this.airContentPercentageO2 = airContentPercentageO2;
    }

    public double getAirContentPercentageN2() {
        return airContentPercentageN2;
    }

    public void setAirContentPercentageN2(double airContentPercentageN2) {
        this.airContentPercentageN2 = airContentPercentageN2;
    }

    public boolean verifyDataIntegrity() {
        return Utility.containsText(temperatureEditText)&& Utility.containsText(seaLevelPressureEditText) && Utility.containsText(elevationAboveSeaLevelEditText)&& Utility.containsText(staticPressureEditText)&& Utility.containsText(temperatureWetBulbEditText);


    }
    public double getAirContentPercentageAr() {
        return airContentPercentageAr;
    }

    public void setAirContentPercentageAr(double airContentPercentageAr) {
        this.airContentPercentageAr = airContentPercentageAr;
    }

    public double getAirContentPercentageH2O() {
        return airContentPercentageH2O;
    }

    public void setAirContentPercentageH2O(double airContentPercentageH2O) {
        this.airContentPercentageH2O = airContentPercentageH2O;
    }

    public Boolean getStandardAir() {
        return standardAir;
    }

    public void setStandardAir(Boolean standardAir) {
        this.standardAir = standardAir;
    }

    public Boolean getWetBulbTemperatureBoolean() {
        return wetBulbTemperatureBoolean;
    }

    public void setWetBulbTemperatureBoolean(int wetBulbTemperatureBoolean) {
        wetBulbTemperatureBoolean = wetBulbTemperatureBoolean;
    }

    public String getPressureUnit() {
        return PressureUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        PressureUnit = pressureUnit;
    }

    public int getSealevelPressure() {
        return SealevelPressure;
    }

    public void setSealevelPressure(int sealevelPressure) {
        SealevelPressure = sealevelPressure;
    }

    public int getElevationAboveSeaLevel() {
        return ElevationAboveSeaLevel;
    }

    public void setElevationAboveSeaLevel(int elevationAboveSeaLevel) {
        ElevationAboveSeaLevel = elevationAboveSeaLevel;
    }

    public int getStaticPressure() {
        return StaticPressure;
    }

    public void setStaticPressure(int staticPressure) {
        StaticPressure = staticPressure;
    }

    public void setWetBulbTemperature(Boolean wetBulbTemperature) {
        this.wetBulbTemperatureBoolean = wetBulbTemperature;
    }

    public String getTemperatureUnit() {
        return TemperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        TemperatureUnit = temperatureUnit;
    }



    public void displayResult(){

        calculateGasDensity();
        System.out.println(this.gasDensity);
        gasDensityResultTextView.setText(String.valueOf(this.gasDensity));

    }

}
