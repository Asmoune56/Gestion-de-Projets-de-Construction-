<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 07:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Resource</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            text-align: center;
        }
        .form-container {
            width: 50%;
            margin: 0 auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-container label {
            display: block;
            margin: 10px 0 5px;
        }
        .form-container input[type="text"], .form-container input[type="number"] {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .btn-submit {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        .btn-submit:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>

<h2>Edit Resource</h2>

<div class="form-container">
    <form action="ressourceList" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${ressource.id}">

        <label for="nom">Resource Name:</label>
        <input type="text" id="nom" name="nom" value="${ressource.nom}" required>

        <label for="type">Resource Type:</label>
        <input type="text" id="type" name="type" value="${ressource.type}" required>

        <label for="quantite">Quantity:</label>
        <input type="number" id="quantite" name="quantite" value="${ressource.quantite}" required min="1">

        <button type="submit" class="btn-submit">Update Resource</button>
    </form>
</div>

</body>
</html>
