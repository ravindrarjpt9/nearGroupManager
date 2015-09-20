
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Session Expired</title>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/css/control.css" />
</head>
<body bgcolor="#5C9CCC">
<div  style="text-align: center;padding-top: 100px;padding-bottom: 100px;">
<%session.invalidate(); %>
<h3>Session Expired . . . . <a href="Login.jsp" style="size: 16">Login</a></h3>
  
</div>
</body>
</html>