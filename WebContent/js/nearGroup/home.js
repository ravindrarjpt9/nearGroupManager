$(window).bind(
		'resize',
		function() {

			if ($('#cDiv').width() != null) {
				if (parseInt($('#cDiv').width()) != 0) {
					$('#groupList').setGridWidth(
							parseInt($('#cDiv').width()), true);
					$('#topicsLists').setGridWidth(
							parseInt($('#cDiv').width()), true);
					
				}
			}
			
		}).trigger('resize');
var timeOut2 = null;
var gridCount = 0;

function fncHomeOnLoad() {



	loadGroupGrid();
	loadPTopicsGrid();
	
	

}
function searchTopic()
{
	jQuery("#groupList").jqGrid('resetSelection');
	//$("#topicsLists").setGridParam({page : 1}).trigger('reloadGrid');
	getSearchTopicList();
}

function loadGroupGrid()
{
	//$("#groupList").GridUnload();
	var searchType = $("#sTypeH").val();
	var searchvalue = $("#sTypeValue").val();
	jQuery("#groupList").jqGrid(
			{
				url : '/nearGroupManager/group.do',
				postData: {type : searchType,value : searchvalue,handshake : "2ff3db1d48ba025df04d06dfdc008a02"},
				datatype : 'xml',
				mtype : 'POST',
				colNames : ['Join', 'ID', 'Name', 'Category', 'Icon Category',
						'Status', 'System Group Name', 'User Count', 'Creation Time','Modified Time'],
				colModel : [ {
					name : 'join',
					index : 'join',
					align : 'left',
					width :'8',
					formatter:myFormatterForJoinGroup
				
				},{
					name : 'id',
					index : 'id',
					sortable : false,
					align : 'left',
					width : '10'

				}, {
					name : 'name',
					index : 'name',
					align : 'left',
					sortable : false,
					width : '60%'

				}, {
					name : 'Category',
					index : 'Category',
					align : 'left',
					sortable : false,
					width : '40%'

				}, {
					name : 'iconCategory',
					index : 'iconCategory',
					align : 'left',
					sortable : false,
					width : '25%'

				}, {
					name : 'status',
					index : 'status',
					align : 'left',
					sortable : false,
					width : '30%',
					hidden :true

				}, {
					name : 'systemName',
					index : 'systemName',
					sortable : false,
					align : 'left',
					width : '80%'

				}, {
					name : 'userCount',
					index : 'userCount',
					align : 'left',
					sortable : false,
					width : '20%'

				}, {
					name : 'creationTime',
					index : 'creationTime',
					align : 'left',
					sortable : false,
					width : '20%'

				},{
					name : 'modifiedTime',
					index : 'modifiedTime',
					align : 'left',
					sortable : false,
					hidden : false,
					width : '40%'
					

				}],
				pager : jQuery('#group'),
				rowNum : 8,
				paging : true,
				autowidth : true,
				viewrecords : true,
				multiselect : false,
				height : '185',
				loadonce : false,
				
				
				
				
				onRightClickRow: function () {
				    grid.jqGrid('resetSelection');
				    return false;
				},
             
				
				caption : "Group List",
				ondblClickRow: function(){ 
					getSearchTopicList();					
					 },
			});

}

function getSearchTopicList()
{
	var id=$("#groupList").jqGrid('getGridParam', 'selrow');
	$("#topicsLists").jqGrid().setGridParam(
			{
				url : '/nearGroupManager/topic.do',
				postData: {gid : id }
			});
	$("#topicsLists").setGridParam({
		page : 1
	}).trigger('reloadGrid');
	
}

var myFormatterForJoinGroup = function(cellVal,options,rowObject) {
	if(cellVal == '0')
    return "<img src='js/images/user_group.png' height='20' width='20' class = 'example1tooltip' style='cursor:pointer' alt='my favorite' title = 'Join Group Chat' onclick=\"joinGroupChat('"+options.rowId+"')\" >";  
	else
		return "";
};
var myFormatterForJoinTopic = function(cellVal,options,rowObject) {
	if(cellVal == '0')
    return "<img src='js/images/user_group.png' height='20' width='20' class = 'example1tooltip' style='cursor:pointer' alt='my favorite' title = 'Join Topic Chat' onclick=\"joinTopicChat('"+options.rowId+"')\" >";  
	else
		return "";
};
function joinGroupChat(id)
{
	$.ajax({
		type : "POST",
		url : "/nearGroupManager/home",
		data : ({
			searchType:"wrT@Y&bg#6n",
			resp : 'joinGroup',
			id : id
		}),
		success : function(msg) {
			if ($.trim(msg) != 'succes')
				{
				alert('Unable to join this group');
		}
			$("#groupList").trigger("reloadGrid");
		}

	});
}
function joinTopicChat(id)
{
	$.ajax({
		type : "POST",
		url : "/nearGroupManager/home",
		data : ({
			searchType:"wrT@Y&bg#6n",
			resp : 'joinTopic',
			id : id
		}),
		success : function(msg) {
			if ($.trim(msg) != 'succes')
				{
				alert('Unable to join this topic');
		}
			$("#topicsLists").trigger("reloadGrid");
		}

	});
}
function loadPTopicsGrid()
{
	jQuery("#topicsLists").jqGrid(
			{
				url : '/nearGroupManager/topic.do',
				datatype : 'xml',
				mtype : 'POST',
				colNames : [ 'Join','ID', 'Topic Name','Despcription', 'Type','Group Name', 
						'Owner FB', 'Owner Name', 'Like', 'Spam','Creation Time','Modified Time','gid'],
				colModel : [{
					name : 'join',
					index : 'join',
					align : 'left',
					width :'8',
					formatter:myFormatterForJoinTopic
				
				}, {
					name : 'id',
					index : 'id',
					sortable : false,
					align : 'left',
					width : '10%'

				},{
					name : 'topicName',
					index : 'topicName',
					align : 'left',
					sortable : false,
					width : '80'

				}, {
					name : 'Despcription',
					index : 'Despcription',
					align : 'left',
					sortable : false,
					width : '90	'

				}, {
					name : 'type',
					index : 'type',
					align : 'left',
					sortable : false,
					width : '60'

				}, {
					name : 'groupName',
					index : 'groupName',
					align : 'left',
					sortable : false,
					width : '60'

				}, {
					name : 'ownerFb',
					index : 'ownerFb',
					align : 'left',
					sortable : false,
					width : '50',
					hidden :true

				}, {
					name : 'ownerName',
					index : 'ownerName',
					sortable : false,
					align : 'left',
					width : '40'

				}, {
					name : 'like',
					index : 'like',
					align : 'left',
					sortable : false,
					width : '15'

				}, {
					name : 'spam',
					index : 'spam',
					align : 'left',
					sortable : false,
					width : '15'

				},{
					name : 'CreationTime',
					index : 'CreationTime',
					align : 'left',
					sortable : false,
					hidden : false,
					width : '30'
					

				},{
					name : 'modifiedTime',
					index : 'modifiedTime',
					align : 'left',
					sortable : false,
					hidden : false,
					width : '40%',
					hidden:true
					

				},{
					name : 'gid',
					index : 'gid',
					align : 'left',
					sortable : false,
					hidden : true
					

				}],
				pager : jQuery('#topics'),
				rowNum : 8,
				paging : true,
				autowidth : true,
				viewrecords : true,
				multiselect : false,
				height : '185',
				loadonce : false,
				width : '90%',
				caption : "Topic List"
			});	
}


//function loadMyLocalityList()
//{
//	jQuery("#localityList").jqGrid(
//			{
//				url : '/nearGroupManager/home.do',
//				datatype : 'xml',
//				mtype : 'POST',
//				colNames : [ 'Id', 'City', 'Locality',
//						'Location', 'Creation Time', 'Creation By','Modified Time','Modified By' ],
//				colModel : [ {
//					name : 'id',
//					index : 'id',
//					sortable : false,
//					align : 'left'
//
//				}, {
//					name : 'city',
//					index : 'city',
//					sortable : false,
//					align : 'left'
//
//				}, {
//					name : 'Locality',
//					index : 'Locality',
//					align : 'left',
//					sortable : false
//
//				}, {
//					name : 'Location',
//					index : 'Location',
//					align : 'left',
//					sortable : false
//
//				}, {
//					name : 'creationTime',
//					index : 'creationTime',
//					align : 'left',
//					sortable : false,
//					hidden : true
//				}, {
//					name : 'creationBy',
//					index : 'creationBy',
//					align : 'left',
//					sortable : false,
//					hidden : true
//
//				},{
//					name : 'modifiedTime',
//					index : 'modifiedTime',
//					align : 'left',
//					sortable : false,
//					hidden : true
//
//				},{
//					name : 'modifiedBy',
//					index : 'modifiedBy',
//					align : 'left',
//					sortable : false,
//					hidden : true
//
//				} ],
//				pager : jQuery('#locality'),
//				rowNum : 20,
//				paging : true,
//				autowidth : true,
//				viewrecords : true,
//				multiselect : false,
//				height : '100%',
//				loadonce : false,
//
//				ondblClickRow : function() {
//					loadJobsDataInDialog();
//					$("#showJobs").dialog({
//						resizable : false,
//						height : 430,
//						position : "center",
//						close : closemyjobdialog,
//						width : 545,
//						modal : false,
//						draggable: false
//					});
//				},
//				
//				
//			});

//}