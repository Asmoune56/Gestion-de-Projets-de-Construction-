package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";

    // Requêtes SQL
    private static final String INSERT_ADMIN = "INSERT INTO admin (email, mot_de_passe) VALUES (?, ?)";
    private static final String SELECT_ALL_ADMINS = "SELECT * FROM admin";
    private static final String UPDATE_ADMIN = "UPDATE admin SET email = ?, mot_de_passe = ? WHERE id = ?";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM admin WHERE email = ?";

    // Connexion à la base de données
    protected Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(JDBC_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC non trouvé", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    // Ajouter un administrateur avec mot de passe sécurisé
    public void insertAdmin(String email, String motDePasse) {
        String hashedPassword = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ADMIN)) {
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'administrateur", e);
        }
    }

    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        User user = new User();
        try(Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement("select * from admin where admin.email = ?")){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){

                int id = rs.getInt("id");
                String email1 = rs.getString("email");
                String password = rs.getString("mot_de_passe");
                user.setEmail(email1);
                user.setPassword(password);
                user.setId(id);

            }
        }

        return user;

    }
    public void insertUser(User user) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ADMIN)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'administrateur", e);
        }
    }

    //  liste  administrateurs
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
            throw new RuntimeException("Erreur lors de la récupération des administrateurs", e);
        }
        return listAdmins;
    }

    // Mettre à jour un administrateur
    public void updateAdmin(int id, String email, String motDePasse) {
        String hashedPassword = BCrypt.hashpw(motDePasse, BCrypt.gensalt()); // Rehash du mot de passe
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_ADMIN)) {
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'administrateur", e);
        }
    }

    // Supprimer un administrateur
    public void deleteAdmin(int id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ADMIN)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'administrateur", e);
        }
    }

    // Vérifier utilisateur existe avec un mot de passe
    public User getUser(String email, String password) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("mot_de_passe");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur", e);
        }
        return null;
    }
}
