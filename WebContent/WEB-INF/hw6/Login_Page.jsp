<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Philosopher|Press+Start+2P|Josefin+Slab" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Files &amp; Folders</title>
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
font-size:x-small;
font-family: 'Press Start 2P', cursive;
}
</style>
</head>
<body>
<div align="center">
<h2 align="center">Login Page</h2>
</div>
<br/>
<h3 align="center">Hello, please enter your details here</h3>
<br/>
<div align="center"> 
<div id = "bord">
<form action='Login_Pg' method='get'>
<br/>
Username:<span id="s1"><input type='text' name='uname' /></span>
<br />
<br/>
Password:<span id="s2"><input type='password' name='pwd'></span>
<br />
<br />
<input type='submit' name='login' value='Login' id="s3"/> 
<br/>
<br/>
</form>
</div>
</div>
<p align="center">
<a href="FM_Home">Back</a>
</p>
<br/>
<p align="center"   id="pstyle">${invalid}</p>
<p align="center"   id="pstyle">${blank_u}</p>
<p align="center"   id="pstyle">${blank_p}</p>
</body>
</html>