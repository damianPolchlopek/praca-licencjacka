<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
	
	<title> Strona glowna</title>
	
	<!-- reference our style sheet -->
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/background.css" />
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/main-panel.css" />
			
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/table-data.css" />
			
	</head>
			
<body>

	<div class="container">
	
		<div id="ban" class="banner">
			<img width=100% height=100: src="<c:url value="/resources/images/banner.png"/>"/>
    	</div>
	    
	    <div class="header">
		    	<h2>Person - wszytskie rekordy</h2>
	    </div>
	    
		<div class="asideContent">
	
	
			<div class="aside">

				<dl>
					<dt><a href="/praca-licencjacka/login/loginOk">Strona glowna</a></dt>
					<dt><a href="/praca-licencjacka/person/showPerson">Pokaz osoby</a></dt>
					<dt><a href="/praca-licencjacka/login/showLogin">Ostatnie logowania</a></dt>
					<dt><a href="/praca-licencjacka/measurement/showMeasurement">Dostepne pomiary</a></dt>
					
					<br><br><br>
					
					<dt><a href="/praca-licencjacka/person/showFormForAdd">Dodaj uzytkownika</a></dt>
					<dt><a href="/praca-licencjacka">Wyloguj</a></dt>

				</dl>
		    </div>
		    
		    	  
		    
		    <div class="content">
				<div id="cvb">
					
					<br><br>
					
					<!-- add our html table here -->
					<table>
						<tr>
							<th>Id</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Action</th>
						</tr>
						
						<!-- loop over and print our people --> 
						<c:forEach var="tempPerson" items="${people}">
							
							<!-- construct an "update" link with person id -->
							<c:url var="updateLink" value="/person/showFormForUpdate">
								<c:param name="personId" value="${tempPerson.id}"></c:param>
							</c:url>
							
							<tr>
								<td> ${tempPerson.id} </td>
								<td> ${tempPerson.firstName} </td>
								<td> ${tempPerson.lastName} </td>
								<td> ${tempPerson.email} </td>
								<td> ${tempPerson.phone} </td>
								
								<td>
									<!-- display the update link -->
									<a href="${updateLink }">Update</a>
								</td>
								
							</tr>
							
						</c:forEach>
					</table>
				</div>					
			</div>
		
		</div>
	
	    
	    <div class="footer">
			<h4>Copyright © Damian Polchlopek. All Rights Reserved.</h4>
	    </div>


	</div>

	
</body>


</html>
