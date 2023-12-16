package application;

import javafx.scene.chart.XYChart;

public class DiscreteMaths {
	 public DiscreteMaths(){}

   public float sumX(XYChart.Series<Number,Number> data) {
       float totalX = 0;

       for(int i=0; i < data.getData().size() ; i++)
           totalX = totalX + data.getData().get(i).getXValue().floatValue();

       return totalX;
   } 
   
   public float sumX2(XYChart.Series<Number,Number> data) {
       float totalX2 = 0;

       for(int i=0; i < data.getData().size() ; i++) {
    	   float xi = data.getData().get(i).getXValue().floatValue();
           totalX2 = totalX2 + xi*xi;
       }

       return totalX2;
   } 

   public float sumY(XYChart.Series<Number,Number> data) {
       float totalY = 0;

       for(int i=0; i < data.getData().size() ; i++)
           totalY = totalY + data.getData().get(i).getYValue().floatValue();

       return totalY;
   }

   public float sumXY(XYChart.Series<Number,Number> data) {
       float totalXY = 0;
       
       for(int i=0; i < data.getData().size() ; i++)
           totalXY = totalXY + (data.getData().get(i).getXValue().floatValue() * data.getData().get(i).getYValue().floatValue());

       return totalXY;
       
   }

   public float sumXsumY(float sumX, float sumY) {
       return sumX * sumY;
   }
}