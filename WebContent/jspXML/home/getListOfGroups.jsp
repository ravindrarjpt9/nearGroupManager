
<%@page import="com.nearGroup.modal.Users"%>
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
  		
  		StringBuilder sql =new StringBuilder("SELECT ID ,DATE(MODIFIED_TIME) as MODIFIED_TIME,DISPLAY_NAME,GROUP_CATEGORY ,DATE(GROUP_CREATION_TIME) as GROUP_CREATION_TIME,GROUP_ICON_CATEGORY,GROUP_STATUS,NAME,USERS_COUNT "+
  				" FROM  groups");
  		if(!type.trim().equals(""))
  		sql.append(" where ").append(type).append(" like '") .append(value.trim()).append("%' ");
  			sql.append(" order by id desc");
  		if (nRowsPerPage != 0) {
  			sql.append(" limit ").append(start).append(" , ")
  					.append(nRowsPerPage);
  		}
  		Map<String, Groups> map = userServices.getListOfGroups(sql.toString(),((Users)session.getAttribute("techprofile")).getId());
%> <page><%=nPageNo%></page> <total><%=total_pages%></total> <records><%=String.valueOf(count)%></records>
<%   for (Map.Entry<String,Groups> obj : map.entrySet()) {
	   %><row id="<%=obj.getValue().getId()%>">
	   <cell><%=obj.getValue().getJoinFlag()%></cell>
	   <cell><%=obj.getValue().getId()%></cell>
	   <cell><%=Helper.htmlEscape(obj.getValue().getName())%></cell>
	   <cell><![CDATA[<%=obj.getValue().getCategory()%>]]></cell>
	      <cell><![CDATA[<%=obj.getValue().getIconCategory()%>]]></cell>
	   <cell><![CDATA[<%=obj.getValue().getStatus()%>]]></cell>
	   <cell><![CDATA[<%=obj.getValue().getSystemGroupName()%>]]></cell>
	   <cell><![CDATA[<%=obj.getValue().getUserCount()%>]]></cell>
	   <cell><![CDATA[<%=obj.getValue().getCreationTime()%>]]></cell>
	   <cell><![CDATA[<%=obj.getValue().getModifiedTime()%>]]></cell>
	   
	   
	   
	    
	   
	   
	   
</row>
    	   <%}
    	   %>
    	</rows>


	