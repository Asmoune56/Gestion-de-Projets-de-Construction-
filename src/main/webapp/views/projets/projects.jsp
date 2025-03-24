<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Projets</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        function confirmDelete(id) {
            if (confirm("Voulez-vous vraiment supprimer ce projet ?")) {
                document.getElementById("delete-form-" + id).submit();
            }
        }
    </script>
</head>
<body>
<div class="container mt-4">
    <h2>Liste des Projets</h2>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Description</th>
            <th>Date Début</th>
            <th>Date Fin</th>
            <th>Budget</th>
            <th>Actions</th> <!-- Nouvelle colonne -->
        </tr>
        </thead>
        <tbody>
        <c:forEach var="projet" items="${projets}">
            <tr>
                <td>${projet.id}</td>
                <td>${projet.nom}</td>
                <td>${projet.description}</td>
                <td>${projet.dateDebut}</td>
                <td>${projet.dateFin}</td>
                <td>${projet.budget} €</td>
                <td>
                    <a href="${pageContext.request.contextPath}/projects/edit-form${projet.id}" class="btn btn-warning btn-sm">Modifier</a>
                    <!-- Formulaire de suppression caché -->
                    <form id="delete-form-${projet.id}" action="${pageContext.request.contextPath}/projects/delete/${projet.id}" method="POST" style="display: inline;">
                        <button type="button" class="btn btn-danger btn-sm" onclick="confirmDelete(${projet.id})">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/projects/new-form" class="btn btn-primary">
        <i class="bi bi-plus-circle"></i> Ajouter un projet
    </a>
</div>
</body>
</html>
