<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Dodawanie nowego uzytkownika</title>
	
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/background.css" />
		
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/stare/add-person-form.css" />
	
	<style type="text/css">
		.error {color:red}
	</style>
	
</head>

<body>
	<div class="container">

		<div class="header">
	    	<h2>Save Person</h2>
	    </div>
    
	    <div class="content">
			<form:form action="savePerson" modelAttribute="personToVal">
			
			<!-- need to associate this data with person id -->
			<form:hidden path="id" />
			
				<table>
					<tbody>
					
						<tr>
							<td><label>Nickname:</label></td>
							<td><form:input path="nickName"/></td>
							<td><form:errors path="nickName" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label>First name:</label></td>
							<td><form:input path="firstName"/></td>
							<td><form:errors path="firstName" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label>Last name:</label></td>
							<td><form:input path="lastName"/></td>
							<td><form:errors path="lastName" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label>Email:</label></td>
							<td><form:input path="email"/></td>
							<td><form:errors path="email" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label>Password:</label></td>
							<td><form:input path="password"/></td>
							<td><form:errors path="password" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label>Phone:</label></td>
							<td><form:input path="phone"/></td>
							<td><form:errors path="phone" cssClass="error" /></td>
						</tr>
						
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>
					
					</tbody>
				</table>
			</form:form>
			
			<br><br><br><br>
			
			<div class="backToMenu">
				<a href="${pageContext.request.contextPath}/login/loginOk">Back to List</a>	
	    	</div>
			
		</div>	
	      
	    <div class="footer">
			<h4>Copyright � Damian Polchlopek. All Rights Reserved.</h4>
	    </div>

	</div>
	
</body>

</html>