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
			if (parseInt($('#homeTabs').width()) != null) {
				if ($('#homeTabs').width() != 0) {
					$('#localityList').setGridWidth($('#homeTabs').width() - 30);
					
				}
			}
		}).trigger('resize');
var timeOut2 = null;
var gridCount = 0;

function fncHomeOnLoad() {



	loadGroupGrid();
	loadPTopicsGrid();
	loadTabs();
	$("#os").multiselect({
		selectedList: 2
		});

}

function loadGroupGrid()
{
	$("#groupList").GridUnload();
	var searchType = $("#sTypeH").val();
	var searchvalue = $("#sTypeValue").val();
	jQuery("#groupList").jqGrid(
			{
				url : '/nearGroupManager/group.do',
				postData: {type : searchType,value : searchvalue,handshake : "2ff3db1d48ba025df04d06dfdc008a02"},
				datatype : 'xml',
				mtype : 'POST',
				colNames : [ 'ID', 'Name', 'Category', 'Icon Category',
						'Status', 'System Group Name', 'User Count', 'Creation Time','Modified Time'],
				colModel : [ {
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
					width : '70%'

				}, {
					name : 'Category',
					index : 'Category',
					align : 'left',
					sortable : false,
					width : '50%'

				}, {
					name : 'iconCategory',
					index : 'iconCategory',
					align : 'left',
					sortable : false,
					width : '40%'

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
					width : '70%'

				}, {
					name : 'userCount',
					index : 'userCount',
					align : 'left',
					sortable : false,
					width : '30%'

				}, {
					name : 'creationTime',
					index : 'creationTime',
					align : 'left',
					sortable : false,
					width : '40%'

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
             
				
				caption : "Group List"
			});

}
function loadPTopicsGrid()
{
	jQuery("#topicsLists").jqGrid(
			{
				url : '/nearGroupManager/group.do',
				datatype : 'xml',
				mtype : 'POST',
				colNames : [ 'ID', 'Topic Name','Despcription', 'Type','Group Name', 
						'Owner FB', 'Owner Name', 'Like', 'Spam','Creation Time','Modified Time','gid'],
				colModel : [ {
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
					width : '110%'

				}, {
					name : 'Despcription',
					index : 'Despcription',
					align : 'left',
					sortable : false,
					width : '170%'

				}, {
					name : 'type',
					index : 'type',
					align : 'left',
					sortable : false,
					width : '80%'

				}, {
					name : 'groupName',
					index : 'groupName',
					align : 'left',
					sortable : false,
					width : '100%'

				}, {
					name : 'ownerFb',
					index : 'ownerFb',
					align : 'left',
					sortable : false,
					width : '50%'

				}, {
					name : 'ownerName',
					index : 'ownerName',
					sortable : false,
					align : 'left',
					width : '80%'

				}, {
					name : 'like',
					index : 'like',
					align : 'left',
					sortable : false,
					width : '20%'

				}, {
					name : 'spam',
					index : 'spam',
					align : 'left',
					sortable : false,
					width : '20%'

				},{
					name : 'CreationTime',
					index : 'CreationTime',
					align : 'left',
					sortable : false,
					hidden : false,
					width : '40%'
					

				},{
					name : 'modifiedTime',
					index : 'modifiedTime',
					align : 'left',
					sortable : false,
					hidden : false,
					width : '40%'	
					

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
function loadTabs() {
	$('#homeTabs').tabs({
		show : function(event, ui) {
			if(ui.index == 0) {
				loadMyLocalityList();
			}
		}

	});

	$('.example1tooltip').tooltip({
		borderSize : '1',
		borderColor : '#4297D7',
		tooltipBGColor : '#DCECF1'
	});
}

function loadMyLocalityList()
{
	jQuery("#localityList").jqGrid(
			{
				url : '/nearGroupManager/home.do',
				datatype : 'xml',
				mtype : 'POST',
				colNames : [ 'Id', 'City', 'Locality',
						'Location', 'Creation Time', 'Creation By','Modified Time','Modified By' ],
				colModel : [ {
					name : 'id',
					index : 'id',
					sortable : false,
					align : 'left'

				}, {
					name : 'city',
					index : 'city',
					sortable : false,
					align : 'left'

				}, {
					name : 'Locality',
					index : 'Locality',
					align : 'left',
					sortable : false

				}, {
					name : 'Location',
					index : 'Location',
					align : 'left',
					sortable : false

				}, {
					name : 'creationTime',
					index : 'creationTime',
					align : 'left',
					sortable : false,
					hidden : true
				}, {
					name : 'creationBy',
					index : 'creationBy',
					align : 'left',
					sortable : false,
					hidden : true

				},{
					name : 'modifiedTime',
					index : 'modifiedTime',
					align : 'left',
					sortable : false,
					hidden : true

				},{
					name : 'modifiedBy',
					index : 'modifiedBy',
					align : 'left',
					sortable : false,
					hidden : true

				} ],
				pager : jQuery('#locality'),
				rowNum : 20,
				paging : true,
				autowidth : true,
				viewrecords : true,
				multiselect : false,
				height : '100%',
				loadonce : false,

				ondblClickRow : function() {
					loadJobsDataInDialog();
					$("#showJobs").dialog({
						resizable : false,
						height : 430,
						position : "center",
						close : closemyjobdialog,
						width : 545,
						modal : false,
						draggable: false
					});
				},
				
				
			});

}