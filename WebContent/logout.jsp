<%@page import="com.nearGroup.modal.Users"%>
<%@page import="com.nearGroup.ui.server.impl.UserServicesImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://nearGroup.in" prefix="nearGroup"%>
<c:if test="${techprofile ne null}">
<%new UserServicesImpl().getLogOut(String.valueOf(((Users)session.getAttribute("techprofile")).getId())); %>
<%session.invalidate();%>
<c:redirect url="Login.jsp">
</c:redirect>
</c:if>
<c:if test="${techsupportid eq null and loggedId eq null}">
<c:redirect url="Login.jsp">
</c:redirect>
</c:if>