package com.gestion.gestionprojets_de_construction.servlets;

import com.gestion.gestionprojets_de_construction.daos.RessourceDao;
import com.gestion.gestionprojets_de_construction.models.Ressource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/resources/*")
public class RessourceServlet extends HttpServlet {
    private final RessourceDao ressourceDao = new RessourceDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();
        System.out.println("Action: " + action);

        switch (action) {
            case "/new":
                createRessource(req, resp);
                break;
            case "/new-form":
                addRessourceForm(req, resp);
                break;
            case "/edit":
                updateRessource(req, resp);
                break;
            case "/edit-form":
                updateRessourceForm(req, resp);
                break;
            case "/delete":
                deleteRessource(req, resp);
                break;
            case "/list":
                listRessources(req, resp);
                break;
            default:
                System.out.println("Unknown action: " + action);
                resp.sendRedirect(req.getContextPath() + "/resources/list");
        }
    }

    private void createRessource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String type = req.getParameter("type");
        int quantite = Integer.parseInt(req.getParameter("quantite"));
        HttpSession session = req.getSession();

        Ressource newRessource = new Ressource();
        newRessource.setNom(nom);
        newRessource.setType(type);
        newRessource.setQuantite(quantite);

        try {
            ressourceDao.insertRessource(newRessource);
            session.setAttribute("message", "Ressource ajoutée avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/resources/list");
    }

    private void addRessourceForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/ressources/ajouter_ressource.jsp").forward(req, resp);
    }

    private void updateRessource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String type = req.getParameter("type");
        int quantite = Integer.parseInt(req.getParameter("quantite"));
        HttpSession session = req.getSession();

        try {
            Ressource ressource = ressourceDao.getRessourceById(id);
            if (ressource != null) {
                ressource.setNom(nom);
                ressource.setType(type);
                ressource.setQuantite(quantite);
                ressourceDao.updateRessource(ressource);
                session.setAttribute("message", "Ressource mise à jour avec succès !");
                session.setAttribute("messageType", "success");
            } else {
                session.setAttribute("error", "Ressource introuvable !");
                session.setAttribute("messageType", "danger");
            }
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/resources/list");
    }

    private void updateRessourceForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Ressource ressource = ressourceDao.getRessourceById(id);
        req.setAttribute("ressource", ressource);
        req.getRequestDispatcher("/WEB-INF/views/ressources/modifier_ressource.jsp").forward(req, resp);
    }

    private void deleteRessource(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();

        try {
            ressourceDao.deleteRessource(id);
            session.setAttribute("message", "Ressource supprimée avec succès !");
            session.setAttribute("messageType", "success");
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            session.setAttribute("messageType", "danger");
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/resources/list");
    }

    private void listRessources(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ressource> ressources = ressourceDao.selectAllRessource();

        if (ressources == null) {
            ressources = new ArrayList<>();
        }

        req.setAttribute("ressources", ressources);
        req.getRequestDispatcher("/views/ressources/resources.jsp").forward(req, resp);
    }
}
