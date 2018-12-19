package com.xj.persistence;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.xj.mqtt.dataEntity;

import pojo.Record;
import pojo.param;
 
public class Dao {
	// 一锟斤拷锟斤拷锟斤拷锟斤拷始锟斤拷锟斤拷锟斤拷
	private Connection con;
 
	// 锟斤拷锟铰达拷锟诫，锟斤拷证锟斤拷锟斤拷只锟斤拷锟斤拷一锟斤拷实锟斤拷
	public Dao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		   /* String url = "jdbc:mysql://localhost:3306/kafkadata?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
			String user = "root";
			String password = "123456";
			*/
			
			String url = "jdbc:mysql://172.30.159.167:3306/app4sql?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
			String user = "userA8S";
			String password = "siJEKXHkEcf5F7xw";
			
			
			
			
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 私锟斤拷锟睫参癸拷锟届方锟斤拷
 
	// 锟斤拷锟皆硷拷锟节诧拷锟斤拷锟斤拷锟皆硷拷锟斤拷一锟斤拷实锟斤拷锟斤拷只锟斤拷锟节诧拷锟斤拷锟斤拷
	private static Dao db = null;
 
	// 锟斤拷锟斤拷锟斤拷锟斤拷锟皆讹拷锟斤拷锟斤拷锟斤拷系统锟结供锟斤拷锟绞碉拷锟斤拷锟斤拷锟�
	// 锟斤拷锟斤拷锟结供锟斤拷一锟斤拷锟斤拷锟解部锟斤拷锟绞憋拷class锟侥撅拷态锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷直锟接凤拷锟斤拷
	public static Dao getInstance() {
		if (db == null) {
			db = new Dao();
		}
		return db;
	}
 

 
	// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷锟斤拷锟捷匡拷锟斤拷锟斤拷锟�
	protected void finalize(){
		try {
		if (!con.isClosed() || con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertBatch(String sql, List<Parameter>param)  {
		
		
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
	                 ResultSet.CONCUR_READ_ONLY);
		             con.setAutoCommit(false);
		            	 for(Parameter entity:param)
		            	 {
		            		ps.setInt(1, entity.getId());
		            		ps.setString(2, entity.getName()); 	                       
		                    ps.addBatch();
		                     }
		                     ps.executeBatch();
		                    con.commit();
	         	}
		         catch (Exception e) {
		 			e.printStackTrace();
		 		}
	}
	
	
	
	
	
	
public void insertoneRecord(String sql, dataEntity data)  {
		
		
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		             ps.setTimestamp(1, data.getTimeStamp());
		             ps.setString(2, data.getDatapoint());
		             ps.setString(3, data.getDeviceId());
                     ps.setDouble(4, data.getValue());
                     ps.execute();
		}
		         catch (Exception e) {
		 			e.printStackTrace();
		 		}
	}
	
	
	

//鏌ヨ鏌愪竴绫讳紶鎰熷櫒璺濈褰撳墠5鍒嗛挓鐨勬柟宸紝鍧囧�硷紝鏈�澶у�硷紝
public param Getindicator(String datapoint)  {
	Statement sql_statement;
	 double max=0;
     double min=0;
     double variance=0;
     double avg=0;
     double range=0;
     param parm=null;
	try {
		String sql="select max(value) ,min(value),VARIANCE(value),avg(value),sum(value) from dataentity where  timeStamp>DATE_SUB(NOW(), INTERVAL 200 MINUTE) and datapoint='"+datapoint+"'";
		 sql_statement = (Statement) con.createStatement();
		 ResultSet result = sql_statement.executeQuery(sql);
         
         while (result.next()) 
         {
             max= result.getDouble(1);
             min= result.getDouble(2);
             variance=result.getDouble(3);
             avg=result.getDouble(4);
             range=max-min;
           
             parm=new param();
             parm.setMax(max);
             parm.setMin(min);
             parm.setRange(range);
             parm.setAvg(avg);
             parm.setVariance(variance);
             System.out.println(" max :" + max + "min " + avg + "鏂瑰樊 " + variance+","+avg); 
         }
                 
                
	}
	catch (Exception e) {
	 			e.printStackTrace();
	 		}
	
	return GetOtherIndector(datapoint,parm);
	
}






public param GetOtherIndector(String datapoint,param parmTmp)  {
	 Statement sql_statement;
	 double data=0;
     param parm=null;
     double sum=0;
     double sum2=0;
     double sum3=0;
     double mse=0;
     double pianxie=0;
     double qiaodu=0;
     double avg=parmTmp.getAvg();
     double variance=parmTmp.getVariance();
     List<Double> datas=new ArrayList<>();
     
     param res=new param();
     int size=0;
	try {
		String sql="select value from dataentity where  timeStamp>DATE_SUB(NOW(), INTERVAL 200 MINUTE) and datapoint='"+datapoint+"'";
		 sql_statement = (Statement) con.createStatement();
		 ResultSet result = sql_statement.executeQuery(sql);
         while (result.next()) 
         {
        	
             data= result.getDouble(1);
             datas.add(data);
	}
         size=datas.size();
         System.out.println("datas is"+datas);
         if(size>0) {
        	 for(double num:datas) {
        		 sum+=(num*num);
        		 sum2+= Math.pow(num-avg, 3);
        		 sum3+= Math.pow(num-avg, 4);
        		
        	 
        	 }
        	 
        		 mse=Math.sqrt(sum/datas.size());
        		 pianxie=sum2/(size*Math.pow(variance,3));
        		 qiaodu=sum2/(size*Math.pow(variance,4));
        		 
         }
         res.setAvg(parmTmp.getAvg());
         res.setMax(parmTmp.getMax());
         res.setMin(parmTmp.getMin());
         res.setVariance(parmTmp.getVariance());
         res.setRange(parmTmp.getRange());
         res.setMse(mse);
         res.setPianxie(pianxie);
         res.setQiaodu(qiaodu);
         
       
         }
	catch (Exception e) {
	 			e.printStackTrace();
	 		}
	System.out.println("key indectoe is "+res);
	return res;
	
}




//鏌ヨ鏌愪竴绫讳紶鎰熷櫒璺濈褰撳墠5鍒嗛挓鐨勬柟宸紝鍧囧�硷紝鏈�澶у�硷紝
public  List<dataEntity> findRangeRecord(String beginTime, String endTime,String datapoint){
	Statement sql_statement;
	List<dataEntity> res=new ArrayList<>();
	try {
		
		String sql="select * from dataentity where  timeStamp>? and timeStamp<? and datapoint=?";
		
      
		 PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
         ps.setString(1, beginTime);
         ps.setString(2, endTime);
         ps.setString(3, datapoint);
         //ps.setTimestamp(2,endTime);
         ResultSet result = ps.executeQuery();
         System.out.println("fdsfds"+result.getRow());
       while (result.next()) 
       {
    	  // System.out.println(result.getInt(4));
    	  res.add(new dataEntity(result.getTimestamp(1), result.getString(2), result.getString(3), result.getDouble(4)));
       }
               
              
	}
	catch (Exception e) {
	 			e.printStackTrace();
	 		}
	return res;
	
}















	
	public static void main(String[] args) {
		Dao db = Dao.getInstance();
    	/*String sql = "insert into dataEntity (timeStamp,datapoint,deviceId,value) value (?,?,?,?)";
	 List<Parameter>param=new ArrayList<>();	
	   for (int i = 1; i <= 100; i++) {
		   param.add(new Parameter(i,"zxj"+i));
       }
		db.insertBatch(sql, param);
		System.out.println("insert finished");*/
		//db.Getindicator("sensor10");
		
		/*List<dataEntity> res=db.findRangeRecord("2018-12-17 23:01:34","2018-12-17 23:02:34","sensor20");
		System.out.println(res);*/
		
		
		db.Getindicator("sensor20");
		
	}
 
}