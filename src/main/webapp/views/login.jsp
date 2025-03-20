<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion | ConstructionXpert Services</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="d-flex justify-content-center align-items-center vh-100 bg-light">

<div class="card p-4 shadow-lg" style="width: 350px;">
    <h3 class="text-center">Connexion</h3>

    <%-- Affichage d'un message d'erreur si l'authentification échoue --%>
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    <div class="alert alert-danger text-center" role="alert">
        <%= errorMessage %>
    </div>
    <% } %>

    <form action="login" method="post">
        <input type="hidden" name="action" value="login">

        <div class="mb-3">
            <label for="email" class="form-label">Email :</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe :</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Se connecter</button>
    </form>
</div>

</body>
</html>
