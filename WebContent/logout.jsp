<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://nearGroup.in" prefix="nearGroup"%>
<c:if test="${techprofile ne null and logId ne null}">
<%new UserServicesImpl().getLogOut((String)session.getAttribute("logId")); %>
<%session.invalidate();%>
<c:redirect url="Login.jsp">
</c:redirect>
</c:if>
<c:if test="${techsupportid eq null and loggedId eq null}">
<c:redirect url="Login.jsp">
</c:redirect>
</c:if>