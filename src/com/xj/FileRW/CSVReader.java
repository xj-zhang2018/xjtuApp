package com.xj.FileRW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CSVReader {

	// 读取csv文件的内容
    public static ArrayList<String> readCsv(String filepath) {
        File csv = new File(filepath); // CSV文件路径
        csv.setReadable(true);//设置可读
        csv.setWritable(true);//设置可写
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        ArrayList<String> allString = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) // 读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
        
    }
	
	
	
    //读取文件第一行
    public static String readOneCsv(int id) {
    	String filePath=System.getProperty("user.dir")+"/WebContent/data/"+id+".csv";
    	
    	//  String str1=Thread.currentThread().getContextClassLoader().getResource("1.csv").getPath();
    	 // System.out.println("路径"+str1);
    	
    	File csv = new File(filePath); // CSV文件路径
        BufferedReader br = null;
        String line="";
        try {
            br = new BufferedReader(new FileReader(csv));
            line =br.readLine();
            
         
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
     
        return line;
        
    }
	
	
    
    public static void main(String[] args) {
    	String filePath=System.getProperty("user.dir")+"/WebContent/data/2.csv";
    	String line=readOneCsv(4);
    	System.out.println(line);
	}
	
	
	
	
}
