<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Ressource à une Tâche</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1>Ajouter une Ressource à une Tâche</h1>

    <form action="tacheRessource?action=ajouterTacheRessource" method="post">


        <div class="form-group">
            <label for="id_tache">Tâche :</label>
            <input type="text" class="form-control" id="id_tache" name="id_tache" value="${tache.id_TA}" readonly>
            <input type="hidden" name="id_tache" value="${tache.id_TA}">
            <p>${tache.description}</p>
        </div>

        <div class="form-group">
            <label for="id_ressource">Ressource :</label>
            <select class="form-control" id="id_ressource" name="id_ressource" required>
                <option value="">Sélectionnez une ressource</option>
                <c:forEach items="${ressources}" var="ressource">
                    <option value="${ressource.id_RESS}">${ressource.nom} - ${ressource.types}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Ajouter</button>
        <a href="tacheRessource?action=listeTacheRessource" class="btn btn-secondary">Annuler</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>