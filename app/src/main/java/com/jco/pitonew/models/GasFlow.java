package com.jco.pitonew.models;

import android.app.Activity;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jco.pitonew.R;
import com.jco.pitonew.Utility;

import java.io.Serializable;

/**
 * Created by jco on 11/17/2016.
 */
public class GasFlow implements Serializable{
    private String DuctUnits;
    private Boolean RectangularDuct;
    private double Diameter;
    private double Height;
    private double Width;
    private double Area;
    private int PilotTubeCoeffecient;
    private int [][] DynamicVelocityPressure;
    private boolean checkRule;
    private double averageVelocity;
    private double massAirFlow;
    private double actualAirFlow;
    private double normalAirFlow;
    private EditText dimensionHeightEditText,pitotTubeCoefficientEditText, dynamicPressure, dimensionWidthEditText, dimensionDiameterEditText;
    private TextView areaTextView;


    public GasFlow(Activity activity, String pipeType){

        dimensionHeightEditText = (EditText)activity.findViewById(R.id.dimensionHeightGasFlowFragmentEditText);
        pitotTubeCoefficientEditText =(EditText)activity.findViewById(R.id.pitotTubeCoefficientEditText);
        dimensionWidthEditText =(EditText)activity.findViewById(R.id.dimensionWidthGasFlowFragmentEditText);
        areaTextView = (TextView)activity.findViewById(R.id.ductAreaGasFlowFragmentTextView);


        if(pipeType == "Circular"){
            this.Diameter= Double.valueOf(dimensionHeightEditText.getText().toString());
            this.Area = Math.PI*this.Diameter/2;
        }
        if(pipeType == "Rectangular"){
            this.Height= Double.valueOf(dimensionHeightEditText.getText().toString());
            this.Width = Double.valueOf(dimensionWidthEditText.getText().toString());
            this.Area = this.Height*this.Width;
        }

      //  Toast.makeText(activity, )




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
    public void calculateArea(){
        areaTextView.setText(String.valueOf(this.Area));

    }

    public boolean verifyDataIntegrity() {
        return Utility.containsText(dimensionDiameterEditText)&&Utility.containsText(dimensionHeightEditText)&&Utility.containsText(dimensionWidthEditText);

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

    public double getDiameter() {
        return Diameter;
    }

    public void setDiameter(int diameter) {
        Diameter = diameter;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
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
