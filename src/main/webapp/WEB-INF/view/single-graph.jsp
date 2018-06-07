<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>

	<head>
	
		<title>Measurement</title>

		<!-- reference our style sheet -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

	    <script type="text/javascript">
			
		  	window.onload = function () {
			    var chart = new CanvasJS.Chart("chartContainer",
			    {
			        title:{
			      	  	text: "Category - ${actualMeasurement.category}"
                    },

					axisY:{
                        title: "${actualMeasurement.descriptionAxisY}"
                    },

                    axisX:{
                        title: "${actualMeasurement.descriptionAxisX}"
                    },

                    <c:if test="${actualMeasurement.typeGraph == 'column'}">
                        dataPointWidth: 1,
                    </c:if>

                    data: [
                    {
                        type: "${actualMeasurement.typeGraph}",
                        color: "blue",

                        dataPoints: [

                            <c:forEach var="tmpNode" items="${actualMeasurement.measurementData}">
                                {x: ${tmpNode.nodeX},
                                 y: ${tmpNode.nodeY}},
                            </c:forEach>

                        ]
                    }
                    ]
				}
			);

			    chart.render();
            }
	  	</script>
		<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
  
	</head>

	<body>

	<div class="body-container">

		<header class="my-5 pt-5 text-center text-small">
			<h4>Aplikacja sluzaca do przechowywania danych pomiarowych</h4>
		</header>

		<div class="container-fluid">

			<div class="row content" style="position: relative;">

				<div class="col-sm-3 sidenav" style="position: relative;
													 height: 675px;">

					<h4>Main menu:</h4>

					<ul class="nav nav-pills nav-stacked">

						<li class="nav-item">
							<a href="${pageContext.request.contextPath}/login/loginOk">
								<span class="glyphicon glyphicon-home" ></span>
								Home
							</a>
						</li>

						<li class="nav-item">
							<a href="${pageContext.request.contextPath}/person/showPerson">
								<span class="glyphicon glyphicon-user" ></span>
								Users
							</a>
						</li>

						<li class="nav-item">
							<a href="${pageContext.request.contextPath}/login/showLogin">
								<span class="glyphicon glyphicon-th-list" ></span>
								Last logs
							</a>
						</li>

						<li class="nav-item">
							<a href="${pageContext.request.contextPath}/measurement/showMeasurement">
								<span class="glyphicon glyphicon-stats" ></span>
								Measurements
							</a>
						</li>

						<security:authorize access="hasRole('EMPLOYEE')">
							<li class="nav-item">
								<a href="${pageContext.request.contextPath}/measurement/addMeasurementPanel">
									<span class="glyphicon glyphicon-plus" ></span>
									Add Measurement
								</a>
							</li>
						</security:authorize>

						<security:authorize access="hasRole('ADMIN')">
							<li class="nav-item">
								<a href="${pageContext.request.contextPath}/person/showFormForAdd">
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

				<div class="col-sm-9" style="position: relative;">

					<div id="chartContainer" style="position: relative;
					 								margin: 0 auto;
					 								top: 30%;
					 								height: 400px;
					 								width: 90%;">

					</div>

                    <hr style="position: relative;
								width: 90%;">

                    <div style="position: relative;
                                   margin: 0 auto;
                                   width: 90%;">

                        <h4><b>Opis pomiaru</b></h4>

                        <table class="table table-striped table-sm">
                            <tr>
                                <th>Maximum: </th>
								<td>
									<fmt:formatNumber type="number" minFractionDigits="2"
													  maxFractionDigits="4" value="${measurementAnalysis.maximum}"/>
								</td>
							</tr>

                            <tr>
                                <th>Minimum: </th>
								<td>
									<fmt:formatNumber type="number" minFractionDigits="2"
													  maxFractionDigits="4" value="${measurementAnalysis.minimum}"/>
								</td>
                            </tr>

                            <tr>
                                <th>Average: </th>
								<td>
									<fmt:formatNumber type="number" minFractionDigits="2"
													  maxFractionDigits="4" value="${measurementAnalysis.average}"/>
								</td>
                            </tr>

                            <tr>
                                <th>Variance: </th>
								<td>
									<fmt:formatNumber type="number" minFractionDigits="2"
													  maxFractionDigits="4" value="${measurementAnalysis.variance}"/>
								</td>
                            </tr>

                            <tr>
                                <th>Standard Deviotion: </th>
								<td>
									<fmt:formatNumber type="number" minFractionDigits="2"
													  maxFractionDigits="4" value="${measurementAnalysis.standardDeviation}"/>
								</td>
                            </tr>

                        </table>

                    </div>

				</div>

			</div>

		</div>

		<div>
			<footer class="my-5 pt-5 text-center text-small">
				<h4>Damian Polchlopek - Praca licencjacka</h4>
			</footer>
		</div>

	</div>

	</body>

</html>
