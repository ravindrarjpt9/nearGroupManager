<%@page import="com.nearGroup.modal.UserLog"%>
<%@page import="com.nearGroup.modal.Users"%>
<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@page import="com.nearGroup.ui.server.UserServices"%>
<%@ page contentType="text/xml; charset=ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%> --%>
<%!UserServices userServices = new UserServicesImpl();%>
<rows>
<% 
  String sPageNo = request.getParameter("page");
	String sRowsPerPage = request.getParameter("rows");
	String stypePractice = request.getParameter("stypePractice");
	String sPracticeValue = request.getParameter("sPracticeValue");
	String logindate = request.getParameter("logindate");
	
	
	

		int nRowsPerPage = 0;
		int nPageNo = 0;
		
		int total_pages = 0;
		int count = 0;

		if (sPageNo != null) {
			nPageNo = Integer.parseInt(sPageNo);
		}
		if (sRowsPerPage != null) {
			nRowsPerPage = Integer.parseInt(sRowsPerPage);
		}
		if(stypePractice == null)
		{
			stypePractice = "";
		}
		
		if(sPracticeValue == null || "Search...".equalsIgnoreCase(sPracticeValue))
		{
			sPracticeValue = "";
		}
		if(logindate == null)
		{
			logindate = "";
		}
		count = userServices.getTotalUserLogs(stypePractice,sPracticeValue,logindate);
		if (count > 0 && nRowsPerPage != 0) {
  			total_pages = (int) (Math.ceil((double) count
  					/ (double) nRowsPerPage));
  		} else {
  			total_pages = 0;
  		}

  		// if for some reasons the requested page is greater than the total
  		// set the requested page to total page
  		if (nPageNo > total_pages) {
  			nPageNo = total_pages;
  		}
  		// calculate the starting position of the rows
  		int start = (nPageNo - 1) * nRowsPerPage;

  		// if for some reasons start position is negative set it to 0
  		// typical case is that the user type 0 for the requested page
  		if (start < 0) {
  			start = 0;
  		}
  		
  		StringBuilder sql =new StringBuilder("SELECT ID ,ROLE,FIRST_NAME,LAST_NAME ,DATE(LOGIN_TIME) as LOGIN_TIME ,DATE(LOGOUT_TIME) as LOGOUT_TIME "+
  				" FROM  USERS_LOG");
  		if(!sPracticeValue.trim().equals(""))
  		sql.append(" where ").append(stypePractice).append(" like '") .append(sPracticeValue.trim()).append("%' ");
  		if(!logindate.trim().equals("") && sPracticeValue.trim().equals(""))
  			sql.append(" and date(LOGIN_TIME)='").append(logindate.trim()).append("'");
  		else if(logindate.trim().equals("") && !sPracticeValue.trim().equals(""))
  			sql.append(" where date(LOGIN_TIME)='").append(logindate.trim()).append("'");
  		if (nRowsPerPage != 0) {
  			sql.append(" order by id desc").append(" limit ").append(start).append(" , ")
  					.append(nRowsPerPage);
  		}
List<UserLog> list = userServices.getAllUserLoginLog(sql.toString());
%> <page><%=nPageNo%></page> <total><%=total_pages%></total> <records><%=String.valueOf(count)%></records>
<%   for (UserLog obj : list) {
	   %><row id="<%=obj.getId()%>">
	   <cell><%=obj.getId() %></cell>
	   <cell><%=obj.getFirstName() %></cell>
	  
	   <cell><![CDATA[<%=obj.getLastName()%>]]></cell>
	      
	   <cell><![CDATA[<%=obj.getRole() %>]]></cell>
	   <cell><![CDATA[<%=obj.getLoginTime() %>]]></cell>
	   <cell><![CDATA[<%=obj.getLogoutTime() %>]]></cell>
	   
	   
	   
	    
	   
	   
	   
</row>
    	   <%}
    	   %>
    	</rows>


