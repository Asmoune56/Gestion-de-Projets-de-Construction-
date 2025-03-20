<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 07:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
            background-color: #4CAF50;
            color: white;
        }
        .actions a.delete {
            background-color: #f44336;
        }
        .actions a.edit {
            background-color: #008CBA;
        }
        .add-button {
            margin: 10px 0;
            display: block;
            text-align: center;
        }
        .add-button a {
            padding: 10px 20px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<h1>Liste des Fournisseurs</h1>

<div class="add-button">
    <a href="add-form">Ajouter un fournisseur</a>
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

