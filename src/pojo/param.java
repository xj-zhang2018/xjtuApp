package pojo;

public class param {

	
	//数学上的方差等重要参数
	
	 double max=0;
     double min=0;
     double VARIANCE=0;
	public param(double max, double min, double vARIANCE) {
		super();
		this.max = max;
		this.min = min;
		VARIANCE = vARIANCE;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getVARIANCE() {
		return VARIANCE;
	}
	public void setVARIANCE(double vARIANCE) {
		VARIANCE = vARIANCE;
	}
     
     
     
	
	
	
}
