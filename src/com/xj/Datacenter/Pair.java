package com.xj.Datacenter;

import java.util.Date;

public class Pair {
  private String NetInterfacePara; 
  private long rxbps;
  private long txbps;
  private long speed;
  private Date date;
public Pair() {

}
public Pair(long rxbps, long txbps,long speed,String NetInterfacePara,Date date) {
	super();
	this.rxbps = rxbps;
	this.txbps = txbps;
	this.speed = speed;
	this.NetInterfacePara=NetInterfacePara;
	this.date = date;
}
public long getRxbps() {
	return rxbps;
}
public void setRxbps(long rxbps) {
	this.rxbps = rxbps;
}
public long getTxbps() {
	return txbps;
}
public void setTxbps(long txbps) {
	this.txbps = txbps;
}

public long getSpeed() {
	return speed;
}

public void setSpeed(long speed) {
	this.speed = speed;
}

public String getNetInterfacePara() {
	return NetInterfacePara;
}
public void setNetInterfacePara(String netInterfacePara) {
	NetInterfacePara = netInterfacePara;
}

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
@Override
	public String toString() {
		return "ʱ��:"+date+"��������"+NetInterfacePara+"��������"+rxbps+",��������"+txbps+",�����ٶ�"+speed;
	}


}
