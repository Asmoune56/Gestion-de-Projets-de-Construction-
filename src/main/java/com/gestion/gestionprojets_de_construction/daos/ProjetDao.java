package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Projet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetDao {
 private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
 private final String jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
 private static final String USER = "root";
 private static final String PASS = "";

 // Requêtes SQL
 private static final String SELECT_ALL_PROJET = "SELECT * FROM projets";
 private static final String SELECT_PROJET_BY_ID = "SELECT * FROM projets WHERE id = ?";
 private static final String INSERT_PROJET = "INSERT INTO projets (nom, description, dateDebut, dateFin, budget) VALUES (?, ?, ?, ?, ?)";
 private static final String DELETE_PROJET = "DELETE FROM projets WHERE id = ?";
 private static final String UPDATE_PROJET = "UPDATE projets SET nom = ?, description = ?, dateDebut = ?, dateFin = ?, budget = ? WHERE id = ?";

 // Connexion
 protected Connection getConnection() {
  Connection con = null;
  try {
   Class.forName(DRIVER);
   con = DriverManager.getConnection(jdbcUrl, USER, PASS);
  } catch (ClassNotFoundException e) {
   throw new RuntimeException("Driver non trouvé", e);
  } catch (SQLException e) {
   throw new RuntimeException("Erreur de connexion à la base de données", e);
  }
  return con;
 }

 // Ajouter un projet
 public void addProjet(Projet projet) {
  try (Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(INSERT_PROJET)) {
   ps.setString(1, projet.getNom());
   ps.setString(2, projet.getDescription());
   ps.setDate(3, projet.getDateDebut());
   ps.setDate(4, projet.getDateFin());
   ps.setDouble(5, projet.getBudget());
   ps.executeUpdate();
   System.out.println("Projet ajouté avec succès !");
  } catch (SQLException e) {
   throw new RuntimeException("Erreur lors de l'ajout du projet", e);
  }
 }

 // les projets
 public List<Projet> getProjets() {
  List<Projet> projets = new ArrayList<>();
  try (Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(SELECT_ALL_PROJET);
       ResultSet rs = ps.executeQuery()) {

   while (rs.next()) {
    Projet projet = new Projet();
    projet.setId(rs.getInt("id"));
    projet.setNom(rs.getString("nom"));
    projet.setDescription(rs.getString("description"));
    projet.setDateDebut(rs.getDate("dateDebut"));
    projet.setDateFin(rs.getDate("dateFin"));
    projet.setBudget(rs.getDouble("budget"));
    projets.add(projet);
   }
  } catch (SQLException e) {
   throw new RuntimeException("Erreur lors de la récupération des projets", e);
  }
  return projets;
 }


 // projet par ID
 public Projet getProjetById(int id) {
  Projet projet = null;
  try (Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(SELECT_PROJET_BY_ID)) {
   ps.setInt(1, id);
   ResultSet rs = ps.executeQuery();
   if (rs.next()) {
    projet = new Projet();
    projet.setId(rs.getInt("id"));
    projet.setNom(rs.getString("nom"));
    projet.setDescription(rs.getString("description"));
    projet.setDateDebut(rs.getDate("dateDebut"));
    projet.setDateFin(rs.getDate("dateFin"));
    projet.setBudget(rs.getDouble("budget"));
   }
  } catch (SQLException e) {
   throw new RuntimeException("Erreur lors de la récupération du projet avec ID: " + id, e);
  }
  return projet;
 }

 // Mettre à jour un projet
 public void updateProjet(Projet projet) {
  try (Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(UPDATE_PROJET)) {
   ps.setString(1, projet.getNom());
   ps.setString(2, projet.getDescription());
   ps.setDate(3, projet.getDateDebut());
   ps.setDate(4, projet.getDateFin());
   ps.setDouble(5, projet.getBudget());
   ps.setInt(6, projet.getId());
   ps.executeUpdate();
   System.out.println("Projet mis à jour avec succès !");
  } catch (SQLException e) {
   throw new RuntimeException("Erreur lors de la mise à jour du projet", e);
  }
 }

 // Supprimer un projet
 public void deleteProjet(int id) {
  try (Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(DELETE_PROJET)) {
   ps.setInt(1, id);
   ps.executeUpdate();
   System.out.println("Projet supprimé avec succès !");
  } catch (SQLException e) {
   throw new RuntimeException("Erreur lors de la suppression du projet", e);
  }
 }
}
