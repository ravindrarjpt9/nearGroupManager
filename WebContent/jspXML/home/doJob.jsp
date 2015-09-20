<%@page import="com.nearGroup.ui.server.impl.GroupChatServicesImpl"%>
<%@page import="com.nearGroup.ui.server.GroupChatServices"%>
<%@page import="com.nearGroup.modal.Users"%>
<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@page import="com.nearGroup.ui.server.UserServices"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%>
<%!
UserServices  usersServices = new UserServicesImpl();
GroupChatServices chatServices = new GroupChatServicesImpl();


%>

<%

String resp = request.getParameter("resp");
if(("restPassword").equalsIgnoreCase(resp))
{
	String status = usersServices.resetPassword(((Users)session.getAttribute("techprofile")).getId(),request.getParameter("newPassword"),((Users)session.getAttribute("techprofile")).getFirstName());
	%><%=status%><%
	}
else if("joinGroup".equalsIgnoreCase(resp))
{
	String status = chatServices.joinGroupChat(request.getParameter("id"),((Users)session.getAttribute("techprofile")).getId(),((Users)session.getAttribute("techprofile")).getFirstName());
	%><%=status%><%
}
else if("joinTopic".equalsIgnoreCase(resp))
{
	String status = chatServices.joinTopicChat(request.getParameter("id"),((Users)session.getAttribute("techprofile")).getId(),((Users)session.getAttribute("techprofile")).getFirstName());
	%><%=status%><%
}
else if("updateProfile".equalsIgnoreCase(resp))
{
	String status = usersServices.updateTechProfile(request.getParameter("tid"),request.getParameter("pTechProfileType1"),request.getParameter("firstName1"),
			request.getParameter("middleName1"),request.getParameter("lastName1"),request.getParameter("passwordStatus1"),request.getParameter("password1"),
			request.getParameter("email1"),request.getParameter("mobile1"),request.getParameter("status1"),((Users)session.getAttribute("techprofile")).getFirstName()+" "+((Users)session.getAttribute("techprofile")).getLastName());
 	%><%=status%><%
}
else if("loadTechProfile".equalsIgnoreCase(resp))
{
	String status = usersServices.getUserProfile(((Users)session.getAttribute("techprofile")).getId());
	%><%=status%><%
}
	%>