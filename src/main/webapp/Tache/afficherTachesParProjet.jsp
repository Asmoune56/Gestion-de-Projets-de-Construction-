<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tâches du Projet: ${projet.nom} | ConstructionXpert</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
  <style>
    :root {
      --primary: #4361ee;
      --primary-dark: #3a0ca3;
      --secondary: #f72585;
      --accent: #4cc9f0;
      --dark: #1a1a2e;
      --light: #f8f9fa;
      --success: #38b000;
      --warning: #ffaa00;
      --danger: #ef233c;
      --glass: rgba(255, 255, 255, 0.15);
      --glass-border: rgba(255, 255, 255, 0.2);
    }

    * {
      padding: 0;
      margin: 0;
      box-sizing: border-box;
      font-family: 'Poppins', sans-serif;
    }

    body {
      background-color: #f5f7fa;
      color: var(--dark);
    }

    /* Navigation Bar */
    .navbar {
      background: linear-gradient(135deg, var(--primary), var(--primary-dark));
      padding: 15px 30px;
      box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
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
      color: white;
    }

    .logo a {
      text-decoration: none;
      color: inherit;
    }

    .menu {
      list-style: none;
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .menu li {
      position: relative;
    }

    .menu li .link {
      color: rgba(255, 255, 255, 0.9);
      font-size: 14px;
      font-weight: 500;
      text-decoration: none;
      padding: 8px 12px;
      border-radius: 6px;
      transition: all 0.3s ease;
    }

    .menu li .link:hover {
      background: rgba(255, 255, 255, 0.2);
      color: white;
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
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
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
      background: rgba(67, 97, 238, 0.1);
      color: var(--primary);
    }

    .logout-btn {
      background: var(--secondary);
      border: none;
      border-radius: 6px;
      padding: 8px 16px;
      margin-left: 15px;
      cursor: pointer;
      transition: all 0.3s ease;
      box-shadow: 0 4px 15px rgba(247, 37, 133, 0.3);
    }

    .logout-btn:hover {
      transform: translateY(-2px);
      box-shadow: 0 6px 20px rgba(247, 37, 133, 0.4);
    }

    .logout-btn a {
      text-decoration: none;
      color: white;
      font-weight: 500;
      font-size: 14px;
    }

    /* Main Content */
    .container {
      width: 100%;
      min-height: 100vh;
      padding: 100px 30px 30px;
      background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
      url('https://karidesignbuild.com/wp-content/uploads/2024/01/project_scope.jpg') no-repeat center center/cover;
    }

    /* Project Details Card */
    .project-card {
      background: rgba(255, 255, 255, 0.1);
      backdrop-filter: blur(10px);
      -webkit-backdrop-filter: blur(10px);
      border-radius: 12px;
      padding: 25px;
      margin-bottom: 30px;
      border: 1px solid var(--glass-border);
      box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.1);
    }

    .project-card h1 {
      color: white;
      font-size: 28px;
      font-weight: 700;
      margin-bottom: 20px;
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .project-card h1 i {
      color: var(--accent);
    }

    .project-info {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 15px;
    }

    .info-item {
      background: rgba(255, 255, 255, 0.1);
      padding: 15px;
      border-radius: 8px;
      border-left: 4px solid var(--accent);
    }

    .info-item strong {
      color: var(--accent);
      display: block;
      margin-bottom: 5px;
      font-size: 14px;
    }

    .info-item p {
      color: white;
      font-weight: 500;
    }

    /* Task Table */
    .task-table-container {
      background: rgba(255, 255, 255, 0.1);
      backdrop-filter: blur(10px);
      -webkit-backdrop-filter: blur(10px);
      border-radius: 12px;
      padding: 20px;
      margin-bottom: 30px;
      border: 1px solid var(--glass-border);
      overflow-x: auto;
    }

    .task-table {
      width: 100%;
      border-collapse: collapse;
      min-width: 600px;
    }

    .task-table th {
      background: linear-gradient(135deg, var(--primary), var(--primary-dark));
      color: white;
      padding: 15px;
      text-align: left;
      font-weight: 500;
    }

    .task-table td {
      padding: 15px;
      color: white;
      border-bottom: 1px solid rgba(255, 255, 255, 0.1);
    }

    .task-table tr:last-child td {
      border-bottom: none;
    }

    .task-table tr:hover td {
      background: rgba(255, 255, 255, 0.05);
    }

    /* Buttons and Actions */
    .btn {
      display: inline-flex;
      align-items: center;
      gap: 8px;
      padding: 12px 24px;
      border-radius: 8px;
      text-decoration: none;
      font-weight: 500;
      transition: all 0.3s ease;
      margin-bottom: 20px;
    }

    .btn-primary {
      background: var(--success);
      color: white;
      box-shadow: 0 4px 15px rgba(56, 176, 0, 0.3);
    }

    .btn-primary:hover {
      transform: translateY(-2px);
      box-shadow: 0 6px 20px rgba(56, 176, 0, 0.4);
    }

    .action-links {
      display: flex;
      gap: 10px;
    }

    .action-btn {
      padding: 8px 12px;
      border-radius: 6px;
      font-size: 13px;
      font-weight: 500;
      text-decoration: none;
      transition: all 0.2s ease;
    }

    .action-edit {
      background: var(--warning);
      color: var(--dark);
    }

    .action-delete {
      background: var(--danger);
      color: white;
    }

    .action-btn:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* Footer Links */
    .footer-links {
      display: flex;
      flex-wrap: wrap;
      gap: 15px;
      margin-top: 30px;
    }

    .footer-link {
      background: rgba(255, 255, 255, 0.1);
      color: white;
      padding: 12px 20px;
      border-radius: 8px;
      text-decoration: none;
      transition: all 0.3s ease;
      display: flex;
      align-items: center;
      gap: 8px;
      border: 1px solid var(--glass-border);
    }

    .footer-link:hover {
      background: rgba(255, 255, 255, 0.2);
      transform: translateY(-2px);
    }

    /* Responsive adjustments */
    @media (max-width: 768px) {
      .container {
        padding: 90px 15px 15px;
      }

      .project-info {
        grid-template-columns: 1fr;
      }

      .footer-links {
        flex-direction: column;
      }

      .menu {
        display: none;
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

<div class="container">
  <div class="project-card">
    <h1><i class="fas fa-project-diagram"></i> Tâches du Projet: ${projet.nom}</h1>
    <div class="project-info">
      <div class="info-item">
        <strong>Description</strong>
        <p>${projet.description}</p>
      </div>
      <div class="info-item">
        <strong>Budget</strong>
        <p>${projet.budget} DH</p>
      </div>
      <div class="info-item">
        <strong>Date de Début</strong>
        <p>${projet.date_debut}</p>
      </div>
      <div class="info-item">
        <strong>Date de Fin</strong>
        <p>${projet.date_fin}</p>
      </div>
    </div>
  </div>

  <a href="tache?action=new&projet_id=${projet.id_PR}" class="btn btn-primary">
    <i class="fas fa-plus-circle"></i> Ajouter une nouvelle tâche
  </a>

  <div class="task-table-container">
    <table class="task-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Date Début</th>
        <th>Date Fin</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${taches}" var="tache">
        <tr>
          <td>${tache.id_TA}</td>
          <td>${tache.description}</td>
          <td>${tache.date_debut}</td>
          <td>${tache.date_fin}</td>
          <td>
            <div class="action-links">
              <a href="tache?action=edit&id=${tache.id_TA}" class="action-btn action-edit">
                <i class="fas fa-edit"></i> Modifier
              </a>
              <a href="tache?action=supprimer&id=${tache.id_TA}" class="action-btn action-delete">
                <i class="fas fa-trash-alt"></i> Supprimer
              </a>
            </div>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <div class="footer-links">
    <a href="projet?action=afficher" class="footer-link">
      <i class="fas fa-arrow-left"></i> Retour aux projets
    </a>
    <a href="tache?action=afficher" class="footer-link">
      <i class="fas fa-list"></i> Toutes les tâches
    </a>
    <a href="tache?action=new" class="footer-link">
      <i class="fas fa-plus"></i> Nouvelle tâche
    </a>
  </div>
</div>
</body>
</html>