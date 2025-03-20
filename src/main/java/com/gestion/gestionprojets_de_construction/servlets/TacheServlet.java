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
        System.out.println(action);
        switch (action) {
            case "new":
                createTache(req, resp);
                break;
            case "new-form":
                addTacheForm(req, resp);
                break;
            case "/edit":
                updateTache(req, resp);
                break;
            case "/edit-form":

                try {
                    updateTacheForm(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "/delete":
                deleteTache(req, resp);
                break;
            case "/list":
                try {
                    listTaches(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                resp.sendRedirect("/views/taches/list.jsp");
        }
    }

    private void createTache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        HttpSession session = req.getSession();

        Tache tache = new Tache();
        try {
            tacheDao.insertTache(tache);
            session.setAttribute("message", "Tache added successfully!");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/views/taches/ajouter_form.jsp");
    }

    private void addTacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ajouter_form.jsp").forward(req, resp);
    }

    private void updateTache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String description = req.getParameter("description");
        Date dateDebut = Date.valueOf(req.getParameter("dateDebut"));
        Date dateFin = Date.valueOf(req.getParameter("dateFin"));
        HttpSession session = req.getSession();

        try {
            Tache tache = new Tache(description, dateDebut, dateFin, id);
            tacheDao.updateTache(tache);
            session.setAttribute("message", "Tache updated successfully!");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/views/taches/listeTache.jsp");
    }

    private void updateTacheForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Tache tache = tacheDao.getAllTaches().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        req.setAttribute("tache", tache);
        req.getRequestDispatcher("/views/taches/modifierTache.jsp").forward(req, resp);
    }

    private void deleteTache(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        try {
            tacheDao.deleteTache(id);
            session.setAttribute("message", "Tache deleted successfully!");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
        }
        resp.sendRedirect("/tache/list");
    }

    private void listTaches(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Tache> taches = tacheDao.getAllTaches();
        req.setAttribute("taches", taches);
        req.getRequestDispatcher("/views/taches/list.jsp").forward(req, resp);
    }
}

