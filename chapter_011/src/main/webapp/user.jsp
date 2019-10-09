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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="col-5 offset-3">
        <h2><%= request.getAttribute("title") %></h2>
        <form method="post" action="http://localhost:8080/users/<%= request.getAttribute("action") %>">
            <div class="form-group">
                <input type="hidden" name="id" class="form-control" id="InputId" value="<%= request.getAttribute("id") %>">
            </div>
            <div class="form-group">
                <label for="InputName">Name</label>
                <input type="text" name="name" class="form-control" id="InputName" placeholder="Enter name"
                       value="<%= request.getAttribute("name") %>">
            </div>
            <div class="form-group">
                <label for="InputLogin">Login</label>
                <input type="text" name="login" class="form-control" id="InputLogin" placeholder="Enter login"
                       value="<%= request.getAttribute("login") %>">
            </div>
            <div class="form-group">
                <label for="InputEmail">Email</label>
                <input type="email" name="email" class="form-control" id="InputEmail" placeholder="Email"
                       value="<%= request.getAttribute("email") %>">
            </div>
            <button type="submit" class="btn btn-primary"><%= request.getAttribute("buttonName") %></button>
        </form>
    </div>
</div>
</body>
</html>