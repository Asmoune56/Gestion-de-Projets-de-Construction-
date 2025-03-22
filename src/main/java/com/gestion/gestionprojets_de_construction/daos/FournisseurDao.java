package com.gestion.gestionprojets_de_construction.daos;


import com.gestion.gestionprojets_de_construction.models.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";

    // Requêtes SQL
    private static final String INSERT_FOURNISSEUR = "INSERT INTO fournisseurs (nom, email, telephone, adresse) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_FOURNISSEURS = "SELECT * FROM fournisseurs";
    private static final String UPDATE_FOURNISSEUR = "UPDATE fournisseurs SET nom = ?, email = ?, telephone = ?, adresse = ? WHERE id = ?";
    private static final String DELETE_FOURNISSEUR = "DELETE FROM fournisseurs WHERE id = ?";

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(jdbcUrl, USER, PASS);
            System.out.println("Connected to database");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    // Ajouter un fournisseur
    public void insertFournisseur(Fournisseur fournisseur) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_FOURNISSEUR)) {
            ps.setString(1, fournisseur.getNom());
            ps.setString(2, fournisseur.getEmail());
            ps.setString(3, fournisseur.getTelephone());
            ps.setString(4, fournisseur.getAdresse());
            ps.executeUpdate();
            System.out.println("Fournisseur ajouté avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  liste des fournisseurs
    public List<Fournisseur> selectAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_FOURNISSEURS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setId(rs.getInt("id"));
                fournisseur.setNom(rs.getString("nom"));
                fournisseur.setEmail(rs.getString("email"));
                fournisseur.setTelephone(rs.getString("telephone"));
                fournisseur.setAdresse(rs.getString("adresse"));
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fournisseurs;
    }

    // Mettre à jour un fournisseur
    public void updateFournisseur(Fournisseur fournisseur) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_FOURNISSEUR)) {
            ps.setString(1, fournisseur.getNom());
            ps.setString(2, fournisseur.getEmail());
            ps.setString(3, fournisseur.getTelephone());
            ps.setString(4, fournisseur.getAdresse());
            ps.setInt(5, fournisseur.getId());
            ps.executeUpdate();
            System.out.println("Fournisseur mis à jour avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Supprimer un fournisseur
    public void deleteFournisseur(int fournisseurId) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_FOURNISSEUR)) {
            ps.setInt(1, fournisseurId);
            ps.executeUpdate();
            System.out.println("Fournisseur supprimé avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

