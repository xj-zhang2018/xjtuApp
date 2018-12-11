package com.xj.Datacenter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


public class Datacenter  {
	private int sum = 0;
	private Log log = LogFactory.getLog(Datacenter.class);
	private MonitorInfoBean monitorInfo=new MonitorInfoBean();
	
	static CopyOnWriteArrayList<Pair>NetWorkDetail=new CopyOnWriteArrayList<Pair>();
	private String data;  
	
   
	
	public static Pair GetNetWorkDetail()  {
		ClusterHelper helper=new ClusterHelper();
		Pair pair=new Pair();
		NetWorkDetail=helper.GetNetWorkParam();
		long rxBytesStart_sum=0L;
        long txBytesStart_sum=0L;
        long speed_sum=0L;
		Date time=new Date();
		for (Pair p:NetWorkDetail) {
			rxBytesStart_sum+=p.getRxbps();
			txBytesStart_sum+=p.getTxbps();
			speed_sum+=p.getSpeed();
		}
		int size=NetWorkDetail.size();
		if(size==0)size=3;
	    pair=new Pair(rxBytesStart_sum/size, txBytesStart_sum/size
				, speed_sum/size, "eth0", time);
		System.out.println(pair);
		return pair;
	}
		
	
	
	
	public static  MonitorInfoBeanForLinux GetPerformanceForLinux() {
		MonitorInfoBeanForLinux	linuxInfo=new MonitorInfoBeanForLinux();
	
		try {
			MonitorServiceImplForLinux imp=new MonitorServiceImplForLinux();
			linuxInfo.setCpuUsage(imp.getCpuUsage());
			linuxInfo.setDiskUsage(imp.getDiskUsage());
			linuxInfo.setMemUsage(imp.getMemUsage());
			System.out.println(linuxInfo.getCpuUsage()+","+linuxInfo.getDiskUsage()+","+linuxInfo.getMemUsage());
		} catch (Exception e) {
			
		}
		return linuxInfo;
		
	}
	
  public static void main(String[] args) throws Exception {
	  GetNetWorkDetail();
	     System.out.println("计算结束");
}	
}

