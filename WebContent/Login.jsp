
<!DOCTYPE>
<html>
<head>

<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/LoginStyle1.css" />
<script src="${pageContext.request.contextPath}/js/jquery.tabSlideOut.v1.3.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/js/css/control.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.tooltip.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/nearGroup/Login1.js"></script>
 
<style type="text/css">
body
{
background-color:#DCECF1;
}
</style>

<title>Login</title>
</head>
<body  onunload="" onkeydown="if(event.ctrlKey && event.keyCode==78) {event.cancelBubble = true;event.returnValue = false;  event.keyCode = false; return false;  }" oncontextmenu="return false;">

 <div id = "main" >
    <div id="login_box">
      <div id="login_header" style="font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;">
            Login Credential
      </div>
      
      <div id="form_val">      
        <div class="label" style="font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;">User Name&nbsp;&nbsp;&nbsp;:</div>
        <div class="control"><input type="text" name="login_id" id="login_id" value="" />
       </div>
        
        <div class="label" style="font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;">Password  &nbsp;&nbsp;&nbsp;&nbsp;:</div>
        <div class="control"><input type="password" name="password" id="password" value="" onkeydown="if (event.keyCode == 13) { loginCorp();}"/>
        
        </div>
        
        <div style="clear:both;height:0px;"></div>
    
      	<div id="msgbox" style="font-family: Lucida Grande,Lucida Sans,Arial,sans-serif;"></div>
      	
      </div>
      
      <div id="login_footer">
        <label>
        <input type="button" name="login" id="login" value="Login" style="width: 100px;" onclick="loginCorp();"/>
        </label>
      </div>
    </div>
</div>
 
    
 


 
</body>

</html>