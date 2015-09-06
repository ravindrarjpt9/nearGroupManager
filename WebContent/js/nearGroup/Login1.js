var hashObj = "";
		var password ="";
	$(function(){
		
		
	    
		hashObj = new jsSHA("mySuperPassword", "ASCII");
		password = hashObj.getHash("SHA-512", "HEX");
	 
		 $.jCryption.authenticate(password, "/nearGroupManager/verifying", "/nearGroupManager/verifying",
				
				function() {
					// Authentication failed
					
				}
		); 
		 
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
			var encryptedString = $.jCryption.encrypt($('#login_id').val()+"~"+$('#password').val(), password);
			
			this.timer = setTimeout(function () {
				$.ajax({
		          	url: '/nearGroupManager/verifying',
		          	data:{jCryption: encryptedString ,encryptData:"true" },
		          	type: 'post',
		   			success: function(msg){
		   				document.body.style.cursor = 'default';
		   				
// 			         if($.trim(msg) == 'null')
//				      {
//				     window.location = "http://localhost:8080/nearGroupManager/Login.jsp?action=login";
//				      }
		   				
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
			           else if($.trim(msg) == 'INACTIVE')
						{
						$("#login").removeAttr("disabled");
						$("#msgbox").fadeTo(200,0.1,function() 
	                	{
		                 
		                  $(this).html('Sorry, You have become inactive .Please contact to administrator.').removeClass().addClass('myerror').fadeTo(900,1);
		                });
						}
			           else if($.trim(msg) == 'PasswordRest')
						{
						$("#login").removeAttr("disabled");
						$("#msgbox").fadeTo(200,0.1,function() 
	                	{
		                 
							window.location =
								"http://localhost:8080/nearGroupManager/ResetPassword.jsp",
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



