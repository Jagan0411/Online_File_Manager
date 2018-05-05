<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Files &amp; Folders</title>
<style>
#bord
{
border: 1px dotted;
width: 30%; 
background-color: #9dbbe1;
}

a:link
{
text-decoration: none;
}
body{
background: #e6edf7;
}
</style>
</head>
<body>
<h2 align="center">Edit <i>${f_name}</i></h2>
<br>

<div align="center" >
<div id="bord" >

<form action ='Edit_FF' method='post'>
<input type='hidden' name='f_id' value='${f_id}' /> <br />
<input type='hidden' name='old_f_name' value='${old_f_name}' /> <br />
<b>Edit Folder:</b> <input type ='text' name='f_name' value ='${f_name}'> <input type ='submit' name='edit' value='Edit'>
<input type='hidden' name='parent_name' value='${name}' /> <br />
<input type='hidden' name='parent_id' value='${id}' /> <br />
<input type='hidden' name='bool_Folder' value='${bool_Folder}' /> <br />
</form>
<br>
<c:if test="${id == null }">
<a href="Display_FF?f_name=${name}">Back</a>
</c:if>

<c:if test="${id != null }">
<a href="Display_FF?f_id=${id}&f_name=${name}">Back</a>
</c:if>

</div>
</div>
<br/>
<div style="float: right; border: 1px dotted; width: 100px;padding-top: 10px;background-color:#9dbbe1; ">
<p align="center">
<a href="Logout_Pg" style="color: black !important;font-size:small;"><b>Logout</b></a> | <a href="FM_Home" style="color: black !important;font-size:small;"><b>Home</b></a> 
</p>
</div>
</body>
</html>