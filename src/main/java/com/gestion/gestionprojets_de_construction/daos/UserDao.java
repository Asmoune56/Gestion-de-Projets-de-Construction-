package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";

    // Requêtes SQL
    private static final String INSERT_ADMIN = "INSERT INTO admin (email, mot_de_passe) VALUES (?, ?)";
    private static final String SELECT_ALL_ADMINS = "SELECT * FROM admin";
    private static final String UPDATE_ADMIN = "UPDATE admin SET email = ?, mot_de_passe = ? WHERE id = ?";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE id = ?";
    private static final String SELECT_USER = "SELECT * FROM admin WHERE email = ? AND mot_de_passe = ?";

    // Connexion à la base de données
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

    // Ajouter un administrateur
    public void insertAdmin(String email, String mot_de_passe) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ADMIN)) {
            ps.setString(1, email);
            ps.setString(2, mot_de_passe);
            ps.executeUpdate();
            System.out.println("Administrateur ajouté avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer la liste de tous les administrateurs
    public List<User> getAllAdmins() {
        List<User> listAdmins = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_ADMINS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User admin = new User();
                admin.setId(rs.getInt("id"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("mot_de_passe"));
                listAdmins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAdmins;
    }

    // Mettre à jour un administrateur
    public void updateAdmin(int id, String email, String mot_de_passe) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ADMIN)) {
            ps.setString(1, email);
            ps.setString(2, mot_de_passe);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Administrateur mis à jour avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un administrateur
    public void deleteAdmin(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ADMIN)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Administrateur supprimé avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Vérifier si un administrateur existe (connexion)
    public User getUser(String email, String motDePasse) {
        User user = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_USER)) {
            ps.setString(1, email);
            ps.setString(2, motDePasse);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
