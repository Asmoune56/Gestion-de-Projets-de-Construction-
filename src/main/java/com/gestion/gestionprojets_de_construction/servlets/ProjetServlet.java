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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        try {
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
                case "/dashboard":
                    showDashboard(req, resp);
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/projects/list");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("error", "Erreur interne du serveur.");
            resp.sendRedirect(req.getContextPath() + "/projects/list");
            e.printStackTrace();
        }
    }

    private void createProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            String nom = req.getParameter("nom");
            String description = req.getParameter("description");
            String dateDebutParam = req.getParameter("dateDebut");
            String dateFinParam = req.getParameter("dateFin");
            double budget = Double.parseDouble(req.getParameter("budget"));

            if (nom == null || nom.isEmpty() || description == null || description.isEmpty() ||
                    dateDebutParam == null || dateFinParam == null || budget < 0) {
                session.setAttribute("error", "Tous les champs sont obligatoires !");
                resp.sendRedirect(req.getContextPath() + "/projects/new-form");
                return;
            }

            LocalDate dateDebut = LocalDate.parse(dateDebutParam);
            LocalDate dateFin = LocalDate.parse(dateFinParam);

            if (dateFin.isBefore(dateDebut)) {
                session.setAttribute("error", "La date de fin doit être après la date de début !");
                resp.sendRedirect(req.getContextPath() + "/projects/new-form");
                return;
            }

            Projet projet = new Projet(nom, description, Date.valueOf(dateDebut), Date.valueOf(dateFin), budget);
            projetDao.addProjet(projet);

            session.setAttribute("message", "Projet ajouté avec succès !");
            session.setAttribute("messageType", "success");
            resp.sendRedirect(req.getContextPath() + "/projects/list");

        } catch (DateTimeParseException e) {
            session.setAttribute("error", "Format de date invalide !");
            resp.sendRedirect(req.getContextPath() + "/projects/new-form");
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Budget invalide !");
            resp.sendRedirect(req.getContextPath() + "/projects/new-form");
        }
    }
    private void addProjetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/projets/addProjet.jsp").forward(req, resp);
    }

    private void updateProjetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            req.getSession().setAttribute("error", "ID du projet manquant !");
            resp.sendRedirect(req.getContextPath() + "/projects/list");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Projet projet = projetDao.getProjetById(id);

            if (projet == null) {
                req.getSession().setAttribute("error", "Projet introuvable !");
                resp.sendRedirect(req.getContextPath() + "/projects/list");
                return;
            }

            req.setAttribute("projet", projet);
            req.getRequestDispatcher("/views/projets/edit_project.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "ID invalide !");
            resp.sendRedirect(req.getContextPath() + "/projects/list");
        }
    }



    private void updateProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String idParam = req.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            String nom = req.getParameter("nom");
            String description = req.getParameter("description");
            String startDateParam = req.getParameter("dateDebut");
            String endDateParam = req.getParameter("dateFin");

            if (nom == null || nom.isEmpty() || description == null || description.isEmpty() ||
                    startDateParam == null || endDateParam == null) {
                session.setAttribute("error", "Tous les champs sont obligatoires !");
                resp.sendRedirect(req.getContextPath() + "/projects/edit-form?id=" + id);
                return;
            }

            LocalDate startDate = LocalDate.parse(startDateParam);
            LocalDate endDate = LocalDate.parse(endDateParam);

            if (endDate.isBefore(startDate)) {
                session.setAttribute("error", "La date de fin doit être après la date de début !");
                resp.sendRedirect(req.getContextPath() + "/projects/edit-form?id=" + id);
                return;
            }

            Projet projet = new Projet(id, nom, description, Date.valueOf(startDate), Date.valueOf(endDate), 0);
            projetDao.updateProjet(projet);

            session.setAttribute("message", "Projet mis à jour avec succès !");
            resp.sendRedirect(req.getContextPath() + "/projects/list");

        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID de projet invalide !");
            resp.sendRedirect(req.getContextPath() + "/projects/list");
        } catch (DateTimeParseException e) {
            session.setAttribute("error", "Format de date invalide !");
            resp.sendRedirect(req.getContextPath() + "/projects/edit-form?id=" + idParam);
        }
    }

    private void listProjets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Projet> projets = projetDao.getProjets();
        if (projets == null) {
            projets = new ArrayList<>();
        }
        req.setAttribute("projets", projets);
        req.getRequestDispatcher("/views/projets/projects.jsp").forward(req, resp);
    }

    private void deleteProjet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String idParam = req.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);
            projetDao.deleteProjet(id);
            session.setAttribute("message", "Projet supprimé avec succès !");
        } catch (NumberFormatException e) {
            session.setAttribute("error", "ID de projet invalide !");
        }

        resp.sendRedirect(req.getContextPath() + "/projects/list");
    }

    private void showDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Projet> derniersProjets = projetDao.getDerniersProjets();
        if (derniersProjets == null || derniersProjets.isEmpty()) {
            req.setAttribute("message", "Aucun projet récent");
        } else {
            req.setAttribute("derniersProjets", derniersProjets);
        }
        req.getRequestDispatcher("/views/dashboard.jsp").forward(req, resp);
    }
}
