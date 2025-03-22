package com.gestion.gestionprojets_de_construction.servlets;


import com.gestion.gestionprojets_de_construction.daos.TacheDao;
import com.gestion.gestionprojets_de_construction.models.Tache;
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
@WebServlet("/tache/*")
public class TacheServlet extends HttpServlet {
    private final TacheDao tacheDao = new TacheDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        if (action == null) action = "/list"; // Si l'URL est vide, afficher la liste

        try {
            switch (action) {
                case "/new":
                    createTache(req, resp);
                    break;
                case "/new-form":
                    req.getRequestDispatcher("/views/taches/ajouter_form.jsp").forward(req, resp);
                    break;
                case "/edit":
                    updateTache(req, resp);
                    break;
                case "/edit-form":
                    updateTacheForm(req, resp);
                    break;
                case "/delete":
                    deleteTache(req, resp);
                    break;
                case "/list":
                    listTaches(req, resp);
                    break;
                default:
                    resp.sendRedirect("/tache/list");
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
        }
    }

    private void createTache(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        HttpSession session = req.getSession();

        try {
            Tache tache = new Tache(description, dateDebut, dateFin);
            tacheDao.insertTache(tache);
            session.setAttribute("message", "Tâche ajoutée avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", "Erreur lors de l'ajout : " + e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/tache/list");
    }

    private void updateTache(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        HttpSession session = req.getSession();

        try {
            Tache tache = new Tache(description, dateDebut, dateFin, id);
            tacheDao.updateTache(tache);
            session.setAttribute("message", "Tâche mise à jour avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", "Erreur lors de la mise à jour : " + e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/tache/list");
    }

    private void updateTacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Tache tache = tacheDao.getTacheById(id);
        req.setAttribute("tache", tache);
        req.getRequestDispatcher("/views/taches/edit-form.jsp").forward(req, resp);
    }

    private void deleteTache(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        try {
            tacheDao.deleteTache(id);
            session.setAttribute("message", "Tâche supprimée avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/tache/list");
    }

    private void listTaches(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tache> taches = tacheDao.getAllTaches();
        req.setAttribute("taches", taches);
        req.getRequestDispatcher("/views/taches/list.jsp").forward(req, resp);
    }
}
