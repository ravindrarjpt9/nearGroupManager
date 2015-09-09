
<%@page import="com.nearGroup.util.Helper"%>
<%@page import="com.nearGroup.modal.Groups"%>
<%@page import="com.nearGroup.ui.server.impl.HomeServicesImpl"%>
<%@page import="com.nearGroup.ui.server.HomeServices"%>

<%@ page contentType="text/xml; charset=ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%> --%>
<%!HomeServices userServices = new HomeServicesImpl();%>
<rows>
<% 
  String sPageNo = request.getParameter("page");
	String sRowsPerPage = request.getParameter("rows");
	String type = request.getParameter("type");
	String value = request.getParameter("value");
	
	
	
	

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
		
		count = userServices.getGroupCount(type,value);
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
  		
  		StringBuilder sql =new StringBuilder("SELECT ID ,MODIFIED_TIME,DISPLAY_NAME,GROUP_CATEGORY ,GROUP_CREATION_TIME ,GROUP_ICON_CATEGORY,GROUP_STATUS,NAME,USERS_COUNT "+
  				" FROM  groups");
  		if(!type.trim().equals(""))
  		sql.append(" where ").append(type).append(" like '") .append(value.trim()).append("%' ").append(" order by id ");
  		else
  			sql.append(" order by id ");
  		if (nRowsPerPage != 0) {
  			sql.append(" limit ").append(start).append(" , ")
  					.append(nRowsPerPage);
  		}
List<Groups> list = userServices.getListOfGroups(sql.toString());
%> <page><%=nPageNo%></page> <total><%=total_pages%></total> <records><%=String.valueOf(count)%></records>
<%   for (Groups obj : list) {
	   %><row id="<%=obj.getId()%>">
	   <cell><%=obj.getId()%></cell>
	   <cell><%=Helper.htmlEscape(obj.getName())%></cell>
	   <cell><![CDATA[<%=obj.getCategory()%>]]></cell>
	      <cell><![CDATA[<%=obj.getIconCategory()%>]]></cell>
	   <cell><![CDATA[<%=obj.getStatus()%>]]></cell>
	   <cell><![CDATA[<%=obj.getSystemGroupName()%>]]></cell>
	   <cell><![CDATA[<%=obj.getUserCount()%>]]></cell>
	   <cell><![CDATA[<%=obj.getCreationTime()%>]]></cell>
	   <cell><![CDATA[<%=obj.getModifiedTime()%>]]></cell>
	   
	   
	   
	    
	   
	   
	   
</row>
    	   <%}
    	   %>
    	</rows>


	