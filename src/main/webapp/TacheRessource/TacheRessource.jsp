<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ressources de la Tâche</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Ressources de la Tâche: ${tache.nom}</h1>

    <div class="card mb-4">
        <div class="card-header">
            Détails de la Tâche
        </div>
        <div class="card-body">
            <h5 class="card-title">${tache.nom}</h5>
            <p class="card-text"><strong>Description:</strong> ${tache.description}</p>
            <p class="card-text"><strong>Date de début:</strong> ${tache.dateDebut}</p>
            <p class="card-text"><strong>Date de fin:</strong> ${tache.dateFin}</p>
        </div>
    </div>

    <h2>Ressources Attachées</h2>

    <c:if test="${empty tacheRessource}">
        <div class="alert alert-info">
            Aucune ressource n'est attachée à cette tâche.
        </div>
    </c:if>

    <c:if test="${not empty tacheRessource}">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Type</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tacheRessource}" var="ressource">
                <tr>
                    <td>${ressource.id}</td>
                    <td>${ressource.nom}</td>
                    <td>${ressource.type}</td>
                    <td>
                        <a href="tacheRessource?action=supprimerTacheRessource&id_ressource=${ressource.id}&id_tache=${tache.id}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette ressource de la tâche?')">
                            Détacher
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="mt-3">
        <a href="tacheRessource?action=afficherAttachForm" class="btn btn-primary">Ajouter une Ressource</a>
        <a href="tacheRessource?action=listeTacheRessource" class="btn btn-secondary">Retour à la Liste</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>