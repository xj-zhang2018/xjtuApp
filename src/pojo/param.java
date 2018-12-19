package pojo;

public class param {

	
	//鏁板涓婄殑鏂瑰樊绛夐噸瑕佸弬鏁�
	
	 double max=0;
     double min=0;
     double variance=0;
     double avg=0;
     double range=0;
     double mse=0;
     double pianxie=0;
     double qiaodu=0;
     
     
   
	public param(double max, double min, double variance, double avg, double range, double mse, double pianxie,
			double qiaodu) {
		super();
		this.max = max;
		this.min = min;
		this.variance = variance;
		this.avg = avg;
		this.range = range;
		this.mse = mse;
		this.pianxie = pianxie;
		this.qiaodu = qiaodu;
	}


	public param() {
	
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



	public double getAvg() {
		return avg;
	}



	public void setAvg(double avg) {
		this.avg = avg;
	}



	public double getRange() {
		return range;
	}



	public void setRange(double range) {
		this.range = range;
	}



	public double getMse() {
		return mse;
	}



	public void setMse(double mse) {
		this.mse = mse;
	}



	public double getPianxie() {
		return pianxie;
	}



	public void setPianxie(double pianxie) {
		this.pianxie = pianxie;
	}



	public double getQiaodu() {
		return qiaodu;
	}



	public void setQiaodu(double qiaodu) {
		this.qiaodu = qiaodu;
	}



	@Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "key indector is"+this.getMse()+","+this.getPianxie()+","+this.qiaodu +","+this.variance;
    }
	
	
	
}
