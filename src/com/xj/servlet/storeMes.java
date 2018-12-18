package com.xj.servlet;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xj.mqtt.Client;

/**
 * Servlet implementation class storeMes
 */
public class storeMes implements ServletContextListener  {
	private static final long serialVersionUID = 1L;
       
	

	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("start store Message!!!");
		 Client client001 = new Client("client001");  
	        client001.start(); 
	}






	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
