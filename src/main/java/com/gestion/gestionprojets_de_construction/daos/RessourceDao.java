package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Ressource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String INSERT_RESSOURCE = "INSERT INTO ressources (nom, type, quantite) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_RESSOURCE = "SELECT * FROM ressources";
    private static final String SELECT_RESSOURCE_BY_ID = "SELECT * FROM ressources WHERE id = ?";
    private static final String UPDATE_RESSOURCE = "UPDATE ressources SET nom = ?, type = ?, quantite = ? WHERE id = ?";
    private static final String DELETE_RESSOURCE = "DELETE FROM ressources WHERE id = ?";

    protected Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(JDBC_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    public void insertRessource(Ressource ressource) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_RESSOURCE)) {
            ps.setString(1, ressource.getNom());
            ps.setString(2, ressource.getType());
            ps.setInt(3, ressource.getQuantite());
            ps.executeUpdate();
            System.out.println("Ressource insérée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'insertion de la ressource", e);
        }
    }

    public List<Ressource> selectAllRessource() {
        List<Ressource> ressources = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_RESSOURCE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ressource ressource = new Ressource();
                ressource.setId(rs.getInt("id"));
                ressource.setNom(rs.getString("nom"));
                ressource.setType(rs.getString("type"));
                ressource.setQuantite(rs.getInt("quantite"));
                ressources.add(ressource);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des ressources", e);
        }
        return ressources;
    }

    public Ressource getRessourceById(int id) {
        Ressource ressource = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_RESSOURCE_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ressource = new Ressource();
                    ressource.setId(rs.getInt("id"));
                    ressource.setNom(rs.getString("nom"));
                    ressource.setType(rs.getString("type"));
                    ressource.setQuantite(rs.getInt("quantite"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération de la ressource par ID", e);
        }
        return ressource;
    }

    public void updateRessource(Ressource ressource) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_RESSOURCE)) {
            ps.setString(1, ressource.getNom());
            ps.setString(2, ressource.getType());
            ps.setInt(3, ressource.getQuantite());
            ps.setInt(4, ressource.getId());
            ps.executeUpdate();
            System.out.println("Ressource mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour de la ressource", e);
        }
    }

    public void deleteRessource(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_RESSOURCE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Ressource supprimée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de la ressource", e);
        }
    }
}
