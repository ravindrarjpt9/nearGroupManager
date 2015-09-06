
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

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/ers/header.js"></script>

 --%>
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
    <td style="width: 160px; text-align:right;"><label for="eFirstName" >First Name <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"   style="width: 250px;height: 20px; " class="inputHide" value="" id="fname"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="eLastName" >Last Name <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"   style="width: 250px;height: 20px; " class="inputHide" value="" id="lname"/></td>
   
  </tr>
 
  <tr>
    <td style="width: 160px; text-align:right;"><label for="eUserName" >eManager User Name <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"  style="width: 250px;height: 20px; " class="inputHide" value="" id="emuname"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="corpUserName">Corp-U-Name <b>:</b></label></td>
    <td colspan="2"><input type="text"  readonly="readonly" style="width: 250px;height: 20px; " class="inputHide" value="" id="corpUserName"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="vncUrl">VNC URL <b>:</b></label></td>
    <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="textBox"  value=""  id="vncurl"/></td>
    
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="vncPort">VNC PORT <b>:</b></label></td>
    <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="textBox" value="" id="vncport"/></td>
   
  </tr>
  
  <tr>
    <td style="width: 160px; text-align:right;"><label for="sshUrl">SSH URL <b>:</b></label></td>
    <!-- <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="textBox" value="" id="sshurl"/></td> -->
    
		  <td colspan="2">
		  	<select id="sshurl" style="width: 100%; height: 25px;" >
				<%-- <c:forEach items="${ers:sshurls()}" var="url">
					<option value="${url}">${url}</option>
				</c:forEach> --%>
			</select>
		  </td>
 </tr>
  
  <tr>
    <td style="width: 160px; text-align:right;"><div id="ajaxLoading" style="float: right; margin-right: -97px; margin-top: -187px;" >
                                <img src="${pageContext.request.contextPath}/css/images/loading75.gif" alt="Loading..."/>
                            </div>
      	<div id="msgbox"></div><label for="sshPort">SSH PORT <b>:</b></label></td>
    <td colspan="2"><input type="text"  style="width: 250px;height: 20px; " class="textBox" value="" id="sshport"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="sshPort">Location <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly" style="width: 172px;height: 20px;" class="inputHide" value="" id="hLocation"/></td>
  
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="activeInactive">Department <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly" style="width: 172px;height: 20px;" class="inputHide" value="" id="hDepartment"/>
    </td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="teshStatus">Team Lead <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly" style="width: 172px;height: 20px;" class="inputHide" value="" id="hTeamLead"/>
</td>
   
    
  </tr>
  
  <tr>
    <td style="width: 160px; text-align:right;"><label for="domainE">Last Update <b>:</b></label></td>
    <td colspan="2"><input type="text" readonly="readonly"  style="width: 172px;" class="inputHide" value="" id="lastdate"/>
</td>

    
  </tr>
   
  <tr>
    <td colspan="3" style="text-align: center;">
     <input type="button" class="button"  value="Save" id="savetechprofiledata" onclick="saveTechProfileValue()" style="width: 94px;"/>
 <input type="button" class="button example1tooltip" id="reloadtechid" value="Reload" onclick="ReloadTechProfile()" style="width: 94px; " title="Reload your Profile from eManager."/>
  <input type="button" class="button"  value="Cancel" onclick="closeTechProfile()" style="width: 94px; "/>  </td> 
  </tr>
</table>
</form>
</div>
<%-- <input type="hidden" id="emanagerid" value="${techprofile.eMUserId}">
 <input type="hidden" id="xmppserverip" value="<%=((PracticeEntity)session.getAttribute("practiceinfo")).getXmpp_domain()%>"></input>
    <input type="hidden" name="xmpp_practice_user" id="xmpp_practice_user" value="${practiceinfo.eManagerPracticeId}">
     <input type="hidden" name="xmpp_tech_user" id="xmpp_tech_user" value="${techprofile.eMUserId}">
      <input type="hidden" name="xmpp_tech_password" id="xmpp_tech_password" value="<%=((TechProfileEntity)session.getAttribute("techprofile")).getXmpp_password()%>">
       <input type="hidden" name="xmpp_bosh_port" id="xmpp_bosh_port" value="<%=((PracticeEntity)session.getAttribute("practiceinfo")).getXmpp_bosh_port()%>">
        <input type="hidden" name="xmpp_domain" id="xmpp_domain" value="<%=((PracticeEntity)session.getAttribute("practiceinfo")).getXmpp_domain()%>">
 --%>     
 <div class="slide-out-div" style="z-index: 10000;display: none;">
        <a class="handle" href="http://link-for-non-js-users" id="feedbacklink">FeedBack</a>
       <div id="feedback">

		<form >
			<h2><b>We would like to hear your feedback on new eRS 3.0</b></h2>
			<!-- <p><label>Name: </label><input type="text" id="feedbackname" /></p> -->
			<p><label>Email: </label><input type="text" id="feedbackemail" style="color: #AAAAAA " onfocus="if(this.value==this.defaultValue){this.value='';this.style.fontstyle='normal';this.style.color = '#000000';}" value="Your email or TL email"/></p>
			
			<p><label>Subject: </label><input type="text" id="feedbacksubject" style="color: #AAAAAA " onfocus="if(this.value==this.defaultValue){this.value='';this.style.fontstyle='normal';this.style.color = '#000000';}" value="FeedBack or Error"/></p>
			<p><label>Message: </label><textarea id="feedbackmessage"></textarea></p>
			
			<p><input type="button" value="Send&raquo;" class="btn" onclick="submitFeedback()"/><div id="ajaxLoadingFeedBack" style="float: right; margin-right: 155px; margin-top: -24px;display: none;" >
                                <img src="${pageContext.request.contextPath}/css/images/feedback-loader.gif" alt="wait..."/>
                            </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span id="feedbackmsg"></span></p>
			
		</form>
		
	</div>
    </div>

<div id="practicelsit" style="display: none;" title="List of Practices">
<div id="myFavPracticeab" >
			<ul>
			   <li><a href="#c1"><font size="2">Open Ticket Practices</font></a></li>
			   <li><a href="#c2"><font size="2">Open Task Practices</font></a></li>
				<li><a href="#c"><font size="2">Practices</font></a></li>
				<li><a href="#d"><font size="2">My Practice</font></a></li>
				 <li><a href="#e"><font size="2">Favorite </font></a></li>
				 <li><a href="#f"><font size="2">History</font></a></li>
			</ul>
			
			<div id="c1" style="height: 340px;">

 <select name="practiceSearch5" id="practicesearchtypeId5" style="width: 20%;height: 25px;">
  <option value="ticketid" selected="selected">Ticket ID</option>
  <option value="PRACTICE_NAME" >Practice Name</option>
  <option value="APU_ID">APU ID</option>
   </select>
<input type="text" id="sPracticeName5" class="tb11 example1tooltip" style="width: 64.5%"  onkeydown="if (event.keyCode == 13) { searchOpenTicketPractice();}" title="Press Enter to Search">
<input type="button" value= "Refresh" onclick="searchOpenTicketPractice();" style="width: 10%" class="example1tooltip" title="Click to search...">
<div id="openticketPracticepages" class="scroll" ></div>
<table id="openticketPracticeList" class="scroll"></table>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Please double click on the Practice</span></div> </div>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Shortcut Ctrl+Alt+O</span></div> </div>

</div>

<div id="c2" style="height: 340px;">

 <select name="practiceSearch7" id="practicesearchtypeId7" style="width: 20%;height: 25px;">
  <option value="ppd.id" selected="selected">Task ID</option>
  <option value="u.ClientName" >Practice Name</option>
  <option value="u.APUId">APU ID</option>
  </select>
<input type="text" id="sPracticeName7" class="tb11 example1tooltip" style="width: 64.5%"  onkeydown="if (event.keyCode == 13) { searchOpenTaskPractice();}" title="Press Enter to Search">
<input type="button" value= "Refresh" onclick="searchOpenTaskPractice();" style="width: 10%" class="example1tooltip" title="Click to search...">
<div id="opentaskPracticepages" class="scroll" ></div>
<table id="opentaskPracticeList" class="scroll"></table>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Please double click on the Practice</span></div> </div>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Shortcut Ctrl+Alt+T</span></div> </div>

</div>

			<div id="c" style="height: 340px;">

 <select name="practiceSearch" id="practicesearchtypeId" style="width: 20%;height: 25px;">
  <option value="PRACTICE_NAME" selected="selected">Practice Name</option>
  <option value="APU_ID">APU ID</option>
   </select>
<input type="text" id="sPracticeName" class="tb11 example1tooltip" style="width: 64.5%"  onkeydown="if (event.keyCode == 13) { searchPractice();}" title="Press Enter to Search">
<input type="button" value= "Refresh" onclick="searchPractice();" style="width: 10%" class="example1tooltip" title="Click to search...">

<div id="pages" class="scroll" ></div>
<table id="practiceList" class="scroll"></table>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Please double click on the Practice</span></div> </div>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Shortcut Ctrl+Alt+P</span></div> </div>

</div>
<div id="d" style="height: 340px;">
 <select name="practiceSearch3" id="practicesearchtypeId3" style="width: 20%;height: 25px;">
  <option value="PRACTICE_NAME" selected="selected">Practice Name</option>
  <option value="APU_ID">APU ID</option>
   </select>
<input type="text" id="sPracticeName3" class="tb11 example1tooltip" style="width: 64.5%"  onkeydown="if (event.keyCode == 13) { searchMyPractice();}" title="Press Enter to Search">
<input type="button" value= "Refresh" onclick="searchMyPractice();" style="width: 10%" class="example1tooltip" title="Click to search...">

<div id="MyPracticepages" class="scroll" ></div>
<table id="MypracticeList" class="scroll"></table>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Please double click on the Practice</span></div> </div>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Shortcut Ctrl+Alt+M</span></div> </div>

</div>

<div id="e" style="height: 340px;">
<select name="practiceSearch1" id="practicesearchtypeId1" style="width: 20%;height: 25px;">
  <option value="PRACTICE_NAME" selected="selected">Practice Name</option>
  <option value="APU_ID">APU ID</option>
   </select>
<input type="text" id="sPracticeName1" class="tb11 example1tooltip" style="width: 64.5%"  onkeydown="if (event.keyCode == 13) { searchMyFavPractice();}" title="Press Enter to Search">
<input type="button" value= "Refresh" onclick="searchMyFavPractice();" style="width: 10%" class="example1tooltip" title="Click to search...">

<div id="MyFavpages" class="scroll" ></div>
<table id="MyFavpracticeList" class="scroll"></table>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Please double click on the Practice</span></div> </div>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Shortcut Ctrl+Alt+F</span></div> </div>

</div>
<div id="f" style="height: 340px;">
<select name="practiceSearch2" id="practicesearchtypeId2" style="width: 20%;height: 25px;">
  <option value="PRACTICE_NAME" selected="selected">Practice Name</option>
  <option value="APU_ID">APU ID</option>
   </select>
<input type="text" id="sPracticeName2" class="tb11 example1tooltip" style="width: 64.5%"  onkeydown="if (event.keyCode == 13) { searchMyHistoryPractice();}" title="Press Enter to Search">
<input type="button" value= "Refresh" onclick="searchMyHistoryPractice();" style="width: 10%" class="example1tooltip" title="Click to search...">

<div id="MyHistorypages" class="scroll" ></div>
<table id="MyHistorypracticeList" class="scroll"></table>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Please double click on the Practice</span></div> </div>
<div style="margin-top : 7px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;" > <div style="color: red">* <span style="color: #B94A48;"> Shortcut Ctrl+Alt+H</span></div> </div>

</div>

</div>
<FONT SIZE="5.5" FACE="courier" COLOR=black><MARQUEE WIDTH=100% BEHAVIOR=SCROLL DIRECTION=RIGHT >Press enter to search...</MARQUEE></FONT>

</div>
			
	<c:url value="/logout.jsp" var="logoutUrl">
	<c:param name="EParam">${login}</c:param>
	</c:url>		

<%-- <%@ page import="com.eclinicalworks.ers.ui.pojo.*"%> --%>
<div id='log'></div>
  
<div  style="font-family: Helvetica,Arial,Verdana,sans-serif;width: 100%;"  style="float:left" id="header" >
<div style="padding:4px 3px 2px 4px;margin:1px 3px 0px 27px;width:27%px;">&nbsp;&nbsp;&nbsp;<b style="font-size: 16px;">Welcome</b> <a id="techProfileLink" onclick="techProifleLoading();" class="example1tooltip" title="Check your Tech Profile" href="javascript:void(0);" style="text-decoration: none;color: #040660;font-size: 16px;cursor:pointer;" >${techprofile.techFName} ${techprofile.techLName} (${techprofile.eMUserId}) </a><span id='xmpptechflag' style="font: bold;font-size: 100%;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-weight: bold; " ></span></div>
<div id="logoutid" style="float: right; font-size: 14px; margin-top: -22px;"  ><a href="${logoutUrl}" style="cursor:pointer">Logout</a></div> 

</div>

<p>
   
 
 </body>
 </html>
 