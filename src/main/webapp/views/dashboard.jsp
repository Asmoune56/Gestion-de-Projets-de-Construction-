<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord - ConstructionXpert Services</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #333;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            font-size: 20px;
        }
        .container {
            display: flex;
            margin: 20px;
        }
        .sidebar {
            width: 250px;
            background-color: #444;
            padding: 15px;
            height: 100vh;
            color: white;
        }
        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 10px;
            margin: 5px 0;
            background-color: #555;
            text-align: center;
        }
        .sidebar a:hover {
            background-color: #777;
        }
        .main-content {
            flex: 1;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
        }
        .stats {
            display: flex;
            justify-content: space-between;
        }
        .stat-box {
            width: 30%;
            padding: 20px;
            background-color: #008CBA;
            color: white;
            text-align: center;
            border-radius: 5px;
            margin: 5px;
        }
        .stat-box.success { background-color: #4CAF50; }
        .stat-box.warning { background-color: #f0ad4e; }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #333;
            color: white;
        }
        .btn {
            padding: 8px 12px;
            background-color: #008CBA;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 3px;
        }
        .btn:hover {
            background-color: #005f73;
        }
        .logout-btn {
            background-color: red;
        }
        .logout-btn:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>

<nav class="navbar">
    <a href="#">ConstructionXpert Services</a>
    <form action="UserServlet" method="get">
        <input type="hidden" name="action" value="logout">
        <button type="submit" class="btn logout-btn">Déconnexion</button>
    </form>
</nav>

<div class="container">
    <div class="sidebar">
        <a href="#">🏠 Tableau de Bord</a>
        <a href="projects/list">📁 Projets</a>
        <a href="tache/list">✅ Tâches</a>
        <a href="Resources/list">🛠️ Ressources</a>
        <a href="fournisseur/list">🚚 Fournisseurs</a>
    </div>

    <div class="main-content">
        <h2>Bienvenue sur le Tableau de Bord</h2>

        <div class="stats">
            <div class="stat-box">📁 <br> 10 Projets en cours</div>
            <div class="stat-box success">✅ <br> 25 Tâches actives</div>
            <div class="stat-box warning">🛠️ <br> 50 Ressources disponibles</div>
        </div>

        <h3>📋 Derniers Projets</h3>
        <table>
            <thead>
            <tr>
                <th>Nom</th>
                <th>Date de Début</th>
                <th>Budget</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Projet A</td>
                <td>01/03/2025</td>
                <td>100 000€</td>
                <td><button class="btn">Détails</button></td>
            </tr>
            <tr>
                <td>Projet B</td>
                <td>10/03/2025</td>
                <td>150 000€</td>
                <td><button class="btn">Détails</button></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
