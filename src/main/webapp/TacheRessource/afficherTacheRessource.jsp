<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Tâches et Ressources</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Liste des Tâches et leurs Ressources</h1>

    <div class="mb-3">
        <a href="tacheRessource?action=afficherAttachForm" class="btn btn-primary">Ajouter une Ressource à une Tâche</a>
    </div>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID Tâche</th>
            <th>Nom de la Tâche</th>
            <th>Description</th>
            <th>Date de Début</th>
            <th>Date de Fin</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${taches}" var="tache">
            <tr>
                <td>${tache.id}</td>
                <td>${tache.nom}</td>
                <td>${tache.description}</td>
                <td>${tache.dateDebut}</td>
                <td>${tache.dateFin}</td>
                <td>
                    <a href="tacheRessource?action=afficherTacheRessource&id=${tache.id}" class="btn btn-info btn-sm">Voir les Ressources</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>