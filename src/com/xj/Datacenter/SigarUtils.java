package com.xj.Datacenter;

import java.io.File;
import java.nio.file.Paths;

import com.jfinal.kit.PathKit;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SigarUtils {

	 public final static Sigar sigar = initSigar();
	    private static Sigar initSigar() {
	        try {
	            //此处只为得到依赖库文件的目录，可根据实际项目自定义
	           // String file = Paths.get(PathKit.getWebRootPath(),  "file", "sigar",".sigar_shellrc").toString();
	            String file = Paths.get(PathKit.getWebRootPath(),"file", "sigar","sigar-x86-winnt.dll").toString();
	           // String file = Paths.get(PathKit.getWebRootPath(),"file", "sigar","sigar-x86-winnt.dll").toString();
	            System.out.println(file);
	            File classPath = new File(file).getParentFile();

	            String path = System.getProperty("java.library.path");
	            String sigarLibPath = classPath.getCanonicalPath();
	            //为防止java.library.path重复加，此处判断了一下
	            if (!path.contains(sigarLibPath)) {
	                if (isOSWin()) {
	                    path += ";" + sigarLibPath;
	                } else {
	                    path += ":" + sigarLibPath;
	                }
	                System.setProperty("java.library.path", path);
	            }
	            System.out.println(file);
	            return new Sigar();
	        } catch (Exception e) {
	            return null;
	        }
	    }

	   
	    
	    public static boolean isOSWin(){//OS 版本判断
	        String OS = System.getProperty("os.name").toLowerCase();
	        if (OS.indexOf("win") >= 0) {
	            return true;
	        } else return false;
	    }

	
	
	
	    
	    public static void main(String[] args) throws Exception {
	    	Sigar sigar = SigarUtils.sigar;
	    	double cpuUsedPerc = sigar.getCpuPerc().getCombined();//cpu
	    	double memUsed = sigar.getMem().getActualUsed();//mem
	    	double memTotal = sigar.getMem().getTotal();
	    	double memUsedPerc = sigar.getMem().getUsedPercent();
	    	String memUsedStr = String.format("%.2f", memUsed/1024/1024/1024)+"GB";// mem to string GB
	    	String memTotalStr = String.format("%.2f", memTotal/1024/1024/1024)+"GB";
	    	String memUsedPercStr = String.format("%.2f", memUsedPerc)+" %";
	    	double diskUsed = sigar.getFileSystemUsage(PathKit.getWebRootPath()).getUsed();//disk
	    	double diskTotal = sigar.getFileSystemUsage(PathKit.getWebRootPath()).getTotal();
	    	double diskUsedPerc = sigar.getFileSystemUsage(PathKit.getWebRootPath()).getUsePercent();
	    	String diskUsedStr = String.format("%.2f", diskUsed/1024/1024)+"GB";//disk to String GB
	    	String diskTotalStr = String.format("%.2f", diskTotal/1024/1024)+"GB";
	    	String diskUsedPercStr = String.format("%.2f", diskUsedPerc*100)+" %";
	    	String fqdn = sigar.getFQDN();//FQDN
	    	
	    	System.out.println(cpuUsedPerc+fqdn+diskUsedPercStr+diskUsedStr);
		}
	    
	    
	
}
