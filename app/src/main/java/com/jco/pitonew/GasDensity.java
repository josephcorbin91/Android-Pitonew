package com.jco.pitonew;

import android.app.Activity;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by jco on 11/17/2016.
 */
public class GasDensity implements Serializable{
    private Boolean standardAir;
    private Boolean wetBulbTemperature;
    private String TemperatureUnit;
    private int DryBulbTemperature;
    private int WetBulbTemperature;
    private String PressureUnit;
    private int SealevelPressure;
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
    private String units;
    private double molarMass;
    private double individualGasConstant;
    private double atmosphericPressure;
    private double gasDensity;
    private static final double universalGasConstant =8.3144598;
    private static final double standardAirMolecularWeight = 28.97;
    private static final double boltzmannConstant =1.38064852E10-23; // Units m2 kg s-2 K-1
    private static final double gravitationalConstant = 9.8;// m * s^-2
    private TextView gasDensityResultTextView,gasAtmosphericPressureTextView;
    private EditText temperatureEditText, seaLevelPressureEditText, elevationAboveSeaLevelEditText,staticPressureEditText,temperatureWetBulbEditText;
private double [] gasCompositionInputArry;
private double pressureCurrentElevation;


    /*Uses pv=nrt to calculate gas density
 PV = nRT and n = m/MW and d = m/V

Where:

    P - pressure

    V - volume

    n - number of moles

    T - temperature

    m - mass

    d - dendity

    MW - Molecular Weight

    R - ideal gas constant. If the units of P, V, n and T are atm, L, mol and K, respectively, the value of R is 0.0821 L x atm/K x mol or 8.314 J/K x mol.

The density (d) of a gas is defined as

d = m / V
The Ideal Gas Law is accurate only at relatively low pressures and high temperatures. To account for deviation from the ideal situation an other factor is included. It is called the Gas Compressibility Factor, or Z-factor. This correction factor is dependent on pressure and temperature for each gas considered.

The True Gas Law, or the Non-Ideal Gas Law, becomes:

P V = Z n R T        (7)

where

Z = Gas Compressibility Factor

n = number of moles of gas present
and the moles of a gas is:

n = m / MW

Where

m is the mass of the gas, and

MW is the molecular weight.

**Take into consideration compmressibility factor http://www.engineeringtoolbox.com/ideal-gas-law-d_157.html
    http://www.engineeringtoolbox.com/molecular-mass-air-d_679.html
     */

public GasDensity(Activity activity, GasDensityFragment gasDensityFragment,String units, Boolean standardAirBoolean){
    temperatureEditText= (EditText)activity.findViewById(R.id.temperatureGasDensityFragmentEditText);
    seaLevelPressureEditText = (EditText)activity.findViewById(R.id.seaLevelPressureGasDensityFragmentEditText);
    elevationAboveSeaLevelEditText = (EditText)activity.findViewById(R.id.ElevationAboveSeaLevelFragmentEdiText);
    staticPressureEditText = (EditText)activity.findViewById(R.id.staticPressureFragmentEditText);
    temperatureWetBulbEditText = (EditText)activity.findViewById(R.id.wetBulbTemperatureGasDensityFragmentEditText);
    gasDensityResultTextView= (TextView)activity.findViewById(R.id.ResultCalculatedGasDensityTextView);
    this.DryBulbTemperature = Integer.valueOf(temperatureEditText.getText().toString());
    this.WetBulbTemperature = Integer.valueOf(temperatureWetBulbEditText.getText().toString());
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
    System.out.println(DryBulbTemperature + WetBulbTemperature +SealevelPressure+ airContentPercentageAr);

}
    public double calculatePressureAtCurrentElevation(){
        this.pressureCurrentElevation = this.SealevelPressure*Math.pow(Math.E,28.9*gravitationalConstant*ElevationAboveSeaLevel/boltzmannConstant*this.DryBulbTemperature);
        this.gasAtmosphericPressureTextView.setText(String.valueOf(this.pressureCurrentElevation));
        return this.pressureCurrentElevation;
    }

    public double calculateGasDensity() {
        if (!standardAir) {
            this.absoluteTemperature = this.getDryBulbTemperature() + 273.15;
            this.individualGasConstant = this.universalGasConstant / this.molecularWeight;
            this.gasDensity = this.atmosphericPressure * this.molecularWeight / this.absoluteTemperature;
            return this.gasDensity;
        }
        else{
            this.absoluteTemperature = this.getDryBulbTemperature() + 273.15;
            this.individualGasConstant = this.universalGasConstant / standardAirMolecularWeight;
            this.gasDensity = (this.atmosphericPressure * this.molecularWeight) / (this.absoluteTemperature *this.universalGasConstant);
            return this.gasDensity;        }
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

    public Boolean getWetBulbTemperature() {
        return wetBulbTemperature;
    }

    public void setWetBulbTemperature(int wetBulbTemperature) {
        WetBulbTemperature = wetBulbTemperature;
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
        this.wetBulbTemperature = wetBulbTemperature;
    }

    public String getTemperatureUnit() {
        return TemperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        TemperatureUnit = temperatureUnit;
    }

    public int getDryBulbTemperature() {
        return DryBulbTemperature;
    }

    public void displayResult(){

        calculateGasDensity();
        System.out.println(this.gasDensity);
        gasDensityResultTextView.setText(String.valueOf(this.gasDensity));

    }

    public void setDryBulbTemperature(int dryBulbTemperature) {
        DryBulbTemperature = dryBulbTemperature;
    }
}
