package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Projet;

import java.sql.*;
import java.util.ArrayList;

public class ProjetDao {
 private static final String DRIVER = "com.mysql.jdbc.Driver";
 private final String  jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
 private static final String USER = "root";
 private static final String PASS = "";

     private static final String SELECT_ALL_PROJET = "select * from projets";
     private static final String SELECT_PROJET_BY_ID = "select * from projets where id = ?";
     private static final String INSERT_PROJET = "insert into projets "+" (nom,description,dateDebut,dateFin,budget) values "+"(?,?,?,?,?)";
     private static final String DELETE_PROJET = "delete from projets where id = ?";
     private  static final String UPDATE_PROJET = "UPDATE projets SET nom = ?, description = ?, dateDebut = ?, dateFin = ? WHERE id = ?";


     protected Connection getConnection(){
      Connection con = null;
      try {
       Class.forName(DRIVER);
       con = DriverManager.getConnection(jdbcUrl, USER, PASS);
       System.out.println("Connected to database");

      }catch (ClassNotFoundException e){
       System.out.println("Class not found");
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      return con;
     }

     public void addProjet(Projet projet) throws SQLException {
      try {
      Connection con = getConnection();
      PreparedStatement ps = con.prepareStatement(INSERT_PROJET);
      ps.setString(1,projet.getNom());
      ps.setString(2,projet.getDescription());
      ps.setDate(3,projet.getDateDebut());
      ps.setDate(4, projet.getDateFin());
      ps.setDouble(5,projet.getBudget());
      System.out.println("projet added");
     }catch (SQLException e) {
      System.out.println("SQL Error : " + e.getMessage());
      }
     }

     public ArrayList<Projet> getProjets() throws SQLException {
      ArrayList<Projet> projets = new ArrayList<>();
      try {
       Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(SELECT_ALL_PROJET);
       ResultSet rs = ps.executeQuery();
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
      }catch (SQLException e) {
       System.out.println("SQL Error : " + e.getMessage());
      }
      return projets;
     }
     public Projet getProjetById(int id) throws SQLException {
      Projet projet = null;
      try {
       Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(SELECT_PROJET_BY_ID);
       ps.setInt(1,id);
       ResultSet rs = ps.executeQuery();
       while (rs.next()) {
        projet = new Projet();
        projet.setId(rs.getInt("id"));
        projet.setNom(rs.getString("nom"));
        projet.setDescription(rs.getString("description"));
        projet.setDateDebut(rs.getDate("dateDebut"));
        projet.setDateFin(rs.getDate("dateFin"));
        projet.setBudget(rs.getDouble("budget"));
       }
      }catch (SQLException e) {
       System.out.println("SQL Error : " + e.getMessage());

      }
      return projet;
     }
     public void updateProjet(Projet projet) throws SQLException {

      try {
       Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(UPDATE_PROJET);
       ps.setString(1,projet.getNom());
       ps.setString(2,projet.getDescription());
       ps.setDate(3,projet.getDateDebut());
       ps.setDate(4,projet.getDateFin());
       ps.setDouble(5,projet.getBudget());
       ps.setInt(6,projet.getId());
       System.out.println("projet updated");
       ps.executeUpdate();

      }catch (SQLException e) {
       System.out.println("SQL Error : " + e.getMessage());
      }
     }
     public void deleteProjet(int id) throws SQLException {
      try {
       Connection con = getConnection();
       PreparedStatement ps = con.prepareStatement(DELETE_PROJET);
       ps.setInt(1,id);
       ps.executeUpdate();

      }catch (SQLException e) {
       System.out.println("SQL Error : " + e.getMessage());
      }
     }



}