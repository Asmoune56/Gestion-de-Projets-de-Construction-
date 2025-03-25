package com.gestion.gestionprojets_de_construction.daos;


import com.gestion.gestionprojets_de_construction.JDBC.ConnectionJDBC;
import com.gestion.gestionprojets_de_construction.models.Projet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDAO {
    private Connection connection;

    public ProjetDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionJDBC.getConnection();
    }

    public void ajouterProjet(Projet projet) throws SQLException {
        String query = "INSERT INTO projet(nom, description, date_debut, date_fin, budget) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, projet.getNom());
            preparedStatement.setString(2, projet.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(projet.getDate_debut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(projet.getDate_fin().getTime()));
            preparedStatement.setDouble(5, projet.getBudget());
            preparedStatement.executeUpdate();
        }
    }

    public List<Projet> afficherProjet() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        String query = "SELECT * FROM projet";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                projets.add(new Projet(
                        resultSet.getInt("id_PR"),
                        resultSet.getString("nom"),
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getDouble("budget")
                ));
            }
        }
        return projets;
    }

    public void supprimerProjet(int id) throws SQLException {
        String query = "DELETE FROM projet WHERE id_PR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void modifierProjet(Projet projet) throws SQLException {
        String query = "UPDATE projet SET nom=?, description=?, date_debut=?, date_fin=?, budget=? WHERE id_PR=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, projet.getNom());
            preparedStatement.setString(2, projet.getDescription());
            preparedStatement.setDate(3, projet.getDate_debut());
            preparedStatement.setDate(4, projet.getDate_fin());
            preparedStatement.setDouble(5, projet.getBudget());
            preparedStatement.setInt(6, projet.getId_PR());
            preparedStatement.executeUpdate();
        }
    }

    public Projet trouverParId(int id) throws SQLException {
        String query = "SELECT * FROM projet WHERE id_PR = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Projet(
                            resultSet.getInt("id_PR"),
                            resultSet.getString("nom"),
                            resultSet.getString("description"),
                            resultSet.getDate("date_debut"),
                            resultSet.getDate("date_fin"),
                            resultSet.getDouble("budget")
                    );
                }
            }
        }
        return null;
    }
}

