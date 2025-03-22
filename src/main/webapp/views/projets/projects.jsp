<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projects List</title>
    <style>
        /* CSS Styles */
    </style>
</head>
<body>

<div class="container">
    <h1>Project List</h1>
    <a href="${pageContext.request.contextPath}/projects/new-form" class="button">Add New Project</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.id}</td>
                <td>${project.nom}</td>
                <td>${project.description}</td>
                <td>${project.dateDebut}</td>
                <td>${project.dateFin}</td>
                <td class="action-buttons">
                    <a href="${pageContext.request.contextPath}/projects/edit-form?id=${projet.id}" class="btn btn-warning">Modifier</a>
                    <a href="${pageContext.request.contextPath}/projects/delete?id=${projet.id}" class="btn btn-danger">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
