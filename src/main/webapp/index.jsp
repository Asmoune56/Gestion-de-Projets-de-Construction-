<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord - ConstructionXpert Services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-dark px-3">
    <a class="navbar-brand" href="#">ConstructionXpert Services</a>
    <button class="btn btn-danger">Déconnexion</button>
</nav>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <a href="#" class="list-group-item list-group-item-action active">Dashboard</a>
                <a href="#" class="list-group-item list-group-item-action">Projets</a>
                <a href="#" class="list-group-item list-group-item-action">Tâches</a>
                <a href="#" class="list-group-item list-group-item-action">Ressources</a>
                <a href="#" class="list-group-item list-group-item-action">Fournisseurs</a>
            </div>
        </div>
        <div class="col-md-9">
            <div class="row">
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Projets</h5>
                            <p class="card-text">10 Projets en cours</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Tâches</h5>
                            <p class="card-text">25 Tâches actives</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-warning mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Ressources</h5>
                            <p class="card-text">50 Ressources disponibles</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mt-3">
                <div class="card-header">Liste des derniers projets</div>
                <div class="card-body">
                    <table class="table table-striped">
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
                            <td><button class="btn btn-primary btn-sm">Détails</button></td>
                        </tr>
                        <tr>
                            <td>Projet B</td>
                            <td>10/03/2025</td>
                            <td>150 000€</td>
                            <td><button class="btn btn-primary btn-sm">Détails</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
