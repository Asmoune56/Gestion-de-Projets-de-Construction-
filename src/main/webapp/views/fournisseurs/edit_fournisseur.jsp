
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier un Fournisseur</title>
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

<h1>Modifier un Fournisseur</h1>

<form action="fournisseur" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${fournisseur.id}">

    <label for="nom">Nom</label>
    <input type="text" id="nom" name="nom" value="${fournisseur.nom}" required>

    <label for="email">Email</label>
    <input type="email" id="email" name="email" value="${fournisseur.email}" required>

    <label for="telephone">Téléphone</label>
    <input type="tel" id="telephone" name="telephone" value="${fournisseur.telephone}" required>

    <label for="adresse">Adresse</label>
    <input type="text" id="adresse" name="adresse" value="${fournisseur.adresse}" required>

    <input type="submit" value="Mettre à jour">
</form>

<div class="back-button">
    <a href="fournisseurs">Retour à la liste des fournisseurs</a>
</div>

</body>
</html>

