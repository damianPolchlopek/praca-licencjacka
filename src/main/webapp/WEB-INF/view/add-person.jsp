<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style type="text/css">
        .error {
            color:red;
            font-size: 12px;
        }

        .bg-light{
            background-color:#f8f9fa
            !important
        }

    </style>


    <title>Dodawanie nowego uzytkownika</title>

</head>

<body class="bg-light">
<div class="container" >
    <div class="row">
        <div class="col-lg-4 col-md-6 col-sm-8 col-xs-12">
            <div class="py-5 text-center"></div>

            <h2>Add Form</h2>
            <form:form action="savePerson" modelAttribute="personToValAdd">
                <div class="form-group">
                    <label for="nickName">Username:</label>
                    <form:input path="nickName" type="text" class="form-control"  placeholder="Enter username" />
                    <form:errors path="nickName" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="firstName">First name:</label>
                    <form:input path="firstName" type="text" class="form-control"  placeholder="Enter first name" />
                    <form:errors path="firstName" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="lastName">Last Name:</label>
                    <form:input path="lastName" type="text" class="form-control"  placeholder="Enter last name" />
                    <form:errors path="lastName" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <form:input path="email" type="email" class="form-control"  placeholder="Enter email" />
                    <form:errors path="email" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <form:input path="password" type="password" class="form-control"  placeholder="Enter password" />
                    <form:errors path="password" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <form:input path="phone" type="text" class="form-control"  placeholder="Enter phone" />
                    <form:errors path="phone" cssClass="error" />
                </div>

                <!-- Blokada konta -->
                <div class="form-group">
                    <label>Enabled:</label>
                    <div class="radio">
                        <label class="custom-control-label">
                            <form:radiobutton path="enabled" class="custom-control-input" value="true"/>
                            True
                        </label>
                    </div>

                    <div class="radio">
                        <label class="custom-control-label">
                            <form:radiobutton path="enabled" class="custom-control-input" value="false"/>
                            False
                        </label>
                    </div>

                </div>

                <!-- Prawa dostepu -->
                <div class="form-group">
                    <label>Roles:</label>
                    <div >
                        <label class="custom-control-label">
                            <form:checkbox path="authorities" class="custom-control-input" value="ROLE_EMPLOYEE"/>
                            Employee
                        </label>
                    </div>

                    <div >
                        <label class="custom-control-label">
                            <form:checkbox path="authorities" class="custom-control-input" value="ROLE_ADMIN"/>
                            Administrator
                        </label>
                    </div>

                </div>

                <button class="btn btn-primary btn-lg btn-block" type="submit" value="Save">Submit</button>

            </form:form>

            <br><br><br><br>

            <div class="backToMenu">
                <a href="${pageContext.request.contextPath}/login/loginOk">Back to List</a>
            </div>

        </div>
    </div>

</div>
</div>

</body>

</html>
