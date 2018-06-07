<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>

<head>

    <title>Help</title>
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

        <div class="row content">
            <div class="col-sm-3 sidenav container" style="height: 100%;
                                                            min-height: 350px;">

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

                <div class="container" style="text-align: center" >

                    <table>
                        <tr>
                            <td><h3>Przeslano niepoprawny plik.</h3></td>
                        </tr>

                        <tr>
                            <td><h4>Aby wprowadzic dane do aplikacji plik musi miec nastepujaca budowe:</h4></td>
                        </tr>

                        <tr>
                            <td>1. Description: (Twoje dane)</td>
                        </tr>

                        <tr>
                            <td>2. Category: (Twoje dane)</td>
                        </tr>

                        <tr>
                            <td>3. Type: (Twoje dane)</td>
                        </tr>

                        <tr>
                            <td>4. Description axis x: (Twoje dane)</td>
                        </tr>

                        <tr>
                            <td>5. Description axis y: (Twoje dane)</td>
                        </tr>

                        <tr>
                            <td>6. Data:</td>
                        </tr>

                        <tr>
                            <td>
                                W kolejnych liniach pliku nalezy umiescic wprowadzane danie.<br>
                                Pierwsza liczba odpowiedzialna jest za wartosc reprezentujaca os x,<br>
                                druga liczba odpowiedzialna jest za wartosc reprezentujaca os y.<br><br>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <div class="dropup">
                                    <button class="btn btn-default dropdown-toggle" type="button"
                                            id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        Kliknij aby wyswetlic przyklad pliku pomiarowego
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                        <li>
                                            <img src="${pageContext.request.contextPath}/resources/image/help-file.png">
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <br>


                </div>
            </div>

        </div>

    </div>

    <footer class="my-5 pt-5 text-center text-small">
        <h4>Damian Polchlopek - Praca licencjacka</h4>
    </footer>

</div>
</body>


</html>
