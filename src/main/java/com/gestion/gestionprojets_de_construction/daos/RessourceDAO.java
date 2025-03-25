package com.gestion.gestionprojets_de_construction.daos;



import com.gestion.gestionprojets_de_construction.JDBC.ConnectionJDBC;
import com.gestion.gestionprojets_de_construction.models.Ressource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RessourceDAO {
    private Connection connection;
    public RessourceDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionJDBC.getConnection();
    }
    public void ajouterRessource(Ressource ressource) throws SQLException {
        String query = "insert into Ressource(nom,types,quantite,fournisseur) values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,ressource.getNom());
        preparedStatement.setString(2,ressource.getTypes());
        preparedStatement.setDouble(3,ressource.getQuantite());
        preparedStatement.setString(4,ressource.getFournisseur());

        preparedStatement.execute();
    }
    public void supprimerRessource(int id_RESS) throws SQLException {
        String query = "delete from Ressource where id_RESS = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id_RESS);
        preparedStatement.execute();
    }
    public List<Ressource> afficherRessource() throws SQLException {
        List<Ressource> ressources = new ArrayList<>();
        String query = "select * from Ressource";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ressources.add(new Ressource(
                    resultSet.getInt("id_RESS"),
                    resultSet.getString("nom"),
                    resultSet.getString("types"),
                    resultSet.getDouble("quantite"),
                    resultSet.getString("fournisseur")));}
        return ressources;
    }
    public void modifierRessource(Ressource ressource) throws SQLException {
        String  sql = "UPDATE ressource SET nom = ?, types = ?, quantite = ?, fournisseur = ? WHERE id_RESS = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ressource.getNom());
        preparedStatement.setString(2, ressource.getTypes());
        preparedStatement.setDouble(3, ressource.getQuantite());
        preparedStatement.setString(4, ressource.getFournisseur());
        preparedStatement.setInt(5, ressource.getId_RESS());
        preparedStatement.execute();
    }
    public Ressource afficherRessourceParid(int id_RESS) throws SQLException {
        String query = "select * from Ressource where id_RESS = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id_RESS);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Ressource(
                            resultSet.getInt("id_RESS"),
                            resultSet.getString("nom"),
                            resultSet.getString("types"),
                            resultSet.getDouble("quantite"),
                            resultSet.getString("fournisseur")
                    );
                }
            }
        }
        return null;
    }

    public List<Ressource> findAllWithStock() throws SQLException {
        List<Ressource> resources = new ArrayList<>();
        String sql = "SELECT * FROM resources WHERE quantity > 0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ressource ressource = new Ressource();
            ressource.setId_RESS(resultSet.getInt("id_RESS"));
            ressource.setNom(resultSet.getString("nom"));
            ressource.setTypes(resultSet.getString("types"));
            ressource.setQuantite(resultSet.getDouble("quantite"));
            ressource.setFournisseur(resultSet.getString("fournisseur"));
            resources.add(ressource);
        }

        return resources;
    }
}

