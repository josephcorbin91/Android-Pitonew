package com.jco.pitonew.Models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jco on 2/6/2017.
 */

public class Gas implements Serializable {


    public Double[] getResults() {
        System.out.println("averageVelocity=inputResults" + averageVelocity);
        System.out.println("Area=inputResults" + area);
        System.out.println("Armostpheric Pressure"+ atmosphericPressure);
        System.out.println("massAirFlow=inputResults" + massAirFlow);
        System.out.println("normalAirFlow" + normalAirFlow);
        System.out.println("ductPressure" + ductPressure);
        System.out.println("gasDensity" + gasDensity);
        System.out.println("molecularWeight" + molecularWeight);
        System.out.println("Actual air flow" + actualAirFlow);
        System.out.println("Relative Humidity" + this.relativeHumidity);

        return new Double[]{this.averageVelocity, this.massAirFlow, this.normalAirFlow,this.actualAirFlow, this.ductPressure, this.gasDensity, this.molecularWeight,this.area,this.atmosphericPressure, this.relativeHumidity};
    }

    public Double[] getDynamicVelocity(){

        Double[] dynamicVelocity= new Double[dynamicVelocityArrayList.size()];

        for(int i=0;i<dynamicVelocity.length;i++)
            dynamicVelocity[i]=dynamicVelocityArrayList.get(i);
        return dynamicVelocity;
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
    private static double pressMmHg=754.30;

    private double humidityH20DryAir;
    private double humidityH20WetAir;

    //AirCompositionVariables
    private double airContentPercentageCO2, airContentPercentageO2, airContentPercentageN2, airContentPercentageAr, airContentPercentageH2O;


    //Temperature
    private double dryBulbTemperature, wetBulbTemperature;

    //Pressure
    private double seaLevelPressure;
    private Double staticPressure;


    //Relative Humidity
    private double relativeHumidity, Kd, Kw, dryBulbWaterSaturationPressurePD, wetBulbWaterSaturationPressurePW, partialWaterPressureDueToDepressionPM, partialPressureOfWaterPA;


    //Altitutde
    private double elevationAboveSeaLevel;


    //Dynamic Velocity
    private List<Double> dynamicVelocityArrayList = new ArrayList<Double>();
    private List<Double> dynamicPressureArrayList;

    //Gas Density
    private double gasDensity;


    //Average Flow/Velocty
    private double averageVelocity, massAirFlow, actualAirFlow, normalAirFlow;


    private Double[] inputResults;

    private boolean wetBulbTemperatureEnabled;

    public Gas(Double[] inputResults, ArrayList<Double> dynamicResults, boolean unitsSwitch, boolean pipeTypeSwitch, boolean wetBulbTemperatureEnabled) {

        this.wetBulbTemperatureEnabled=wetBulbTemperatureEnabled;

        dynamicPressureArrayList= dynamicResults;
        dynamicVelocityArrayList = new ArrayList<Double>();
        if (unitsSwitch)
            this.units = "US";
        else
            this.units = "SI";

        System.out.println("UNITS "+ this.units);
        if (pipeTypeSwitch)
            this.pipeType = "CIRCULAR";
        else
            this.pipeType = "RECTANGULAR";


        System.out.println("PipeType "+ this.pipeType);
        this.inputResults = inputResults;
        width = inputResults[0];
        height = inputResults[1];
        pilotTubeCoeffecient = inputResults[2];
        staticPressure = inputResults[3];
        dryBulbTemperature = inputResults[4];
        elevationAboveSeaLevel = inputResults[5];
        wetBulbTemperature = inputResults[6];
        seaLevelPressure = inputResults[7];

        airContentPercentageCO2= inputResults[8];
        airContentPercentageO2= inputResults[9];
        airContentPercentageN2= inputResults[10];
        airContentPercentageAr= inputResults[11];
        airContentPercentageH2O= inputResults[12];


        System.out.println("width=inputResults" + inputResults[0]);
        System.out.println("height=inputResults" + inputResults[1]);
        System.out.println("pilotTubeCoeffecient" + inputResults[2]);
        System.out.println("staticPressure" + inputResults[3]);
        System.out.println("dryBulbTemperature" + inputResults[4]);
        System.out.println("elevationAboveSeaLevel" + inputResults[5]);
        System.out.println("wetBulbTemperature" + inputResults[6]);
        System.out.println("seaLevelPressure" + inputResults[7]);

        calculateResults();

    }
    private double C02WetBasis,O2WetBasis,N2WetBasis,ArWetBasis,H20WetBasis;

    public void calculateMolecularWeight() {

        if(isStandardAir) {
            if(wetBulbTemperatureEnabled) {
                this.molecularWeight =0.03*(1-H20WetBasis)+20.95*(1-H20WetBasis)+78.09*(1-H20WetBasis)+0.93*(1-H20WetBasis)+100*H20WetBasis;
            }
            else {
                this.molecularWeight = 28.96;
            }
        }
        else if(!isStandardAir) {
            if(wetBulbTemperatureEnabled) {
                this.molecularWeight=(44.01*airContentPercentageCO2*(1-H20WetBasis)+31.999*airContentPercentageO2*(1-H20WetBasis)+28.013*airContentPercentageN2*(1-H20WetBasis)+39.948*airContentPercentageAr*(1-H20WetBasis)+18.016*100*H20WetBasis)/100;
            }
            else {
                this.molecularWeight = (44.01 * airContentPercentageCO2 + 31.999 * airContentPercentageO2 + 28.013 * airContentPercentageN2 + 39.948 * airContentPercentageAr) / 100;
            }

        }
    }

    public void calculateResults() {
        if(wetBulbTemperatureEnabled)
            calculateRelativeHumidtiy();

        calculateMolecularWeight();


        calculateArea();
        calculateAtmosphericPressure();
        calculateDuctPressure();

        calculateGasDensity();
        calculateDynamicVelocity();

        calculateAverageVelocity();
        calculateActualAirFlow();
        calculateMassAirFlow();
        calculateNormalAirFlow();

        calculateAtmosphericPressure();


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
                this.atmosphericPressure = (seaLevelPressure / 0.2952998) * (Math.pow(10, -0.00001696 * elevationAboveSeaLevel));
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

        this.gasDensity =Math.floor(gasDensity * 10000) / 10000;
        return gasDensity;
    }


    private double dryBulbRankine, wetBulbRankine,dryMolecularWeight;
    public double calculateRelativeHumidtiy(){

        System.out.println("UNITS"+units);
        if(units.equals("SI")) {
            dryBulbRankine = (dryBulbTemperature*1.8+32)  + 459.67;
            wetBulbRankine = (wetBulbTemperature*1.8+32) + 459.67;
        }
        else {
            dryBulbRankine=dryBulbTemperature+459.67;
            wetBulbRankine=wetBulbTemperature+459.67;
        }

        Kd =-0.0000000008833*Math.pow(dryBulbRankine,3)+0.000003072*Math.pow(dryBulbRankine,2)-0.003469*dryBulbRankine+4.39553;
        Kw =-0.0000000008833*Math.pow(wetBulbRankine,3)+0.000003072*Math.pow(wetBulbRankine,2)-0.003469*wetBulbRankine+4.39553;
        dryBulbWaterSaturationPressurePD =criticalPressureH20*Math.pow(10,Kd*(1-(criticalTemperatureH20/dryBulbRankine)));
        wetBulbWaterSaturationPressurePW=criticalPressureH20*Math.pow(10,Kw*(1-(criticalTemperatureH20/wetBulbRankine)));
        partialWaterPressureDueToDepressionPM =0.000367*(1+((wetBulbRankine-459.67)-32)/1571)*(pressMmHg-wetBulbWaterSaturationPressurePW)*((dryBulbRankine-459.67) -(wetBulbRankine-459.67));
         if((wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD>=100 ||(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD<0)
            System.out.println("ERROR");
        else
            this.relativeHumidity=100*(wetBulbWaterSaturationPressurePW-partialWaterPressureDueToDepressionPM)/dryBulbWaterSaturationPressurePD;
        partialPressureOfWaterPA=0.01*this.relativeHumidity*dryBulbWaterSaturationPressurePD;

        this.dryMolecularWeight=(44.01*(airContentPercentageCO2*(1-humidityH20WetAir))+31.999*(airContentPercentageO2*(1-humidityH20WetAir))+28.013*(airContentPercentageN2*(1-humidityH20WetAir))+39.948*(airContentPercentageAr*(1-humidityH20WetAir)))/100;

        humidityH20DryAir=(18.02/this.dryMolecularWeight)*(partialPressureOfWaterPA/(pressMmHg-partialPressureOfWaterPA));
        if(wetBulbTemperatureEnabled)
            humidityH20WetAir=partialPressureOfWaterPA/pressMmHg;
        else
            humidityH20WetAir=0;

        System.out.println("PDD");
        System.out.println("DryMolecularWeight: "+dryMolecularWeight);
        System.out.println("DryBulbRankine: "+dryBulbRankine);
        System.out.println("WetBulbRankine: "+wetBulbRankine);
        System.out.println("KD: dryBulbWaterSaturationPressurePD"+Kd);
        System.out.println("KW: dryBulbWaterSaturationPressurePD"+Kw);
        System.out.println("PD: dryBulbWaterSaturationPressurePD"+dryBulbWaterSaturationPressurePD);
        System.out.println("PW: wetBulbWaterSaturationPressurePW"+wetBulbWaterSaturationPressurePW);
        System.out.println("PM: partialWaterPressureDueToDepressionPM"+partialWaterPressureDueToDepressionPM);
        System.out.println("PA: partialPressureOfWaterPA"+partialPressureOfWaterPA);
        System.out.println("RH: relative humidity "+this.relativeHumidity);
        System.out.println("RH: humidityH20DryAir"+humidityH20DryAir);
        System.out.println("RH: humidityH20WetAir"+humidityH20WetAir);


/*
        switch(getUnits()) {
            case "SI":
                this.relativeHumidity = seaLevelPressure + ductPressure * 0.249088;
                break;
            case "US":
                this.relativeHumidity = seaLevelPressure + ductPressure * 0.07355;
                break;
        }
        */
        return this.relativeHumidity;

    }
    public double calculateArea(){
        System.out.println("PIPPE" +pipeType);
        if(pipeType.equals("CIRCULAR")){
            area = Math.PI*(diameter /2.0);
        }
        else if(pipeType.equals("RECTANGULAR")){
            area = height *width;
        }
        System.out.println("AREA " + area);
        if(getUnits().equals("US"))
            this.area=1550*area;
        return area;
    }


    //Dyanmic Velocity
    public void calculateDynamicVelocity(){
        for(int i=0; i<dynamicPressureArrayList.size();i++)
        {
            Double currentVelocity=pilotTubeCoeffecient*Math.pow(2*dynamicPressureArrayList.get(i)*1000/4.01864/gasDensity,0.5);
            dynamicVelocityArrayList.add(currentVelocity);
            System.out.println("DynamicVelocity  " + i  + " : " + currentVelocity);
        }
    }

    public double calculateAverageVelocity(){
        double sum=0;
        for(Double velocity: dynamicVelocityArrayList)
            sum+=velocity;
        averageVelocity=sum/(double)dynamicVelocityArrayList.size();
        if(getUnits().equals("US"))
            this.averageVelocity=averageVelocity*(39.3701/12);
        return this.averageVelocity;
    }

    public double calculateMassAirFlow(){
        massAirFlow=actualAirFlow*gasDensity/3600;
        if(getUnits().equals("US"))
            this.massAirFlow=massAirFlow*2.2046*60;
        return massAirFlow;
    }

    public double calculateActualAirFlow(){
        actualAirFlow= averageVelocity*area*3600;
        if(getUnits().equals("US"))
            this.actualAirFlow=this.actualAirFlow/60*Math.pow((39.3701/12),3);
        return actualAirFlow;

    }
    public double calculateNormalAirFlow(){
        normalAirFlow=(actualAirFlow*ductPressure/101.325)*273.15/(273.15+dryBulbTemperature);
        if(getUnits().equals("US"))
            this.normalAirFlow=normalAirFlow/60*Math.pow((39.3701/12),3)*(294.26/273.15);
        return normalAirFlow;
    }



    }






