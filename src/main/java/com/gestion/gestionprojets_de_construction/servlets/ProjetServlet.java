package com.gestion.gestionprojets_de_construction.servlets;

import com.gestion.gestionprojets_de_construction.daos.ProjetDao;
import com.gestion.gestionprojets_de_construction.models.Projet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/projet/*")
public class ProjetServlet extends HttpServlet {
    private final ProjetDao projetDao = new ProjetDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        System.out.println(action);

        // Handle actions based on the path info
        switch (action) {
            case "/new":
                createProjet(req, resp);
                break;
            case "/new-form":
                addProjetForm(req, resp);
                break;
            case "/edit":
                updateProjet(req, resp);
                break;
            case "/edit-form":
                try {
                    updateProjetForm(req, resp);
                } catch (SQLException e) {
                    throw new ServletException("Error while fetching project data for update", e);
                }
                break;
            case "/delete":
                deleteProjet(req, resp);
                break;
            case "/list":
                try {
                    listProjets(req, resp);
                } catch (SQLException e) {
                    throw new ServletException("Error while fetching projects list", e);
                }
                break;
            default:
                System.out.println("Unknown action: " + action);
                resp.sendRedirect("/projet/list");
        }
    }

    private void createProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        double budget = Double.parseDouble(req.getParameter("budget"));
        HttpSession session = req.getSession();

        Projet projet = new Projet(nom, description, dateDebut, dateFin, budget);
        try {
            projetDao.addProjet(projet);
            session.setAttribute("message", "Projet ajouté avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace(); // Log the error for debugging
        }

        // Redirect to list page after creation
        resp.sendRedirect("/projet/list");
    }

    private void addProjetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/projets/ajouter_form.jsp").forward(req, resp);
    }

    private void updateProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        double budget = Double.parseDouble(req.getParameter("budget"));
        HttpSession session = req.getSession();

        try {
            Projet projet = projetDao.getProjetById(id);
            if (projet != null) {
                projet.setNom(nom);
                projet.setDescription(description);
                projet.setDateDebut(dateDebut);
                projet.setDateFin(dateFin);
                projet.setBudget(budget);
                projetDao.updateProjet(projet);
                session.setAttribute("message", "Projet mis à jour avec succès !");
                session.setAttribute("messageType", "success");
            } else {
                session.setAttribute("error", "Projet introuvable !");
                session.setAttribute("messageType", "danger");
            }
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace(); // Log the error for debugging
        }

        resp.sendRedirect("/projet/list");
    }

    private void updateProjetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Projet projet = projetDao.getProjetById(id);
        req.setAttribute("projet", projet);
        req.getRequestDispatcher("/WEB-INF/views/projets/modifierProjet.jsp").forward(req, resp);
    }

    private void deleteProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();

        try {
            projetDao.deleteProjet(id);
            session.setAttribute("message", "Projet supprimé avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace(); // Log the error for debugging
        }

        resp.sendRedirect("/projet/list");
    }

    private void listProjets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Projet> projets = projetDao.getProjets();
        req.setAttribute("projets", projets);
        req.getRequestDispatcher("/WEB-INF/views/projets/listeProjet.jsp").forward(req, resp);
    }
}
