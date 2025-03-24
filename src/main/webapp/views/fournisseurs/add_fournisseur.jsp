<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un Fournisseur</title>
    <link rel="stylesheet" href="assets/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .message {
            text-align: center;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }

        .error {
            background-color: #ffdddd;
            color: #d8000c;
        }

        .success {
            background-color: #ddffdd;
            color: #4F8A10;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
        }

        input {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        button {
            margin-top: 20px;
            background-color: #28a745;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #218838;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Ajouter un Fournisseur</h2>

    <%-- Affichage des messages d'erreur/succès --%>
    <% String error = (String) request.getAttribute("error");
        String success = (String) session.getAttribute("success");
        if (error != null) { %>
    <p class="message error"><%= error %></p>
    <% } %>
    <% if (success != null) { %>
    <p class="message success"><%= success %></p>
    <% session.removeAttribute("success"); %>
    <% } %>

    <form action="${pageContext.request.contextPath}/fournisseur/create" method="post">
        <input type="hidden" name="action" value="create">

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required>

        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required>

        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" required>

        <label for="adresse">Adresse :</label>
        <input type="text" id="adresse" name="adresse" required>

        <button type="submit">Ajouter</button>
    </form>

    <a class="back-link" href="${pageContext.request.contextPath}/fournisseur/list">Retour à la liste des fournisseurs</a>
</div>

</body>
</html>
