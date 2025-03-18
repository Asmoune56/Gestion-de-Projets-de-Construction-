package com.gestion.gestionprojets_de_construction.daos;

import com.gestion.gestionprojets_de_construction.models.Administrateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministrateurDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private final String  jdbcUrl = "jdbc:mysql://localhost:3306/DBprojets";
    private static final String USER = "root";
    private static final String PASS = "";


    private static final String INSERT_admin = "insert into admin "+" (email,mot_de_passe) values "+"(?,?)";
    private static final String SELECT_ALL_admin = "select * from admin";
    private  static final String UPDATE_admin = "UPDATE admin SET, email = ?, mot_de_passe = ? = ? WHERE id = ?";
    private static final String DELETE_admin = "delete from admin where id = ?";


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
    public void insertAdmin(String email, String mot_de_passe) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_admin);
            ps.setString(1,email);
            ps.setString(2,mot_de_passe);
            ps.executeUpdate();
            con.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Administrateur> getAllAdmin() {
        List<Administrateur> listAdmin = new ArrayList<Administrateur>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_admin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Administrateur admin = new Administrateur();
                admin.setId(rs.getInt("id"));
                admin.setEmail(rs.getString("email"));
                admin.setMotDePasse(rs.getString("mot_de_passe"));
                listAdmin.add(admin);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listAdmin;
    }
    public void updateAdmin(String email, String mot_de_passe) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_admin);
            ps.setString(1,email);
            ps.setString(2,mot_de_passe);
            ps.executeUpdate();
            con.close();

        }catch (SQLException e){
            e.printStackTrace();

        }
    }
    public void deleteAdmin(int id) {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_admin);
            ps.setInt(1,id);
            ps.executeUpdate();
            con.close();

        }catch (SQLException e){
            e.printStackTrace();

        }
    }

}
