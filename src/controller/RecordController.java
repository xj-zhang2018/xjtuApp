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

import pojo.Record;
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
	        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"���"}));//���ݷ���
	        List<Series> series = new ArrayList<Series>();
	        series.add(new  Series("���", "line",index));
	        
	        Echarts data=new Echarts(legend, category, series);
	        return data;  
	    }

	
	
	
	
    @SuppressWarnings("deprecation")  
    @ResponseBody 
    @RequestMapping(value="test")  
    public Echarts lineData(String id,String type,String isIncome,String beginTime,String endTime) throws ParseException {        
        OrderCountBo orderCount = new OrderCountBo();
        orderCount.setAccountId(id);
        orderCount.setAccountType(type);
        orderCount.setIsIncome(isIncome);                  //��1�������룬��2����֧��
        //���ӵ�
        SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // orderCount.setBeginCreateDate( sdf.parse(beginTime));
        //orderCount.setEndCreateDate( sdf.parse(endTime));
        orderCount.setBeginCreateDate( sdf.parse("2016-1-24 21:59:06"));
        orderCount.setEndCreateDate( sdf.parse("2016-5-24 22:59:06"));
        //Ϊʱ�����ã����Զ���ֵ
        if(null==orderCount.getBeginCreateDate()||null==orderCount.getEndCreateDate()){
            orderCount.setEndCreateDate(new Date());
            Calendar calendar = Calendar.getInstance();    //�õ�����
            calendar.setTime(new Date());                 //�ѵ�ǰʱ�丳������
            calendar.add(Calendar.DAY_OF_MONTH, -1);      //����Ϊǰһ��
            Date date = calendar.getTime();               //�õ�ǰһ���ʱ��
            orderCount.setBeginCreateDate(date);            
        } 
        List<OrderCountBo> getList = new ArrayList<>();
        //orderCountService.getList(orderCount);    //��ѯ��ȡ����,�������üٵ�����
        
        for(int i=1;i<50;i++) {
        	OrderCountBo order=new OrderCountBo(id,"1","1",sdf.parse("2016-1-24 21:59:06"),sdf.parse("2016-5-24 22:59:06"),new BigDecimal(100+i*5));
        	getList.add(order);
        }
        List<String> category = new ArrayList<String>();
        List<BigDecimal>  index = new ArrayList<BigDecimal>();
        String getDate = null;
        BigDecimal getPrice = null;
        for(int i=0;i<getList.size();i++){                                     //ѭ����ȡ����
            OrderCountBo getOrderCount = getList.get(i); 
           // getDate = DateUtils.formatDateTime(getOrderCount.getCreateLogDate());           
            getDate = new Date().toString();        
            getPrice = getOrderCount.getPrice();
            category.add(getDate);
            index.add(getPrice);
        }           
        List<String> legend = new ArrayList<String>(Arrays.asList(new String[]{"���"}));//���ݷ���
        List<Series> series = new ArrayList<Series>();
        series.add(new  Series("���", "line",index));
        
        Echarts data=new Echarts(legend, category, series);
        return data;  
    }
}
