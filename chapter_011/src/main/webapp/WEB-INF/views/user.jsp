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
</head>
<body>
<c:set var = "baseUrl" scope = "session" value = "${pageContext.servletContext.contextPath}"/>
<div class="container">
    <div class="col-5 offset-3">
        <h2><c:out value="${title}"/></h2>
        <form method="post" action="<c:out value="${baseUrl}" />/users/<c:out value="${action}"/>" enctype="multipart/form-data">
            <div class="form-group">
                <input type="hidden" name="id" class="form-control" id="InputId" value="<c:out value="${user.id}"/>">
            </div>
            <div class="form-group">
                <label for="InputName">Name</label>
                <input type="text" name="name" class="form-control" id="InputName" placeholder="Enter name"
                       value="<c:out value="${user.name}"/>">
            </div>
            <div class="form-group">
                <label for="InputLogin">Login</label>
                <input type="text" name="login" class="form-control" id="InputLogin" placeholder="Enter login"
                       value="<c:out value="${user.login}"/>">
            </div>
            <div class="form-group">
                <label for="InputEmail">Email</label>
                <input type="email" name="email" class="form-control" id="InputEmail" placeholder="Email"
                       value="<c:out value="${user.email}"/>">
            </div>
            <div class="form-group">
                <label for="customFile">Photo</label>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="customFile" name="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary"><c:out value="${buttonName}"/></button>
        </form>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>