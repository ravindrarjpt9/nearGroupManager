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
		 $("#loggingdate").datepicker({dateFormat: 'yy-mm-dd',});
			
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
	
	$("#firstName1").removeAttr('disabled','disabled');
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
	
	 if(document.techProfileForm.firstName1.value == "" || document.techProfileForm.firstName1.value == null)
		{
		alert('Please enter the First Name');
		
		return false;
		}
	 
	else if(document.techProfileForm.lastName1.value == "" || document.techProfileForm.lastName1.value == null)
		{
		alert('Please enter the Last Name');
		return false;
		}
	 else if(document.techProfileForm.email1.value == "" || document.techProfileForm.email1.value == null)
	{
	alert('Please enter the email');
	return false;
	}
	else if(document.techProfileForm.mobile1.value == "" || document.techProfileForm.mobile1.value == null)
	{
	alert('Please enter the Mobile No');
	return false;
	}
	
	
	return true;
}
function closeTechDialog()
{
    $("#techprofiledilog").dialog('close');
    CloseDialogFunction();

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
        data:({resp : "save",tid : $("#htechid").val(),pTechProfileType1 : $("#TechProfileType1").val(),firstName1 :$("#firstName1").val() ,middleName1:$("#middleName1").val() ,lastName1 :$("#lastName1").val() , passwordStatus1:$('#checkPassword1').is(':checked'),password1 :$("#password1").val(),email1:$("#email1").val(),mobile1:$("#mobile1").val(),status1:$("#status1").val() }),
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
	$("#TechProfileType1").val('TECH_SUPPORT');
	$("#firstName1").val('');
	$("#middleName1").val('');
	$("#lastName1").val('');
	$("#password1").val('Default@123');
	$("#email1").val('');
	$("#mobile1").val('');
	$("#status1").val('');
	$('#checkPassword1').attr('checked', false);
	
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
    	$("#firstName1").attr('disabled','disabled');
	$("#htechid").val($('#techProfile').jqGrid ('getCell', id, 'tech_id'));
	$("#TechProfileType1").val($('#techProfile').jqGrid ('getCell', id, 'role'));
	$("#firstName1").val($('#techProfile').jqGrid ('getCell', id, 'first_name'));
	$("#middleName1").val($('#techProfile').jqGrid ('getCell', id, 'middle_name'));
	$("#lastName1").val($('#techProfile').jqGrid ('getCell', id, 'last_name'));
	$("#password1").val($('#techProfile').jqGrid ('getCell', id, 'password'));
	$("#email1").val($('#techProfile').jqGrid ('getCell', id, 'email'));
	$("#mobile1").val($('#techProfile').jqGrid ('getCell', id, 'mobile'));
	$("#status1").val($('#techProfile').jqGrid ('getCell', id, 'status'));
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
function getClearSearch()
{
	$("#sLoggedUserLogs").val('First Name');
	$("#loggedUserid").val('');
	$("#loggingdate").val('');
}

function loadUserLogsDetails()
{
	$("#userlogslist").GridUnload();
	var searchType = $("#sLoggedUserLogs").val();
	var searchvalue = $("#loggedUserid").val();
	var loggindate = $("#loggingdate").val();
	jQuery("#userlogslist").jqGrid(
			{
				url : '/nearGroupManager/userLog.do',
				postData: {stypePractice : searchType,sPracticeValue : searchvalue,logindate : loggindate ,handshake : "2ff3db1d48ba025df04d06dfdc008a02"  },
				datatype : 'xml',
				mtype : 'POST',
				colNames : [ 'ID', 'First Name','Last Name', 'Role','Login Time',
						'Logout Time'],
				colModel : [  {
					name : 'id',
					index : 'id',
					align : 'left',
					sortable : false,
					width : '100%'
				}, {
					name : 'firstName',
					index : 'firstName',
					align : 'left',
					sortable : false,
					width : '100%'
				}, {
					name : 'lastName',
					index : 'lastName',
					align : 'left',
					sortable : false,
					width : '140%'
				},  {
					name : 'Role',
					index : 'Role',
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
					name : 'logoutTime',
					index : 'logoutTime',
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

