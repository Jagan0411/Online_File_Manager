<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Files &amp; Folders</title>
<style>
body{
background: #e6edf7;
}
a:LINK 
{
text-decoration: none;	
}
#bord
{
border: 1px dotted;
background-color: #9dbbe1;
width: 30%;
}
</style>
</head>
<body>

<c:if test="${name!= null}">
<h2 align="center">Upload here in <i>${name}</i></h2>
<br>
</c:if>

<c:if test="${name == null}">
<h2 align="center">Create here in <i>Home Page</i></h2>
<br>
</c:if>

<div align="center" >
<div id="bord" >
<br/>
<form action='Upload_FF' method='post' enctype='multipart/form-data'>
File: <input type='file' name='file' />
<c:if test="${id!=null}">
    	
    <input type='hidden' name='f_id' value='${id}' />
    <input type='hidden' name='parent' value='${name}' />
    	
</c:if>    	
<br/>
<br/>
<input type='submit' name='upload' value='Upload' />

</form>
<br/>
<a href="Display_FF">Back</a>
</div>
</div>
<br/>
<div style="float: right; border: 1px dotted; width: 100px;background-color:#9dbbe1; ">
<p align="center">
<a href="Logout_Pg" style="color: black !important;font-size:small;"><b>Logout</b></a> | <a href="FM_Home" style="color: black !important;font-size:small;"><b>Home</b></a> 
</p>
</div>
</body>
</html>