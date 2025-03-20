package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Ressource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String INSERT_RESSOURCE = "INSERT INTO ressources (nom, type, quantite) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_RESSOURCE = "SELECT * FROM ressources";
    private static final String UPDATE_RESSOURCE = "UPDATE ressources SET nom = ?, type = ?, quantite = ? WHERE id = ?";
    private static final String DELETE_RESSOURCE = "DELETE FROM ressources WHERE id = ?";

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(jdbcUrl, USER, PASS);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public void insertRessource(Ressource ressource) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_RESSOURCE)) {
            ps.setString(1, ressource.getNom());
            ps.setString(2, ressource.getType());
            ps.setInt(3, ressource.getQuantite());
            ps.executeUpdate();
            System.out.println("Ressource inserted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
        return ressources;
    }

    public void updateRessource(Ressource ressource) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_RESSOURCE)) {
            ps.setString(1, ressource.getNom());
            ps.setString(2, ressource.getType());
            ps.setInt(3, ressource.getQuantite());
            ps.setInt(4, ressource.getId());
            ps.executeUpdate();
            System.out.println("Ressource updated successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRessource(Ressource ressource) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_RESSOURCE)) {
            ps.setInt(1,ressource.getId());
            ps.executeUpdate();
            System.out.println("Ressource deleted successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
