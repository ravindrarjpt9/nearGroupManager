<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<meta http-equiv="pragma" content="nocache">
<head>

<title></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/nearGroup/tech.js"></script>
<script type="text/javascript">
	
</script>

</head>
<body onload="javascript:fncOnTechProfileLoad();">


	<br>

	<div id="myTabs">
		<ul>
			<li><a href="#awq"><font size="2">Profile</font></a></li>
			<li><a href="#cwq"><font size="2">LOGS</font></a></li>

		</ul>
		<div id="awq" style="height: 525px;">

			<div style="padding-bottom: 4px;">
				<b>Search By</b> <select id="sTypeTechProfile"
					style="width: 130px; height: 25px;">
					<option value="ROLE">Profile Type</option>
					<option value="FIRST_NAME" selected="selected">First
						Name</option>
					<option value="LAST_NAME">Last Name</option>
				</select>&nbsp;<input type="text" id="techProfileSearchValu"
					class="tb11 example1tooltip"
					title="Select search by and press enter to search"
					style="color: #AAAAAA"
					onfocus="if(this.value==this.defaultValue){this.value='';this.style.color='#000000';this.style.fontstyle='normal'}"
					value="Search..." style="width :10%;font-size:14px;height: 17px;"
					onkeydown="if (event.keyCode == 13) { gridReloadTechProfile();}" />
			</div>

			<div id="page11" class="scroll"></div>
			<table id="techProfile" class="scroll"></table>
			<div align="center" style="margin-top: 5px;">
				<input type="button" class="button" name="create"
					value="Create Tech Profile" style="font-size: 11px; width: 140px;"
					onclick="createNewProfile()" />  <input type="button" class="button"
					name="update" value="Update Tech Profile"
					style="font-size: 11px; width: 140px;" onclick="getUpdateProfile()" />
				
			</div>


		</div>


		<div id="cwq" style="height: 525px;">
			<form method=post name="logginuserreport" id="logginuserreport"
				target=_top>
				<div style="padding-top: 1px; padding-bottom: 5px;">
					<b>Search By</b> <select id="sLoggedUserLogs"
						style="width: 130px; height: 25px;">
						<option value="FIRST_NAME" selected="selected">First Name</option>
						 <option value="LAST_NAME">Last Name</option>
						  <option value="ROLE">Role</option>
					</select>&nbsp;<input type="text" id="loggedUserid"
						class="tb11 example1tooltip"
						title="Select search by and press enter to search"
						style="color: #AAAAAA"
						onfocus="if(this.value==this.defaultValue){this.value='';this.style.color='#000000';this.style.fontstyle='normal'}"
						value="Search..." style="width :10%;font-size:14px;height: 17px;"
						onkeydown="if (event.keyCode == 13) { loadUserLogsDetails();}" />
					&nbsp;&nbsp; <label for="enddate"
						style="font-family: Helvetica, Arial, Verdana, sans-serif; color: #000000; text-decoration: none; text-transform: uppercase;"><b>Login
							Time</b></label> <input type="text" id="loggingdate" value=""
						style="width: 7%; height: 18px" class="textBox" />&nbsp;&nbsp; <input
						type="button" class="button" value="Search"
						onclick="loadUserLogsDetails()" style="width: 5%;" />&nbsp; <input
						type="button" class="button" value="Clear"
						onclick="getClearSearch()" style="width: 5%;" />&nbsp;&nbsp;&nbsp;&nbsp;
					
				</div>
				<div id="userlogs" class="scroll"></div>
				<table id="userlogslist" class="scroll"></table>
			</form>
		</div>

	</div>



	<div id="techprofiledilog" style="display: none;"
		title="TechProfile Form">
		<form method="post" name="techProfileForm" id="techProfileFormId">



			<table style="width: 450px;" class="dialog">
				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="TechProfileType" style="size: 20px;">TechProfile
							Type</label><font color="red">*</font></td>
					<td colspan="2"><select id="TechProfileType"
						name="TechProfileType" style="width: 135px; height: 20px;"
						class="textBox">
							<option value="TECH_SUPPORT" selected="selected">TECH_SUPPORT</option>
							<option value="ADMIN">ADMIN</option>
							<option value="SYSTEM_ADMIN">SYSTEM_ADMIN</option>
					</select></td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="firstName">First Name</label><font color="red">*</font></td>
					<td colspan="2"><input type="text" name="firstName"
						id="firstName" style="width: 250px; height: 20px;"
						class="textBox" value=""  /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="middleName">Middle Name</label></td>
					<td colspan="2"><input type="text" name="middleName" id="middleName"
						style="width: 250px; height: 20px;" class="textBox" value=""
						 /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="lastName">Last Name</label><font color="red">*</font></td>
					<td colspan="2"><input type="text" name="lastName"
						id="lastName" style="width: 250px; height: 20px;" class="textBox"
						value="" /></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="password">Password</label><font color="red">*</font></td>
					<td colspan="2"><input type="text" name="password"
						id="password" style="width: 250px; height: 20px;" class="textBox"
						value="Default@123" disabled="disabled" /></td>
					<td><input type="checkbox" class="example1tooltip" name="checkPassword" id = "checkPassword" onclick="changePaswword()" title="Update password"/></td>
				</tr>
				<tr>

					<td style="width: 150px; text-align: right;"><label
						for="email">EMAIL </label><font color="red">*</font></td>
					<td colspan="2"><input type="text" name="email"
						id="email" style="width: 250px; height: 20px;" class="textBox"
						value=""  /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="mobile">Mobile </label><font color="red">*</font></td>
					<td colspan="2"><input type="text" name="mobile"
						id="mobile" style="width: 250px; height: 20px;"
						class="textBox" value=""  /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 150px; text-align: right;"><label
						for="status">STATUS</label><font color="red">*</font></td>
					<td colspan="2"><select id="status" name="status"
						style="width: 135px;" class="textBox">
							<option value="ACTIVE" selected="selected">ACTIVE</option>
							<option value="INACTIVE">INACTIVE</option>
					</select></td>
					<td>&nbsp;</td>

				</tr>
				
				<tr>
					<td colspan="3" style="height: 4px;"></td>

				</tr>
				<tr>
					<td colspan="3"><div style="" align="center">
							<input type="button" class="button" name="save"
								value="Save Tech Profile" onclick="saveTechProfile()"
								style="width: 129px;" /> <input type="reset" class="button"
								name="reset" value="Reset Form" style="width: 113px;"> <input
								type="button" class="button" value="Cancel"
								onclick="closeTechDialog()" style="width: 94px;" />
						</div></td>
					<td>&nbsp;</td>
				</tr>
			</table>

			<input type="hidden" name="htechid" id="htechid" value=""> <input
				type="hidden" name="techFirstName" id="tfn" value=""> <input
				type="hidden" name="techMiddelName" id="tmn" value=""> <input
				type="hidden" name="techLastName" id="tln" value=""> <input
				type="hidden" name="eManagerUserId" id="eid" value=""> <input
				type="hidden" name="techType" id="techType"
				value="SYSTEM_ADMIN">
		</form>
	</div>



</body>
</html>
