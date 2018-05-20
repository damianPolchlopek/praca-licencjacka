<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
	<title>Measurement</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

</head>

<body>
	<div class="body-container">

		<header class="my-5 pt-5 text-muted text-center text-small">
			<h4>Aplikacja sluzaca do przechowywania danych pomiarowych</h4>
		</header>


		<div class="container-fluid">

			<div class="row content">

				<div class="col-sm-3 sidenav">

					<h4>Main menu:</h4>

					<ul class="nav nav-pills nav-stacked">

						<li class="nav-item">
							<a href="/login/loginOk">
								<span class="glyphicon glyphicon-home" ></span>
								Home
							</a>
						</li>

						<li class="nav-item">
							<a href="/person/showPerson">
								<span class="glyphicon glyphicon-user" ></span>
								Users
							</a>
						</li>

						<li class="nav-item">
							<a href="/login/showLogin">
								<span class="glyphicon glyphicon-th-list" ></span>
								Last logs
							</a>
						</li>

						<li class="nav-item">
							<a href="/measurement/showMeasurement">
								<span class="glyphicon glyphicon-stats" ></span>
								Measurement
							</a>
						</li>

						<security:authorize access="hasRole('ADMIN')">
							<li class="nav-item">
								<a href="/person/showFormForAdd">
									<span class="glyphicon glyphicon-plus" ></span>
									Add user
								</a>
							</li>
						</security:authorize>

						<security:authorize access="hasRole('ADMIN')">
							<li class="nav-item">
								<a href="${pageContext.request.contextPath}/admin">
									<span class="glyphicon glyphicon-eye-open" ></span>
									Admin
								</a>
							</li>
						</security:authorize>

						<hr>
						<li class="nav-item">
							<!-- Add a logout button -->
							<form:form action="${pageContext.request.contextPath}/logout"
									   method="POST">

								<button type="submit" class="btn btn-info">
									<span class="glyphicon glyphicon-off" ></span>
									Logout
								</button>

							</form:form>
						</li>

					</ul><br>

				</div>


				<div class="col-sm-9">

					<!-- menu szukania -->

					<nav class="navbar navbar-default" role="navigation">
						<div class="container">
							<div class="navbar-header">

								<form:form action="searchMeasurements" modelAttribute="dataMeasurement"
										   class="navbar-form navbar-left" method="post" role="search">

									<form:select path="category" class="form-control">
										<form:option value=""> All </form:option>
										<form:options items="${availableCategory}"></form:options>
									</form:select>

									<form:input  path="description" type="text" class="form-control" placeholder="Szukaj" />

									<button type="submit" class="btn btn-default">Search</button>
								</form:form>

							</div>
						</div>
					</nav>


<form:form action="showMultipleGraph" modelAttribute="multipleMeasurement">

					<!-- add our html table here -->
					<table class="table table-striped table-sm">
						<tr>
							<th>User First Name</th>
							<th>User Last Name</th>
							<th>Date Measurement</th>
							<th>Description</th>
							<th>Category</th>

							<c:if test="${empty dataMeasurement.category}" >
								<th>Show</th>
							</c:if>

							<c:if test="${not empty dataMeasurement.category}" >
								<th>Which meas</th>
							</c:if>
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

								<c:if test="${empty dataMeasurement.category}" >
								<td>
									<!-- display the update link -->
									<a href="${showLink }">Show</a>
								</td>
								</c:if>
								<%--dataMeasurement.category--%>
								<c:if test="${not empty dataMeasurement.category}" >
									<td> <form:checkbox path="measurementToGraph" value="${tempMeasurement.id}" /> </td>
								</c:if>

							</tr>

						</c:forEach>
					</table>

	<input type="submit" value="Submit" />


</form:form>
					<hr>

					<!-- -->
					<%--<p>Descritpiot: '${wantedMeasurement.description}'</p>--%>
					<%--<p>Category: '${wantedMeasurement.category.category}'</p>--%>
					<%--<p>Aval Category: '${availableCategory}'</p>--%>
					<hr>
					<%--<p>Descritpiot: '${dataMeasurement.description}'</p>--%>
					<p>Category: ${dataMeasurement.category}</p>
					<p>Multiple: ${multipleMeasurement}</p>


				</div>

			</div>


		</div>


		<footer class="my-5 pt-5 text-muted text-center text-small">
			<h4>Damian Polchlopek - Praca licencjacka</h4>
		</footer>

	</div>

</body>


</html>
