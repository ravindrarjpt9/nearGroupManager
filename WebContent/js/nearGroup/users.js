$(window).bind('resize', function() {
	if($('#myTabs').width() != null || $('#myTabs').width() != "")
		{
		 $('#appUsersProfile').setGridWidth($('#myTabs').width() - 20, true);
		}
    }).trigger('resize');

function fncOnUsersProfileLoad()
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
				loadUsersDetails();
			} else if(ui.index == 1)
				{
				loadMessagesDetais();
				}
			else if(ui.index == 2)
			{
			loadUserLogsDetails();
			}
		}

	});
}

function loadUsersDetails()
{
	$("#appUsersProfile").GridUnload();
	var searchType = $("#sTypeTechProfile").val();
	var searchvalue = $("#techProfileSearchValu").val();
	var loggindate = $("#loggingdate").val();
	
	jQuery("#appUsersProfile").jqGrid({
		url : '/nearGroupManager/appusers.do',
		postData: {type : searchType,value : searchvalue,logindate : loggindate ,handshake : "2ff3db1d48ba025df04d06dfdc008a02"  },
		datatype : 'xml',
		mtype : 'POST',
		colNames : [ '','','','ID', 'Device Type','Name', 'EMAIL' , 'No Of Friends','DOB','FB','FB RelactionShip Status','Job Type','Industry','City','Location','Locality','pushId','Like','Spam','Score' ,'Created', 'Modified' , 'Status'],
		colModel : [ {
			name : 'block',
			index : 'block',
			align : 'left',
			width :'8',
			formatter:myFormatterForBlock
		
		},{
			name : 'delete',
			index : 'delete',
			align : 'left',
			width :'8',
			formatter:myFormatterForDelate
		
		},{
			name : 'active',
			index : 'active',
			align : 'left',
			width :'8',
			formatter:myFormatterForActive
		
		},{
			name : 'id',
			index : 'id',
			sortable : false,
			align : 'left',
		    hidden:true
		},{
			name : 'deviceType',
			index : 'deviceType',
			align : 'left',
			sortable : false,
			width : '30'
			
		},{
			name : 'firstName',
			index : 'firstName',
			align : 'left',
			sortable : false,
			width : '50'
			
		},{
			name : 'email',
			index : 'email',
			align : 'left',
			sortable : false,
			
			hidden : true
			
		},{
			name : 'noOfFriends',
			index : 'noOfFriends',
			align : 'left',
			sortable : false,
			
			hidden:true
		
		},{
			name : 'dob',
			index : 'dob',
			align : 'left',
			sortable : false,
			hidden:true
			
		},{
			name : 'fbUrl',
			index : 'fbUrl',
			align : 'left',
			sortable : false,
			width : '10',
			formatter: returnHyperLink
		},{
			name : 'relactionShipStatus',
			index : 'relactionShipStatus',
			align : 'left',
			sortable : false,
			
			hidden : true
			
		},{
			name : 'jobType',
			index : 'jobType',
			align : 'left',
			sortable : false,
			
			hidden : true
			
		},{
			name : 'industry',
			index : 'industry',
			align : 'left',
			sortable : false,
			
			hidden:true
			
		},{
			name : 'city',
			index : 'city',
			align : 'left',
			sortable : false,
			width : '40'
			
		},{
			name : 'location',
			index : 'location',
			align : 'left',
			sortable : false,
			width : '60'
			
		},{
			name : 'locality',
			index : 'locality',
			align : 'left',
			sortable : false,
			width : '60'
			
		},{
			name : 'pushId',
			index : 'pushId',
			align : 'left',
			sortable : false,
			
			hidden:true
			
		},{
			name : 'like',
			index : 'like',
			align : 'left',
			sortable : false,
			width : '15'
			
		},{
			name : 'spam',
			index : 'spam',
			align : 'left',
			sortable : false,
			width : '15'
			
		},{
			name : 'score',
			index : 'score',
			align : 'left',
			sortable : false,
			width : '15'
			
		},{
			name : 'createdoN',
			index : 'createdoN',
			align : 'left',
			sortable : false,
			width : '55'
			
		},{
			name : 'modifiedTime',
			index : 'modifiedTime',
			align : 'left',
			sortable : false,
			width : '55'
			
		},{
			name : 'status',
			index : 'status',
			align : 'left',
			sortable : false,
			width : '50'
			
		}],
		pager : jQuery('#userPagers'),
		rowNum : 15,
		paging : true,
		autowidth : true,
		viewrecords : true,
		viewPagerButtons: true,
		multiselect : false,
		height: 350,
		 width: '100%',
		caption:"List of App's Users ",
		ondblClickRow: function(){ 
	        openAppUsersProfileDialog();					
			 },
			 loadComplete : function() {
					var rowIDs = jQuery("#appUsersProfile").getDataIDs();
					for ( var i = 0; i < rowIDs.length; i = i + 1) {
						rowData = jQuery("#appUsersProfile").getRowData(
								rowIDs[i]);
						if (rowData.status == "ACTIVE") {
							jQuery('#appUsersProfile').setCell(rowIDs[i],
									'status', '', {
										'background-color' : '#72F182'
									}, '');
						}else if (rowData.status == "BLOCKED") {
							jQuery('#appUsersProfile').setCell(rowIDs[i],
									'status', '', {
										'background-color' : '#FF8080'
									}, '');
						}else if (rowData.status == "DELETED") {
							jQuery('#appUsersProfile').setCell(rowIDs[i],
									'status', '', {
										'background-color' : '#E60000'
									}, '');
						}
					}
				}
	});
}

	var myFormatterForBlock = function(cellVal,options,rowObject) {
		if(cellVal == 'ACTIVE' || cellVal == 'PENDING')
	    return "<img src='js/images/block.ico' height='20' width='20' class = 'example1tooltip' style='cursor:pointer' alt='my favorite' title = 'Block User' onclick=\"blockUser('"+options.rowId+"')\" >";  
		else
			return "";
	};

	var myFormatterForDelate = function(cellVal,options,rowObject) {
		if(cellVal == 'ACTIVE' || cellVal == 'BLOCKED')
	    return "<img src='js/images/delete.ico' height='20' width='20' class = 'example1tooltip' style='cursor:pointer' alt='my favorite' title = 'Delete User' onclick=\"deleteUser('"+options.rowId+"')\" >";  
		else
			return "";
	};

	var myFormatterForActive = function(cellVal,options,rowObject) {
		if(cellVal == 'DELETED' || cellVal == 'BLOCKED' || cellVal == 'PENDING')
	    return "<img src='js/images/active.ico' height='20' width='20' class = 'example1tooltip' style='cursor:pointer' alt='my favorite' title = 'Active User' onclick=\"activeUser('"+options.rowId+"')\" >";  
		else
			return "";
	};
	
	function closeDialog(vName)
	{
		if(vName == 'Message')
			{
			$("#notifyMessage").dialog('close');
			$("#notifym").val('');
			$("#uid").val('');
			$("#upushid").val('');
			$("#ustatus").val('');
			}
	}
	
	function closeNotifyDialog()
	{
		
		$("#notifym").val('');
		$("#uid").val('');
		$("#upushid").val('');
		$("#ustatus").val('');
	}
function blockUser(id)
{
	$("#notifyMessage").dialog({
		resizable : false,
		height : 200,
		position : "center",
		close : closeNotifyDialog,
		width : 245,
		draggable: false
		
	});
	var pushid = $('#appUsersProfile').jqGrid ('getCell', id, 'pushId');
	$("#uid").val(id);
	$("#upushid").val(pushid);
	$("#ustatus").val('BLOCKED');
	
	
	
		
		
}
function saveNotifyMessage()
{
	
	if($("#uid").val() != '' && $("#upushid").val() != '' && $("#ustatus").val() != '')
		{
		var msg = $("#notifym").val();
	$.ajax({
		type : "POST",
		url : "/nearGroupManager/doAppUsers.do",
		data : ({
			searchType:"wrT@Y&bg#6n",
			resp : $("#ustatus").val(),
			id : $("#uid").val(),
			pushid : $("#upushid").val(),
			message : msg
		}),
		success : function(msg) {
			if ($.trim(msg) != 'succes')
				{
				alert($.trim(msg));
		}
			closeDialog('Message');
			$("#appUsersProfile").trigger("reloadGrid");
		}

	}); 
		}
}

function deleateUserFromDb()
{
	var id=jQuery("#appUsersProfile").jqGrid('getGridParam', 'selrow');
	if(id != null)
		{
	$.ajax({
		type : "POST",
		url : "/nearGroupManager/doAppUsers.do",
		data : ({
			searchType:"wrT@Y&bg#6n",
			resp : "DeleteUserFromDb",
			id : id
			}),
		success : function(msg) {
			if ($.trim(msg) != 'succes')
				{
				alert($.trim(msg));
		}
			closeDialog('Message');
			$("#appUsersProfile").trigger("reloadGrid");
		}

	}); }
	else
		{
		alert('No User is Selected,Please Select the User..!');
		}
}
function deleteUser(id)
{
	$("#notifyMessage").dialog({
		resizable : false,
		height : 200,
		position : "center",
		close : closeNotifyDialog,
		width : 245,
		draggable: false
		
	});
	var pushid = $('#appUsersProfile').jqGrid ('getCell', id, 'pushId');
	$("#uid").val(id);
	$("#upushid").val(pushid);
	$("#ustatus").val('DELETED');
	
}
function activeUser(id)
{
	$("#notifyMessage").dialog({
		resizable : false,
		height : 200,
		position : "center",
		close : closeNotifyDialog,
		width : 245,
		draggable: false
		
	});
	var pushid = $('#appUsersProfile').jqGrid ('getCell', id, 'pushId');
	$("#uid").val(id);
	$("#upushid").val(pushid);
	$("#ustatus").val('ACTIVE');
}

function myFormatterForMyDelete()
{
	
}
function returnHyperLink(cellValue, options, rowdata, action) 
{
	
	
    return "<a target='_blank' href="+cellValue+"><img src='js/images/facebook.png' height='20' width='20' /></a>";
}   
function openAppUsersProfileDialog()
{
	
}
function loadMessagesDetais()
{
	
}
function loadUserLogsDetails()
{
	
}