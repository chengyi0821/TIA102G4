package com.tia102g4.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tia102g4.util.HibernateUtil;

@WebListener
public class InitializerListener implements ServletContextListener {

	@Override 
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory(); 	//呼叫getSessionFactory()創建工廠
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context ended");
		HibernateUtil.shutdown(); 			//呼叫shutdown()將工廠關閉
	}

}
