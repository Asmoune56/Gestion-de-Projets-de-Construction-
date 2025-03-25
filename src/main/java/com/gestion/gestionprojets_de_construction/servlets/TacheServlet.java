package com.gestion.gestionprojets_de_construction.servlets;

// Servlets


import com.gestion.gestionprojets_de_construction.daos.ProjetDAO;
import com.gestion.gestionprojets_de_construction.daos.RessourceDAO;
import com.gestion.gestionprojets_de_construction.daos.TacheDAO;
import com.gestion.gestionprojets_de_construction.daos.TacheRessourceDAO;
import com.gestion.gestionprojets_de_construction.models.Projet;
import com.gestion.gestionprojets_de_construction.models.Tache;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tache")
public class TacheServlet extends HttpServlet {
    private TacheDAO tacheDAO;
    private ProjetDAO projetDAO;
    private RessourceDAO ressourceDAO ;

    @Override
    public void init() {
        try {
            ressourceDAO = new RessourceDAO();
            tacheDAO = new TacheDAO();
            projetDAO = new ProjetDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error initializing TacheDAO or ProjetDAO", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "afficher";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "ajouter":
                    ajouterTache(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "modifier":
                    modifierTache(request, response);
                    break;
                case "supprimer":
                    supprimerTache(request, response);
                    break;
                case "afficher":
                    afficherTaches(request, response);
                    break;
                case "parProjet":
                    afficherTachesParProjet(request, response);
                    break;
                case "ajouterRessource":
//                    ajouterRessourcetoTache(request, response);
                    break;
                case "supprimerRessource":
                    supprimerRessourcetoTache(request, response);
                    break;
                default:
                    response.sendRedirect("/tache?action=afficher");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Erreur dans la gestion des tâches", e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Projet> projets = projetDAO.afficherProjet();
        request.setAttribute("projets", projets);

        String projetId = request.getParameter("projet_id");
        if (projetId != null && !projetId.isEmpty()) {
            request.setAttribute("projet_id", projetId);
        }

        request.getRequestDispatcher("/Tache/ajouteTache.jsp").forward(request, response);
    }

    private void ajouterTache(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int projet_id = Integer.parseInt(request.getParameter("projet_id"));
        String description = request.getParameter("description");
        Date date_debut = Date.valueOf(request.getParameter("date_debut"));
        Date date_fin = Date.valueOf(request.getParameter("date_fin"));

        Tache tache = new Tache(projet_id, description, date_debut, date_fin);
        tacheDAO.ajouterTache(tache);

        response.sendRedirect("tache?action=parProjet&projet_id=" + projet_id);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tache tache =  tacheDAO.trouverParId(id);

        if (tache != null) {
            List<Projet> projets = projetDAO.afficherProjet();
            request.setAttribute("projets", projets);
            request.setAttribute("tache", tache);
            request.getRequestDispatcher("/Tache/modifierTache.jsp").forward(request, response);
        } else {
            response.sendRedirect("tache?action=afficher");
        }
    }

    private void modifierTache(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_TA = Integer.parseInt(request.getParameter("id_TA"));
        int projet_id = Integer.parseInt(request.getParameter("projet_id"));
        String description = request.getParameter("description");
        Date date_debut = Date.valueOf(request.getParameter("date_debut"));
        Date date_fin = Date.valueOf(request.getParameter("date_fin"));

        Tache tache = new Tache(id_TA, projet_id, description, date_debut, date_fin);
        tacheDAO.modifierTache(tache);

        response.sendRedirect("tache?action=parProjet&projet_id=" + projet_id);
    }

    private void supprimerTache(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Tache tache = tacheDAO.trouverParId(id);

        if (tache != null) {
            int projet_id = tache.getProjet_id();
            tacheDAO.supprimerTache(id);
            response.sendRedirect("tache?action=parProjet&projet_id=" + projet_id);
        } else {
            // Handle the case where the task is not found (e.g., display an error message)
            response.sendRedirect("tache?action=afficher"); // Redirect to task list or error page
        }
    }

    private void afficherTaches(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Tache> taches = tacheDAO.afficherTaches();
        request.setAttribute("taches", taches);
        request.getRequestDispatcher("/Tache/afficherTache.jsp").forward(request, response);
    }

    private void afficherTachesParProjet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int projet_id = Integer.parseInt(request.getParameter("projet_id"));
        Projet projet = projetDAO.trouverParId(projet_id);
        List<Tache> taches = tacheDAO.afficherTachesParProjet(projet_id);

        request.setAttribute("projet", projet);
        request.setAttribute("taches", taches);
        request.getRequestDispatcher("/Tache/afficherTachesParProjet.jsp").forward(request, response);
    }

    //    public void  ajouterRessourcetoTache(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
//        int tache_id = Integer.parseInt(request.getParameter("id_TA"));
//        int ressource_id = Integer.parseInt(request.getParameter("projet_id"));
//        TacheRessourceDAO tacheRessourceDAO = new TacheRessourceDAO();
//        tacheRessourceDAO.ajouteTacheRessource(tache_id, ressource_id);
//        response.sendRedirect("tache?action=afficher");
//    }
    private void supprimerRessourcetoTache(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
        int tache_id = Integer.parseInt(request.getParameter("tache_id"));
        int ressource_id = Integer.parseInt(request.getParameter("projet_id"));
        TacheRessourceDAO tacheRessourceDAO = new TacheRessourceDAO();
        tacheRessourceDAO.supprimerResourceFromtache(tache_id, ressource_id);
    }

}
