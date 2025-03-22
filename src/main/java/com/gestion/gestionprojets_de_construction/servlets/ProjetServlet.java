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
import java.util.ArrayList;
import java.util.List;
@WebServlet("/projects/*")
public class ProjetServlet extends HttpServlet {
    private final ProjetDao projetDao = new ProjetDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
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
                updateProjetForm(req, resp);
                break;
            case "/delete":
                deleteProjet(req, resp);
                break;
            case "/list":
                listProjets(req, resp);
                break;
            default:
                resp.sendRedirect("/projects/list");
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
        }

        resp.sendRedirect(req.getContextPath() + "/projects/list");
    }

    private void addProjetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/projets/add_new-form.jsp").forward(req, resp);
    }



    private void updateProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nom");
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        double budget = Double.parseDouble(req.getParameter("budget"));
        HttpSession session = req.getSession();

        try {
            Projet projet = projetDao.getProjetById(id);
            if (projet != null) {
                projet.setNom(nome);
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
        }

        resp.sendRedirect(req.getContextPath() + "/projects/list");
    }

    private void updateProjetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Vérifier si l'ID est vide ou null
        if (idParam == null || idParam.isEmpty()) {
            req.setAttribute("error", "ID du projet manquant !");
            req.getRequestDispatcher("/views/projets/projects.jsp").forward(req, resp);
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Projet projet = projetDao.getProjetById(id);
            if (projet == null) {
                req.setAttribute("error", "Projet introuvable !");
                req.getRequestDispatcher("/views/projets/projects.jsp").forward(req, resp);
            } else {
                req.setAttribute("projet", projet);
                req.getRequestDispatcher("/views/projets/modifierProjet.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID du projet invalide !");
            req.getRequestDispatcher("/views/projets/projects.jsp").forward(req, resp);
        }
    }


    private void deleteProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Vérifier si l'ID est vide ou null
        if (idParam == null || idParam.isEmpty()) {
            req.getSession().setAttribute("error", "ID du projet manquant !");
            resp.sendRedirect(req.getContextPath() + "/projects/list");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            projetDao.deleteProjet(id);
            req.getSession().setAttribute("message", "Projet supprimé avec succès !");
            req.getSession().setAttribute("messageType", "success");
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "ID du projet invalide !");
        } catch (Exception e) {
            req.getSession().setAttribute("error", e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/projects/list");
    }


    private void listProjets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Projet> projets = projetDao.getProjets();
        req.setAttribute("projects", projets);
        req.getRequestDispatcher("/views/projets/projects.jsp").forward(req, resp);
    }
}
