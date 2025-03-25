package com.gestion.gestionprojets_de_construction.servlets;


import com.gestion.gestionprojets_de_construction.daos.RessourceDAO;
import com.gestion.gestionprojets_de_construction.daos.TacheDAO;
import com.gestion.gestionprojets_de_construction.daos.TacheRessourceDAO;
import com.gestion.gestionprojets_de_construction.models.Ressource;
import com.gestion.gestionprojets_de_construction.models.Tache;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tacheRessource")
public class TacheRessourceServlet extends HttpServlet {
    private RessourceDAO ressourceDAO;
    private TacheDAO tacheDAO;
    private TacheRessourceDAO tacheRessourceDAO;
    public void init() {
        try {
            ressourceDAO = new RessourceDAO();
            tacheDAO = new TacheDAO();
            tacheRessourceDAO = new TacheRessourceDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error initializing TacheDAO or ProjetDAO", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {

        }

        try {
            switch (action) {
                case "listeTacheRessource":
                    ListTacheRessource(request,response);
                    break;
                case "afficherAttachForm":
                    Showform(request,response);
                    break;
                case "ajouterTacheRessource":
                    AjouterTacheRessource(request,response);
                    break;
                case "supprimerTacheRessource":
                    SupprimertacheRessource(request,response);
                    break;
                case "afficherTacheRessource":
                    Affichertacheressource(request,response);
                    break;
                default:
                    break;
            }
        }catch (Exception e) {
            throw new ServletException("Erreur dans la gestion des projets", e);
        }
    }

    private void Affichertacheressource(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_tache = Integer.parseInt(request.getParameter("id"));
        Tache taches = tacheDAO.trouverParId(id_tache);

        List<Integer> id_ressource = tacheRessourceDAO.selectAllTacheRessourceParsid(id_tache);
        List<Ressource> tacheRessource = new ArrayList<>();

        for (Integer idRessource : id_ressource) {
            Ressource ressource = ressourceDAO.afficherRessourceParid(idRessource);
            if (ressource != null) {
                tacheRessource.add(ressource);
            }
        }
        request.setAttribute("tacheRessource", tacheRessource);
        request.setAttribute("tache",taches);
        request.getRequestDispatcher("/TacheRessource/TacheRessource.jsp").forward(request,response);

    }

    private void SupprimertacheRessource(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id_ressource = Integer.parseInt(request.getParameter("id_ressource"));
        int id_tache = Integer.parseInt(request.getParameter("id_tache"));
        tacheRessourceDAO.supprimerResourceFromtache(id_ressource,id_tache);
        response.sendRedirect("tacheRessource?action=listeTacheRessource");

    }

    private void AjouterTacheRessource(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id_ressource = Integer.parseInt(request.getParameter("id_ressource"));
        int id_tache = Integer.parseInt(request.getParameter("id_tache"));

        try {
            tacheRessourceDAO.ajouterRessourcedeTache(id_ressource, id_tache);
            response.sendRedirect("tache?action=parProjet&projet_id=" + tacheDAO.trouverParId(id_tache).getProjet_id());
        } catch (SQLException e) {
            request.setAttribute("error", "Erreur lors de l'ajout de la ressource: " + e.getMessage());
            Showform(request, response);
        }
    }

    private void Showform(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_tache = Integer.parseInt(request.getParameter("id_tache"));
        Tache tache = tacheDAO.trouverParId(id_tache);
        List<Ressource> ressources = ressourceDAO.afficherRessource();

        request.setAttribute("tache", tache);
        request.setAttribute("ressources", ressources);
        request.getRequestDispatcher("/TacheRessource/ajouterTacheRessource.jsp").forward(request,response);
    }

    private void ListTacheRessource(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Tache> taches = tacheDAO.afficherTaches();
        List<Ressource> ressources = ressourceDAO.afficherRessource();
        request.setAttribute("taches", taches);
        request.setAttribute("ressources", ressources);
        request.getRequestDispatcher("/TacheRessource/afficherTacheRessource.jsp").forward(request,response);
    }
}

