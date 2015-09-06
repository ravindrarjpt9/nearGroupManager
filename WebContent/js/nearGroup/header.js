
$('.example1tooltip').tooltip({
	borderSize:'1',
	borderColor : '#4297D7',
	tooltipBGColor :  	'#DCECF1'
});

function techProifleLoading()
{
	 $("#ptype").val($("#techPofileType").val());
     $("#fname").val($("#firstName").val());
     $("#mname").val($("#lastName").val());
     $("#lname").val($("#middleName").val());
     $("#password").val($("#ppassword").val());
     $("#email").val($("#eemail").val());
     $("#mobile").val($("#mmobile").val());
     $("#createdBy").val($("#ccreatedBy").val());
     $("#createdOn").val($("#ccreatedOn").val());
     $("#lastUpdateBy").val($("#mmodifyBy").val());
     $("#lastUpdateOn").val($("#mmodifyOn").val());
     $("#status").val($("#sstatus").val());
	$("#techprofiledata").dialog({
		resizable: false,bgiframe: true,height: 425,position: "center",width :420,	modal: true,draggable: false});	 
	
}

function closeTechProfile()
{
	$("#techprofiledata").dialog('close');
}