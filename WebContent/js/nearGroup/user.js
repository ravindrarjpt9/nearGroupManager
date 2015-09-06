$(window).bind('resize', function() {
	if($('#myTabs').width() != null || $('#myTabs').width() != "")
		{
		 $('#techProfile').setGridWidth($('#myTabs').width() - 20, true);
		}
    }).trigger('resize');
function fncOnTechProfileLoad() 
	{
		 loadTabs();
		 $('.example1tooltip').tooltip({
				borderSize:'1',
				borderColor : '#4297D7',
				tooltipBGColor :  	'#DCECF1'
			}); 
		 
			
}

function loadTabs()
{
	$('#myTabs').tabs({
		show : function(event, ui) {
			if (ui.index == 0) {
				loadFirstTabFirstGrid();
			} else if(ui.index == 1)
				{
				loadUserLogsDetails();
				}
		}

	});
}
function createNewProfile()
{
	
	
	$("#techprofiledilog").dialog({
		resizable: false,
		bgiframe: true,
		height: 310,
		position: "center",
		close: CloseDialogFunction,
		width :485,
		modal: true,
		draggable: false
	});
	
	
}
function formValidation()
{
	
	 if(document.techProfileForm.firstName.value == "" || document.techProfileForm.firstName.value == null)
		{
		alert('Please enter the First Name');
		
		return false;
		}
	 
	else if(document.techProfileForm.lastName.value == "" || document.techProfileForm.lastName.value == null)
		{
		alert('Please enter the Last Name');
		return false;
		}
	 else if(document.techProfileForm.email.value == "" || document.techProfileForm.email.value == null)
	{
	alert('Please enter the email');
	return false;
	}
	else if(document.techProfileForm.mobile.value == "" || document.techProfileForm.mobile.value == null)
	{
	alert('Please enter the Mobile No');
	return false;
	}
	
	
	return true;
}
function closeTechDialog()
{
    $("#techprofiledilog").dialog('close');
    //document.getElementById("goid").style.display = 'block';
    $('#TechProfileType').removeAttr('disabled');

}
function getUpdateProfile()
{
	
	var id=jQuery("#techProfile").jqGrid('getGridParam', 'selrow');
	if(id == null || id=='')
		{
		alert('No Tech Profile is Selected,Please Select the Tech..!');
		return false;
		}
	else
		{
		openTechProfileDialog();
		}
		
}
function saveTechProfile()
{
	
if(formValidation())
	{
	
	$.ajax({
        type: "POST",
        url: "/nearGroupManager/userDo.do",
        data:({resp : "save",tid : $("#htechid").val(),pTechProfileType : $("#TechProfileType").val(),firstName :$("#firstName").val() ,middleName:$("#middleName").val() ,lastName :$("#lastName").val() , password :$("#password").val(),email:$("#email").val(),mobile:$("#mobile").val(),status:$("#status").val() }),
        success: function (msg)
 	     {
        	if($.trim(msg) == "update")
        		{
        	
        		closeTechDialog();
        		$('#techProfile').trigger("reloadGrid");
        		}
        	else if($.trim(msg) == "insert")
        		{
        		
        		closeTechDialog();
        		$('#techProfile').trigger("reloadGrid");
        		}
        	else if($.trim(msg) == "duplicate")
        		{
        		alert('Profile is already Registered');
        		}
        	else
        		{
        		closeTechDialog();
        		alert('Something is Wrong in Java Layer');
        		}
        	
        }
      });
	
	
	}

}


function CloseDialogFunction()
{
	$("#htechid").val('');
	$("#TechProfileType").val('TECH_SUPPORT');
	$("#eid").val('');
	$("#tfn").val('');
	$("#tmn").val('');
	$("#tln").val('');
	$("#eUser_Name").val('');
	$("#vncUrl").val('');
	$("#vncPort").val('');

	$("#location").val('');
	$("#department").val('');
	$("#teamlead").val('');
	
	$("#sshUrl").val('');
	$("#sshPort").val('');
	
	
	$("#CorpUserName1").val('');
	$("#domainE").val('BILLING');
	$("#lastupdate").val('');	
	$('#TechProfileType').removeAttr('disabled');
}
function validate()
{
	var profile = jQuery("#techProfile").jqGrid('getGridParam','selrow'); 
	if(!profile)
		{
		alert('No Profile is Selected,Please Select the Profile');
		return false;
		}
	return true;
}
function openTechProfileDialog()
{
	
	
	if($("#techType").val() == 'SYSTEM_ADMIN'){
		//$('#TechProfileType').attr('disabled', 'disabled');
	//document.getElementById("goid").style.display = 'none';
	var id=jQuery("#techProfile").jqGrid('getGridParam', 'selrow');
    if(id != null)
	{
	$("#htechid").val($('#techProfile').jqGrid ('getCell', id, 'tech_id'));
	$("#profile_type").val($('#techProfile').jqGrid ('getCell', id, 'role'));
	$("#firstName").val($('#techProfile').jqGrid ('getCell', id, 'first_name'));
	$("#middleName").val($('#techProfile').jqGrid ('getCell', id, 'middle_name'));
	$("#lastName").val($('#techProfile').jqGrid ('getCell', id, 'last_name'));
	$("#password").val($('#techProfile').jqGrid ('getCell', id, 'password'));
	$("#email").val($('#techProfile').jqGrid ('getCell', id, 'email'));
	$("#mobile").val($('#techProfile').jqGrid ('getCell', id, 'mobile'));
	$("#status").val($('#techProfile').jqGrid ('getCell', id, 'status'));
	$("#techprofiledilog").dialog({resizable: false,bgiframe: true,height: 310,width :485,close: CloseDialogFunction,position: "center",modal: false,draggable: false});
	}
	}
}
function changePaswword()
{

if($('#checkPassword').is(':checked'))
	{
	$("#password").val("Default@123");
	}
else
	{
	$("#password").val($('#techProfile').jqGrid ('getCell', $("#techProfile").jqGrid('getGridParam', 'selrow'), 'password'));
	}
}
function gridReload()
{
	
	
	$("#techProfileSearchValu").val('');
	gridReloadTechProfile();
	
}
function deleateProfile()
{
	var id = jQuery("#techProfile").jqGrid('getGridParam','selarrrow');
	if(id == null || id == '')
		{
		alert("select any one of tech profile...");
		}
	else if(new String(id).split(",").length != 1)
		{
		alert("Only one profile can be delete");
		}
	else{
	$.ajax({
        type: "POST",
        url: "/oneclicksupport/techDo.do",
        data:({resp :"deleteTech",ids:$.trim(id.toString())}),
        success: function (msg)
 	     {
       	 if($.trim(msg) == 'succes')
       		 {
       		 alert('Successfully deleted');
       		$('#techProfile').trigger("reloadGrid");
       		 }
       	 else
       		 {
       		 alert('Something is Wrong in DAO Layer');
       		
       		 }
        }
      });
	}	
	
}
function ReloadTechProfileData()
{
	
	var id = jQuery("#techProfile").jqGrid('getGridParam','selarrrow');
	var emanagername = getEmanagernamebyId(id);
	if(id == null || id == '')
		{
		alert('No Profile is Selected,Please Select the Profile..!');
		}
	else
		{
		$.blockUI({ message: '<h3>We are processing your request.  Please be patient</h3><p><img src="css/images/loading.gif" /><p><h4 style="color: red">**Do not refresh this page**</h4>' }); 
	$.ajax({
        type: "POST",
        url: "/oneclicksupport/techDo.do",
        data:({resp : "ReloadProfile",eManagerIds : id.toString(),eManagernames : emanagername.toString(),handshake : "2ff3db1d48ba025df04d06dfdc008a02"}),
        success: function (msg)
 	     {
        	
        	 $.unblockUI();
       	 if(parseInt($.trim(msg)) >= 0)
       		 {
       		 alert($.trim(msg) +" Techprofile has been Updated");
       		 $('#techProfile').trigger("reloadGrid");
       		 }
       	 else
       		 {
       		 alert('Something is Wrong in DAO Layer');
       		 
       		 }
        }
      });
		}
	
}
function gridReloadTechProfile()
{
	var searchTypeTechProfile = jQuery("#sTypeTechProfile").val();
	var techProfileValue = jQuery("#techProfileSearchValu").val();
	jQuery("#techProfile").jqGrid().setGridParam(
			{
				url : "/nearGroupManager/userList.do",
						postData: {type : searchTypeTechProfile,value : techProfileValue ,handshake : "2ff3db1d48ba025df04d06dfdc008a02"}
			});
	$("#techProfile").setGridParam({page:1}).trigger('reloadGrid');
	
}
function loadFirstTabFirstGrid() {
	jQuery("#techProfile").jqGrid({
		url : '/nearGroupManager/userList.do',
		datatype : 'xml',
		mtype : 'POST',
		colNames : [ 'ID',  'First Name', 'Middle Name','Last Name' , 'EMAIL' , 'Password','Mobile' ,'Created Time','Created By','Modified By', 'Modified Time' , 'Status' ,'Role'],
		colModel : [ {
			name : 'tech_id',
			index : 'tech_id',
			sortable : false,
			align : 'left',
		    width : '30%'
		},{
			name : 'first_name',
			index : 'first_name',
			align : 'left',
			sortable : false,
			width : '80%'
			
		},{
			name : 'middle_name',
			index : 'middle_name',
			align : 'left',
			sortable : false,
			width : '80%'
			
		},{
			name : 'last_name',
			index : 'last_name',
			align : 'left',
			sortable : false,
			width : '80%'
			
		},{
			name : 'email',
			index : 'email',
			align : 'left',
			sortable : false,
			width : '150%'
		
		},{
			name : 'password',
			index : 'password',
			align : 'left',
			sortable : false,
			width : '90%',
			hidden:true
			
		},{
			name : 'mobile',
			index : 'mobile',
			align : 'left',
			sortable : false,
			width : '90%'
			
		},{
			name : 'createdTime',
			index : 'createdTime',
			align : 'left',
			sortable : false,
			width : '100%'
			
		},{
			name : 'createdBy',
			index : 'createdBy',
			align : 'left',
			sortable : false,
			width : '100%'
			
		},{
			name : 'modifiedBy',
			index : 'modifiedBy',
			align : 'left',
			sortable : false,
			width : '105%'
			
		},{
			name : 'modifiedTime',
			index : 'modifiedTime',
			align : 'left',
			sortable : false,
			width : '100%'
			
		},{
			name : 'status',
			index : 'status',
			align : 'left',
			sortable : false,
			width : '50%'
			
		},{
			name : 'role',
			index : 'role',
			align : 'left',
			sortable : false,
			width : '135%'
			
		}],
		pager : jQuery('#page11'),
		rowNum : 10,
		paging : true,
		autowidth : true,
		viewrecords : true,
		viewPagerButtons: true,
		multiselect : false,
		height: 250,
		 width: '100%',
		caption:"List of Tech Profile",
		ondblClickRow: function(){ 
	        openTechProfileDialog();					
			 },
			 loadComplete : function() {
					var rowIDs = jQuery("#techProfile").getDataIDs();
					for ( var i = 0; i < rowIDs.length; i = i + 1) {
						rowData = jQuery("#techProfile").getRowData(
								rowIDs[i]);
						if (rowData.xmpp_status == "Active") {
							jQuery('#techProfile').setCell(rowIDs[i],
									'status', '', {
										'background-color' : '#72F182'
									}, '');
						}
					}
				}
	});

}
function loadUserLogsDetails()
{
	var searchType = $("#sLoggedUserLogs").val();
	var searchvalue = $("#loggedUserid").val();
	var loggindate = $("#loggingdate").val();
	jQuery("#userlogslist").jqGrid(
			{
				url : '/oneclicksupport/uLoggedList.do',
				postData: {stypePractice : searchType,sPracticeValue : searchvalue,logindate : loggindate ,timezone : timezonename,handshake : "2ff3db1d48ba025df04d06dfdc008a02"  },
				datatype : 'xml',
				mtype : 'POST',
				colNames : [ 'Domain', 'Machine Name','Tech Name', 'Login Time','Login Mode',
						'Logout Time','Logout Mode'],
				colModel : [  {
					name : 'domain',
					index : 'domain',
					align : 'left',
					sortable : false,
					width : '100%'
				}, {
					name : 'machinename',
					index : 'machinename',
					align : 'left',
					sortable : false,
					width : '100%'
				}, {
					name : 'techname',
					index : 'techname',
					align : 'left',
					sortable : false,
					width : '140%'
				},  {
					name : 'logintime',
					index : 'logintime',
					align : 'left',
					width : '140%',
					sortable : false,
				}, {
					name : 'loginMode',
					index : 'loginMode',
					align : 'left',
					width : '140%',
					sortable : false,
				}, {
					name : 'logouttime',
					index : 'logouttime',
					align : 'left',
					width : '140%',
					sortable : false,
				},{
					name : 'logoutMode',
					index : 'logoutMode',
					align : 'left',
					width : '140%',
					sortable : false,
				}],
				pager : jQuery('#userlogs'),
				rowNum : 17,
				paging : true,
				autowidth : true,
				viewrecords : true,
				multiselect : false,
				height : '100%',
				caption:"Logs"
					});
	
}

