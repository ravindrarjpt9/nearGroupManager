<%@page import="com.nearGroup.modal.Users"%>
<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@page import="com.nearGroup.ui.server.UserServices"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%>
<%!
UserServices  usersServices = new UserServicesImpl();


%>

<%

String resp = request.getParameter("resp");
if(("restPassword").equalsIgnoreCase(resp))
{
	String status = usersServices.resetPassword(((Users)session.getAttribute("techprofile")).getId(),request.getParameter("newPassword"));
	%><%=status%><%
	}
	%>