 <%@ include file="/WEB-INF/templates/sessionValidation.jsp"%>
<%
String status = request.getParameter("status");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Update <%= status%></title>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/css/control.css" />
</head>
<body bgcolor="#5C9CCC">
<% if(status.equalsIgnoreCase("succes")) {%>
<div  style="text-align: center;padding-top: 100px;padding-bottom: 100px;">
<h3>Your password has been successfully updated. . . . <a href="Login.jsp" style="size: 16">Login</a></h3>
</div>
  <%}
else{%>
<div  style="text-align: center;padding-top: 100px;padding-bottom: 100px;">
<h3>We are unable to rest your password , Please contact to your administrator</h3>
</div>
<%} %>

</body>
</html>