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
import com.nearGroup.util.Constants;


public class ActiveResources extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ActiveResources.class);
    
		   
	 
	   
	    
	    
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			 // initializes database info */
			 DBManager.loadDatabaseInfo();
			
			 /**** Loading Application Specific Info  ****/
			 ResourceBundle appprops = ResourceBundle.getBundle("nearGroup");
			 logger.debug("App ResourceBundle Loaded :"+appprops.toString());
			  Constants.FACEBOOK_URL=appprops.getString("FACEBOOK_URL");
			  Constants.XMPP_PROTOCOL=appprops.getString("XMPP_PROTOCOL");
			  Constants.XMPP_SERVER_URL=appprops.getString("XMPP_SERVER_URL");
			  Constants.XMPP_HTTP_PORT=appprops.getString("XMPP_HTTP_PORT");
			  Constants.XMPP_BOSH_PORT=appprops.getString("XMPP_BOSH_PORT");
			  Constants.XMPP_STANDARD_PORT=appprops.getString("XMPP_STANDARD_PORT");
			  Constants.XMPP_SECRET_KEY=appprops.getString("XMPP_SECRET_KEY");
			  Constants.NEARGROUP = appprops.getString("NEARGROUP");
			  Constants.SERVER_API_KEY = appprops.getString("SERVER_API_KEY");
			  logger.info("*** End app ResourceBundle***");
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
