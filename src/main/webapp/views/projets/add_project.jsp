<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 06:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Project</title>
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
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
            font-size: 14px;
        }
        input, textarea {
            padding: 10px;
            margin-bottom: 20px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
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
        .back-button {
            background-color: #f39c12;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            text-align: center;
            border-radius: 5px;
            display: inline-block;
            margin-top: 20px;
        }
        .back-button:hover {
            background-color: #e67e22;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Add New Project</h1>
    <form action="addProject" method="POST">
        <label for="name">Project Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="description">Project Description:</label>
        <textarea id="description" name="description" rows="4" required></textarea>

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required>

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required>

        <input type="submit" value="Add Project">
    </form>
    <a href="projects.jsp" class="back-button">Back to Project List</a>
</div>

</body>
</html>

