
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="https://ersapp.eclinicalworks.com" prefix="ers" %> --%>
<%@page import="java.net.URLEncoder"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9, IE=9" >
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/grid.locale-en.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tooltip.js?"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/jquery.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/ui.jqgrid.css" />


<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/css/control.css" />
<script type="text/javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/shortcut.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.tabSlideOut.v1.3.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/detect_timezone.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.tabs.js"></script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/nearGroup/header.js"></script>

</head>
<body onkeydown="if(event.ctrlKey && event.keyCode==78) {event.cancelBubble = true;event.returnValue = false;  event.keyCode = false; return false;  }" oncontextmenu="return false;" style="overflow: scroll;" >
<div  id="techprofiledata" style="display: none;" title="Tech Profile" >
<form>
  <table class="dialog" style="width: 400px" border="0"> 
  <tr>
    <td style="width: 160px; text-align:right;"><label for="TechProfileType" style="size: 20px;"  >TechProfile Type <b>:</b></label></td>
    <td  colspan="2" ><input type="text" readonly="readonly" style="width: 250px;height: 20px;cursor: default;" class="inputHide" value="" id="ptype"/>
    
</td>
     </tr>
     <tr>
    <td style="width: 160px; text-align:right;"><label for="fname" >User Name(XMPP)<b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"   style="width: 250px;height: 20px; " class="inputHide" value="" id="xmppusername"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="fname" >First Name <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"   style="width: 250px;height: 20px; " class="inputHide" value="" id="fname"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="mname" >Middle Name <b>:</b></label></td>
    <td colspan="2"><input type="text"   style="width: 250px;height: 20px; " class="textBox" value="" id="mname"/></td>
   
  </tr>
 
  <tr>
    <td style="width: 160px; text-align:right;"><label for="lname" >Last Name <b>:</b></label></td>
    <td colspan="2"><input type="text"   style="width: 250px;height: 20px; " class="textBox" value="" id="lname"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="password">Password <b>:</b></label></td>
    <td colspan="2"><input type="text"   style="width: 250px;height: 20px; " class="textBox" value="" id="password"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="email">eMail <b>:</b></label></td>
    <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="textBox"  value=""  id="email"/></td>
    
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="mobile">Mobile <b>:</b></label></td>
    <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="textBox" value="" id="mobile"/></td>
   
  </tr>
  
  
  
  <tr>
    <td style="width: 160px; text-align:right;"><div id="ajaxLoading" style="float: right; margin-right: -97px; margin-top: -187px;" >
                                <%-- <img src="${pageContext.request.contextPath}/css/images/loading75.gif" alt="Loading..."/> --%>
                            </div>
      	<div id="msgbox"></div><label for="createdBy">Created By <b>:</b></label></td>
    <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="inputHide" value="" id="createdBy"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="createdOn">Created On <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly" style="width: 172px;height: 20px;" class="inputHide" value="" id="createdOn"/></td>
  
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="lastUpdateBy">Last Update By <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly" style="width: 172px;height: 20px;" class="inputHide" value="" id="lastUpdateBy"/>
    </td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="lastUpdateOn">Last Update On <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly" style="width: 172px;height: 20px;" class="inputHide" value="" id="lastUpdateOn"/>
</td>
   
    
  </tr>
  
  <tr>
    <td style="width: 160px; text-align:right;"><label for="domainE">Status <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"  style="width: 172px;" class="inputHide" value="" id="status"/>
</td>

    
  </tr>
   
  <tr>
    <td colspan="3" style="text-align: center;">
     <input type="button" class="button"  value="Save" id="savetechprofiledata" onclick="saveTechProfileValue()" style="width: 94px;"/>
   <input type="button" class="button"  value="Cancel" onclick="closeTechProfile()" style="width: 94px; "/>  </td> 
  </tr>
</table>
</form>
</div>
<input type="hidden" id="id" value="${techprofile.id}">
<input type="hidden" id="techPofileType" value="${techprofile.techPofileType}">
<input type="hidden" id="firstName" value="${techprofile.firstName}">
<input type="hidden" id="lastName" value="${techprofile.lastName}">
<input type="hidden" id="middleName" value="${techprofile.middleName}">
<input type="hidden" id="ppassword" value="${techprofile.password}">
<input type="hidden" id="eemail" value="${techprofile.email}">
<input type="hidden" id="mmobile" value="${techprofile.mobile}">
<input type="hidden" id="ccreatedBy" value="${techprofile.createdBy}">
<input type="hidden" id="ccreatedOn" value="${techprofile.createdOn}">
<input type="hidden" id="mmodifyBy" value="${techprofile.modifyBy}">
<input type="hidden" id="mmodifyOn" value="${techprofile.modifyOn}">
<input type="hidden" id="sstatus" value="${techprofile.status}">
			
	<c:url value="/logout.jsp" var="logoutUrl">
	</c:url>		

<%-- <%@ page import="com.eclinicalworks.ers.ui.pojo.*"%> --%>
<div id='log'></div>
  
<div  style="font-family: Helvetica,Arial,Verdana,sans-serif;width: 100%;"  style="float:left" id="header" >
<div style="padding:4px 3px 2px 4px;margin:1px 3px 0px 0px;width:27%px;"><b style="font-size: 16px;">Welcome</b> <a id="techProfileLink" onclick="techProifleLoading();" class="example1tooltip" title="Check your Tech Profile" href="javascript:void(0);" style="text-decoration: none;color: #040660;font-size: 16px;cursor:pointer;" >${techprofile.firstName} ${techprofile.lastName}</a><span id='xmpptechflag' style="font: bold;font-size: 100%;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-weight: bold; " ></span></div>
<div id="logoutid" style="float: right; font-size: 14px; margin-top: -22px;"  ><a href="${logoutUrl}" style="cursor:pointer">Logout</a></div> 

</div>

<p>
   
 
 </body>
 </html>
 