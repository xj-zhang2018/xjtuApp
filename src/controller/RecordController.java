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
import com.xj.mqtt.Client;
import com.xj.mqtt.dataEntity;
import com.xj.persistence.Dao;

import pojo.Record;
import pojo.User;
import pojo.param;
import service.RecordManager;

@ResponseBody//���ص���json����
@Controller
public class RecordController {
	@Autowired
	private RecordManager recordManager;
	@RequestMapping(value="/record",method=RequestMethod.POST)
	public Record addUser(Record record){
		System.out.println("id is"+record.getAccountId());
		return recordManager.regist(record);//ע�����
	}
	
	
	@RequestMapping(value="/record",method=RequestMethod.GET)
	public List<Record> findAllRecord(){
		return recordManager.findAllRecord();
	}
	
	@RequestMapping(value="/recordrange",method=RequestMethod.POST)
	public List<Record> findRangeRecord(String beginTime, String endTime) throws ParseException{
		//(@RequestParam("beginTime")String beginTime, @RequestParam("endTime")String endTime) ���ַ�ʽҲ����
	
		SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return recordManager.findRangeRecord(sdf.parse(beginTime),sdf.parse(endTime));
	}
	
	
	
	@SuppressWarnings("deprecation")  
	    @ResponseBody 
	    @RequestMapping(value="Echarts")  
	    public Echarts showCharts(String id,String type,String isIncome,String beginTime,String endTime) throws ParseException {        
	      System.out.println("数据通到的值"+isIncome);
			//System.out.println("startTime��"+beginTime+",end:"+endTime+",id"+id+",type:"+type+",income:"+isIncome);
		 
	        SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      
	        //List<Record> records = recordManager.findRangeRecord(sdf.parse(beginTime),sdf.parse(endTime));
	        Dao db = Dao.getInstance();
	        List<dataEntity> records = db.findRangeRecord(beginTime,endTime,isIncome);
	       System.out.println("查到记录列表"+records);
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
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"value"}));//���ݷ���
	        List<Series> series = new ArrayList<Series>();
	        series.add(new  Series("value", "line",index));
	        
	        Echarts data=new Echarts(legend, category, series);
	        return data;  
	    }

	
	
	
	
	@SuppressWarnings("deprecation")  
    @ResponseBody 
    @RequestMapping(value="Echarts333")  
    public Echarts showCharts333(String id,String type,String isIncome,String beginTime,String endTime) throws ParseException {        
      
		//System.out.println("startTime��"+beginTime+",end:"+endTime+",id"+id+",type:"+type+",income:"+isIncome);
	 
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
        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"金额"}));//���ݷ���
        List<Series> series = new ArrayList<Series>();
       // series.add(new  Series("金额", "line",index));
        
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
    
    
    
    
    //查询方差等参数
  @RequestMapping(value="/GetParm",method=RequestMethod.POST)
  	public param  Getparam(String datapoint){
      	Dao db=new Dao();
    	return db.Getindicator(datapoint);
  	}
    
    
    
    
    
    
    
    
    
    
}
