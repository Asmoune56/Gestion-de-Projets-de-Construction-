<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List</title>
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
        h2 {
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
        td {
            background-color: #fafafa;
        }
        a {
            text-decoration: none;
            padding: 6px 12px;
            color: #fff;
            border-radius: 4px;
        }
        a:hover {
            opacity: 0.8;
        }
        .edit {
            background-color: #f39c12;
        }
        .delete {
            background-color: #e74c3c;
        }
        .action-buttons {
            display: flex;
            justify-content: space-around;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Task List</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tache" items="${taches}">
            <tr>
                <td>${tache.id}</td>
                <td>${tache.description}</td>
                <td>${tache.dateDebut}</td>
                <td>${tache.dateFin}</td>
                <td class="action-buttons">
                    <a href="/tache/edit-form${tache.id}" class="edit">Edit</a>
                    <a href="/tache/delete?id=${tache.id}" class="delete"
                       onclick="return confirm('Voulez-vous vraiment supprimer cette tâche ?');">
                        Supprimer
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
