package com.xj.persistence;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;
 
public class Dao {
	// 一、单例初始化连接
	private Connection con;
 
	// 以下代码，保证该类只能有一个实例
	private Dao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 其中test是我们要链接的数据库，user是数据库用户名，password是数据库密码。
			// 3306是mysql的端口号，一般是这个
			// 后面那串长长的参数是为了防止乱码，免去每次都需要在任何语句都加入一条SET NAMES UTF8
			String url = "jdbc:mysql://localhost:3306/kafkadata?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
			String user = "root";
			String password = "123456";
			
			
			/*String url = "jdbc:mysql://172.30.230.200:3306/kafkadb?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true";
			String user = "zxj";
			String password = "junjun123";*/
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 私有无参构造方法
 
	// 在自己内部定义自己的一个实例，只供内部调用
	private static Dao db = null;
 
	// 这个类必须自动向整个系统提供这个实例对象
	// 这里提供了一个供外部访问本class的静态方法，可以直接访问
	public static Dao getInstance() {
		if (db == null) {
			db = new Dao();
		}
		return db;
	}
 

 
	// 析构函数，中断数据库的连接
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
	
	public static void main(String[] args) {
		Dao db = Dao.getInstance();
    	String sql = "insert into entity (id,name) value (?,?)";
	 List<Parameter>param=new ArrayList<>();	
	   for (int i = 1; i <= 100; i++) {
		   param.add(new Parameter(i,"zxj"+i));
       }
		db.insertBatch(sql, param);
		System.out.println("insert finished");
	}
 
}