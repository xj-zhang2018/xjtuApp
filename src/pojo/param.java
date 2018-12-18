package pojo;

public class param {

	
	//鏁板涓婄殑鏂瑰樊绛夐噸瑕佸弬鏁�
	
	 double max=0;
     double min=0;
     double variance=0;
     double avg=0;
	public param(double max, double min, double variance,double avg) {
		super();
		this.max = max;
		this.min = min;
		this.variance = variance;
		this.avg=avg;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
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
	
     
     
     public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	@Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "方差是"+this.variance;
    }
	
	
	
}
