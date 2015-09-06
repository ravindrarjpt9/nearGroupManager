<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${techprofile eq null }">
<c:redirect url="/SessionExpired.jsp"></c:redirect>
</c:if>
<%@page import="com.nearGroup.modal.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Set New Password</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" href="css/tableLayout.css">
<script type="text/javascript">
	function verfiypass() {
		var emailpattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if (document.getElementById("mailid").value == "") {
			alert("Enter Email ID ");
			document.getElementById("mailid").focus();
			return false;
		} else if (!emailpattern.test(document.getElementById("mailid").value)) {
			alert("please enter valid e-mail address");
			document.getElementById("mailid").focus();
			return false;
		}
		if (document.getElementById("pwd").value == "") {
			alert("Enter Password");
			document.getElementById("pwd").focus();
			return false;
		} else if (document.getElementById("cpwd").value == "") {
			alert("Confirm the Password");
			document.getElementById("cpwd").focus();
			return false;
		} else if (document.getElementById("pwd").value != document
				.getElementById("cpwd").value) {
			alert("Password and Confirm password do not match");
			document.getElementById("pwd").focus();
			return false;
		} else {
			return true;
		}
	}
	function RestPassword()
	{
		if(verfiypass())
			{
		$.ajax({
	        type: "POST",
	        url: "/nearGroupManager/home",
	        data:({resp :"restPassword",newPassword:$("#pwd").val()}),
	        success: function (msg)
	 	     {
	       	 if($.trim(msg) == 'success')
	       		 {
	       		window.location =
					"http://localhost:8080/nearGroupManager/passwordStatus.jsp?status=succes",
					'',
					'toolbar=no,location=no,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes';

	       		 }
	       	 else
	       		 {
	       		 alert('Something is Wrong in DAO Layer');
	       		 }
	        }
	      });
			}
	
	}
</script>
</head>
<body>
	
		<table class="tablelayout curve">
			<caption>Set new Credentials</caption>
			<tr>
				<td><label class="required">Enter your Registered mail id</label></td>
				<td><input type="text" name="mailid" id="mailid" disabled="disabled" value="<%=((Users)session.getAttribute("techprofile")).getEmail()%>"/></td>
			</tr>
			<tr>
				<td><label class="required">Enter new Password</label></td>
				<td><input type="password" name="pwd" id="pwd"></td>
			</tr>
			<tr>
				<td><label class="required">Confirm Password</label></td>
				<td><input type="password" name="cpwd" id="cpwd"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit"
					value="submit" onclick="RestPassword()" /></td>
			</tr>
		</table>
	
</body>
</html>