
<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@page import="com.nearGroup.ui.server.UserServices"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%>
 --%><%!
UserServices  usersServices = new UserServicesImpl();


%>

<%

String resp = request.getParameter("resp");

String id = request.getParameter("tid");if(id == null){id="";}

if(("save").equalsIgnoreCase(resp) && id =="")
{
	String status = usersServices.insertNewTechProfile(request.getParameter("pTechProfileType"),request.getParameter("firstName"),
			request.getParameter("middleName"),request.getParameter("lastName"),request.getParameter("password"),
			request.getParameter("email"),request.getParameter("mobile"),request.getParameter("status"),"LoginUser");
	%><%=status%><%
	
}
// else if(("save").equalsIgnoreCase(resp) && id !="")
// {
	
// 	String status = techProfileService.updateTechProfile(id,request.getParameter("pTechProfileType"),
// 			request.getParameter("pVNCurl"),request.getParameter("pVNCPort"),request.getParameter("pSSHUrl"),request.getParameter("pSSHPort"),
// 			request.getParameter("pDomain"),request.getParameter("tfn"),request.getParameter("tmn"),
// 			request.getParameter("tln"),((TechProfileEntity)session.getAttribute("techprofile")).getTechId(),request.getParameter("corpUserName"));


// }
// else if(("update").equalsIgnoreCase(resp))
// {
// 	String status = techProfileService.updatePracticeRecord(request.getParameter("pid"),request.getParameter("smode"),request.getParameter("email"));

// }
// else if("deleteall".equalsIgnoreCase(resp))
// {

// }
// else if("LoadTechProfile".equalsIgnoreCase(resp))
// {
// 	String msg = userInfo.getUserInfoByUserName(request.getParameter("techName"));

	
// }else if(("ReloadProfile").equalsIgnoreCase(resp))
// {
// 	int msg = techProfileService.getReloadTechProfileData(request.getParameter("eManagerIds"),request.getParameter("eManagernames"));

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
