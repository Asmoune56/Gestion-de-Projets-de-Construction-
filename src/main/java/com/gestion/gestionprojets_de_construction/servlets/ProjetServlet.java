package com.gestion.gestionprojets_de_construction.servlets;

import com.gestion.gestionprojets_de_construction.daos.ProjetDAO;
import com.gestion.gestionprojets_de_construction.models.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/projet")
public class ProjetServlet extends HttpServlet {
    private ProjetDAO projetDAO;

    @Override
    public void init() {
        try {
            projetDAO = new ProjetDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error initializing ProjetDAO", e);
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
                    showForm(request, response);
                    break;
                case "ajouter":
                    ajouterProjet(request, response);
                    break;
                case "modifier":
                    modifierProjet(request, response);
                    break;
                case "supprimer":
                    supprimerProjet(request, response);
                    break;
                case "afficher":
                    afficherProjet(request, response);
                    break;
                case "trouverParid":
                    trouverProjetParId(request, response);
                    break;
                default:
                    response.sendRedirect("/projet?action=afficher");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Erreur dans la gestion des projets", e);
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Projet/ajouteProjet.jsp").forward(request, response);
    }

    private void ajouterProjet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        Date date_debut = Date.valueOf(request.getParameter("debut"));
        Date date_fin = Date.valueOf(request.getParameter("fin"));
        Double budget = Double.valueOf(request.getParameter("budget"));


        Projet projet = new Projet(nom, description, date_debut, date_fin, budget);

        projetDAO.ajouterProjet(projet);

        response.sendRedirect("projet?action=afficher");
    }

    private void afficherProjet(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Projet> projets = projetDAO.afficherProjet();
        request.setAttribute("projets", projets);
        request.getRequestDispatcher("/Projet/afficherProjet.jsp").forward(request, response);
    }

    private void modifierProjet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_PR = Integer.parseInt(request.getParameter("id_PR"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        Date date_debut = Date.valueOf(request.getParameter("debut"));
        Date date_fin = Date.valueOf(request.getParameter("fin"));
        Double budget = Double.valueOf(request.getParameter("budget"));

        Projet projet = new Projet(id_PR, nom, description, date_debut, date_fin, budget);
        projetDAO.modifierProjet(projet);

        response.sendRedirect("projet?action=afficher");
    }

    private void supprimerProjet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_PR = Integer.parseInt(request.getParameter("id_PR"));
        projetDAO.supprimerProjet(id_PR);
        response.sendRedirect("projet?action=afficher");
    }

    private void trouverProjetParId(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Projet projetParid = projetDAO.trouverParId(id);

        if (projetParid != null) {
            request.setAttribute("projetParid", projetParid);
            request.getRequestDispatcher("/Projet/modifierProjet.jsp").forward(request, response);
        } else {
            response.sendRedirect("projet?action=afficher");
        }
    }
}

