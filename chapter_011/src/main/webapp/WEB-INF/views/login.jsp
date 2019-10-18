<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abramov_av.sit
  Date: 08.10.2019
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Login</title>
</head>
<body>
<c:set var="baseUrl" scope="session" value="${pageContext.servletContext.contextPath}"/>
<div class="container">
    <div class="col-4 offset-3">
        <h2 class="form-signin-heading">Please sign in</h2>

        <form class="form-signin" method="post" action="<c:out value="${baseUrl}" />/login">
            <div class="form-group">
                <label for="inputLogin" class="sr-only">Login</label>
                <input type="login" id="inputLogin" name="login" class="form-control ${userNotFound ? "is-invalid" : ""}
" placeholder="Login" required autofocus>
                <div class="invalid-feedback">
                    user not found
                </div>
            </div>

            <div class="form-group">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control ${passwordIncorrect ? "is-invalid" : ""}" placeholder="Password" required>
                <div class="invalid-feedback">
                    password incorrect
                </div>
            </div>

            <div class="form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </div>
        </form>
    </div>
</div> <!-- /container -->


</body>
</html>
