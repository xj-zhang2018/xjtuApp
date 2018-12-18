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
	// һ��������ʼ������
	private Connection con;
 
	// ���´��룬��֤����ֻ����һ��ʵ��
	public Dao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		    String url = "jdbc:mysql://localhost:3306/kafkadata?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
			String user = "root";
			String password = "123456";
			
			
			/*String url = "jdbc:mysql://172.30.159.167:3306/app4sql?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
			String user = "userA8S";
			String password = "siJEKXHkEcf5F7xw";*/
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // ˽���޲ι��췽��
 
	// ���Լ��ڲ������Լ���һ��ʵ����ֻ���ڲ�����
	private static Dao db = null;
 
	// ���������Զ�������ϵͳ�ṩ���ʵ������
	// �����ṩ��һ�����ⲿ���ʱ�class�ľ�̬����������ֱ�ӷ���
	public static Dao getInstance() {
		if (db == null) {
			db = new Dao();
		}
		return db;
	}
 

 
	// �����������ж����ݿ������
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
	
	
	

//查询某一类传感器距离当前5分钟的方差，均值，最大值，
public param Getindicator(String datapoint)  {
	Statement sql_statement;
	 double max=0;
     double min=0;
     double VARIANCE=0;
     param parm=null;
	try {
		String sql="select max(value) ,min(value),VARIANCE(value)from dataEntity where  timeStamp>DATE_SUB(NOW(), INTERVAL 200 MINUTE) and datapoint='"+datapoint+"'";
		 sql_statement = (Statement) con.createStatement();
		 ResultSet result = sql_statement.executeQuery(sql);
         
         while (result.next()) 
         {
             max= result.getDouble(1);
             min= result.getDouble(2);
             VARIANCE=result.getDouble(3);
             parm=new param(max, min, VARIANCE);
             System.out.println(" max :" + max + "min " + min + "方差 " + VARIANCE); 
         }
                 
                
	}
	catch (Exception e) {
	 			e.printStackTrace();
	 		}
	return parm;
	
}











//查询某一类传感器距离当前5分钟的方差，均值，最大值，
public  List<dataEntity> findRangeRecord(String beginTime, String endTime,String datapoint){
	Statement sql_statement;
	List<dataEntity> res=new ArrayList<>();
	try {
		
		String sql="select * from dataEntity where  timeStamp>? and timeStamp<? and datapoint=?";
		
      
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
		
		List<dataEntity> res=db.findRangeRecord("2018-12-17 23:01:34","2018-12-17 23:02:34","sensor20");
		System.out.println(res);
		
		
	}
 
}