<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 20/03/2025
  Time: 07:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Fournisseur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }
        h1 {
            text-align: center;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            font-size: 14px;
            margin-bottom: 8px;
            display: block;
        }
        input[type="text"], input[type="email"], input[type="tel"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .back-button {
            margin-top: 20px;
            display: block;
            text-align: center;
        }
        .back-button a {
            padding: 10px 20px;
            text-decoration: none;
            background-color: #008CBA;
            color: white;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<h1>Ajouter un Fournisseur</h1>

<form action="fournisseur" method="post">
    <input type="hidden" name="action" value="create">
    <label for="nom">Nom</label>
    <input type="text" id="nom" name="nom" required>

    <label for="email">Email</label>
    <input type="email" id="email" name="email" required>

    <label for="telephone">Téléphone</label>
    <input type="tel" id="telephone" name="telephone" required>

    <label for="adresse">Adresse</label>
    <input type="text" id="adresse" name="adresse" required>

    <input type="submit" value="Ajouter">
</form>

<div class="back-button">
    <a href="fournisseurs">Retour à la liste des fournisseurs</a>
</div>

</body>
</html>

