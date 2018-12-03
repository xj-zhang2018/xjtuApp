package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtu.demo.Echarts;
import org.xjtu.demo.OrderCountBo;
import org.xjtu.demo.Series;

import pojo.User;
import service.UserManager;

@ResponseBody//返回的是json字体
@Controller
public class UserController {
	@Autowired
	private UserManager userManager;
	
	
	
	
	
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public User addUser(User u){
		System.out.println("名字是"+u.getUname());
		return userManager.regist(u);//注册操作
	}
	
	
	
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<User> findAllUser(){
		return userManager.findAllUser();
	}
	
	
	@RequestMapping(value="/GetImg",method=RequestMethod.GET)
	public void GetImg(HttpServletRequest request, HttpServletResponse response){
		InputStream is=null;
		OutputStream os=null;
		try {
		is=new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\图材\\图片1.jpg"));
		os=response.getOutputStream();
		response.setContentType("image/png");
		byte[] bur=new byte[is.available()];
		int n=0;
		while ((n=is.read(bur))!=-1) {
			os.write(bur);
			
		}
		os.flush();
		   
	} catch (Exception e) {
	
		e.printStackTrace();
	}finally{
		
	
		try {
			if(is!=null){
			is.close();
			}
			if(os!=null){
				os.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	return os;
	}
}
