package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final String  jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";


    private static final String INSERT_TACHE = "insert into taches "+" (description,dateDebut,dateFin) values "+"(?,?,?,?,?)";
    private static final String SELECT_ALL_TACHE = "select * from taches";
    private  static final String UPDATE_TACHE = "UPDATE taches SET, description = ?, dateDebut = ?, dateFin = ? WHERE id = ?";
    private static final String DELETE_TACHE = "delete from taches where id = ?";


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

    public void insertTache(Tache tache) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_TACHE);
            ps.setString(1,tache.getDescription());
            ps.setDate(2,tache.getDateDebut());
            ps.setDate(3,tache.getDateFin());
            System.out.println("tache inserted");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Tache> getAllTaches() {
        List<Tache> taches = new ArrayList<Tache>();
        try {
            Connection con = getConnection();
            PreparedStatement ps =con.prepareStatement(SELECT_ALL_TACHE);
            ResultSet rs = ps .executeQuery();
            while (rs.next()) {
                Tache tache = new Tache();
                tache.setId(rs.getInt("id"));
                tache.setDescription(rs.getString("description"));
                tache.setDateDebut(rs.getDate("dateDebut"));
                tache.setDateFin(rs.getDate("dateFin"));
                taches.add(tache);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return taches;
    }
    public void updateTache(Tache tache) {
       try {
           Connection con = getConnection();
           PreparedStatement ps = con.prepareStatement(UPDATE_TACHE);
           ps.setString(1,tache.getDescription());
           ps.setDate(2,tache.getDateDebut());
           ps.setDate(3,tache.getDateFin());
           ps.setInt(4,tache.getId());
           System.out.println("tache updated");

       }catch (SQLException e){
           throw new RuntimeException(e);
       }

    }
    public void deleteTache(int id) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_TACHE);
            ps.setInt(1,id);
        }catch (SQLException e){
            throw new RuntimeException(e);

        }
    }
}
