package com.jco.pitonew;

import android.app.Activity;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by jco on 11/17/2016.
 */
public class GasFlow implements Serializable{
    private String DuctUnits;
    private Boolean RectangularDuct;
    private int Diameter;
    private int Height;
    private int Widfth;
    private int PilotTubeCoeffecient;
    private int [][] DynamicVelocityPressure;
    private boolean checkRule;
    private double averageVelocity;
    private double massAirFlow;
    private double actualAirFlow;
    private double normalAirFlow;
    private EditText dimensionHeightEditText,pitotTubeCoefficientEditText, dynamicPressure, dimensionWidthEditText, dimensionDiameterEditText;


    public GasFlow(Activity activity){
        dimensionHeightEditText = (EditText)activity.findViewById(R.id.dimensionHeightGasFlowFragmentEditText);
        pitotTubeCoefficientEditText =(EditText)activity.findViewById(R.id.pitotTubeCoefficientEditText);
        dimensionWidthEditText =(EditText)activity.findViewById(R.id.dimensionWidthGasFlowFragmentEditText);



    }
    public String getDuctUnits() {
        return DuctUnits;
    }

    public double calculateAverageVelocity(String unit){
         this.averageVelocity = this.Diameter; return this.averageVelocity;
    }

    public double calculateMassAirFlow(String units){
        this.massAirFlow = this.Diameter; return this.massAirFlow;
    }

    public double calculateNormalAirFlow(String unit){
        this.normalAirFlow = this.Diameter;return  this.normalAirFlow;
    }

    public double calculateActualAirFlow(String unit){
        this.actualAirFlow = this.Diameter;
        return  this.actualAirFlow;
    }
    public void displayResult() {

    }



    public void setDuctUnits(String ductUnits) {
        DuctUnits = ductUnits;
    }

    public Boolean getRectangularDuct() {
        return RectangularDuct;
    }

    public void setRectangularDuct(Boolean rectangularDuct) {
        RectangularDuct = rectangularDuct;
    }

    public int getDiameter() {
        return Diameter;
    }

    public void setDiameter(int diameter) {
        Diameter = diameter;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWidfth() {
        return Widfth;
    }

    public void setWidfth(int widfth) {
        Widfth = widfth;
    }

    public int getPilotTubeCoeffecient() {
        return PilotTubeCoeffecient;
    }

    public void setPilotTubeCoeffecient(int pilotTubeCoeffecient) {
        PilotTubeCoeffecient = pilotTubeCoeffecient;
    }

    public int[][] getDynamicVelocityPressure() {
        return DynamicVelocityPressure;
    }

    public void setDynamicVelocityPressure(int[][] dynamicVelocityPressure) {
        DynamicVelocityPressure = dynamicVelocityPressure;
    }

    public boolean isCheckRule() {
        return checkRule;
    }

    public void setCheckRule(boolean checkRule) {
        this.checkRule = checkRule;
    }
}
