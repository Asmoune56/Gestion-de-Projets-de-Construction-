<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Projet | ConstructionXpert</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary: #8724a8;
            --primary-light: #3A7CB9;
            --secondary: #4ECDC4;
            --accent: #FF6B6B;
            --dark: #292F36;
            --light: #F7FFF7;
            --gray: #E0E0E0;
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
            background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
            url('https://png.pngtree.com/thumb_back/fh260/background/20240611/pngtree-construction-tools-in-the-form-of-house-on-wooden-background-image_15747755.jpg') no-repeat center center/cover;
            min-height: 100vh;
            color: var(--dark-color);
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


        header {
            width: 100%;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
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

        .input-box.full-width {
            width: 100%;
        }

        .input-box label {
            display: block;
            color: white;
            font-weight: 500;
            margin-bottom: 8px;
            font-size: 14px;
        }

        .input-box input {
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

        .input-box input:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 3px rgba(42, 92, 141, 0.3);
            background: rgba(255, 255, 255, 0.3);
        }

        .input-box input::placeholder {
            color: rgba(255, 255, 255, 0.7);
        }

        .button-container {
            width: 100%;
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .submit-btn {
            background: linear-gradient(135deg, var(--primary), var(--primary-light));
            color: white;
            border: none;
            border-radius: 8px;
            padding: 12px 30px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            box-shadow: 0 4px 15px rgba(42, 92, 141, 0.3);
        }

        .submit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(42, 92, 141, 0.4);
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                padding: 30px 20px;
            }

            .input-box {
                width: 100%;
            }

            .menu {
                display: none;
            }

            .navdiv {
                flex-direction: column;
                align-items: flex-start;
                gap: 15px;
            }

            .logout-btn {
                margin-left: 0;
                margin-top: 10px;
            }
        }

        @media (max-width: 480px) {
            .container {
                margin: 10px;
                padding: 20px 15px;
            }

            h2 {
                font-size: 22px;
                margin-bottom: 20px;
            }

            .input-box input {
                padding: 10px 12px;
            }

            .submit-btn {
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

<header>
    <div class="container">
        <h2><i class="fas fa-plus-circle"></i> Ajouter un Projet</h2>
        <form action="projet?action=ajouter" method="post">
            <div class="content">
                <div class="input-box">
                    <label for="nom"><i class="fas fa-project-diagram"></i> Nom projet :</label>
                    <input type="text" id="nom" name="nom" placeholder="Entrez le nom du projet" required>
                </div>

                <div class="input-box">
                    <label for="description"><i class="fas fa-align-left"></i> Description :</label>
                    <input type="text" id="description" name="description" placeholder="Entrez la description" required>
                </div>

                <div class="input-box">
                    <label for="debut"><i class="fas fa-calendar-alt"></i> Date de Début :</label>
                    <input type="date" id="debut" name="debut" required>
                </div>

                <div class="input-box">
                    <label for="fin"><i class="fas fa-calendar-check"></i> Date de Fin :</label>
                    <input type="date" id="fin" name="fin" required>
                </div>

                <div class="input-box">
                    <label for="budget"><i class="fas fa-money-bill-wave"></i> Budget :</label>
                    <input type="number" id="budget" name="budget" placeholder="Montant en DH" required>
                </div>

                <div class="button-container">
                    <button type="submit" class="submit-btn">
                        <i class="fas fa-save"></i> Ajouter le Projet
                    </button>
                </div>
            </div>
        </form>
    </div>
</header>
</body>
</html>