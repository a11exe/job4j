<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.crud.model.User" %>
<%@ page import="ru.job4j.crud.logic.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: abramov_av.sit
  Date: 08.10.2019
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script>
        function deleteUser(userId) {
          $.ajax({
            url: "/users/deletejson",
            type: 'GET',
            data: {
              id: userId,
            },
            success: function () {
              updateTable();
            },
            error: function(jqXHR, textStatus, errorThrown) {
              console.log("Error... " + textStatus + "        " + errorThrown);
            },
          });
        }
        function edit() {
            $("#modalTitle").html("Edit user");
        }
        // function save() {
        //
        //     var form = $("#userForm");
        //     var formData = new FormData(form[0]);
        //
        //     $.ajax({
        //         url: "/users/create",
        //         type: 'POST',
        //         data: formData,
        //         success: function (data) {
        //             updateTable();
        //         },
        //         cache: false,
        //         contentType: false,
        //         processData: false
        //     });
        // }
        function save() {

          // get inputs
          var user = new Object();
          user.name = $('#InputName').val();
          user.login = $('#InputLogin').val();
          user.email = $('#InputEmail').val();
          user.password = $('#InputPassword').val();
          user.role = $('#inputGroupSelect01').val();

          $.ajax({
            url: "/users/createjson",
            cache: false,
            type: 'POST',
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(user),
            success: function (data) {
              updateTable();
            },

          });
        }

        function updateTable() {

          $.ajax({
            type: 'GET',
            url: "/users/get",
            cache: false,
            success: function(data) {
              var users = data;
              var result = "";
              var editUrl = "";
              var deleteUrl = "";
              var imgUser = "";
              var imgDownload = "";
              var createDate = "";
              var hasPhoto = false;
              for (var i = 0; i != users.length; ++i) {

                editUrl = "\"/users/edit?id=" + users[i].id + "\"";
                hasPhoto = !!(users[i].photoId);
                if (hasPhoto) {
                  imgUser = "<img src=\"/images/" + users[i].id + "\"" +
                      "class=\"rounded mx-auto d-block\" width=\"100\" height=\"100\" alt=\"7\">";
                  imgDownload = "<a href=\"/download?photoId=" + users[i].id + "\"" +
                      "class=\"btn btn-link\" role=\"button\"" +
                      "aria-pressed=\"true\">download</a>";
                } else {
                  imgUser = "";
                  imgDownload = "";
                }
                createDate = !(users[i].createDate) ? "" : users[i].createDate;
                result += "<tr>\n" +
                    "<td>" + users[i].id + "</td>" +
                    "<td>" + users[i].name + "</td>" +
                    "<td>" + users[i].login + "</td>" +
                    "<td>" + users[i].email + "</td>" +
                    "<td>" + createDate + "</td>" +
                    "<td>" + imgUser + "</td>" +
                    "<td>" + imgDownload + "</td>" +
                    <c:if test="${loggedUser.isAdmin()}">
                        "<td>" +
                            "<a href=" + editUrl +
                                "class=\"btn btn-link\" role=\"button\"" +
                                "aria-pressed=\"true\" data-toggle=\"modal\" data-target=\"#addEditUser\" onclick=\"edit()\">edit</a></td>" +
                        "</td>" +
                        "<td>" +
                            "<button type=\"button\"" +
                            "class=\"btn btn-danger\"" +
                            "onclick=\"deleteUser(" + users[i].id + ")\">delete</button></td>" +
                        "</td>" +
                    </c:if>
                    "</tr>";
              }
              var tableBody = document.getElementById("tbody");
              tableBody.innerHTML = result;
              console.log(result);
            },
            error: function(jqXHR, textStatus, errorThrown) {
              console.log("Error... " + textStatus + "        " + errorThrown);
            },
            dataType: 'json'
          });

        }

    </script>
</head>
<body>
<c:set var="baseUrl" scope="session" value="${pageContext.servletContext.contextPath}"/>
<div class="container">
    <div class="col-6 offset-2">

        <div class="form-inline">
            <a class="btn btn-default" href="<c:out value="${baseUrl}"/>">Home</a>
            <a class="btn btn-default float-right"
               href="<c:out value="${baseUrl}"/>/users/edit?id=<c:out value="${loggedUser.id}"/>"><c:out
                    value="${loggedUser.name}"/></a>
            <a class="btn btn-default float-right" href="<c:out value="${baseUrl}"/>/logout">logout</a>
        </div>

        <h2>Users</h2>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>createDate</th>
                <th>image</th>
                <th>download</th>
                <c:if test="${loggedUser.isAdmin()}">
                    <th>edit</th>
                    <th>delete</th>
                </c:if>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/>
                    </td>
                    <td><c:out value="${user.name}"/>
                    </td>
                    <td><c:out value="${user.login}"/>
                    </td>
                    <td><c:out value="${user.email}"/>
                    </td>
                    <td><c:out value="${user.createDate}"/>
                    </td>
                    <td>
                        <c:if test="${user.photoId != null && user.photoId != 0}">
                        <img src="<c:out value="${baseUrl}" />/images/<c:out value="${user.id}"/>"
                             class="rounded mx-auto d-block" width="100" height="100" alt="<c:out value="${user.id}"/>">
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${user.photoId != null && user.photoId != 0}">
                        <a href="<c:out value="${baseUrl}" />/download?photoId=<c:out value="${user.photoId}"/>"
                           class="btn btn-link" role="button"
                           aria-pressed="true">download</a>
                        </c:if>
                    </td>
                    <c:if test="${loggedUser.isAdmin()}">
                        <td><a href="<c:out value="${baseUrl}" />/users/edit?id=<c:out value="${user.id}"/>"
                               class="btn btn-link" role="button"
                               aria-pressed="true" data-toggle="modal" data-target="#addEditUser" onclick="edit()">edit</a></td>
                        <td><button type="button"
                                    class="btn btn-danger"
                                    onclick="deleteUser(<c:out value="${user.id}"/>)">delete</button>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${loggedUser.isAdmin()}">
            <a href="" class="btn btn-success" role="button"
               aria-pressed="true" data-toggle="modal" data-target="#addEditUser">Add user</a>
        </c:if>
    </div>

    <div class="modal fade" id="addEditUser" tabindex="-1" role="dialog" aria-labelledby="addEditUser" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="userForm" method="post" action="<c:out value="${baseUrl}" />/users/<c:out value="${action}"/>"
                          enctype="multipart/form-data">
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
                            <label for="InputPassword">Password</label>
                            <input type="password" name="password" class="form-control" id="InputPassword" placeholder="Password"
                                   value="<c:out value="${user.password}"/>">
                        </div>
                        <div class="form-group">
                            <label for="customFile">Photo</label>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="customFile" name="customFile">
                                <label class="custom-file-label" for="customFile">Choose file</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group mb-3">
                                <c:if test="${loggedUser.isAdmin()}">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" for="inputGroupSelect01">Role</label>
                                    </div>
                                    <select class="custom-select" id="inputGroupSelect01" name="role">
                                        <c:forEach var="role" items="${roles}">
                                            <option value="<c:out value="${role}"/>" ${role.equals(user.role) ? "selected" : ""}><c:out value="${role}"/></option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save()">Save changes</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        // Add the following code if you want the name of the file appear on select
        $(".custom-file-input").on("change", function () {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        });
    </script>

</div>
</body>
</html>