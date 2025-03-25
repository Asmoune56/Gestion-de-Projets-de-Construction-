package com.gestion.gestionprojets_de_construction.daos;




import com.gestion.gestionprojets_de_construction.JDBC.ConnectionJDBC;
import com.gestion.gestionprojets_de_construction.models.Ressource;
import com.gestion.gestionprojets_de_construction.models.Tache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TacheDAO {
    private Connection connection;

    public TacheDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionJDBC.getConnection();
    }

    public void ajouterTache(Tache tache) throws SQLException {
        String sql = "INSERT INTO tache(projet_id, description, date_debut, date_fin) VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tache.getProjet_id());
            preparedStatement.setString(2, tache.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(tache.getDate_debut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(tache.getDate_fin().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    public List<Tache> afficherTaches() throws SQLException {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                taches.add(new Tache(
                        resultSet.getInt("id_TA"),
                        resultSet.getInt("projet_id"),
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin")));
            }
        }
        return taches;
    }

    public List<Tache> afficherTachesParProjet(int projet_id) throws SQLException {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE projet_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, projet_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    taches.add(new Tache(
                            resultSet.getInt("id_TA"),
                            resultSet.getInt("projet_id"),
                            resultSet.getString("description"),
                            resultSet.getDate("date_debut"),
                            resultSet.getDate("date_fin")));
                }
            }
        }
        return taches;
    }

    public void supprimerTache(int id) throws SQLException {
        String sql = "DELETE FROM tache WHERE id_TA = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void modifierTache(Tache tache) throws SQLException {
        String sql = "UPDATE tache SET projet_id = ?, description = ?, date_debut = ?, date_fin = ? WHERE id_TA = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tache.getProjet_id());
            preparedStatement.setString(2, tache.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(tache.getDate_debut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(tache.getDate_fin().getTime()));
            preparedStatement.setInt(5, tache.getId_TA());
            preparedStatement.executeUpdate();
        }
    }

    public Tache trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM tache WHERE id_TA = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Tache(
                            resultSet.getInt("id_TA"),
                            resultSet.getInt("projet_id"),
                            resultSet.getString("description"),
                            resultSet.getDate("date_debut"),
                            resultSet.getDate("date_fin"));
                }
            }
        }
        return null;
    }
    public void ajouterRessourcedeTache(int id_ressource,int id_tache,double quantiteUtilise) throws SQLException {
        String sql = "SELECT quantite FROM Ressource WHERE id_RESS = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_ressource);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int quantitéactuelle = resultSet.getInt("quantite");
                if (quantitéactuelle < quantiteUtilise) {
                    throw new SQLException("Quantité insuffisante en stock pour la ressource ID " + id_ressource + ". Disponible : " + quantitéactuelle);
                }
            } else {
                throw new SQLException("Ressource ID " + id_ressource + " non trouvée");
            }}
        String Updatequery = "UPDATE Ressource SET quantite = quantite - ? WHERE id_RESS = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(Updatequery)) {
            preparedStatement.setDouble(1, quantiteUtilise);
            preparedStatement.setInt(2, id_ressource);
            preparedStatement.executeUpdate();
        }
        String insertsql = "INSERT INTO Tache_Ressource (id_Tache, id_Ressource,quantiteUtilise) VALUES (?, ?,?) "+
                "ON DUPLICATE KEY UPDATE quantiteUtilise = quantiteUtilise + ?";;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertsql)) {
            preparedStatement.setInt(1, id_tache);
            preparedStatement.setInt(2, id_ressource);
            preparedStatement.setDouble(3, quantiteUtilise);
            preparedStatement.setDouble(4, quantiteUtilise);
            preparedStatement.executeUpdate();
        }}
    public List<Ressource> TrouverRessourcrsParIdTache(int id_Tache) throws SQLException {
        List<Ressource> ressources = new ArrayList<>();
        String sql = "SELECT r.*, tr.quantiteUtilise " +
                "FROM Ressource r " +
                "JOIN Tache_Ressource tr ON r.id_RESS = tr.id_ressource " +
                "WHERE tr.id_tache= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_Tache);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ressource ressource = new Ressource();
                ressource.setId_RESS(resultSet.getInt("id_RESS"));
                ressource.setNom(resultSet.getString("nom"));
                ressource.setTypes(resultSet.getString("types"));
                ressource.setQuantite(resultSet.getDouble("quantite"));
                ressource.setFournisseur(resultSet.getString("fournisseur"));
                ressource.setQuantityUsed(resultSet.getInt("quantityUsed"));
                ressources.add(ressource);
            }
        }
        return ressources;
    }

}


