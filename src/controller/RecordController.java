package controller;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtu.demo.Echarts;
import org.xjtu.demo.OrderCountBo;
import org.xjtu.demo.Series;

import com.xj.Datacenter.Datacenter;
import com.xj.Datacenter.MonitorInfoBeanForLinux;
import com.xj.Datacenter.Pair;
import com.xj.FileRW.CSVReader;
import com.xj.mqtt.Client;
import com.xj.mqtt.dataEntity;
import com.xj.persistence.Dao;

import pojo.Record;
import pojo.User;
import pojo.param;
import service.RecordManager;

@ResponseBody//锟斤拷锟截碉拷锟斤拷json锟斤拷锟斤拷
@Controller
public class RecordController {
	@Autowired
	private RecordManager recordManager;
	@RequestMapping(value="/record",method=RequestMethod.POST)
	public Record addUser(Record record){
		System.out.println("id is"+record.getAccountId());
		return recordManager.regist(record);//注锟斤拷锟斤拷锟�
	}
	
	
	@RequestMapping(value="/record",method=RequestMethod.GET)
	public List<Record> findAllRecord(){
		return recordManager.findAllRecord();
	}
	
	@RequestMapping(value="/recordrange",method=RequestMethod.POST)
	public List<Record> findRangeRecord(String beginTime, String endTime) throws ParseException{
		//(@RequestParam("beginTime")String beginTime, @RequestParam("endTime")String endTime) 锟斤拷锟街凤拷式也锟斤拷锟斤拷
	
		SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return recordManager.findRangeRecord(sdf.parse(beginTime),sdf.parse(endTime));
	}
	
	
	
	@SuppressWarnings("deprecation")  
	    @ResponseBody 
	    @RequestMapping(value="Echarts")  
	    public Echarts showCharts(String id,String type,String isIncome,String beginTime,String endTime) throws ParseException {        
	      System.out.println("鏁版嵁閫氬埌鐨勫��"+isIncome);
			//System.out.println("startTime锟斤拷"+beginTime+",end:"+endTime+",id"+id+",type:"+type+",income:"+isIncome);
		 
	        SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      
	        //List<Record> records = recordManager.findRangeRecord(sdf.parse(beginTime),sdf.parse(endTime));
	        Dao db = Dao.getInstance();
	        List<dataEntity> records = db.findRangeRecord(beginTime,endTime,isIncome);
	       System.out.println("鏌ュ埌璁板綍鍒楄〃"+records);
            List<String> category = new ArrayList<String>();
	        List<Double>  index = new ArrayList<Double>();
	        String getDate = null;
	        double getValue =0;
	       
	        for(dataEntity reco:records)
	        {
	        	    getDate = reco.getTimeStamp().toString();       
	        	    getValue =reco.getValue();
		            category.add(getDate);
		            index.add(getValue);
	        }
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"value"}));//锟斤拷锟捷凤拷锟斤拷
	        List<Series> series = new ArrayList<Series>();
	        series.add(new  Series("value", "line",index));
	        
	        Echarts data=new Echarts(legend, category, series);
	        return data;  
	    }

	
	
	
	
	@SuppressWarnings("deprecation")  
    @ResponseBody 
    @RequestMapping(value="Echarts333")  
    public Echarts showCharts333(String id,String type,String isIncome,String beginTime,String endTime) throws ParseException {        
      
		//System.out.println("startTime锟斤拷"+beginTime+",end:"+endTime+",id"+id+",type:"+type+",income:"+isIncome);
	 
        SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
        List<Record> records = recordManager.findRangeRecord(sdf.parse(beginTime),sdf.parse(endTime));
        List<String> category = new ArrayList<String>();
        List<BigDecimal>  index = new ArrayList<BigDecimal>();
        String getDate = null;
        BigDecimal getPrice = null;
       
        for(Record reco:records)
        {
        	    getDate = reco.getCreateDate().toString();        
	            getPrice =reco.getPrice();
	            category.add(getDate);
	            index.add(getPrice);
        }
        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"閲戦"}));//锟斤拷锟捷凤拷锟斤拷
        List<Series> series = new ArrayList<Series>();
       // series.add(new  Series("閲戦", "line",index));
        
        Echarts data=new Echarts(legend, category, series);
        return data;  
    }


    
    
    
    
    
    @RequestMapping(value="/receiveMessage",method=RequestMethod.GET)
	public void receiveMessage(){
    	  Client client001 = new Client("client001");  
          client001.start();  
	}
    
    
  
  
    @RequestMapping(value="/network",method=RequestMethod.GET)
	public MonitorInfoBeanForLinux network(){
    	Datacenter datacenter=new Datacenter();
    	return datacenter.GetPerformanceForLinux();
    	
	}
  
    @RequestMapping(value="/networkDatail",method=RequestMethod.GET)
  	public Pair  networkDatail(){
      	Datacenter datacenter=new Datacenter();
      	return datacenter.GetNetWorkDetail();
  	}
    
    
    
    
    //鏌ヨ鏂瑰樊绛夊弬鏁�
  @RequestMapping(value="/GetParm",method=RequestMethod.POST)
  	public param  Getparam(String datapoint){
      	Dao db=new Dao();
      	param indctor=db.Getindicator(datapoint);
      	System.out.println(indctor);
    	return indctor;
  	}
    
    
  
  //get sensor result data
  @RequestMapping(value="/GetRes",method=RequestMethod.POST)
	public String  GetRes(int id){//question type
	 /*String line=CSVReader.readOneCsv(id);
  	return line;*/
	  System.out.println("id is"+id);
	 if(id==1)
  	return "112,98,69,82,91,93,91,95,111,96,97,124,95,107,83,84,50,28,87,16,57,111,113,20,145,119,66,97,90,115,8,48,106,7,11,19,21,50,142,28,18,10,59,109,114,47,135,92,21,79,114,29,26,97,137,15,103,37,114,100,21,54,72,28,128,14,77,8,121,94,118,50,131,126,113,10,34,107,63,90,8,9,137,58,118,89,116,115,136,28,38,20,85,55,128,137,82,59,117,20\n" + 
  			"";
	 if(id==2)
		 return "18,79,106,110,15,155,6,90,11,79,6,73,30,11,37,67,68,99,22,54,97,10,142,77,88,163,126,138,83,78,75,11,53,173,63,100,151,55,48,37,44,27,18,6,15,112,131,13,122,13,98,53,52,106,103,152,123,26,178,73,169,39,39,14,11,121,86,56,115,17,148,104,78,86,98,36,94,52,91,15,141,74,146,17,47,194,21,79,97,8,9,73,183,97,73,49,31,97,9,14,106,8,8,106,116,120,61,168,35,80,9,50,151,78,91,7,181,150,106,15,67,145,180,7,179,124,82,108,79,121,120,39,38,9,167,87,88,7,51,55,155,47,81,43,98,10,92,11,165,34,115,59,99,103,108,83,171,15,9,42,13,41,88,14,155,188,96,82,135,182,36,107,14,95,142,23,6,144,35,97,68,14,67,191,19,10,158,183,43,12,148,13,37,122,80,93,132,32,103,174,111,68,192,121,134,48,85,8,23,8,6,57,83,172,101,81,86,165,73,121,139,75,151,145,11,108,14,126,61,85,8,101,153,89,190,12,62,134,101,121,167,17,161,181,16,152,148,56,111,23,84,12,43,48,122,191,56,131,51\n" + 
		 		"";
		 if(id==3)
			 return "44,51,27,120,101,99,71,55,55,66,77,115,115,31,108,56,136,132,85,56,18,119,78,9,58,11,88,144,124,89,79,55,71,65,87,137,145,22,8,41,131,115,128,69,111,7,137,55,135,11,78,120,87,87,55,93,88,40,49,128,129,58,117,28,115,87,92,103,100,63,35,45,99,117,45,27,86,20,18,133,15,6,145,104,56,25,68,144,41,51,81,14,67,10,127,113,123,17,8,28\n" + 
			 		"";
			 if(id==4)
				 return "22,39,107,75,149,78,94,14,99,162,143,7,71,105,12,160,162,104,194,82,91,11,26,142,39,92,76,124,64,118,6,22,147,126,36,73,89,11,151,10,97,30,42,60,85,134,34,45,24,86,119,151,142,176,157,67,97,8,154,139,51,33,184,46,12,133,46,46,12,33,15,176,23,89,124,163,25,74,78,114,96,10,172,166,115,70,94,56,86,96,50,73,154,129,171,71,105,113,37,7,13,22,9,120,100,107,41,153,126,59,18,66,13,14,139,13,75,8,109,137,41,192,23,86,184,15,195,126,120,165,101,116,126,36,7,122,159,88,173,146,130,108,53,162,59,100,56,145,76,57,31,88,173,34,7,133,172,6,22,83,82,84,95,174,111,72,109,87,179,158,126,12,8,10,123,103,12,106,12,32,37,116,15,10,46,142,24,135,56,43,178,71,104,15,166,89,36,11,92,96,59,13,167,151,154,109,116,91,11,88,108,76,14,89,145,17,66,154,41,182,73,39,58,14,145,88,162,189,120,98,33,184,110,68,24,75,18,16,166,98,176,81,118,35,131,194,112,26\n" + 
				 		"";
	 return "";
	} 
    
    
    
    
    
    
    
}
