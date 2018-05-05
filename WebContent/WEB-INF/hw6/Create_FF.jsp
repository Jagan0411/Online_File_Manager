<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Files &amp; Folders</title>
<style>
a 
{
text-decoration:none;
}
body{
background: #e6edf7;
}

#bord
{
border: 1px dotted;
background-color: #9dbbe1;
width: 30%;
}
</style>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script>

$(function () {

	$('#create').click(function() {
		
		var f_name=$('#f_name').val();
		var f_id=$("input[name='f_id']").val();
		var name=$("input[name='name']").val();
		
		if(f_id == undefined || name == undefined)
			{
			f_id = null;
			name = null;
			}
		
		alert(f_name+" "+f_id+" "+name);
		
		 $.ajax({

			url:"Create_FF",
			type:"POST",
			data:{
				"f_name":f_name,
				"f_id":	f_id,
				"name": name
			},
			success: function (data) {			
				if(data=="false")
					{
						if(f_id == null || name== null)
						{
							window.location = 'Display_FF';
						}
						else
						{
							window.location = 'Display_FF?f_id='+f_id+'&f_name='+name;
						}
					}
			} 
			
			});
	});
		
});

</script>

</head>
<body>

<c:if test="${name!= null}">
<h2 align="center">Create here in <i>${name}</i></h2>
<br>
</c:if>

<c:if test="${name == null}">
<h2 align="center">Create here in <i>Home Page</i></h2>
<br>
</c:if>

<div align="center" >
<div id="bord" >

<br>
<b>New Folder:</b> <input type ='text' id ='f_name'>
<c:if test="${id!= null}">
<input type ='hidden' name='f_id' value='${id}' id='f_id'>
<input type ='hidden' name='name' value='${name}' id='name'>
</c:if>		

<br/>
<button id="create">Create</button>

<br/>
<c:if test="${id == null }">
<a href="Display_FF?f_name=${name}">Back</a>
</c:if>

<c:if test="${id != null }">
<a href="Display_FF?f_id=${id}&f_name=${name}">Back</a>
</c:if>

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