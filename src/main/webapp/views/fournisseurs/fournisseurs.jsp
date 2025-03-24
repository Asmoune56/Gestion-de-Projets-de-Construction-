<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Fournisseurs</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }
        h1 {
            text-align: center;
        }
        .buttons-container {
            text-align: center;
            margin-bottom: 20px;
        }
        .button {
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            border-radius: 5px;
            display: inline-block;
            margin: 5px;
        }
        .add-button a {
            background-color: #4CAF50;
        }
        .dashboard-button a {
            background-color: #007bff;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .actions a {
            margin: 0 5px;
            text-decoration: none;
            padding: 5px;
            color: white;
        }
        .actions a.edit {
            background-color: #008CBA;
        }
        .actions a.delete {
            background-color: #f44336;
        }
    </style>
</head>
<body>

<h1>Liste des Fournisseurs</h1>

<div class="buttons-container">

    <div class="add-button">
        <a href="${pageContext.request.contextPath}/fournisseur/add-form" class="button">Ajouter un fournisseur</a>
    </div>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Email</th>
        <th>Téléphone</th>
        <th>Adresse</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="fournisseur" items="${fournisseurs}">
        <tr>
            <td>${fournisseur.id}</td>
            <td>${fournisseur.nom}</td>
            <td>${fournisseur.email}</td>
            <td>${fournisseur.telephone}</td>
            <td>${fournisseur.adresse}</td>
            <td class="actions">
                <a href="edit-form?id=${fournisseur.id}" class="edit">Éditer</a>
                <a href="delete?id=${fournisseur.id}" class="delete" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce fournisseur ?')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
