
<%@page import="com.nearGroup.modal.Users"%>
<%@page import="com.nearGroup.ui.server.impl.*"%>
<%@page import="com.nearGroup.ui.server.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javacryption.aes.AesCtr,javacryption.jcryption.JCryption,java.security.KeyPair,java.util.* "%>
 
 	
	<%! UserServices obj = new UserServicesImpl(); %>


<% 
 if(request.getMethod().equalsIgnoreCase("GET")){
		JCryption jc = new JCryption();
		KeyPair keys = jc.getKeyPair();
		request.getSession().getServletContext()
				.setAttribute("jCryptionKeys", keys);
		String e = jc.getPublicExponent();
		String n = jc.getKeyModulus();
		String md = String.valueOf(jc.getMaxDigits());
		//System.out.println("111111 = " + md);
		/** Sends response **/
		out.print("{\"e\":\"" + e + "\",\"n\":\"" + n
				+ "\",\"maxdigits\":\"" + md + "\"}");
		return;
	
	
}
if(request.getMethod().equalsIgnoreCase("POST") & request.getSession()
		.getServletContext().getAttribute("jCryptionKeys") != null)
{
	
		/** Decrypts password using private key **/
		JCryption jc = new JCryption((KeyPair) request.getSession()
				.getServletContext().getAttribute("jCryptionKeys"));
		String key = jc.decrypt(request.getParameter("key"));
		request.getSession().getServletContext()
				.removeAttribute("jCryptionKeys");
		request.getSession().getServletContext()
				.setAttribute("jCryptionKey", key);
		/** Encrypts password using AES **/
		String ct = AesCtr.encrypt(key, key, 256);
		/** Sends response **/
		//System.out.println("222222 = " + ct);
		out.print("{\"challenge\":\"" + ct + "\"}");
		return;
}
if(request.getMethod().equalsIgnoreCase("POST"))
{
	String status = "" ;
 if (request.getParameter("encryptData").equals("true") && request.getParameter("jCryption") != null) {
	String key = (String) request.getSession().getServletContext()
			.getAttribute("jCryptionKey");
	String ct = AesCtr.decrypt(request.getParameter("jCryption"), key, 256);
	String[] val = ct.split("~");
	
	 status = obj.isValidUser(val[0],val[1]);
	if(!status.equalsIgnoreCase("false") && status.length() >0) 
	{

		Users users = obj.getTechProfile(status);
		if(users.getStatus().equalsIgnoreCase("INACTIVE"))
		{
			out.print("INACTIVE");
			return;
		}
		else if(users.getPassword().equalsIgnoreCase("Default@123"))
		{
			HttpSession sess = request.getSession(true);
			session.setAttribute("techprofile",users);
			out.print("PasswordRest");
			return;
		}
		HttpSession sess = request.getSession(true);
		//session.setAttribute("coprid", userid);
		//session.setAttribute("corpUserName", val[0]);
		String logId = obj.insertUserLoginLog(users.getFirstName(), users.getLastName(), users.getTechPofileType());
		session.setAttribute("techprofile",users);
		session.setAttribute("logId", logId);
		out.print("true");
		return;
		}
		else
		{
			out.print(status);
			return;
		}
		
	}
	 %><%=status%><%

}
%>