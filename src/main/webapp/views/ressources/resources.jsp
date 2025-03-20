<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 07:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resources Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px 12px;
            text-align: left;
        }
        .btn {
            padding: 10px;
            margin: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn-create {
            background-color: #4CAF50;
            color: white;
        }
        .btn-edit {
            background-color: #FFA500;
            color: white;
        }
        .btn-delete {
            background-color: #f44336;
            color: white;
        }
        .btn:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>

<h2>Resources List</h2>

<!-- Create New Resource Button -->
<div style="text-align: center;">
    <a href="ressourceList?action=create" class="btn btn-create">Add New Resource</a>
</div>

<!-- Resources Table -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ressource" items="${ressources}">
        <tr>
            <td>${ressource.id}</td>
            <td>${ressource.nom}</td>
            <td>${ressource.type}</td>
            <td>${ressource.quantite}</td>
            <td>
                <a href="ressourceList?action=edit&id=${ressource.id}" class="btn btn-edit">Edit</a>
                <a href="ressourceList?action=delete&id=${ressource.id}" class="btn btn-delete" onclick="return confirm('Are you sure you want to delete this resource?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

