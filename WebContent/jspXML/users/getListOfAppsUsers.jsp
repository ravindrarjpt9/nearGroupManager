<%@page import="com.nearGroup.modal.AppUsers"%>
<%@page import="com.nearGroup.ui.server.impl.AppsUsersServicesImpl"%>
<%@page import="com.nearGroup.ui.server.AppsUsersServices"%>

<%@ page contentType="text/xml; charset=ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>



<%@ page errorPage="/fail.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%> --%>
<%!AppsUsersServices userServices = new AppsUsersServicesImpl();%>
<rows>
<% 
  String sPageNo = request.getParameter("page");
	String sRowsPerPage = request.getParameter("rows");
	String type = request.getParameter("type");
	String value = request.getParameter("value");
	String joindate = request.getParameter("logindate");
	
	
	

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
		if(type == null)
		{
			type = "";
		}
		
		if(value == null || "Search...".equalsIgnoreCase(value))
		{
			value = "";
		}
		if(joindate == null)
		{
			joindate = "";
		}
		count = userServices.getCountOfAppsUser(type, value, joindate);
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
  		
  		StringBuilder sql =new StringBuilder("SELECT ID ,DATE(MODIFIED_TIME) as MODIFIED_TIME, CITY,DEVICE_TYPE ,DOB ,EMAIL ,FB_RELATIONSHIP_STATUS ,FB_USER_ID,"+
  				"FIRST_NAME, INDUSTRY,JOB_TYPE,LOCATION,LOCALITY,NUM_OF_FRIENDS,PUSH_ID,DATE(USER_CREATION_TIME) as USER_CREATION_TIME,USER_LIKE_COUNT,USER_SPAM_COUNT,USER_SCORE,USER_STATUS FROM  USERS");
  		if(!value.trim().equals(""))
  		sql.append(" where ").append(type).append(" like '") .append(value.trim()).append("%' ");
  		if(!joindate.trim().equals("") && value.trim().equals(""))
  			sql.append(" and date(LOGIN_TIME)='").append(joindate.trim()).append("'");
  		else if(joindate.trim().equals("") && !value.trim().equals(""))
  			sql.append(" where date(LOGIN_TIME)='").append(joindate.trim()).append("'");
  		if (nRowsPerPage != 0) {
  			sql.append(" order by id desc ").append(" limit ").append(start).append(" , ")
  					.append(nRowsPerPage);
  		}
List<AppUsers> techList = userServices.getListOfUsers(sql.toString());
%> <page><%=nPageNo%></page> <total><%=total_pages%></total> <records><%=String.valueOf(count)%></records>
<%   for (AppUsers obj : techList) {
	   %><row id="<%=obj.getId()%>">
	   <cell><![CDATA[<%=obj.getStatus() %>]]></cell>
	   <cell><![CDATA[<%=obj.getStatus() %>]]></cell>
	   <cell><![CDATA[<%=obj.getStatus() %>]]></cell>
	   <cell><%=obj.getId() %></cell>
	   <cell><%=obj.getDeviceType().equalsIgnoreCase("0")?"IOS":"ANDROID" %></cell>
	   <cell><%=obj.getName() %></cell>
	   <cell><![CDATA[<%=obj.getEmail()%>]]></cell>
	   <cell><![CDATA[<%=obj.getNumberOfFriends() %>]]></cell>
	   <cell><![CDATA[<%=obj.getDob() %>]]></cell>
	   <cell><![CDATA[<%=obj.getFbUrl()%>]]></cell>
	   <cell><![CDATA[<%=obj.getFbRelactionShipStatus() %>]]></cell>
	   
	    <cell><![CDATA[<%=obj.getJobType() %>]]></cell>
	     <cell><![CDATA[<%=obj.getIndustry() %>]]></cell>
	      <cell><![CDATA[<%=obj.getCity() %>]]></cell>
	      
	   <cell><![CDATA[<%=obj.getLocation() %>]]></cell>
	   
	    <cell><![CDATA[<%=obj.getLocality() %>]]></cell>
	    <cell><![CDATA[<%=obj.getPushId() %>]]></cell>
	    <cell><![CDATA[<%=obj.getLike() %>]]></cell>
	    <cell><![CDATA[<%=obj.getSpam() %>]]></cell>
	    <cell><![CDATA[<%=obj.getUserScore() %>]]></cell>
	    <cell><![CDATA[<%=obj.getUserCreationTime() %>]]></cell>
	    <cell><![CDATA[<%=obj.getModifiedTime() %>]]></cell>
	    <cell><![CDATA[<%=obj.getStatus() %>]]></cell>
	    
	   
	   
	   
</row>
    	   <%}
    	   %>
    	</rows>


