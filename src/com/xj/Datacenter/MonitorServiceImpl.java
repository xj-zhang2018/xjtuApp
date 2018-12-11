package com.xj.Datacenter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class MonitorServiceImpl {
	
    private static final int CPUTIME = 5000;  
  
    private static final int PERCENT = 100;  
  
    private static final int FAULTLENGTH = 10;  
	
public MonitorInfoBean getMonitorInfoBean() throws Exception {
	     int kb = 1024;   
        
         long totalMemory = Runtime.getRuntime().totalMemory() / kb;   
       
         long freeMemory = Runtime.getRuntime().freeMemory() / kb;   
   
         long maxMemory = Runtime.getRuntime().maxMemory()/kb;   
         OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
        		 .getOperatingSystemMXBean();   
   
         String osName = System.getProperty("os.name");   
         long totalMemorySize = osmxb.getTotalPhysicalMemorySize()/kb;   
         long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize()/kb;   
         long usedMemory = (osmxb.getTotalPhysicalMemorySize()-osmxb.getFreePhysicalMemorySize())/ kb;   
   
         ThreadGroup parentThread;   
         for (parentThread = Thread.currentThread().getThreadGroup(); parentThread   
                 .getParent() != null; parentThread = parentThread.getParent())   
             ;   
         int totalThread = parentThread.activeCount();   
   
         double cpuRatio = 0;   
         if (osName.toLowerCase().startsWith("windows")) {   
             cpuRatio = this.getCpuRatioForWindows();   
         }   
            
         MonitorInfoBean infoBean = new MonitorInfoBean();   
         infoBean.setFreeMemory(freeMemory);   
         infoBean.setFreePhysicalMemorySize(freePhysicalMemorySize);   
         infoBean.setMaxMemory(maxMemory);   
         infoBean.setOsName(osName);   
         infoBean.setTotalMemory(totalMemory);   
         infoBean.setTotalMemorySize(totalMemorySize);   
         infoBean.setTotalThread(totalThread);   
         infoBean.setUsedMemory(usedMemory);   
         infoBean.setCpuRatio(cpuRatio);   
         return infoBean;   
     }
		
		
		
	
    private double getCpuRatioForWindows() {   
        try {   
            String procCmd = System.getenv("windir")   
                    + "//system32//wbem//wmic.exe process get Caption,CommandLine,"  
                    + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";   
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));   
            Thread.sleep(CPUTIME);   
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));   
            if (c0 != null && c1 != null) {   
                long idletime = c1[0] - c0[0];   
                long busytime = c1[1] - c0[1];   
                return Double.valueOf(   
                        PERCENT * (busytime) / (busytime + idletime))   
                        .doubleValue();   
            } else {   
                return 0.0;   
            }   
        } catch (Exception ex) {   
            ex.printStackTrace();   
            return 0.0;   
        }   
    }   
	
	
	
	
    
    private long[] readCpu(final Process proc) {   
        long[] retn = new long[2];   
        try {   
            proc.getOutputStream().close();   
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());   
            LineNumberReader input = new LineNumberReader(ir);   
            String line = input.readLine();  
         
            if (line == null || line.length() < FAULTLENGTH) {   
                return null;   
            }   
  
            int capidx = line.indexOf("Caption");   
            int cmdidx = line.indexOf("CommandLine");   
            int rocidx = line.indexOf("ReadOperationCount");   
            int umtidx = line.indexOf("UserModeTime");   
            int kmtidx = line.indexOf("KernelModeTime");   
            int wocidx = line.indexOf("WriteOperationCount");   
            long idletime = 0;   
            long kneltime = 0;   
            long usertime = 0;   
            while ((line = input.readLine()) != null) {   
                if (line.length() < wocidx) {   
                    continue;   
                }   
                String caption = Bytes.substring(line, capidx, cmdidx - 1)   
                        .trim();   
                String cmd = Bytes.substring(line, cmdidx, kmtidx - 1).trim();   
              
                if (cmd.indexOf("wmic.exe") >= 0) {   
                    continue;   
                }   
                // log.info("line="+line);  
             
                if(caption.equals("System Idle Process")   
                        || caption.equals("System")) {   
                    idletime += Long.valueOf(   
                            Bytes.substring(line, kmtidx, rocidx - 1).trim())   
                            .longValue();   
                    idletime += Long.valueOf(   
                            Bytes.substring(line, umtidx, wocidx - 1).trim())   
                            .longValue();   
                    continue;   
                }   
  
                kneltime += Long.valueOf(   
                        Bytes.substring(line, kmtidx, rocidx - 1).trim())   
                        .longValue();   
                usertime += Long.valueOf(   
                        Bytes.substring(line, umtidx, wocidx - 1).trim())   
                        .longValue();   
            }   
            retn[0] = idletime;   
            retn[1] = kneltime + usertime;   
            return retn;   
        } catch (Exception ex) {   
            ex.printStackTrace();   
        } finally {   
            try {   
                proc.getInputStream().close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
        return null;   
    }   
	}
