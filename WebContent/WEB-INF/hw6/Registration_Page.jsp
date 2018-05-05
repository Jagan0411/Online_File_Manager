<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Philosopher|Press+Start+2P|Josefin+Slab" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Files &amp; Folders</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<style type="text/css">
body
{
background: #e6edf7;
}
a:link
{
text-decoration: none;
}

h2
{
font-family: 'Philosopher', sans-serif;
border: 1px dotted;
width:30%;
background-color: #9dbbe1;
}

h3
{
font-weight:bold;
font-family: 'Press Start 2P', cursive;
font-size: x-small;
}

#bord
{
border: 1px dotted;
background-color: #9dbbe1;
width: 26%;
}
#s1
{
padding-left: 28px;
}
#s2
{
padding-left: 34px;
}

#s3:hover
{
background-color: #9daaad;
}
#pstyle
{
font-size:xx-small;
font-family: 'Press Start 2P', cursive;
}
</style>

<script >

$(function() {
	$('#s3').click(function() {
		var uname=$('#s1').val();
		var pwd=$('#s2').val();
		
		$.ajax({

			url:"Registration",
			type:"GET",
			data:{
				"uname":uname,
				"pwd":	pwd
			},
			success: function (data) {			
				if(data=="false")
					{
					window.location = "FM_Home";
					}
				else
					{
					$("#msg").text(data);
					}
			} 
			
			});
	});
	
});

</script>

</head>
<body>
<div align="center">
<h2 align="center">Registration Page</h2>
</div>
<h3 align="center">Hello New User, please enter your details here</h3>
<br/>
<div align="center"> 
<div id = "bord">
<font color="red"><label id="msg"></label></font>
<br/>
Username:<span><input id="s1" type='text' name='uname' /></span>
<br />
<br/>
Password:<span><input id="s2" type='password'  name='pwd'></span>
<br />
<br />
<button id="s3">Register</button> 
<br/>
<br/>
</div>
</div>
<p align="center">
<a href="FM_Home">Back</a>
</p>
<br/>
<p align="center"  id="pstyle">${invalid}</p>
<p align="center"  id="pstyle">${same}</p>
<p align="center"  id="pstyle">${blank_p}</p>
</body>
</html>