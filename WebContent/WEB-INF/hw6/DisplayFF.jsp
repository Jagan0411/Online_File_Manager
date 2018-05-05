<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Files &amp; Folders</title>
<style>
body 
{ 
background: #e6edf7 !important;  
} 

a:LINK 
{
text-decoration: none;
}
 
.center
{
margin-left:auto;
margin-right:auto;
text-align: center;
} 

#operations 
{
border-left: 1px dotted;
}

#CU
{
width: 25%;
border-bottom: 1px dotted;
border-top: 1px dotted;
border-right: 1px dotted;
}
 
table 
{
border-collapse:collapse;
width: 40%;
border-spacing: 10px;
background-color: #9dbbe1 !important;
}

table tr:NOT(:FIRST-CHILD):hover 
{
background-color:#a9e8f9;
}

table th
{
text-align:center;
background-color: #d9dadb;
}
                
table td {
border-left: 1px dotted;
border-right: 1px dotted;
text-align: left;
}

table td:first-child {
border-left: none;
}

table td:last-child {
border-right: none;
}

table th
{
border-bottom: 1px dotted;
}

</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<h2 align="center">Folder Manager</h2>
<br>
<c:choose>
	
	<c:when test="${id == null }">
		
		<table id="CU">
		<tr id="operations">
		<td><a href ='Create_FF' title="Create Folder" style="color:black !important;"><i class="material-icons" style="font-size:30px;color:#ffff4d">create_new_folder</i>    <b>Create</b></a></td>
		<td><a href ='Upload_FF' title="Upload File" style="color:black !important;"><i class="material-icons" style="font-size:30px;color:#a3a375">file_upload</i>    <b>Upload</b></a></td>
		</tr>
		</table>	
		<br>
			<br>
			<table class="center">
			<tr><th>Name</th><th>Date</th><th>Size</th><th>Operations</th></tr>
			
			<c:forEach items="${ff}" var="f">
				<c:choose>
					
					<c:when test="${f.getParentId() == null || f.getParentId() == 0 && f.bool_Folder == true }">
						<tr>
						  	<td><a href ='Display_FF?f_id=${f.getId()}&f_name=${f.getName()}' title="Open Folder"><i class="material-icons" style="font-size:25px;color:#ffff4d">folder</i>${f.getName()}</a></td>
						    <td><fmt:formatDate value="${f.getDate()}" pattern="MM/dd/yyyy hh:mm:ss a" /></td>
						    <td></td> 
						    <td><a href='Edit_FF?f_name=${f.getName()}&f_id=${f.getId()}'>Rename </a>|<a href='Delete_FF?f_name=${f.getName()}&f_id=${f.getId()}'> Delete</a></td>
						</tr>
					</c:when>
						
					<c:when test="${f.getParentId() == null || f.getParentId() == 0 && f.bool_Folder == false }">
						<tr>
						   <td><a href ='Download_FF?f_name=${f.getName()}' title="Download File"><span class="glyphicon glyphicon-open-file" style="font-size:25px;;color:#bfc0c1"></span>${f.getName()}</a></td>
						   <td><fmt:formatDate value="${f.getDate()}" pattern="MM/dd/yyyy hh:mm:ss a" /></td>
						   <td>${f.getSize()} KB</td> 
						    <td><a href='Edit_FF?f_name=${f.getName()}&f_id=${f.getId()}&bool_Folder=${f.isBool_Folder()}'>Rename </a>|<a href='Delete_FF?f_name=${f.getName()}&f_id=${f.getId()}'> Delete</a></td>
						</tr>
					</c:when>
				
				</c:choose>
			</c:forEach>
		</table>
		
	
	</c:when>	
	
	<c:when test="${id != null }">

		<c:set var="flag" value="true" />
		<table id="CU">
		<tr id="operations">
		<c:forEach items="${ff}" var="f">
			<c:if test="${f.getId()== id}">			
				<td>
				<a href ='Display_FF?f_name=${f.getName()}&f_id=${id}' title="Back"  style="color:black !important;"><span class="glyphicon glyphicon-level-up" style="font-size:28px;color:#ffff4d"></span>   <i>${name}</i></a> 						
					<c:set var="flag" value="false" />
				</td>	
			</c:if>
		</c:forEach>
			
		<c:if test="${flag == true}">
			<td><a href ='Display_FF' title="Back" style="color:black !important;"><span class="glyphicon glyphicon-level-up" style="font-size:28px;color:#ffff4d"></span>  <i>${name}</i></a>		
			</td>
		</c:if>
	
		<td>
		<a href ='Create_FF?f_id=${id}&f_name=${name}' title="Create Folder" style="color:black !important;"><i class="material-icons" style="font-size:30px;color:#ffff4d">create_new_folder</i>    <b>Create</b></a>
		</td>
		<td>
		<a href ='Upload_FF?f_id=${id}&f_name=${name}' title="Upload File" style="color:black !important;"><i class="material-icons" style="font-size:30px;color:#a3a375">file_upload</i>    <b>Upload</b></a>
		</td>
		</tr>
		</table>	
			<br>
			<br>
			<table class ="center">
	        <tr><th>Name</th><th>Date</th><th>Size</th><th>Operations</th></tr>
			
			<c:forEach items="${ff}" var="f">
				<c:choose>
					
					<c:when test="${f.getParentId()!= null && f.getParentId()== id && f.bool_Folder==false }">
						<tr>
			        		<td><a href ='Download_FF?f_name=${f.getName()}' title="Download File"><span class="glyphicon glyphicon-open-file" style="font-size:25px;;color:#bfc0c1"></span>${f.getName()}</a></td>
			        		<td><fmt:formatDate value="${f.getDate()}" pattern="MM/dd/yyyy hh:mm:ss a" /></td>
			        		<td>${f.getSize()} KB</td> 
			                <td><a href='Edit_FF?f_name=${f.getName()}&f_id=${f.getId()}&bool_Folder=${f.isBool_Folder()}'>Rename </a>|<a href='Delete_FF?f_name=${f.getName()}&f_id=${f.getId()}'> Delete</a></td>
			            </tr>
					</c:when>
				
					<c:when test="${f.getParentId()!= null && f.getParentId()== id && f.bool_Folder==true }">
						<tr>
			        		<td><a href ='Display_FF?f_id=${f.getId()}&f_name=${f.getName()}' title="Open Folder"><i class="material-icons" style="font-size:25px;color:#ffff4d">folder</i>${f.getName()}</a></td>
			        		<td><fmt:formatDate value="${f.getDate()}" pattern="MM/dd/yyyy hh:mm:ss a" /></td>
			        		<td></td> 
			                <td><a href='Edit_FF?f_name=${f.getName()}&f_id=${f.getId()}'>Rename </a>|<a href='Delete_FF?f_name=${f.getName()}&f_id=${f.getId()}'> Delete</a></td>
			            </tr>
					</c:when>
				
				</c:choose>
			</c:forEach>
		</table>	
	</c:when>
	
</c:choose>		
<br/>
<div style="float: right; border: 1px dotted; width: 100px;padding-top: 10px;background-color:#9dbbe1; ">
<p align="center">
<a href="Logout_Pg" style="color: black !important;font-size:small;"><b>Logout</b></a> | <a href="FM_Home" style="color: black !important;font-size:small;"><b>Home</b></a> 
</p>
</div>
</body>
</html>