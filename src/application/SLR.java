package application;

import javafx.scene.chart.XYChart;

public class SLR {
	private float beta0;
	private float beta1;
	private int n;
	private XYChart.Series<Number,Number> data;
	private DiscreteMaths maths;
	public SLR(XYChart.Series<Number,Number> series) {
		data = series;
		maths = new DiscreteMaths();
		n = data.getData().size();
		beta1 = toComputeBeta1();
		beta0 = toComputeBeta0();
	}
	
	public void printRegEquation(){
		System.out.println("Reg Equation= " + beta1 + "x + " + beta0);
	}
	
	public float toComputeBeta0() {
		return (maths.sumY(data)-beta1*maths.sumX(data))/n;
	}
	
	public float toComputeBeta1() {
		return (n*maths.sumXY(data)-maths.sumX(data)*maths.sumY(data))
				/(n*maths.sumX2(data)-maths.sumX(data)*maths.sumX(data));
	}
	
	public float getBeta0() {
		return beta0;
	}
	
	public float getBeta1() {
		return beta1;
	}
	
	public float toPredict(float x) {
		return beta1*x + beta0;
	}
	public float RSquared () {
		float SSR= 0;
		float SST= 0;
		float tempSSR= 0;
		float tempSST= 0;
		float yavg= 0;
		for(int i=0; i < data.getData().size(); i++) {
			yavg = yavg + data.getData().get(i).getYValue().floatValue();
		}
		yavg = yavg/n;
		for(int i=0; i < data.getData().size(); i++) {
			tempSSR = data.getData().get(i).getYValue().floatValue() - toPredict(data.getData().get(i).getXValue().floatValue());
			SSR = SSR + tempSSR*tempSSR;
			tempSST = data.getData().get(i).getYValue().floatValue() - yavg;
			SST = SST + tempSST*tempSST;
		}
		return 1 - (SSR/SST);
	}
}
