package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class TacheDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String INSERT_TACHE = "INSERT INTO taches (description, dateDebut, dateFin) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_TACHE = "SELECT * FROM taches";
    private static final String UPDATE_TACHE = "UPDATE taches SET description = ?, dateDebut = ?, dateFin = ? WHERE id = ?";
    private static final String DELETE_TACHE = "DELETE FROM taches WHERE id = ?";
    private static final String SELECT_TACHE_BY_ID = "SELECT * FROM taches WHERE id = ?";


    // connection
    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(jdbcUrl, USER, PASS);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
        return con;
    }

    // Insert  Tache
    public void insertTache(Tache tache) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_TACHE)) {
            ps.setString(1, tache.getDescription());
            ps.setDate(2, tache.getDateDebut());
            ps.setDate(3, tache.getDateFin());
            ps.executeUpdate();  // Execute the insert
            System.out.println("Tache inserted");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting tache: " + e.getMessage());
        }
    }

    //  all Taches
    public List<Tache> getAllTaches() {
        List<Tache> taches = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_TACHE);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Tache tache = new Tache();
                tache.setId(rs.getInt("id"));
                tache.setDescription(rs.getString("description"));
                tache.setDateDebut(rs.getDate("dateDebut"));
                tache.setDateFin(rs.getDate("dateFin"));
                taches.add(tache);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving taches: " + e.getMessage());
        }
        return taches;
    }

    // Update  Tache
    public void updateTache(Tache tache) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_TACHE)) {
            ps.setString(1, tache.getDescription());
            ps.setDate(2, tache.getDateDebut());
            ps.setDate(3, tache.getDateFin());
            ps.setInt(4, tache.getId());
            ps.executeUpdate();  // Execute the update
            System.out.println("Tache updated");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating tache: " + e.getMessage());
        }
    }

    // Delete Tache
    public void deleteTache(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_TACHE)) {
            ps.setInt(1, id);
            ps.executeUpdate();  // Execute the delete
            System.out.println("Tache deleted");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting tache: " + e.getMessage());
        }
    }
    public Tache getTacheById(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_TACHE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Tache(rs.getString("description"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de la tâche : " + e.getMessage());
        }
        return null;
    }
}
