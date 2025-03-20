package com.gestion.gestionprojets_de_construction.servlets;


import com.gestion.gestionprojets_de_construction.daos.RessourceDao;
import com.gestion.gestionprojets_de_construction.models.Ressource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

public class RessourceServlet extends HttpServlet {
    private final RessourceDao ressourceDao = new RessourceDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Ressource ressource = new Ressource();
            ressource.setId(id);
            ressourceDao.deleteRessource(ressource);
            response.sendRedirect("ressourceList");  // Redirect to list of ressources
        } else if ("edit".equals(action)) {
            // Editing a ressource
            int id = Integer.parseInt(request.getParameter("id"));
            Ressource ressource = getRessourceById(id);
            request.setAttribute("ressource", ressource);
            request.getRequestDispatcher("editRessource.jsp").forward(request, response);
        } else {
            // Displaying all ressources
            List<Ressource> ressources = ressourceDao.selectAllRessource();
            request.setAttribute("ressources", ressources);
            request.getRequestDispatcher("ressourceList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // Creating a new ressource
            String nom = request.getParameter("nom");
            String type = request.getParameter("type");
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            Ressource newRessource = new Ressource();
            newRessource.setNom(nom);
            newRessource.setType(type);
            newRessource.setQuantite(quantite);

            ressourceDao.insertRessource(newRessource);
            response.sendRedirect("ressourceList");  // Redirect to list of ressources
        } else if ("update".equals(action)) {
            // Updating an existing ressource
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String type = request.getParameter("type");
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            Ressource updatedRessource = new Ressource();
            updatedRessource.setId(id);
            updatedRessource.setNom(nom);
            updatedRessource.setType(type);
            updatedRessource.setQuantite(quantite);

            ressourceDao.updateRessource(updatedRessource);
            response.sendRedirect("ressourceList");  // Redirect to list of ressources
        }
    }

    private Ressource getRessourceById(int id) {
        // Helper method to retrieve a single ressource by ID (for editing)
        List<Ressource> ressources = ressourceDao.selectAllRessource();
        for (Ressource ressource : ressources) {
            if (ressource.getId() == id) {
                return ressource;
            }
        }
        return null;
    }
}

