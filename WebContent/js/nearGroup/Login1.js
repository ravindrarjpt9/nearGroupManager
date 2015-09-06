
	$(function(){
		
		window.history.forward();
	    function noBack() { window.history.forward(); }
	    
		
		 $('#login_id').focus();
		 
	}
	
	
			);
	
	
	
	
	function loginCorp()
	{
		
		
		if(validationForm())
			{
			
			$("#login").attr("disabled", "disabled");
			$("#msgbox").removeClass().addClass('myinfo').text('Validating Your Login..... ').fadeIn(1000);
			document.body.style.cursor = 'wait';
			var encryptedString = $('#login_id').val()+"~"+$('#password').val();
			this.timer = setTimeout(function () {
				$.ajax({
		          	url: '/nearGroupManager/verifying',
		          	data:{jCryption: encryptedString ,encryptData:"true" },
		          	type: 'post',
		   			success: function(msg){
		   				document.body.style.cursor = 'default';
		   				alert($.trim(msg));
 			         if($.trim(msg) == 'null')
				      {
				     window.location = "http://localhost:8080/nearGroupManager/Login.jsp?action=login";
				      }
			           if($.trim(msg) == 'true') 
						{				
							$("#msgbox").html('Login Verified, Logging in.....').addClass('myinfo').fadeTo(900,1,
			                  function()
			                  {
								window.location =
										"http://localhost:8080/nearGroupManager/home.jsp",
										'',
										'toolbar=no,location=no,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes';
			                  });
							
							  }
						  else if($.trim(msg) == "notregistered")
							  {
							  $("#msgbox").html('Profile is not Registered.....').removeClass().addClass('myerror').fadeTo(900,1,
					                  function()
					                  {
										
								  window.location = "http://10.211.39.37:8080/nearGroupManager/techError.jsp",
												'',
												'toolbar=no,location=no,status=yes,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes';
					                  });
									 
							  }
						else if($.trim(msg) == 'false')
							{
							$("#login").removeAttr("disabled");
							$("#msgbox").fadeTo(200,0.1,function() 
		                	{
			                 
			                  $(this).html('Sorry, Wrong Combination Of Username And Password.').removeClass().addClass('myerror').fadeTo(900,1);
			                });
							}
						else if($.trim(msg) == 'databasedown')
						{
						$("#login").removeAttr("disabled");
						$("#msgbox").fadeTo(200,0.1,function() 
	                	{
		                 
		                  $(this).html('Server unreachable right now please try to login from Corp mode').removeClass().addClass('myerror').fadeTo(900,1);
		                });
						}
					}
				
				});
			}, 200);
			return false;
 				

	}
		
		
			
	}
function validationForm()
{
	if($('#login_id').val() == "")
		{
		var txt=document.getElementById("un");
		  txt.innerHTML="<font color=red>&nbsp;Username should not be empty</font>";
		return false;
		}
	else if($('#password').val() == "")
		{
		var txt=document.getElementById("ps");
		  txt.innerHTML="<font color=red> &nbsp;Password should not be empty</font>";
		return false;
		
		}
	return true;
}



