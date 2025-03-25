package com.gestion.gestionprojets_de_construction.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbprojets_construction", "root", "");
        Statement statement = connection.createStatement();

        // Table Projet
        String ProjetTable = "CREATE TABLE IF NOT EXISTS Projet ("
                + "id_PR INT AUTO_INCREMENT PRIMARY KEY, "
                + "nom VARCHAR(255) NOT NULL, "
                + "description VARCHAR(255) NOT NULL, "
                + "date_debut DATE NOT NULL, "
                + "date_fin DATE NOT NULL, "
                + "budget DOUBLE NOT NULL)";
        statement.executeUpdate(ProjetTable);

        // Table Tache
        String TacheTable = "CREATE TABLE IF NOT EXISTS Tache ("
                + "id_TA INT AUTO_INCREMENT PRIMARY KEY, "
                + "description VARCHAR(254) NOT NULL, "
                + "date_debut DATE NOT NULL, "
                + "date_fin DATE NOT NULL, "
                + "projet_id INT NOT NULL, "
                + "FOREIGN KEY (projet_id) REFERENCES Projet(id_PR))";
        statement.executeUpdate(TacheTable);

        // Table Ressource
        String RessourceTable = "CREATE TABLE IF NOT EXISTS Ressource ("
                + "id_RESS INT AUTO_INCREMENT PRIMARY KEY, "
                + "nom VARCHAR(50) NOT NULL, "
                + "types VARCHAR(50) NOT NULL, "
                + "quantite DOUBLE NOT NULL, "
                + "fournisseur VARCHAR(50) NOT NULL" +
                ")";
        statement.executeUpdate(RessourceTable);

        // Table Tache_Ressource
        String Tache_Ressource = "CREATE TABLE IF NOT EXISTS Tache_Ressource ("
                + "tache_ressource_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "id_ressource INT NOT NULL, "
                + "id_tache INT NOT NULL, "
                + "FOREIGN KEY (id_ressource) REFERENCES Ressource(id_RESS), "
                + "FOREIGN KEY (id_tache) REFERENCES Tache(id_TA))";
        statement.executeUpdate(Tache_Ressource);

        // Table Users
        String UserTable = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50) NOT NULL, "
                + "password VARCHAR(50) NOT NULL)";
        statement.executeUpdate(UserTable);

        return connection;
    }
}

