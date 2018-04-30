<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html>

<head>
	
	<title>Login Panel</title>

	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/background.css" />
			
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/login-panel.css" />		
	
	
	<style type="text/css">
		.error {color:red}
	</style>
	
</head>

<body>

	<div class="container">
	    
	    <div class="header">
		    <h2>Login Panel</h2>
	    </div>
		 
	    <div class="content">

	    	<br><br><br><br>
	    	
			<form:form action="processForm" modelAttribute="userToValLogin">

				<table>
					
					<tr> 
						<td><label>Nick Name: </label></td>
						<td><form:input path="nickName" /></td>
						<td><form:errors path="nickName" cssClass="error" /></td>
					</tr>
					
					<tr>
						<td><label>Password:</label></td>
						<td><form:input path="password" /></td>
						<td><form:errors path="password" cssClass="error" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class="save"/></td>
					</tr>
					
		
				</table>
	
			</form:form>
			
			<br><br><br><br>
			<br><br><br><br>
			
			<a href="/praca-licencjacka">Strona startowa</a>
			
	    </div>


	    <div class="footer">
			Copyright © Damian Polchlopek. All Rights Reserved.
	    </div>
	    
	</div>
	
</body>

</html>
