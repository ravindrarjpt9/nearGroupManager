/**
 * 
 */
package com.nearGroup.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import noNamespace.Row;
import noNamespace.WhoLoggedInMessage;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.soap.MimeHeaders;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.nearGroup.db.DBManager;
import com.nearGroup.security.AesEncryptor;
import com.nearGroup.security.Base64;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;

public class Helper {

	private static Logger logger = Logger.getLogger(Helper.class);

	public static String chkNull(String obj) {
		return obj != null ? obj.trim() : "";
	}

	public static ArrayList<String[]> soapParser(String soapMessage) {

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] row = new String[5];

		try {

			byte[] b = soapMessage.getBytes();
			ByteArrayInputStream input = new ByteArrayInputStream(b);
			MimeHeaders mimeHeaders = new MimeHeaders();
			mimeHeaders.addHeader("Content-Type",
					"text/xml; charset=iso-8859-1");
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage(null, input);
			// SOAPHeader header = message.getSOAPHeader();
			SOAPBody body = message.getSOAPBody();
			Document doc = body.getOwnerDocument();
			NodeList nodeList = doc.getElementsByTagName("return");
			Node returnNode = nodeList.item(0);

			WhoLoggedInMessage msg = WhoLoggedInMessage.Factory
					.parse(returnNode);
			Row[] rows = msg.getReturn().getResultset().getData().getRowArray();

			for (int i = 0; i < rows.length; i++) {
				row = new String[5];
				row[0] = Helper.chkNull(rows[i].getHostName());
				row[1] = Helper.chkNull(rows[i].getHostMac());
				row[2] = Helper.chkNull(rows[i].getHostIP());
				row[3] = Helper.chkNull(rows[i].getUlname()) + " , "
						+ Helper.chkNull(rows[i].getUfname());
				row[4] = Helper.chkNull(rows[i].getUsrstatus());
				list.add(row);
			}
			return list;
		} catch (SOAPException e) {
			logger.info("Error While parsing SOAP Message:", e);
		} catch (IOException e) {
			logger.info("Error While parsing SOAP Message:", e);
		} catch (XmlException e) {
			logger.info("Error While parsing SOAP Message:", e);
		} catch (Exception e) {
			logger.info("Error While parsing SOAP Message:", e);
		}

		return null;
	}

	public static String getParameter(HttpServletRequest request, String param) {

		BufferedReader br = null;
		try {
			br = request.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line = "";
		String value = "";
		while (line != null) {
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (line != null) {
				value = line;
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		br = null;
		value = value.substring(param.length() + 1);
		logger.debug(param + ":" + value);
		return value;
	}
	
	public static String decrypt(String encryptedText, String encryptedKey) {

		String plainText = null;
		try {
			String ivCode = "VenkatGiriRajesh";
			AesEncryptor en = new AesEncryptor();

			en.setProvider("SunJCE");
			en.setIv(ivCode.getBytes());
			en.setKey(encryptedKey);
			//TODO:First comment code
/*			if(logger.isDebugEnabled()){
				logger.debug("Received encryptedText:" + encryptedText);
			}
*/			
			plainText = new String(en.Decrypt(Base64.decode(encryptedText)),"UTF-8");
            //TODO:Second comment code
/*			
			if(logger.isDebugEnabled()){
				logger.debug("decryptedMsg:" + plainText);
			}
*/		} catch (UnsupportedEncodingException e) {
			if(logger.isInfoEnabled()){
				logger.info("Error While decrypting Message:" + encryptedText, e);
			}			
			//throw new DecryptionFailureException();
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("Error While decrypting Message:" + encryptedText, e);
			}
			//throw new DecryptionFailureException();
		}
		return plainText;
	}
	public static String decryptErsTechSupportValue(String encryptedText, String encryptedKey) {

		String plainText = null;
		try {
			String ivCode = "RavindDiliPravin";
			AesEncryptor en = new AesEncryptor();

			en.setProvider("SunJCE");
			en.setIv(ivCode.getBytes());
			en.setKey(encryptedKey);
			//TODO:First comment code
/*			if(logger.isDebugEnabled()){
				logger.debug("Received encryptedText:" + encryptedText);
			}
*/			
			plainText = new String(en.Decrypt(Base64.decode(encryptedText)),"UTF-8");
            //TODO:Second comment code
/*			
			if(logger.isDebugEnabled()){
				logger.debug("decryptedMsg:" + plainText);
			}
*/		} catch (UnsupportedEncodingException e) {
			if(logger.isInfoEnabled()){
				logger.info("Error While decrypting Message:" + encryptedText, e);
			}			
			//throw new DecryptionFailureException();
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("Error While decrypting Message:" + encryptedText, e);
			}
			//throw new DecryptionFailureException();
		}
		return plainText;
	}

	public static String encrypt(String plainText, String encryptedKey) {

		//TODO:Third Comment code
/*		if(logger.isDebugEnabled()){
			logger.debug("plainText:" + plainText);
		}
*/		
		String encryptedText = null;
		try {
			String ivCode = "VenkatGiriRajesh";
			AesEncryptor en = new AesEncryptor();
			en.setProvider("SunJCE");
			en.setIv(ivCode.getBytes());
			en.setKey(encryptedKey);
			byte[] dMsg = en.Encrypt(plainText);
            //Forth Comment code
/*			if(logger.isDebugEnabled()){
				logger.debug("Encrypted plainText:" + new String(dMsg));
			}
*/			
			encryptedText = Base64.encode(dMsg);
            //Fifth Comment code
/*			if(logger.isDebugEnabled()){			
				logger.debug("Base64 Encoded Encrypted plainText:" + encryptedText);
			}
*/			
		} catch (UnsupportedEncodingException e) {

			if(logger.isDebugEnabled()){
				logger.debug("Error While encrypting Message:" + plainText, e);
			}
			
		} catch (Exception e) {

			if(logger.isDebugEnabled()){
				logger.debug("Error While encrypting Message:" + plainText, e);
			}
		}
		return encryptedText;
	}

	

	
	public static String hashString(String pwd) {

		StringBuffer hex = new StringBuffer();
        //TODO:Sixth Comment code
/*		if (logger.isDebugEnabled()) {
			logger.debug("Password to build MD5 Value:" + pwd);
		}
*/
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(Helper.chkNull(pwd).getBytes());
			byte[] d = md.digest();
			String plaintxt = "";
			for (int i = 0; i < d.length; i++) {
				plaintxt = Integer.toHexString(0xFF & d[i]);
				if (plaintxt.length() < 2) {
					plaintxt = "0" + plaintxt;
				}
				hex.append(plaintxt);
			}
		} catch (NoSuchAlgorithmException nsae) {

			if (logger.isDebugEnabled()) {
				logger.debug("MD5 Hashing failed", nsae);
			}
		}
		return hex.toString();
	}

	public static String getPageContent(String strUrl) {

		String line = null;
		BufferedReader in = null;
		StringBuffer webCallContent = new StringBuffer();
		try {

			URL webCallUrl = new URL(strUrl);
			in = new BufferedReader(new InputStreamReader(webCallUrl
					.openStream()));

			while ((line = in.readLine()) != null) {
				webCallContent.append(line);
			}

		} catch (Exception ex) {
			if(logger.isDebugEnabled()){
				logger.debug("Errr In Making Web Call" + strUrl, ex);
			}
		}

		finally {
			try {
				in.close();
			} catch (IOException e) {

				if(logger.isDebugEnabled()){
					logger.debug("Error While closing webUrl stream", e);
				}

			}
			in = null;

		}
		
		if (webCallContent != null && !(webCallContent.toString().equalsIgnoreCase("null"))) { 
			return webCallContent.toString();
		}
			return null;
	}

	public static ArrayList<String[]> soapParser1(String soapMessage) {

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] row = new String[5];

		try {

			byte[] b = soapMessage.getBytes();
			ByteArrayInputStream input = new ByteArrayInputStream(b);
			MimeHeaders mimeHeaders = new MimeHeaders();
			mimeHeaders.addHeader("Content-Type",
					"text/xml; charset=iso-8859-1");
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage message = messageFactory.createMessage(null, input);
			// SOAPHeader header = message.getSOAPHeader();
			SOAPBody body = message.getSOAPBody();
			Document doc = body.getOwnerDocument();
			NodeList nodeList = doc.getElementsByTagName("return");
			Node returnNode = nodeList.item(0);

			WhoLoggedInMessage msg = WhoLoggedInMessage.Factory
					.parse(returnNode);
			Row[] rows = msg.getReturn().getResultset().getData().getRowArray();

			for (int i = 0; i < rows.length; i++) {
				row = new String[5];
				row[0] = Helper.chkNull(rows[i].getHostName());
				row[1] = Helper.chkNull(rows[i].getHostMac());
				row[2] = Helper.chkNull(rows[i].getHostIP());
				row[3] = Helper.chkNull(rows[i].getUlname()) + " , "
						+ Helper.chkNull(rows[i].getUfname());
				row[4] = Helper.chkNull(rows[i].getUsrstatus());
				list.add(row);
			}
			return list;
		} catch (SOAPException e) {

			if (logger.isDebugEnabled()) {
				logger.debug("Error While parsing SOAP Message:", e);
			}

			e.printStackTrace();
		} catch (IOException e) {

			if (logger.isDebugEnabled()) {
				logger.debug("Error While parsing SOAP Message:", e);
			}

		} catch (XmlException e) {
			logger.debug("Error While parsing SOAP Message:", e);
		} catch (Exception e) {
			logger.debug("Error While parsing SOAP Message:", e);
		}

		return null;
	}
	
public static String sendUserNotification(String pushId,String message)
{
	logger.info("Going to send GCM message to push id["+pushId+"] message ["+message+"]");
	Sender sender = new Sender(Constants.SERVER_API_KEY);
	Message msg = new Message.Builder().addData("message", message)
			.build();
	
		Result result = null;
		try {
			result = sender.send(msg, pushId, 1);
			logger.info("Sent message to one device: " + result);
			
		} catch (IOException e) {
			logger.error("Error while sending message to push id["+pushId+"] message ["+message+"]",e);
			
		
		}
		return result.getMessageId();
		

}
	  public static String valdiateSQL(String strIn) {
		String str = null;
		String strQuote = null;
		String strOut = null;
		StringTokenizer st = null;

		if (strIn == null)
			return null;

		if (strIn.length() == 0)
			return strIn;

		strOut = "";
		strQuote = "'";
		st = new StringTokenizer(strIn, strQuote, true);
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			if (str.compareTo(strQuote) == 0)
				str = "''";

			strOut += str;
		}

		return strOut;
	}
	  
	  public static boolean  versionCheck(String activeFileVer,String reqFileVer){
			
			String [] s1 = activeFileVer.split("[.]");
			String [] s2=  reqFileVer.split("[.]");
			int activeVer=0;
			int reqVer=0;
			for (int i = 0; i < s1.length ; i++) {
				s1[i] = chkNull(s1[i]);
				activeVer=	Integer.parseInt(s1[i]);
				if(i < s2.length){
					s2[i] = chkNull(s2[i]);

					reqVer=	Integer.parseInt(s2[i]);
				}else {
					reqVer=0;
				}
				if(activeVer > reqVer){
					return true;
				}
				else if(activeVer < reqVer){
					return false;
				}
			}
			
			return false;
			
		}
	  
//	public static String getUIJobStatus(String jobName, String status, String errMsg) {
//
//			if (OneClickConstants.JOB_APPROVAL_EMAIL_SENT.equalsIgnoreCase(status)) {
//				if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//								.equalsIgnoreCase(jobName)) {
//					status = "Approval e-mail Sent";
//				}
//			}
//			if (OneClickConstants.JOB_APPROVAL_EMAIL_EXPIRED.equalsIgnoreCase(status)) {
//				if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//								.equalsIgnoreCase(jobName)) {
//					status = "Approval e-mail Expried";
//				}
//			}		
//					
//
//			if (OneClickConstants.JOB_LOGGED.equalsIgnoreCase(status)) {
//
//				if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//								.equalsIgnoreCase(jobName)) {
//					status = "Connection Requested";
//				}
//
//				if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//					status = "Disconnecting...";
//				}
//				if (OneClickConstants.JOB_BATCH_FILE.equalsIgnoreCase(jobName)) {
//					status = "Requested";
//				}			
//
//				if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//					status = "Sending Message";
//				}
//
//				if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_FTP_DOWNLOAD
//								.equalsIgnoreCase(jobName)) {
//					status = "Downloading File...";
//				}
//
//				if (OneClickConstants.JOB_LOG_IN_USER_STATUS
//						.equalsIgnoreCase(jobName)) {
//					status = "Processing Who Logged In...";
//				}
//			}
//
//			if (OneClickConstants.JOB_COMPLETED.equalsIgnoreCase(status)) {
//
//				if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//								.equalsIgnoreCase(jobName)) {
//					if(errMsg!=null && (errMsg.indexOf("process exit code 0")!= -1)){
//						status = "Connected";	
//					}
//					else {
//						status = "Failed";
//					}
//				}
//
//				if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//					status = "Disconnected";
//				}
//
//				if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//					status = "Message Received";
//				}
//
//				if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_FTP_DOWNLOAD
//								.equalsIgnoreCase(jobName)) {
//					status = "Downloaded";
//				}
//
//				if (OneClickConstants.JOB_LOG_IN_USER_STATUS
//						.equalsIgnoreCase(jobName)) {
//					status = "Success";
//				}
//				if (OneClickConstants.JOB_BATCH_FILE.equalsIgnoreCase(jobName)) {
//					status = "Completed";
//				}			
//			}
//
//			if (OneClickConstants.JOB_PENDING.equalsIgnoreCase(status)) {
//				status = "In Process";
//			}
//			if (OneClickConstants.JOB_RECEIVED.equalsIgnoreCase(status)) {
//				if (OneClickConstants.JOB_BATCH_FILE.equalsIgnoreCase(jobName)) {
//					status = "Ready For Exectuion";
//				}
//				else {			
//					status = "In Process";
//				}
//				
//			}
//			if (OneClickConstants.JOB_RUNNING.equalsIgnoreCase(status)) {
//				status = "In Process";
//			}
//			if (OneClickConstants.JOB_ERROR.equalsIgnoreCase(status)) {
//
//				if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//								.equalsIgnoreCase(jobName)) {
//					status = "Failed";
//				}
//
//				if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//					status = "Failed";
//				}
//
//				if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//					status = "Unable to Send";
//				}
//
//				if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//						|| OneClickConstants.JOB_FTP_DOWNLOAD
//								.equalsIgnoreCase(jobName)) {
//					status = "Download Failed";
//				}
//
//				if (OneClickConstants.JOB_LOG_IN_USER_STATUS
//						.equalsIgnoreCase(jobName)) {
//					status = "Error";
//				}
//			}
//			return status;
//		}
//			
	 
//	public static String getUIJobStatus(String jobName, String status) {
//
//		if (OneClickConstants.JOB_APPROVAL_EMAIL_SENT.equalsIgnoreCase(status)) {
//			if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//							.equalsIgnoreCase(jobName)) {
//				status = "Approval e-mail Sent";
//			}
//		}
//		if (OneClickConstants.JOB_APPROVAL_EMAIL_EXPIRED.equalsIgnoreCase(status)) {
//			if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//							.equalsIgnoreCase(jobName)) {
//				status = "Approval e-mail Expried";
//			}
//		}		
//				
//
//		if (OneClickConstants.JOB_LOGGED.equalsIgnoreCase(status)) {
//
//			if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//							.equalsIgnoreCase(jobName)) {
//				status = "Connection Requested";
//			}
//
//			if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//				status = "Disconnecting...";
//			}
//			if (OneClickConstants.JOB_BATCH_FILE.equalsIgnoreCase(jobName)) {
//				status = "Requested";
//			}			
//
//			if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//				status = "Sending Message";
//			}
//
//			if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_FTP_DOWNLOAD
//							.equalsIgnoreCase(jobName)) {
//				status = "Downloading File...";
//			}
//
//			if (OneClickConstants.JOB_LOG_IN_USER_STATUS
//					.equalsIgnoreCase(jobName)) {
//				status = "Processing Who Logged In...";
//			}
//		}
//
//		if (OneClickConstants.JOB_COMPLETED.equalsIgnoreCase(status)) {
//
//			if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//							.equalsIgnoreCase(jobName)) {
//				status = "Connected";
//			}
//
//			if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//				status = "Disconnected";
//			}
//
//			if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//				status = "Message Received";
//			}
//
//			if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_FTP_DOWNLOAD
//							.equalsIgnoreCase(jobName)) {
//				status = "Downloaded";
//			}
//
//			if (OneClickConstants.JOB_LOG_IN_USER_STATUS
//					.equalsIgnoreCase(jobName)) {
//				status = "Success";
//			}
//			if (OneClickConstants.JOB_BATCH_FILE.equalsIgnoreCase(jobName)) {
//				status = "Completed";
//			}			
//		}
//
//		if (OneClickConstants.JOB_PENDING.equalsIgnoreCase(status)) {
//			status = "In Process";
//		}
//		if (OneClickConstants.JOB_RECEIVED.equalsIgnoreCase(status)) {
//			if (OneClickConstants.JOB_BATCH_FILE.equalsIgnoreCase(jobName)) {
//				status = "Ready For Exectuion";
//			}
//			else {			
//				status = "In Process";
//			}
//			
//		}
//		if (OneClickConstants.JOB_RUNNING.equalsIgnoreCase(status)) {
//			status = "In Process";
//		}
//		if (OneClickConstants.JOB_ERROR.equalsIgnoreCase(status)) {
//
//			if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//							.equalsIgnoreCase(jobName)) {
//				status = "Failed";
//			}
//
//			if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//				status = "Failed";
//			}
//
//			if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//				status = "Unable to Send";
//			}
//
//			if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//					|| OneClickConstants.JOB_FTP_DOWNLOAD
//							.equalsIgnoreCase(jobName)) {
//				status = "Download Failed";
//			}
//
//			if (OneClickConstants.JOB_LOG_IN_USER_STATUS
//					.equalsIgnoreCase(jobName)) {
//				status = "Error";
//			}
//		}
//		return status;
//	}
//		
//
//	public static String getUIJobName(String jobName) {
//
//		String jobUIName = "";
//		if (OneClickConstants.JOB_VNC_CONNECT.equalsIgnoreCase(jobName)
//				|| OneClickConstants.JOB_TIGHTVNC_CONNECT
//						.equalsIgnoreCase(jobName)) {
//			jobUIName = "Connection";
//		}
//
//		if (OneClickConstants.JOB_VNC_DISCONNECT.equalsIgnoreCase(jobName)) {
//			jobUIName = "Disconnect";
//		}
//
//		if (OneClickConstants.JOB_NOTIFY.equalsIgnoreCase(jobName)) {
//			jobUIName = "Message";
//		}
//
//		if (OneClickConstants.JOB_HTTP_DOWNLOAD.equalsIgnoreCase(jobName)
//				|| OneClickConstants.JOB_FTP_DOWNLOAD.equalsIgnoreCase(jobName)) {
//			jobUIName = "File Download";
//		}
//
//		if (OneClickConstants.JOB_LOG_IN_USER_STATUS.equalsIgnoreCase(jobName)) {
//			jobUIName = "Who Logged In";
//		}
//
//		return jobUIName;
//
//	}
//
//	
//	public static String generateUniqueURL(String eRsId,String practiceId, String logstype){
//		
//		String[] secMsgs = new String [2];
//		String jobTransId = UUID.randomUUID().toString();
//		secMsgs[0] = "SSO_REQUEST:"+jobTransId+"#"+eRsId+"#"+"PRACTICE_LOG="+logstype;
//		//String uniqueURL = "https://ers.eclinicalweb.com/oneclicksupport/message.jsp";
//		String uniqueURL = OneClickConstants.ONECLICK_SERVER_APP_URL +"/oneclicksupport/message.jsp";
//		Connection conn = null;
//		PreparedStatement secureInsertPrepSt = null;
//		
//		try {
//			secMsgs[0] = BlowFish.getBlowFishEncryptedAndBse64EncodedString(secMsgs[0], "esotericpass");
//			secMsgs[1] =  com.nearGroup.security.encryption.MD5.getHash(secMsgs[0]);
//			StringBuffer secTransSQL = new StringBuffer();
//			secTransSQL.append("INSERT ");
//			secTransSQL.append("INTO   ers_security_trans ");
//			secTransSQL.append("       ( ");
//			secTransSQL.append("              REQUEST_TYPE  , ");
//			secTransSQL.append("              JOBS_TRANS_ID , ");					
//			secTransSQL.append("              ERS_ID         , ");
//			secTransSQL.append("              ERS_PRACTICE_ID, ");
//			secTransSQL.append("              ENCRYPT_MSG    , ");
//			secTransSQL.append("              ENCRYPT_MSG_MD5 , ");
//			secTransSQL.append("              ENCRYPT_MSG_STATUS , ");
//			secTransSQL.append("              CREATION_TIME ");
//			secTransSQL.append("       ) ");
//			secTransSQL.append("       VALUES ");
//			secTransSQL.append("       ( ");
//			secTransSQL.append("              ?, ");
//			secTransSQL.append("              ?, ");
//			secTransSQL.append("              ?, ");					
//			secTransSQL.append("              ?, ");
//			secTransSQL.append("              ?, ");
//			secTransSQL.append("              ?, ");
//			secTransSQL.append("              'VALID', ");
//			secTransSQL.append("              UTC_TIMESTAMP() ");
//			secTransSQL.append("       )");
//			conn = null;
//			secureInsertPrepSt = null;
//
//			conn = DBManager.getConnection(OneClickConstants.ERS_DS);
//			secureInsertPrepSt = conn.prepareStatement(secTransSQL.toString());
//			int j=0;
//			//Make entries into security transaction table 
//			secureInsertPrepSt.setString(++j,"SSO_REQUEST");
//			secureInsertPrepSt.setString(++j,jobTransId);					
//			secureInsertPrepSt.setString(++j,eRsId);
//			secureInsertPrepSt.setString(++j,practiceId);
//			secureInsertPrepSt.setString(++j,secMsgs[0]);// encrypt message
//			secureInsertPrepSt.setString(++j,secMsgs[1]);// sec code
//			secureInsertPrepSt.addBatch();
//			int[] securityReqCount = secureInsertPrepSt.executeBatch();
//			//uniqueURL = "https://ers.eclinicalweb.com/oneclicksupport/ersrouter?route="+secMsgs[1];
//			uniqueURL = OneClickConstants.ONECLICK_SERVER_APP_URL +"/oneclicksupport/ersrouter?route="+secMsgs[1];
//			if(logger.isDebugEnabled()){
//				logger.debug("SSORequest :"+uniqueURL);
//			}					
//		} catch (SQLException e) {
//			logger.debug("Error While creating UniqueURL:",e);
//		} catch (Exception e1) {
//			logger.debug("Error While creating UniqueURL:",e1);
//		}
//		finally {
//			try {
//				if (secureInsertPrepSt != null) {
//					secureInsertPrepSt.close();
//					secureInsertPrepSt = null;
//				}
//				if (conn != null) {
//					conn.close();
//					conn = null;
//				}
//			} catch (SQLException e) {
//				logger.info("Error While Closing Resources:", e);
//			}
//		}
//		
//		return uniqueURL;
//		
//	}
//
//	public static void sendToErrorPage(HttpServletRequest request,
//			HttpServletResponse response, String url, String message) {
//		
//			if(!(Helper.chkNull(url).length()>0)){
//				url = "/jsp/portal/message.jsp";
//			}
//				request.setAttribute("message",message);
//				
//			try {
//					request.getRequestDispatcher(url).forward(request, response);
//					//response.sendRedirect(url);
//				} catch (ServletException e) {
//					logger.debug("Invalid Request, forwading to:" + url);
//				} catch (IOException e) {
//					logger.debug("Invalid Request, forwading to:" + url);
//				}
//	}
//
//    	
//
//	public String isNewProdVersionAvialble(String clientversion,String prodversion){
//		
//		String result = "true";
//		
//		try {
//			String [] v = clientversion.split("\\.");	
//			String[] p = prodversion.split("\\.");
//			int counter = v != null ? v.length:0;
//			String local="0";
//			String prod="0";
//
//			counter = p!=null && (counter > p.length) ?  counter : p.length;
//			if(counter > 0)
//			{
//				for(int i = 0; i < counter ; i++)
//				{
//					local =  i < v.length ? v[i]:"0";
//					prod  =  i < p.length ? p[i]:"0";
//					
//					long v1 = Long.parseLong(local);
//					long p1 = Long.parseLong(prod);
//					if(p1 > v1)
//					{
//						result = "false";
//						break;
//					}
//					else if(p1 < v1){
//						result = "true";
//						break;
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.debug("Error , client version :"+clientversion, e);
//		}
//		return result;
//	  }	
	
	public static String htmlEscape(String s) {
		if(s != null )
		{
		  StringBuffer sb = null;
		    String replacement;
		    int start = 0; // the earliest input position we haven't copied yet.
		    for (int i = 0; i < s.length(); i++) {
		      switch (s.charAt(i)) {
		        case '"':
		          replacement = "&quot;";
		          break;
		        case '&':
		          replacement = "&amp;";
		          break;
		        case '<':
		          replacement = "&lt;";
		          break;
		        case '>':
		          replacement = "&gt;";
		          break;
		        case '�':
		        	replacement = "%E2";
		        case '?':
		        	replacement = "%3F";
		        default:
		          replacement = null;
		      }

		      if (replacement != null) {
		        if (sb == null) {
		          // This is the first time we have found a replacement. Allocate the
		          // StringBuilder now.
		          // This initial size for the StringBuilder below will be exact if
		          // this initial replacement is the only one. If not, sb will expand.
		          sb = new StringBuffer(s.length() + replacement.length() - 1);
		        }
		        if (i > start) {
		          // we have to copy some of the earlier string.
		          sb.append(s.substring(start, i));
		        }
		        sb.append(replacement);
		        start = i+1;
		      }
		    }
		    // now possibly also copy what's leftover in the input string.
		    if (start > 0) {
		      sb.append(s.substring(start));
		    }

		    if (sb != null) {
		      return sb.toString();
		    }
		}
		    return s;
		  }
	
	public static String getParameterValue(String key,HttpServletRequest request)
	{
		StringBuffer value = new StringBuffer();
		Enumeration parameterList = request.getParameterNames();
		  while( parameterList.hasMoreElements() )
		  {
		    String sName = parameterList.nextElement().toString();
		    if(sName.toLowerCase().startsWith(key)){
		      String[] sMultiple = request.getParameterValues( sName );
		      if( 1 >= sMultiple.length )
		        value.append(request.getParameter(sName));
		      else
		        for( int i=0; i<sMultiple.length; i++ )
		      value.append(sMultiple[i]).append(",");
		    }
		  }
		  return value.toString();
	}
	
	public static String getUniversalDate()
	{

		SimpleDateFormat f = new SimpleDateFormat("yyyy-yyyy");
		f.setTimeZone(TimeZone.getTimeZone("UTC"));
		return f.format(new Date());
	}
	
	public static String getSplitStringOnInMySql(String data){
		String[] arr = data.split(",");
		String temp = "";
		for(int i = 0; i< arr.length; i++)
		{
			temp += "'"+arr[i]+"'," ;
		}
		return temp.substring(0, temp.length() -1);
		
		
		
	}
	
	public static StringBuilder getLimitParam(String sPageNo,String sRowsPerPage,String counts)
	{
		int nRowsPerPage = 0;
		int nPageNo = 0;
		int total_pages = 0;
		int count = Integer.parseInt(counts);
		if (sPageNo != null) {
			nPageNo = Integer.parseInt(sPageNo);
	}
		if (sRowsPerPage != null) {
			nRowsPerPage = Integer.parseInt(sRowsPerPage);
		}
		if (count > 0 && nRowsPerPage != 0) {
			total_pages = (int) (Math.ceil((double) count
					/ (double) nRowsPerPage));
		} else {
			total_pages = 0;
		}
		if (nPageNo > total_pages) {
			nPageNo = total_pages;
		}
		int start = (nPageNo - 1) * nRowsPerPage;
		if (start < 0) {
			start = 0;
		}
		StringBuilder sql = new StringBuilder();
		if (nRowsPerPage != 0) {
  			sql.append(" limit ").append(start).append(" , ")
  					.append(nRowsPerPage);
  		}
		
		return sql.append("#").append(total_pages);
	}
	

	
}