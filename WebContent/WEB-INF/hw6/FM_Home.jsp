<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="https://fonts.googleapis.com/css?family=Philosopher|Press+Start+2P|Josefin+Slab" rel="stylesheet">

<title>Files &amp; Folders</title>
<style type="text/css">
h2
{
font-family: 'Philosopher', sans-serif;
border: 1px dotted;
width:50%;
background-color: #9dbbe1;
}

a:link 
{
text-decoration:none;
}
body
{
background: #e6edf7;
}

#bord
{
border: 1px dotted;
background-color: #9dbbe1;
width: 26%;
}

#right
{
padding-left: 85px;
}

#left
{
padding-left: 65px;
}

#pstyle
{
font-size:x-small;
font-family: 'Press Start 2P', cursive;
}

#ps
{
font-weight:bold;
font-family: 'Josefin Slab', serif;
font-size: large;
}

.b:hover
{
  background-color: #9daaad;
}

</style>
</head>
<body>
<div align="center">
<h2 class="w3-container w3-center w3-animate-zoom">Welcome to Online Folder Manager</h2>
</div>
<br/>
<p align="center" class="w3-center w3-animate-top" id="pstyle" ><b>Are you a/an??</b></p>
<br/>

<div align="center" class="w3-animate-bottom">

<div id="bord">

<form action="FM_Home" method="post">

<p id="ps">
New User
<span id="left">
Existing User
</span>
</p>

<p>
<input type="submit" name="button" value="New" title="Register" class="b" />
<span id="right">
<input type="submit" name="button" value="Login" title="Login" class="b"/>
</span>
</p>

</form>

</div>
</div>
<br/>
<p align="center" class="w3-center w3-animate-bottom" id="pstyle">${msg}</p>
<p align="center" class="w3-center w3-animate-bottom" id="pstyle">${logout}</p>
</body>
</html>