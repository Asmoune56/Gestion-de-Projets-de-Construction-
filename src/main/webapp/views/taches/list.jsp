<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 06:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List</title>
</head>
<body>

<h2>Task List</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tache" items="${taches}">
        <tr>
            <td>${tache.id}</td>
            <td>${tache.description}</td>
            <td>${tache.dateDebut}</td>
            <td>${tache.dateFin}</td>
            <td>
                <a href="/tache/edit-form?id=${tache.id}">Edit</a>
                <a href="/tache/delete?id=${tache.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

