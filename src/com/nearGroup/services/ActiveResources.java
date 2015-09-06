/**
 * 
 */
package com.nearGroup.services;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nearGroup.db.DBManager;


public class ActiveResources extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ActiveResources.class);
    
		   
	 
	   
	    
	    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			logger.debug("Statrup Loading Active File List Called:");	
			 
			 /**** Loading DB Info  ****/	
			//TODO:***First Comment 
		/*	 ResourceBundle dbprops = ResourceBundle.getBundle("oneclickdb");
			 logger.debug("Database ResourceBundle Loaded :"+dbprops.toString());
			 OneClickConstants.DB_NAME=dbprops.getString("DB_NAME");
			 OneClickConstants.DB_USERNAME=dbprops.getString("DB_USERNAME");
			 OneClickConstants.DB_PASSWORD=dbprops.getString("DB_PASSWORD");
			 OneClickConstants.DB_HOST=dbprops.getString("DB_HOST");
			 OneClickConstants.DB_PORT=dbprops.getString("DB_PORT");
			 OneClickConstants.DB_JDBC_DRIVER_NAME=dbprops.getString("DB_JDBC_DRIVER_NAME");

			 // initializes database info */
			 DBManager.loadDatabaseInfo();
			
			 /**** Loading Application Specific Info  ****/
			 ResourceBundle appprops = ResourceBundle.getBundle("nearGroup");
			 logger.debug("App ResourceBundle Loaded :"+appprops.toString());
//			 OneClickConstants.APP_CONTEXT_PATH=getServletContext().getRealPath("/");
//			 //OneClickConstants.ZIP_ARCHIVE_PATH=appprops.getString("ZIP_ARCHIVE_PATH");
//			 OneClickConstants.ONECLICK_SERVER_APP_URL = appprops.getString("PROTOCOL")+"://"+appprops.getString("ONECLICK_SERVER_NAME");
//			 OneClickConstants.DOWNLOAD_URL=appprops.getString("PROTOCOL")+"://"+appprops.getString("ONECLICK_SERVER_NAME")
//			  +":"+appprops.getString("SSL_PORT")+"/oneclicksupport/ersdownloadmanager";
//			 OneClickConstants.ONECLICKSUPPORT_REPO_CONTEXT=appprops.getString("ONECLICKSUPPORT_REPO_CONTEXT");
//			 
//			 OneClickConstants.ONECLICK_RELEASES_PATH = appprops.getString("ONECLICK_RELEASES_PATH");
//			 OneClickConstants.E_MANAGER_URL=appprops.getString("E_MANAGER_URL");
//			 OneClickConstants.E_MANAGER_PRACTICE_URL=
//				 OneClickConstants.E_MANAGER_URL+"/jsp/catalog/xml/ers/getPracticeInfo.jsp";
//			 OneClickConstants.E_MANAGER_USER_INFO_URL=
//				 OneClickConstants.E_MANAGER_URL+"/jsp/catalog/xml/ers/getUserInfo.jsp";
//			 OneClickConstants.SECURITY_EMAIL_RESPONSE_URL=appprops.getString("SECURITY_EMAIL_RESPONSE_URL");
//			 OneClickConstants.E_MANAGER_PRACTICE_VALIDATION_INFO_URL=
//				 OneClickConstants.E_MANAGER_URL+"/jsp/catalog/xml/ers/validatePracticeInfo.jsp";				 
//			 
//			//Download file information
//			 OneClickConstants.DOWNLOAD_FILE_PATH = appprops.getString("DOWNLOAD_FILE_PATH");
//			 OneClickConstants.FILE_NAME = appprops.getString("FILE_NAME");
//			 OneClickConstants.SECOND_FILE_NAME = appprops.getString("SECOND_FILE_NAME");
//			 
//			 //Corp server ip address
//			 OneClickConstants.CORP_SERVER_IP = appprops.getString("CORP_SERVLER_IP");
//
//			 //XMPP URL information
//			
//             OneClickConstants.XMPP_PROTOCOL = appprops.getString("XMPP_PROTOCOL");
//
//			 OneClickConstants.xmpp_user_registration_url = appprops.getString("XMPP_USER_REGISTRATION_URL");
//			 OneClickConstants.xmpp_SECRET = appprops.getString("SECRET");
//			 
//			 OneClickConstants.XMPP_PRACTICE_INTERVAL = Integer.parseInt(appprops.getString("XMPP_PRACTICE_INTERVAL"));
//			 OneClickConstants.XMPP_MODE = appprops.getString("XMPP_MODE");
//			 
//			 //ers log upload path
//			 OneClickConstants.ERS_UPLOAD_LOG_PATH = appprops.getString("ERS_UPLOAD_LOG_PATH");
//			 //email server information
//			 
//			 OneClickConstants.ERS_SYSTEM_TECH_ID = appprops.getString("ERS_SYSTEM_TECH_ID");
//			 
//			 OneClickConstants.strEmailFrom = appprops.getString("EMAIL_USER_NAME");
//			 OneClickConstants.strAccPwd = appprops.getString("EMAIL_USER_PASSWORD");
//			 OneClickConstants.strSmtpHost = appprops.getString("EMAIL_SMTP_SERVER");
//			 OneClickConstants.strSmtpPort = appprops.getString("EMAIL_SMTP_PORT");
//			 OneClickConstants.ERS_SUPPORT_EMAILS = appprops.getString("ERS_SUPPORT_EMAILS");
//			 
//			 // ERS SYSTEM ADMIN CORP USER NAME
//			 OneClickConstants.ERS_CORP_SYSTEM_ADMIN = appprops.getString("ERS_CORP_SYSTEM_ADMIN");
//			 
//			 
//			 //Auto Upgrade eRS Client agent
//			 OneClickConstants.ERS_CLIENT_VERSION = appprops.getString("ERS_CLIENT_VERSION");
//			 OneClickConstants.ERS_CLIENT_EXE_PATH = appprops.getString("ERS_CLIENT_EXE_PATH");
//			 OneClickConstants.ERS_CLIENT_EXE_FILE_NAME = appprops.getString("ERS_CLIENT_EXE_FILE_NAME");
//			 
//			 
//			//Auto Upgrade eRS Tech Support Client
//			 OneClickConstants.ERS_TECH_SUPPORT_VERSION = appprops.getString("ERS_TECH_SUPPORT_VERSION");
//			 OneClickConstants.ERS_TECH_SUPPORT_EXE_PATH = appprops.getString("ERS_TECH_SUPPORT_EXE_PATH");
//			 OneClickConstants.ERS_TECH_SUPPORT_FILE_NAME = appprops.getString("ERS_TECH_SUPPORT_FILE_NAME");
//			 
//			 
//			 // eRS Tech Support Download 
//			 OneClickConstants.ERS_TECH_SETUP_FILE_NAME = appprops.getString("ERS_TECH_SETUP_FILE_NAME");
//			 
//			 
//			 logger.info("SECURITY_EMAIL_RESPONSE_URL:"+OneClickConstants.SECURITY_EMAIL_RESPONSE_URL);			 
//			 logger.info("E_MANAGER_URL:"+OneClickConstants.E_MANAGER_URL);
//			 
//			 
//			 
//			//Caching Active Resource(s) from Database
//			Map<String,File> activeFiles = ResourceManager.getActiveResourceInfo();
//			logger.debug("Statrup Loading Active File List 1:"+activeFiles);
//			getServletContext().setAttribute("ACTIVE_FILES",activeFiles);
//			if (activeFiles != null) {
//				logger.debug("Statrup Loading Active File List 2:"+activeFiles.toString());	
//			}
//			
		} catch (Exception e) {
			logger.debug("Exception while loading resources",e);
		}
	}
	
	public ActiveResources() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}   	 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
    }
   
  
}
