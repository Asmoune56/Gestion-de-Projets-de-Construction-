<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Liste des Projets | ConstructionXpert</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
  <style>
    :root {
      --primary: #2A5C8D;
      --primary-light: #3A7CB9;
      --secondary: #4ECDC4;
      --accent: #FF6B6B;
      --dark: #292F36;
      --light: #F7FFF7;
      --gray: #E0E0E0;
      --success: #6BCB77;
      --warning: #FFD166;
      --danger: #EF476F;
    }

    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
      font-family: 'Poppins', sans-serif;
    }

    body {
      background-color: #F5F7FA;
      color: var(--dark);
    }

    /* Navigation Bar */
    .navbar {
      background: white;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      padding: 15px 30px;
      position: fixed;
      width: 100%;
      z-index: 1000;
    }

    .navdiv {
      display: flex;
      align-items: center;
      justify-content: space-between;
      max-width: 1200px;
      margin: 0 auto;
    }

    .logo {
      font-size: 24px;
      font-weight: 700;
      color: var(--primary);
    }

    .logo a {
      text-decoration: none;
      color: inherit;
    }

    .menu {
      list-style: none;
      display: flex;
      align-items: center;
      gap: 15px;
    }

    .menu li {
      position: relative;
    }

    .menu li .link {
      color: var(--dark);
      font-size: 14px;
      font-weight: 500;
      text-decoration: none;
      padding: 8px 12px;
      border-radius: 6px;
      transition: all 0.3s ease;
    }

    .menu li .link:hover {
      background-color: rgba(42, 92, 141, 0.1);
      color: var(--primary);
    }

    .menu li:hover .submenu {
      display: block;
    }

    .submenu {
      display: none;
      position: absolute;
      background: white;
      list-style: none;
      min-width: 180px;
      border-radius: 6px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.1);
      padding: 10px 0;
      z-index: 100;
    }

    .submenu li {
      padding: 0;
    }

    .submenu li a {
      display: block;
      padding: 8px 20px;
      color: var(--dark);
      transition: all 0.2s ease;
      font-size: 14px;
    }

    .submenu li a:hover {
      background-color: rgba(42, 92, 141, 0.1);
      color: var(--primary);
    }

    .logout-btn {
      background: var(--primary);
      color: white;
      border: none;
      border-radius: 6px;
      padding: 8px 16px;
      margin-left: 15px;
      cursor: pointer;
      transition: all 0.3s ease;
    }

    .logout-btn:hover {
      background: var(--primary-light);
      transform: translateY(-2px);
    }

    .logout-btn a {
      text-decoration: none;
      color: inherit;
      font-weight: 500;
      font-size: 14px;
    }

    /* Main Content */
    .main-container {
      max-width: 1200px;
      margin: 80px auto 30px;
      padding: 0 20px;
    }

    .page-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 30px;
    }

    .page-title {
      font-size: 28px;
      font-weight: 600;
      color: var(--primary);
    }

    .add-btn {
      background: var(--primary);
      color: white;
      border: none;
      border-radius: 6px;
      padding: 10px 20px;
      font-weight: 500;
      cursor: pointer;
      transition: all 0.3s ease;
      text-decoration: none;
      display: inline-flex;
      align-items: center;
      gap: 8px;
    }

    .add-btn:hover {
      background: var(--primary-light);
      transform: translateY(-2px);
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    /* Project Table */
    .project-table-container {
      background: white;
      border-radius: 10px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.05);
      overflow: hidden;
    }

    .project-table {
      width: 100%;
      border-collapse: collapse;
    }

    .project-table thead {
      background: var(--primary);
      color: white;
    }

    .project-table th {
      padding: 15px 20px;
      text-align: left;
      font-weight: 500;
    }

    .project-table td {
      padding: 15px 20px;
      border-bottom: 1px solid var(--gray);
    }

    .project-table tr:last-child td {
      border-bottom: none;
    }

    .project-table tr:hover td {
      background-color: rgba(42, 92, 141, 0.03);
    }

    /* Action Buttons */
    .action-btns {
      display: flex;
      gap: 10px;
    }

    .action-btn {
      padding: 6px 12px;
      border-radius: 4px;
      font-size: 13px;
      font-weight: 500;
      text-decoration: none;
      display: inline-flex;
      align-items: center;
      gap: 5px;
      transition: all 0.2s ease;
    }

    .edit-btn {
      background: var(--warning);
      color: var(--dark);
    }

    .delete-btn {
      background: var(--danger);
      color: white;
    }

    .view-btn {
      background: var(--success);
      color: white;
    }

    .action-btn:hover {
      transform: translateY(-2px);
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    /* Status Badges */
    .budget-badge {
      font-weight: 600;
      color: var(--primary);
    }

    /* Responsive Design */
    @media (max-width: 768px) {
      .main-container {
        margin-top: 70px;
        padding: 0 15px;
      }

      .page-header {
        flex-direction: column;
        align-items: flex-start;
        gap: 15px;
      }

      .project-table {
        display: block;
        overflow-x: auto;
      }

      .action-btns {
        flex-wrap: wrap;
      }
    }
  </style>
</head>
<body>
<nav class="navbar">
  <div class="navdiv">
    <div class="logo"><a href="index.jsp">ConstructionXpert</a></div>
    <ul class="menu">
      <li>
        <a class="link" href="index.jsp"><i class="fas fa-home"></i> Accueil</a>
      </li>
      <li>
        <a class="link" href="projet?action=new"><i class="fas fa-project-diagram"></i> Projet</a>
        <ul class="submenu">
          <li><a class="link" href="projet?action=new">Ajouter</a></li>
          <li><a class="link" href="projet?action=afficher">Afficher</a></li>
        </ul>
      </li>
      <li>
        <a class="link" href="tache?action=new"><i class="fas fa-tasks"></i> Tâche</a>
        <ul class="submenu">
          <li><a class="link" href="tache?action=new">Ajouter</a></li>
          <li><a class="link" href="tache?action=afficher">Afficher</a></li>
        </ul>
      </li>
      <li>
        <a class="link" href="ressource?action=new"><i class="fas fa-users"></i> Ressource</a>
        <ul class="submenu">
          <li><a class="link" href="ressource?action=new">Ajouter</a></li>
          <li><a class="link" href="ressource?action=afficher">Afficher</a></li>
        </ul>
      </li>
      <li>
        <button class="logout-btn"><a href="logout"><i class="fas fa-sign-out-alt"></i> Déconnexion</a></button>
      </li>
    </ul>
  </div>
</nav>

<div class="main-container">
  <div class="page-header">
    <h1 class="page-title"><i class="fas fa-project-diagram"></i> Liste des Projets</h1>
    <a href="projet?action=new" class="add-btn">
      <i class="fas fa-plus"></i> Nouveau Projet
    </a>
  </div>

  <div class="project-table-container">
    <table class="project-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Date Début</th>
        <th>Date Fin</th>
        <th>Budget</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="projet" items="${projets}">
        <tr>
          <td>${projet.id_PR}</td>
          <td>${projet.nom}</td>
          <td>${projet.description}</td>
          <td>${projet.date_debut}</td>
          <td>${projet.date_fin}</td>
          <td><span class="budget-badge">${projet.budget} DH</span></td>
          <td>
            <div class="action-btns">
              <a href="projet?action=trouverParid&id=${projet.id_PR}" class="action-btn edit-btn">
                <i class="fas fa-edit"></i> Modifier
              </a>
              <a href="projet?action=supprimer&id_PR=${projet.id_PR}" class="action-btn delete-btn">
                <i class="fas fa-trash-alt"></i> Supprimer
              </a>
              <a href="tache?action=parProjet&projet_id=${projet.id_PR}" class="action-btn view-btn">
                <i class="fas fa-eye"></i> Afficher
              </a>
            </div>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>