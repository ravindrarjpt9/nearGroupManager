<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://nearGroup.in" prefix="ers" %>
<%@page import="java.util.ArrayList"%>



<!DOCTYPE html>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9, IE=9" >
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
 <title>Tech Support</title>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/css/jquery.multiselect.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.contextmenu.js"></script>
<%-- 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/deploy/flXHR.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/strophe.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/strophe.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/strophe.flxhr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ers/xmpp-connection.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ers/xmpp-ersid-updates.js"></script> --%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/nearGroup/home.js"></script>


 
<script type="text/javascript">
$(document).ready(function() {
	fncHomeOnLoad();
});
</script>

</head>
<body  style="overflow: scroll;" onkeydown="if(event.ctrlKey && event.keyCode==78) {event.cancelBubble = true;event.returnValue = false;  event.keyCode = false; return false;  }" oncontextmenu="return false;" oncontextmenu="return false;">

<div style="padding-top: 3px;min-width: 830px;table-layout: fixed; ">

<b>Search By</b>
<select id="sTypeH" style="width: 18%x; height: 25px;" >
   <option value="DISPLAY_NAME" selected="selected">Name</option>
   <option value="GROUP_CATEGORY" >Category</option>
    
 </select>&nbsp;<input type="text" class="tb11 example1tooltip" id="sTypeValue"  style="font-style: italic; width: 9%;font-size:14px;height: 14.5px;" onfocus="if(this.value==this.defaultValue){this.value='';this.style.fontstyle='normal'}" value="Search..." onkeydown="if (event.keyCode == 13) { loadGroupGrid();}" title="Press Enter to Search"/>
   <b>All Topics</b>
   <input type="checkbox" name="allTopics" id="allTopics" onclick ="searchTopic()"/>
    <input type="button" class="button" value="Search Group" style="width: 7%" onclick="loadGroupGrid()"/>  
   <input type="button" class="button" value="Clear Search" style="width: 7%;height: 27px;" onclick="clearsearchfilter()"/>
 
</div>
<div id = "pDiv" class="content" style="margin-top: 4px;">

<div style="width: 100%;float: left;" id="cDiv" class="left" >
 <div id="group" class="scroll"></div>
<table id="groupList" class="scroll"></table>
 <hr>	
				<div id="topics" class="scroll" ></div>
				<table id="topicsLists" class="scroll"></table>
</div>
 <!-- <div id="homeTabs" style="width:43.5%;height: 570px; float: right;" class="right11">
			<ul>
			    <li><a href="#abb"><font size="2">Locality</font></a></li>
				
				<li><a href="#cbb"><font size="2">Auto Upgrade</font></a></li>
				
				
			</ul>
			
			<div id="abb" >
			<div id="locality" class="scroll" ></div>
				<table id="localityList" class="scroll" ></table>
			</div>
			
			</div>   -->
</div>
 

<div id="button" align="center">

  
 </div>
<br />
 <input type="hidden" name="ersPracticeId" id="ersPId" value="${techprofile.id}">
<input type="hidden" name="userType" id="userType" value="${techprofile.techPofileType}">

 
 

<!-- <div  id="showJobs" style="display: none;font-size: 20px;" title="JOB DETAILS" >
	    <table style="width: 495px; " class="dialog">
<tr>
<td style="width: 125px;text-align:center;" ><label for="machine_name" style="size: 20px;"><b>Machine Name</b></label></td>
<td style="width: 370px;"><input type="text" id="machine_name" style="height: 21px; width: 350px" class="inputHide" readonly="readonly"></td>
</tr>
<tr>
<td style="width: 116px;text-align:center;"><label for="date_time" style="size: 20px;"><b>Date Time(UTC)</b></label></td>
<td style="width: 370px;"><input type="text" id="date_time" style="height: 21px; width: 350px"  class="inputHide" readonly="readonly"></td>
</tr>
<tr>
<td style="width: 116px;text-align:center;"><label for="job_name" style="size: 20px;"><b>Job Name</b></label></td>
<td style="width: 370px;"><input type="text" id="job_name" style="height: 21px; width: 350px" class="inputHide" readonly="readonly" ></td>
</tr>
<tr>
<td style="width: 116px;text-align:center;vertical-align: top;"><label for="message" style="size: 20px;vertical-align: top;"><b>Message</b></label></td>
<td style="width: 370px;"><textarea rows="17" cols="62" id="message"  style="" readonly="readonly" class="inputHide"></textarea></td>
</tr>

<tr>
<td>&nbsp;</td>
<td style="text-align: right;"><input type="button" id="rdpConnect" class="button" value="Connect" onclick="connectRdpWebConnection();" style="width: 110px;display: none; "/>
<input type="button" class="button" value="Close" onclick="closeDialog('msg')" readonly="readonly" style="width: 81px; "/>&nbsp;&nbsp;&nbsp;</td>
</tr>
</table> 
</div> -->
<!-- <div  id="practicPassword" style="display: none;font-size: 12px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-weight: bold;z-index: 10000" title="Practice Security Check"  >

	<label for="practicePassword"><B>Practice(Support Login) Password:</B></label><br>
	<input type="password" id="practicePassword" value="" style="width: 160px;height: 20px;" class="textBox" onkeypress="if (event.keyCode == 13){PracticeSecurityPassword();}"></input>
	 <input type="button" class="button" id="passwordok" value="OK" onclick="PracticeSecurityPassword()" style="width: 50px; " />
    <input type="button" class="button" value="Cancel" onclick="closePasswordChackingDialog()" style="width: 60px; "/> 
   <form id= "formtwo">
    <input type="hidden" name="jobType" id="jobType" value="">
    <input type="hidden" name="jobId" id="jobId" value="">
    <input type="hidden" name="ipaddress" id="ipaddress" value="">
    <input type="hidden" name="portNom" id="portNom" value="">
    <input type="hidden" name="userName" id="userName" value="">
    <input type="hidden" name="password" id="password" value="">
    <input type="hidden" name="machinename" id="machinename" value="">
     <input type="hidden" name="machineNameXmpp" id="machineNameXmpp" value="">
      <input type="hidden" name="vncDirectConnect" id="vncDirectConnect" value="">
    
    </form>
    </div> -->
    <!-- <div  id="notifyMessage" style="display: none;font-size: 13px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-weight: bold"  title="Send Notification Message">
    
    <form id="formthree">
    <label for="notifym"><b>Enter Notification Message:</b></label><p>
    <textarea rows="6" cols="30" id="notifym" class="textBox" style="resize:none;"></textarea>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   &nbsp; <input type="button" class="button"  value="OK" onclick="saveNotifyMessage()" style="width: 50px; "/>
    <input type="button" class="button" value="Cancel" onclick="closeDialog('Notify')" style="width: 60px; "/>
    </form>
</div> -->
<%--     <div  id="machineUpdate" style="display: none;font-size: 13px;font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;font-weight: bold"  title="Update Machine Information">
    
    <form id="formforth">
 <table class="dialog" style="width: 400px" border="0"> 
  <tr>
    <td style="width: 160px; text-align:right;"><label for="machinenameD" style="size: 20px;"  >Machine Name <b>:</b></label></td>
    <td  colspan="1" ><input type="text" readonly="readonly" style="width: 250px;height: 20px;cursor: default;" class="inputHide" value="" id="machinenameD"/>
    <input type="hidden" name="updatetedersid" id="updatetedersid" value="">
</td>
     </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="customnameD" >Custom Name <b>:</b></label></td>
    <td colspan="1"><input type="text" class="textBox" style="width: 250px;height: 20px; " value="" id="customnameD"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;"><label for="ipaddressD" >IP Address <b>:</b></label></td>
    <td colspan="1"><input type="text" readonly="readonly"   style="width: 250px;height: 20px; " class="inputHide" value="" id="ipaddressD"/></td>
   
  </tr>
 
  <tr>
    <td style="width: 160px; text-align:right;"><label for="osD" >OS <b>:</b></label></td>
    <td colspan="1"><input type="text" readonly="readonly"  style="width: 250px;height: 20px; " class="inputHide" value="" id="osD"/></td>
   
  </tr>
  <tr>
    <td style="width: 160px; text-align:right;vertical-align: top;"><label for="comdescD" >Description <b>:</b></label></td>
    <td colspan="1"><textarea rows="4" cols="38" id="comdescD" name="comdescD"  class="textBox" style="resize:none;"></textarea>
    </td>
   
  </tr>
 <tr>
 <td colspan="2" style="text-align: center;">
      <input type="button" class="button example1tooltip" id="updatemachineinfo" value="Save" onclick="UpdateMachineInfo()" style="width: 94px;display: <%= ((TechProfileEntity)session.getAttribute("techprofile")).getTechProfileType().equalsIgnoreCase("Admin") || ((TechProfileEntity)session.getAttribute("techprofile")).getTechProfileType().equalsIgnoreCase("SYSTEM_ADMIN") ? "inline":"none"%>; " title="Update the Machine Information"/>
      <input type="button" class="button example1tooltip" id="disablemachine" value="Delete" onclick="DeleteMachineInfo()" style="width: 94px;display: <%= ((TechProfileEntity)session.getAttribute("techprofile")).getTechProfileType().equalsIgnoreCase("Admin") || ((TechProfileEntity)session.getAttribute("techprofile")).getTechProfileType().equalsIgnoreCase("SYSTEM_ADMIN") ? "inline":"none"%>; " title="Delete this machine from this Practice"/> 
     <input type="button" class="button"  value="Cancel" onclick="closeMachineDialog()" style="width: 94px; "/>
 
 </td>
 </tr>
 </table>
     </form>
</div>
 --%>




</body>
</html>