package com.xj.persistence;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;
 
public class Dao {
	// һ��������ʼ������
	private Connection con;
 
	// ���´��룬��֤����ֻ����һ��ʵ��
	private Dao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ����test������Ҫ���ӵ����ݿ⣬user�����ݿ��û�����password�����ݿ����롣
			// 3306��mysql�Ķ˿ںţ�һ�������
			// �����Ǵ������Ĳ�����Ϊ�˷�ֹ���룬��ȥÿ�ζ���Ҫ���κ���䶼����һ��SET NAMES UTF8
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