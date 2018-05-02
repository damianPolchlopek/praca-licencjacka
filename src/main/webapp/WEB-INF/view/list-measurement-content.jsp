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
		    	<h2>Wszystkie pomiary</h2>
	    </div>
	    
		<div class="asideContent">

			<div class="aside">

				<dl>
					<dt><a href="/login/loginOk">Strona glowna</a></dt>
					<dt><a href="/person/showPerson">Pokaz osoby</a></dt>
					<dt><a href="/login/showLogin">Ostatnie logowania</a></dt>
					<dt><a href="/measurement/showMeasurement">Dostepne pomiary</a></dt>

					<br><br><br>

					<dt><a href="/person/showFormForAdd">Dodaj uzytkownika</a></dt>
					<dt><a href="/">Wyloguj</a></dt>

				</dl>
			</div>
		    
		    <div id="cont" class="content">
		    
				<br><br>			
				
				<!-- add our html table here -->
				<table>
					<tr>
						<th>User First Name</th>
						<th>User Last Name</th>
						<th>Date Measurement</th>
						<th>Description</th>
						<th>Category</th>
						<th>Show</th>
					</tr>
					
					<!-- loop over and print our people --> 
					<c:forEach var="tempMeasurement" items="${measurements}">

						<!-- construct an "update" link with person id -->
						<c:url var="showLink" value="/measurement/showGraph">
							<c:param name="measurementId" value="${tempMeasurement.id}"></c:param>
						</c:url>
						
						
						<tr>
							<td> ${tempMeasurement.personId.firstName} </td>
							<td> ${tempMeasurement.personId.lastName} </td>
							<td> ${tempMeasurement.dateMeasurement} </td>
							<td> ${tempMeasurement.description} </td>
							<td> ${tempMeasurement.category.category} </td>

							<td>
								<!-- display the update link -->
								<a href="${showLink }">Show</a>
							</td>
								
						</tr>
						
					</c:forEach>
					
				</table>			
			</div>
		
		    
		    <div class="footer">
				<h4>Copyright � Damian Polchlopek. All Rights Reserved.</h4>
		    </div>

		</div>
	</div>

	
</body>


</html>
