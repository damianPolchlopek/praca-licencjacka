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
        <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

	    <script type="text/javascript">
			
		  	window.onload = function () {
			    var chart = new CanvasJS.Chart("chartContainer",
			    {

                    <c:if test="${fourierDescription.zoom == 'true'}">
                    	zoomEnabled: "${fourierDescription.zoom}",
                    </c:if>

			        title:{
			      	  	text: "Fourier"
                    },

					axisX:{
                        title: "Frequence",

                        <c:if test="${fourierDescription.typeAxisX == 'log'}">
                            logarithmic: true,
                            title: "Frequence (log)",
                        </c:if>
                    },

                    axisY:{
                        title: "Amplitude",

                        <c:if test="${fourierDescription.typeAxisY == 'log'}">
							logarithmic: true,
							title: "Amplitude (log)",
                        </c:if>
                    },

					dataPointWidth: 1,

                    data: [
                    {
                        type: "${fourierDescription.typeGraph}",
                        color: "blue",

                        dataPoints: [

                            <c:forEach var="tmpNode" items="${dataFourier}">
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

  
	</head>

	<body>

	<div class="body-container">

		<header class="my-5 pt-5 text-center text-small">
			<h4>Aplikacja sluzaca do przechowywania danych pomiarowych</h4>
		</header>

		<div class="container-fluid">

			<div class="row content">

				<div class="col-sm-3 sidenav" style="position: relative;
													 height: 100%;
                                                     min-height: 575px;">

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

				<div class="col-sm-9">

                    <table class="table" style="width: 90%;
                                                margin: 0 auto;">

                    <form:form action="changeFourierGraph" modelAttribute="fourierDescription"
                               class="navbar-form navbar-left" method="post" role="search">
                        <tr>
                            <%--<th>Axis X</th>--%>
                            <th>Axis Y </th>
                            <th>Type Graph</th>
                            <th>Zoom</th>
                        </tr>

                        <tr>
                            <%--<td>--%>
                            <%--<form:select path="typeAxisX" class="form-control">--%>
                                <%--<form:option value="normal"> Normal </form:option>--%>
                                <%--<form:option value="log"> Log </form:option>--%>
                            <%--</form:select>--%>
                            <%--</td>--%>

                            <td>
                            <form:select path="typeAxisY" class="form-control">
                                <form:option value="normal"> Normal </form:option>
                                <form:option value="log"> Log </form:option>
                            </form:select>
                            </td>

                            <td>
                            <form:select path="typeGraph" class="form-control">
                                <form:option value="line"> Line </form:option>
                                <form:option value="column"> Column </form:option>
                            </form:select>
                            </td>

							<td>
							<form:select path="zoom" class="form-control">
								<form:option value="false"> Zoom off </form:option>
								<form:option value="true"> Zoom on </form:option>
							</form:select>
							</td>

                        </tr>

                        <tr>
                            <td></td>
                            <td style="position: relative;
                                        margin: 0 auto;"> <button type="submit" class="btn btn-default">Change</button> </td>
                            <td></td>
							<td></td>
                        </tr>

                    </form:form>

                    </table>

                    <%--<!-- napisy pomocnicze -->--%>
                    <%--<p>Type: ${fourierDescription.typeGraph}</p>--%>
                    <%--<p>Axis: ${fourierDescription.typeAxisY}</p>--%>
                    <br>

                    <div id="chartContainer" style="position: relative;
                                                    margin: 0 auto;
                                                    height: 400px;
                                                    width: 90%;">


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
