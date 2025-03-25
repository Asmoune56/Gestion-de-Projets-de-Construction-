
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;

    }

    .navbar {
        background: white;
        font-family: calibri;
        padding-right: 15px;
        padding-left: 15px;
    }

    .navdiv {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .logo {
        font-size: 35px;
        font-weight: 600;
    }

    .logo a {
        color: rgb(0, 0, 0);
        text-decoration: none;
    }

    .menu {
        list-style: none;
        display: flex;
    }

    .menu li {
        position: relative;
        padding: 10px; /* Keep the padding */
    }

    .menu li .link  {
        color: rgb(0, 0, 0);
        font-size: 15px;
        font-weight: 600;
        text-decoration: none;
        padding: 10px;
        display: block; /* Make the link fill the li */
    }

    .menu li:hover .submenu {
        display: block;
    }

    .submenu {
        display: none;
        position: absolute;
        background: white;
        list-style: none;
        padding: 10px;
        box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
        z-index: 1; /* Ensure submenu appears on top */
    }

    .submenu li {
        padding: 5px 10px;
    }

    .submenu li a {
        white-space: nowrap;
        display: block; /* Ensure link fills the list item */
    }

    ul button {
        background-color: rgb(0, 0, 0);
        margin-left: 30px;
        margin-top: 8px;
        border-radius: 10px;
        padding: 5px;
        width: 140px;
        border: none;

    }

    button a {
        text-decoration: none;
        color: white;
        font-weight: 600;
        font-size: 15px;
    }



    header{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 92vh;
        background-image:  url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdWdG1gXrG1ECnG-M_UiRRQKL246_Z3OctSdM6w206Bwq-E9RRpTlZmwNaLv1M_s-z5g4&usqp=CAU');
        background-size: cover ;
    }
    .container{

        max-width: 650px;
        padding: 20px;
        margin: 0px 28px;
        border-radius: 10px;
        overflow: hidden;
        background: rgba(0, 0,0, 0.2);
        box-shadow: 0 15px 20px rgba(0, 0,0, 0.6);
    }
    h2{
        font-size: 26px;
        font-weight: bold;
        text-align: center;
        color: #fff;
        padding-bottom: 10px;
        border-bottom: 3px solid rgb(255, 255, 255);
        text-transform: uppercase;
        font-family: initial;
    }
    .content{
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        padding: 20px 0;
    }
    .input-box{
        display: flex;
        flex-wrap: wrap;
        width: 50%;
        padding-bottom: 15px;
    }

    .input-box label{
        width: 95%;
        color: #fff;
        font-weight: bold;
        margin: 5px 0px;
    }
    .input-box input{
        height: 40px;
        width: 95%;
        padding: 0 10px;
        border-radius: 8px;
        border: 1px solid #fff;
        outline: none;
    }
    .input-box input:is(:focus,:valid){
        box-shadow: 0 3px 5px rgba(0, 0,0, 0.2)  ;
    }
    .button-container{
        width: 50%;
        display: flex;
        align-items: center;
        justify-content: center;


    }
    .button-container button{
        margin-top: 10px;
        background-color: black;
        color: white;
        font-weight: bold;
        font-size: 16px;
        border: none;
        padding:  8px  27px;;
        border-radius: 30px;
        cursor: pointer;
        text-transform: uppercase;
        transition: 1s;
        margin-left: 300px;
    }
    .button-container button:hover{
        background-color: rgb(255, 255, 255);
        color: rgb(0, 0, 0)
    }
</style>
<body>
<nav class="navbar">
    <div class="navdiv">
        <div class="logo"><a href="index.jsp">ConstructionXpert</a></div>
        <ul class="menu">
            <li>
                <a class="link" href="index.jsp">Home</a>
            </li>
            <li>
                <a class="link" href="projet?action=new">Projet</a>
                <ul class="submenu">
                    <li><a class="link" href="projet?action=new">Ajouter</a></li>
                    <li><a class="link" href="projet?action=afficher">Afficher</a></li>
                </ul>
            </li>
            <li>
                <a class="link" href="tache?action=new">Tache</a>
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
                <button><a href="logout">Logout</a></button>
            </li>
        </ul>
    </div>
</nav>
<header>
    <div class="container">
        <form action="ressource?action=ajouter" method="post">
            <h2>Ressource</h2>
            <div class="content">
                <div class="input-box">
                    <label>ajouter un Ressource : </label><br>
                    <input type="text" name="nom" ><br>
                </div>
                <div class="input-box">
                    <label>Types :</label><br>
                    <input type="text" name="type"><br>
                </div>
                <div class="input-box">
                    <label>quantite :</label><br>
                    <input type="number" name="quantite" ><br>
                </div>
                <div class="input-box">
                    <label>Nom fournisseur :</label><br>
                    <input type="text" name="fournisseur" ><br><br>
                </div>
                <div class="button-container">
                    <button type="submit">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</header>
</body>
</html>
