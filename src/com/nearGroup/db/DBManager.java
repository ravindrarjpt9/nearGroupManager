package com.nearGroup.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.nearGroup.util.Constants;

public class DBManager {

	private static Logger logger = Logger.getLogger(DBManager.class);
	
/*	private static String username="ecwDbUser";
	private static String pwd="Pwd4db@114";
	private static String url="jdbc:mysql://localhost:3306";
	private static String drivername="com.mysql.jdbc.Driver";
	private static String dummySql="SHOW TABLES FROM ers ";
	//private static String DB_JDBC_URL="":jdbc:mysql://localhost:3306/ers?" + "user=ecwDbUser&password=Pwd4db@114?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	//private static String DB_JDBC_URL="jdbc:mysql://localhost:3306/ers?" + "user=ecwDbUser&password=Pwd4db@114?zeroDateTimeBehavior=convertToNull&autoReconnect=true";
	public static String DB_JDBC_URL="";
*/	

	private static DataSource nearGroupDataSource = null;
	
	private static DataSource alphaDataSource = null;
	
	
	

	public static synchronized Connection getAlphaConnection() {
		Connection con = null;
		//Email email = new Email();
		try {
			con = alphaDataSource.getConnection();

			
		} catch (SQLException e) {
			logger.debug("Error While obtaining alphaDataSource Connection", e);
			try {
				
				//email.SendEmail("eManager DataBase Down... Alert", email.getBody(e), "ravindra.rajpoot@eclinicalworks.com");
				//email.SendEmail("eManager local DataBase Down... Alert", email.getBody(e), "ravindra.rajpoot@eclinicalworks.com,dilip.kamble@eclinicalworks.com,pravin.jha@eclinicalworks.com");
                
			} catch (Exception e1) {
				logger.debug("Alpha DataBase Down... Alert", e);
				
			}

		} catch (Exception e) {
			logger.debug("Error While obtaining alphaDataSource Connection", e);
			try {
				
				//email.SendEmail("eManager  DataBase Down... Alert", email.getBody(e), "ravindra.rajpoot@eclinicalworks.com");
				//email.SendEmail("eManager local DataBase Down... Alert", email.getBody(e), "ravindra.rajpoot@eclinicalworks.com,dilip.kamble@eclinicalworks.com,pravin.jha@eclinicalworks.com");
				 
				} catch (Exception e1) {
				logger.debug("Error While sending alpha database down email alert", e);
			}

		}
		return con;
	}	
	
	public static synchronized Connection getNearGroupConnection() {
		Connection con = null;
		try {
			con = nearGroupDataSource.getConnection();
			
		} catch (SQLException e) {
			logger.debug("Error While obtaining nearGroup Connection", e);
		} catch (Exception e) {
			logger.debug("Error While obtaining nearGroup Connection", e);
		}
		return con; 
	}	
	
	public static Connection getConnection(String ds) {
		if (Constants.NEAR_GROUP_DS.equalsIgnoreCase(ds)) {
			return getNearGroupConnection();
		} else if (Constants.ALPHA_DS.equalsIgnoreCase(ds)) {
			
			return getAlphaConnection();
		}
		return null;		
	}
	
	
	

	

	public static void setDataSource(DataSource dataSource) {
		
		DBManager.nearGroupDataSource = dataSource;
		
	}

	public static void loadDatabaseInfo() {
		
		alphaDataSource = getDataSource("jdbc/alpha");
		logger.debug("Error While Loading alpha data source :"+alphaDataSource);

		nearGroupDataSource = getDataSource("jdbc/nearGroupManager");
		logger.debug("Error While Loading near Group Data Source :"+nearGroupDataSource);
		
		
    }
    
    public static void printDataSourceStats(DataSource ds) throws SQLException {
        BasicDataSource bds = (BasicDataSource) ds;
    }

    public static void shutdownDataSource(DataSource ds) throws SQLException {
        BasicDataSource bds = (BasicDataSource) ds;
        bds.close();
    }
    
    private static DataSource getDataSource(String dsName) {
		 
    	DataSource ds = null;
		 try {
			 Context initCtx = new InitialContext();
			 Context envCtx = (Context) initCtx.lookup("java:comp/env");
			 ds = (DataSource)envCtx.lookup(dsName);
		 }catch(Exception ex){
			 logger.debug("Error While Loading DataSource :"+dsName,ex);
		 }
		return ds;
    }

	public static DataSource getNearGroupDataSource() {
		return nearGroupDataSource;
	}

	

	public static DataSource getAlphaDataSource() {
		return alphaDataSource;
	}

	

	

	
    
   /*public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            System.out.println("Creating connection.");
            //loadDatabaseInfo();
			conn = alphaDataSource.getConnection();
            System.out.println("Creating statement.");
            stmt = conn.createStatement();
            System.out.println("Executing statement.");
            rset = stmt.executeQuery("select * from users");
            System.out.println("Results:");
            int numcols = rset.getMetaData().getColumnCount();
            while(rset.next()) {
                for(int i=1;i<=numcols;i++) {
                    System.out.print("\t" + rset.getString(i));
                }
                System.out.println("");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { rset.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { conn.close(); } catch(Exception e) { }
        }
    }*/
    
 
    
}

