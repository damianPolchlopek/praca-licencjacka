<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>
    <title>Main</title>
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
                                    Admin stuff
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

                    <!-- display user name and role -->
                    <p>
                    <h3> Witaj <security:authentication property="principal.username" /> </h3>
                        <%--<br><br>--%>
                        <%--Role(s): <security:authentication property="principal.authorities" />--%>
                    </p>



                </div>

            </div>

        </div>


        <footer class="my-5 pt-5 text-muted text-center text-small">
            <h4>Copyright � Damian Polchlopek. All Rights Reserved.</h4>
        </footer>

    </div>
</body>


</html>
