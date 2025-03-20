<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projects List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
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
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            text-decoration: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #45a049;
        }
        .action-buttons a {
            margin: 5px;
            color: white;
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 5px;
        }
        .action-buttons a.edit {
            background-color: #f39c12;
        }
        .action-buttons a.delete {
            background-color: #e74c3c;
        }
        .action-buttons a:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Project List</h1>
    <a href="addProject.jsp" class="button">Add New Project</a>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%-- Loop through the projects list and display them --%>
        <%
            List<Project> projects = (List<Project>) request.getAttribute("projects");
            for (Project project : projects) {
        %>
        <tr>
            <td><%= project.getId() %></td>
            <td><%= project.getName() %></td>
            <td><%= project.getDescription() %></td>
            <td><%= project.getStartDate() %></td>
            <td><%= project.getEndDate() %></td>
            <td class="action-buttons">
                <a href="editProject.jsp?id=<%= project.getId() %>" class="edit">Edit</a>
                <a href="deleteProject?id=<%= project.getId() %>" class="delete">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>

