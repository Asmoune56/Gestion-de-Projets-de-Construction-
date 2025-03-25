<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une Tâche | ConstructionXpert</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-color: #4361ee;
            --secondary-color: #3a0ca3;
            --accent-color: #f72585;
            --dark-color: #1a1a2e;
            --light-color: #f8f9fa;
            --glass-color: rgba(255, 255, 255, 0.15);
            --glass-border: rgba(255, 255, 255, 0.2);
        }

        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            color: var(--dark-color);
        }

        /* Navbar Styles */
        .navbar {
            background: rgba(255, 255, 255, 0.9);
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            padding: 15px 30px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            position: fixed;
            width: 100%;
            z-index: 1000;
            border-bottom: 1px solid var(--glass-border);
        }

        .navdiv {
            display: flex;
            align-items: center;
            justify-content: space-between;
            max-width: 1200px;
            margin: 0 auto;
        }

        .logo {
            font-size: 28px;
            font-weight: 700;
            background: linear-gradient(to right, var(--primary-color), var(--secondary-color));
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
        }

        .logo a {
            text-decoration: none;
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
            color: var(--dark-color);
            font-size: 15px;
            font-weight: 500;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 8px;
            transition: all 0.3s ease;
        }

        .menu li .link:hover {
            background: var(--glass-color);
            color: var(--primary-color);
        }

        .menu li:hover .submenu {
            display: block;
        }

        .submenu {
            display: none;
            position: absolute;
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            -webkit-backdrop-filter: blur(10px);
            list-style: none;
            padding: 10px 0;
            border-radius: 8px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            min-width: 180px;
            z-index: 100;
            border: 1px solid var(--glass-border);
        }

        .submenu li {
            padding: 0;
        }

        .submenu li a {
            display: block;
            padding: 8px 20px;
            color: var(--dark-color);
            transition: all 0.2s ease;
            font-size: 14px;
        }

        .submenu li a:hover {
            background: rgba(67, 97, 238, 0.1);
            color: var(--primary-color);
            padding-left: 25px;
        }

        .logout-btn {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            margin-left: 20px;
            border-radius: 8px;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        .logout-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(67, 97, 238, 0.4);
        }

        .logout-btn a {
            text-decoration: none;
            color: white;
            font-weight: 500;
            font-size: 15px;
        }

        /* Main Content */
        header {
            width: 100%;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
            url('https://www.ie.edu/insights/wp-content/uploads/2020/11/VanSchendel-Construction.jpg') no-repeat center center/cover;
            padding: 100px 20px 20px;
        }

        .container {
            max-width: 800px;
            width: 100%;
            padding: 40px;
            margin: 20px;
            border-radius: 15px;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(15px);
            -webkit-backdrop-filter: blur(15px);
            border: 1px solid var(--glass-border);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        }

        h2 {
            font-size: 28px;
            font-weight: 600;
            text-align: center;
            color: white;
            padding-bottom: 15px;
            margin-bottom: 30px;
            border-bottom: 2px solid rgba(255, 255, 255, 0.3);
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .content {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            gap: 20px;
        }

        .input-box {
            width: calc(50% - 10px);
            margin-bottom: 20px;
        }

        .input-box label {
            display: block;
            color: white;
            font-weight: 500;
            margin-bottom: 8px;
            font-size: 14px;
        }

        .input-box input,
        .input-box select {
            width: 100%;
            padding: 12px 15px;
            background: rgba(255, 255, 255, 0.2);
            border: 1px solid rgba(255, 255, 255, 0.3);
            border-radius: 8px;
            outline: none;
            color: white;
            font-size: 14px;
            transition: all 0.3s ease;
        }

        .input-box input:focus,
        .input-box select:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 3px rgba(67, 97, 238, 0.3);
            background: rgba(255, 255, 255, 0.3);
        }

        .input-box input::placeholder {
            color: rgba(255, 255, 255, 0.7);
        }

        .button-container {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }

        .btn {
            padding: 12px 30px;
            border-radius: 8px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 8px;
        }

        .btn-submit {
            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
            color: white;
            border: none;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(67, 97, 238, 0.4);
        }

        .btn-back {
            background: rgba(255, 255, 255, 0.1);
            color: white;
            border: 1px solid rgba(255, 255, 255, 0.3);
        }

        .btn-back:hover {
            background: rgba(255, 255, 255, 0.2);
            transform: translateY(-2px);
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .input-box {
                width: 100%;
            }

            .container {
                padding: 30px 20px;
            }

            .button-container {
                flex-direction: column;
                gap: 15px;
            }

            .btn {
                width: 100%;
                justify-content: center;
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
                <a class="link" href="index.jsp">Accueil</a>
            </li>
            <li>
                <a class="link" href="projet?action=new">Projet</a>
                <ul class="submenu">
                    <li><a class="link" href="projet?action=new">Ajouter</a></li>
                    <li><a class="link" href="projet?action=afficher">Afficher</a></li>
                </ul>
            </li>
            <li>
                <a class="link" href="tache?action=new">Tâche</a>
                <ul class="submenu">
                    <li><a class="link" href="tache?action=new">Ajouter</a></li>
                    <li><a class="link" href="tache?action=afficher">Afficher</a></li>
                </ul>
            </li>
            <li>
                <a class="link" href="ressource?action=new">Ressource</a>
                <ul class="submenu">
                    <li><a class="link" href="ressource?action=new">Ajouter</a></li>
                    <li><a class="link" href="ressource?action=afficher">Afficher</a></li>
                </ul>
            </li>
            <li>
                <button class="logout-btn"><a href="logout">Déconnexion</a></button>
            </li>
        </ul>
    </div>
</nav>

<header>
    <div class="container">
        <h2><i class="fas fa-edit"></i> Modifier une Tâche</h2>
        <form action="tache?action=modifier" method="post">
            <div class="content">
                <input type="hidden" name="id_TA" value="${tache.id_TA}">

                <div class="input-box">
                    <label for="projet_id"><i class="fas fa-project-diagram"></i> Projet:</label>
                    <select name="projet_id" id="projet_id">
                        <c:forEach items="${projets}" var="projet">
                            <option value="${projet.id_PR}" ${projet.id_PR == tache.projet_id ? 'selected' : ''}>${projet.nom}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-box">
                    <label for="description"><i class="fas fa-align-left"></i> Description:</label>
                    <input type="text" id="description" name="description" value="${tache.description}" required>
                </div>

                <div class="input-box">
                    <label for="date_debut"><i class="fas fa-calendar-alt"></i> Date de Début:</label>
                    <input type="date" id="date_debut" name="date_debut" value="${tache.date_debut}" required>
                </div>

                <div class="input-box">
                    <label for="date_fin"><i class="fas fa-calendar-check"></i> Date de Fin:</label>
                    <input type="date" id="date_fin" name="date_fin" value="${tache.date_fin}" required>
                </div>

                <div class="button-container">
                    <a href="tache?action=afficher" class="btn btn-back">
                        <i class="fas fa-arrow-left"></i> Retour
                    </a>
                    <button type="submit" class="btn btn-submit">
                        <i class="fas fa-save"></i> Enregistrer
                    </button>
                </div>
            </div>
        </form>
    </div>
</header>
</body>
</html>