package com.xj.FileRW;
import java.io.BufferedReader; 
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;





public class fileHandal {

	  public static void main(String[] args) { 
		  SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  
          try { 
              BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\工业app大赛\\test.csv"));//换成你的文件名
              reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
              String line = null; 
              while((line=reader.readLine())!=null){ 
                  String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                  System.out.println(line);
                  if(item.length==5) {
                   Date date = dateFormat.parse(item[0]);
                   DataVO vo=new DataVO(date, item[1], item[2],item[3],item[4]);
                   JSONObject json= JSONObject.fromObject(vo);
                   System.out.println(json.toString());
                  }
              } 
          } catch (Exception e) { 
              e.printStackTrace(); 
          } 
      } 
	
	
	
	
}
