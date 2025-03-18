package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Ressource;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RessourceDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final String  jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";


    private static final String INSERT_Ressource = "insert into ressources "+" (nom,type,quantite) values "+"(?,?,?)";
    private static final String SELECT_ALL_Ressource = "select * from ressources";
    private  static final String UPDATE_Ressource = "UPDATE ressources SET, description = ?, dateDebut = ?, dateFin = ? WHERE id = ?";
    private static final String DELETE_Ressource = "delete from ressources where id = ?";


    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(jdbcUrl, USER, PASS);
            System.out.println("Connected to database");

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    public void insertRessource(Ressource ressource) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_Ressource);
            ps.setString(1,ressource.getNom());
            ps.setString(2,ressource.getType());
            ps.setInt(3,ressource.getQuantite());
            ps.executeUpdate();
            System.out.println("Ressource inserted successfully");
            con.close();
        }catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
public List<Ressource> selectAllRessource() {
        List<Ressource> ressources = new ArrayList<Ressource>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_Ressource);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Ressource ressource = new Ressource();
                ressource.setId(ressource.getId());
                ressource.setNom(rs.getString("nom"));
                ressource.setType(rs.getString("type"));
                ressource.setQuantite(rs.getInt("quantite"));
                ressources.add(ressource);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ressources;
    }
    public void updateRessource(Ressource ressource) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_Ressource);
            ps.setString(1,ressource.getNom());
            ps.setString(2,ressource.getType());
            ps.setInt(3,ressource.getQuantite());
            ps.setInt(4,ressource.getId());
            ps.executeUpdate();
            System.out.println("Ressource updated successfully");
            con.close();

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }
    public void deleteRessource(Ressource ressource) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_Ressource);
            ps.setInt(1,ressource.getId());
        }catch (SQLException e ){
            throw new RuntimeException(e);

        }
    }


}
