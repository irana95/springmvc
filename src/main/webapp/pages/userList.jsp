<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/11/2019
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/bootstrap/bootstrap.min.css">
</head>
<body>

<table class="table  table-hover table-bordered " >
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Mail</th>
        <th>Phone</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.id} </td>
            <td>${u.name} </td>
            <td>${u.surname} </td>
            <td>${u.mail} </td>
            <td>${u.phone} </td>
            <td><a href="${pageContext.request.contextPath}/editUser/${u.id}" >Edit User</a></td>
            <td><a href="${pageContext.request.contextPath}/deleteUser/${u.id}" >Delete User</a></td>

        </tr>
    </c:forEach>

    </tbody>
</table>

<script src="${pageContext.request.contextPath}/theme/js/bootstrap.min.js"></script>
</body>
</html>
