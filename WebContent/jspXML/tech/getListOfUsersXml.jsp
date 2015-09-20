<%@page import="com.nearGroup.modal.Users"%>
<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@page import="com.nearGroup.ui.server.UserServices"%>
<%@ page contentType="text/xml; charset=ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>



<%@ page errorPage="/fail.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%> --%>
<%!UserServices userServices = new UserServicesImpl();%>
<rows>
<% 
  String sPageNo = request.getParameter("page");
	String sRowsPerPage = request.getParameter("rows");
	String techProfileType = request.getParameter("type");
	String searchValue = request.getParameter("value");
	
	
	

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
		if(techProfileType == null)
		{
			techProfileType = "";
		}
		
		if(searchValue == null)
		{
			searchValue = "";
		}
		count = userServices.getTotalTechProfiles(techProfileType,searchValue);
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
  		
  		StringBuilder sql =new StringBuilder("SELECT ID ,FIRST_NAME, MIDDLE_NAME,LAST_NAME ,PASSWORD ,EMAIL ,MOBILE_NUM ,DATE(CREATED_TIME) as CREATED_TIME,"+
  				"CREATED_BY, DATE(MODIFIED_TIME) as MODIFIED_TIME,MODIFY_BY,USER_STATUS,ROLE FROM  USERS");
  		if(!searchValue.trim().equals(""))
  		sql.append(" where ").append(techProfileType).append(" like '") .append(searchValue.trim()).append("%' ");
  		if (nRowsPerPage != 0) {
  			sql.append(" order by id desc ").append(" limit ").append(start).append(" , ")
  					.append(nRowsPerPage);
  		}
ArrayList<Users> techList = userServices.getAllTechProfiles(sql.toString());
%> <page><%=nPageNo%></page> <total><%=total_pages%></total> <records><%=String.valueOf(count)%></records>
<%   for (Users obj : techList) {
	   %><row id="<%=obj.getId()%>">
	   <cell><%=obj.getId() %></cell>
	   <cell><%=obj.getFirstName() %></cell>
	   <cell><%=obj.getMiddleName() %></cell>
	   <cell><![CDATA[<%=obj.getLastName()%>]]></cell>
	   <cell><![CDATA[<%=obj.getEmail() %>]]></cell>
	   <cell><![CDATA[<%=obj.getPassword() %>]]></cell>
	   <cell><![CDATA[<%=obj.getMobile()%>]]></cell>
	   <cell><![CDATA[<%=obj.getCreatedOn() %>]]></cell>
	   
	    <cell><![CDATA[<%=obj.getCreatedBy() %>]]></cell>
	     <cell><![CDATA[<%=obj.getModifyBy() %>]]></cell>
	      <cell><![CDATA[<%=obj.getModifyOn() %>]]></cell>
	      
	   <cell><![CDATA[<%=obj.getStatus() %>]]></cell>
	   
	    <cell><![CDATA[<%=obj.getTechPofileType() %>]]></cell>
	    
	   
	   
	   
</row>
    	   <%}
    	   %>
    	</rows>


