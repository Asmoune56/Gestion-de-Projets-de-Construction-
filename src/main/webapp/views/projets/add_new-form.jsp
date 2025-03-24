
<%@ page import="com.gestion.gestionprojets_de_construction.models.Projet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Project</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 60%;
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
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input, textarea {
            padding: 10px;
            font-size: 1rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            text-align: center;
            padding: 10px;
            margin-bottom: 20px;
        }
        .message.success {
            background-color: #d4edda;
            color: #155724;
        }
        .message.danger {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Add New Project</h1>

    <%-- Display messages from session --%>
    <%
        String message = (String) session.getAttribute("message");
        String messageType = (String) session.getAttribute("messageType");
        if (message != null) {
    %>
    <div class="message <%= messageType %>">
        <%= message %>
    </div>
    <%
            session.removeAttribute("message");
            session.removeAttribute("messageType");
        }
    %>

    <form action="<%= request.getContextPath() %>/projects/new" method="POST">
        <label for="nom">Project Name:</label>
        <input type="text" id="nom" name="nom" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>

        <label for="dateDebut">Start Date:</label>
        <input type="date" id="dateDebut" name="dateDebut" required>

        <label for="dateFin">End Date:</label>
        <input type="date" id="dateFin" name="dateFin" required>

        <label for="budget">Budget:</label>
        <input type="number" step="0.01" id="budget" name="budget" required>

        <button type="submit">Add Project</button>
    </form>

</div>

</body>
</html>

