<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 22/03/2025
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Task</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
            font-weight: bold;
        }
        input {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        .buttons {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        .buttons button {
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            color: #fff;
        }
        .save {
            background-color: #2ecc71;
        }
        .cancel {
            background-color: #e74c3c;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Edit Task</h2>
    <form action="/tache/edit" method="post">
        <input type="hidden" name="id" value="${tache.id}">

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${tache.description}" required>

        <label for="dateDebut">Start Date:</label>
        <input type="date" id="dateDebut" name="dateDebut" value="${tache.dateDebut}" required>

        <label for="dateFin">End Date:</label>
        <input type="date" id="dateFin" name="dateFin" value="${tache.dateFin}" required>

        <div class="buttons">
            <button type="submit" class="save">Save</button>
            <a href="/tache/list" class="cancel" style="text-decoration:none; padding:10px 15px; color:white;">Cancel</a>
        </div>
    </form>
</div>

</body>
</html>

