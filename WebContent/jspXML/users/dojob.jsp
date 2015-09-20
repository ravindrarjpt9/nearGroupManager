
<%@page import="com.nearGroup.ui.server.impl.AppsUsersServicesImpl"%>
<%@page import="com.nearGroup.ui.server.AppsUsersServices"%>
<%@page import="com.nearGroup.modal.Users"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%>
 --%><%!
AppsUsersServices appsUsersServices = new AppsUsersServicesImpl();


%>

<%

String resp = request.getParameter("resp");

 
if("ACTIVE".equalsIgnoreCase(resp) || "BLOCKED".equalsIgnoreCase(resp) || "DELETED".equalsIgnoreCase(resp))
{
	String msg = appsUsersServices.getUpdateUserStatus(request.getParameter("id"),((Users)session.getAttribute("techprofile")).getFirstName()+" "+((Users)session.getAttribute("techprofile")).getLastName(),resp,request.getParameter("pushid"),request.getParameter("message"));
	%><%=msg%><%
	
}else if("DeleteUserFromDb".equalsIgnoreCase(resp))
{
String msg = appsUsersServices.getDeletedUserFromDb(request.getParameter("id"));
%><%=msg%><%
}



// }else if(("ReloadProfile").equalsIgnoreCase(resp))
// {
// 	int msg = appsUsersServices.getReloadTechProfileData(request.getParameter("eManagerIds"),request.getParameter("eManagernames"));

// }else if(("ReloadSingleTechProfile").equalsIgnoreCase(resp))
// {
// 	String data = techProfileService.getReloadSingleTechProfile(((TechProfileEntity)session.getAttribute("techprofile")).geteMUserId(),request.getParameter("timezone"),request.getParameter("emanagername"));

// }
// else if(("updatesometechvalue").equals(resp))
// {
// 	String data = techProfileService.updateTechProfile(((TechProfileEntity)session.getAttribute("techprofile")).geteMUserId(),request.getParameter("emanagername"),request.getParameter("vncurl"),request.getParameter("vncport"),
// 			request.getParameter("sshurl"),request.getParameter("sshport"),request.getParameter("corpUserName"));

// }
// else if("xmppconnection".equals(resp))
// {
// 	int data = techProfileService.getCreateXmppUser(request.getParameter("practiceDetail"));

// }
// else if("xmppdisconnection".equals(resp))
// {
// 	int msg = techProfileService.getDeleteXmppUsers(request.getParameter("practiceDetail"));

// }
// else if("getxmppurldetails".equals(resp))
// {
// 	String data = techProfileService.getXmppURLDetails(request.getParameter("url"));

// }
// else if("xmppUrlUpdateation".equals(resp))
// {
// 	int data = techProfileService.getReCreateNewXmppUser(request.getParameter("practiceDetail"),request.getParameter("newurl"),request.getParameter("xmpphttpport"),
// 			request.getParameter("xmppdomain"),request.getParameter("boshport"),request.getParameter("standardport"));

// }	
// else if("sybchPracticeData".equals(resp))
// {
// 	int data = techProfileService.getSyncPracticeData(request.getParameter("practiceDetail"));

// }
// else if("ReCreateXmppUser".equals(resp))
// {
// 	int data = techProfileService.getRecreateXmppPracticeUsers(request.getParameter("data"));
	
// }
// else if("deleteTech".equalsIgnoreCase(resp))
// {
// 	String status = techProfileService.getDeleteTechProfile(request.getParameter("ids"),((TechProfileEntity)session.getAttribute("techprofile")).geteMUserId(),((TechProfileEntity)session.getAttribute("techprofile")).geteManagerUserName());

// }
%>
