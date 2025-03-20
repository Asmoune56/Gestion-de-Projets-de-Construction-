<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 06:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Task</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            font-size: 16px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Add New Task</h2>
    <form action="/tache/new" method="POST">
        <label for="description">Task Description:</label>
        <textarea id="description" name="description" required></textarea>

        <label for="dateDebut">Start Date:</label>
        <input type="date" id="dateDebut" name="dateDebut" required>

        <label for="dateFin">End Date:</label>
        <input type="date" id="dateFin" name="dateFin" required>

        <input type="submit" value="Add Task">
    </form>
</div>

</body>
</html>

