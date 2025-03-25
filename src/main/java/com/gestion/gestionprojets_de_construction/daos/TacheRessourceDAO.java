package com.gestion.gestionprojets_de_construction.daos;





import com.gestion.gestionprojets_de_construction.JDBC.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TacheRessourceDAO {
    private Connection connection;
    public TacheRessourceDAO() throws SQLException, ClassNotFoundException {
        connection = ConnectionJDBC.getConnection();
    }
    public void ajouterRessourcedeTache(int id_ressource, int id_tache) throws SQLException {
        connection.setAutoCommit(false);
        try {
            // Verify resource exists
            String checkResource = "SELECT id_RESS FROM Ressource WHERE id_RESS = ?";
            try (PreparedStatement ps = connection.prepareStatement(checkResource)) {
                ps.setInt(1, id_ressource);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new SQLException("Ressource ID " + id_ressource + " non trouvée");
                }
            }

            // Verify task exists
            String checkTask = "SELECT id_TA FROM tache WHERE id_TA = ?";
            try (PreparedStatement ps = connection.prepareStatement(checkTask)) {
                ps.setInt(1, id_tache);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new SQLException("Tâche ID " + id_tache + " non trouvée");
                }
            }

            // Add resource to task
            String insertSql = "INSERT INTO Tache_Ressource (id_ressource, id_tache) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
                ps.setInt(1, id_ressource);
                ps.setInt(2, id_tache);
                ps.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    public void  supprimerResourceFromtache(int id_ressource,int id_tache) throws SQLException {
        String query = "DELETE FROM Tache_Ressource WHERE id_ressource = ? AND id_tache = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_ressource);
        preparedStatement.setInt(2, id_tache);
        preparedStatement.executeUpdate();
    }
    public List<Integer> selectAllTacheRessourceParsid(int id_tache) throws SQLException {
        ArrayList<Integer> ressourceid = new ArrayList<Integer>();
        String query = "SELECT * FROM Tache_Ressource WHERE id_tache = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id_tache);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            ressourceid.add(resultSet.getInt("id_ressource"));
        }
        return ressourceid;
    }

}

