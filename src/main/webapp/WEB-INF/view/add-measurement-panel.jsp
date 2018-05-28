<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
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

        <header class="text-center text-small">
            <h4>Aplikacja sluzaca do przechowywania danych pomiarowych</h4>
        </header>

        <div class="container-fluid">

            <div class="row content" id="wrapper">

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
                                Measurements
                            </a>
                        </li>

                        <security:authorize access="hasRole('EMPLOYEE')">
                            <li class="nav-item">
                                <a href="/measurement/addMeasurementPanel">
                                    <span class="glyphicon glyphicon-plus" ></span>
                                    Add Measurement
                                </a>
                            </li>
                        </security:authorize>

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

                <div class="col-sm-9" id="center">


                        <form:form action="addMeasurement?${_csrf.parameterName}=${_csrf.token}"
                                   method="post" modelAttribute="newMeasurement"
                                   enctype="multipart/form-data">

                            <table style="width: 25%;
                                        height: 20%;
                                        text-align: left;
                                        margin-left: auto;
                                        margin-right: auto;">

                                <tr>
                                    <td><form:input path="file" name="file" type="file"/></td>
                                </tr>

                                <tr>
                                    <td><button type="submit" class="btn btn-default">Przeslij</button></td>
                                </tr>

                            </table>

                        </form:form>
                </div>

            </div>

        </div>

        <footer class="my-5 pt-5 text-center text-small">
            <h4>Damian Polchlopek - Praca licencjacka</h4>
        </footer>

    </div>
</body>


</html>
