
$('.example1tooltip').tooltip({
	borderSize:'1',
	borderColor : '#4297D7',
	tooltipBGColor :  	'#DCECF1'
});

function techProifleLoading()
{
	
	$.ajax({
        type: "POST",
        url: "/nearGroupManager/home",
        data:({resp :"loadTechProfile"}),
        success: function (msg)
 	     {
        	
        	$('#ajaxLoading').hide();
       		 var valuesArr = $.trim(msg).split("^");
       		if(valuesArr.length != 1)
       		{
       		
       		$("#ptype").val(valuesArr[1]);
       		$("#xmppusername").val(valuesArr[2]);
            $("#fname").val(valuesArr[3]);
            $("#mname").val(valuesArr[4]);
            $("#lname").val(valuesArr[5]);
            $("#password").val(valuesArr[6]);
            $("#email").val(valuesArr[7]);
            $("#mobile").val(valuesArr[8]);
            $("#createdBy").val(valuesArr[9]);
            $("#createdOn").val(valuesArr[10]);
            $("#lastUpdateBy").val(valuesArr[11]);
            $("#lastUpdateOn").val(valuesArr[12]);
            $("#status").val(valuesArr[13]);
        		}
       		else
       			{
       			window.navigate("SessionExpired.jsp");
       			}
 	     }
        });
    
	 
	$("#techprofiledata").dialog({
		resizable: false,bgiframe: true,height: 425,position: "center",width :420,	modal: true,draggable: false});	 
	
}
function saveTechProfileValue()
{

	$.ajax({
		type : "POST",
		url : "/nearGroupManager/home",
		data : ({
			resp : 'updateProfile',
			id : $("#tid").val(),
			pTechProfileType1 : $("#ptype").val(),
			firstName1 : $("#fname").val(),
			middleName1 : $("#mname").val(),
			lastName1 : $("#lname").val(),
			password1 : $("#password").val(),
			email1 : $("#email").val(),
			mobile1 : $("#mobile").val(),
			status1 : $("#status").val()
			
		}),
		success : function(msg) {
			if ($.trim(msg) != 'update')
				{
				alert('Unable to update your profile.');
		}
			
		}

	});	
}
function closeTechProfile()
{
	$("#techprofiledata").dialog('close');
}