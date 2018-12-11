package com.xj.Datacenter;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;


public class ClusterHelper {
    static CopyOnWriteArrayList<Pair>result=new CopyOnWriteArrayList();
    public ClusterHelper(){
    }
    
    
  
    	public  CopyOnWriteArrayList<Pair> GetNetWorkParam(){
    	Sigar sigar = SigarUtils.sigar;
         String[] netIfs;
		try {
			netIfs = sigar.getNetInterfaceList();
			for (String name:netIfs) {
				
				gather(sigar, name);
				
			}
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    	
    }
    
    
  
 
    
    
    public static void gather(Sigar sigar, String name)
        throws SigarException {
    	Pair pair=null;
        try {
            NetInterfaceStat statStart = sigar.getNetInterfaceStat(name);
            long rxBytesStart = statStart.getRxBytes()/(1000*1000);
            long txBytesStart = statStart.getTxBytes()/(1000*1000);
            long RePackets= statStart.getRxPackets();
            if(!(rxBytesStart+"").equals("0")||!(txBytesStart+"").equals("0")){
            	
            	Date now=new Date();
            	pair=new Pair(rxBytesStart, txBytesStart,RePackets,name,now);
            	result.add(pair);
            }
        } catch (SigarException e) {
        } catch (Exception e) {
        }
    }
   
}