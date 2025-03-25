package com.gestion.gestionprojets_de_construction.servlets;





import com.gestion.gestionprojets_de_construction.daos.RessourceDAO;
import com.gestion.gestionprojets_de_construction.daos.TacheDAO;
import com.gestion.gestionprojets_de_construction.daos.TacheRessourceDAO;
import com.gestion.gestionprojets_de_construction.models.Ressource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ressource")
public class RessourceServlet extends HttpServlet {
    private RessourceDAO ressourceDAO;
    private TacheDAO tacheDAO;
    private TacheRessourceDAO tacheRessourceDAO;

    public void init() throws ServletException {
        try {
            tacheRessourceDAO = new TacheRessourceDAO();
            ressourceDAO = new RessourceDAO();
            tacheDAO = new TacheDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error initializing RessourceDAO", e);
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

        }
        try{
            switch (action) {
                case "new":
                    afficherForm(request,response);
                    break;
                case "ajouter":
                    ajouterRessource(request,response);
                    break;
                case "modifier":
                    modifierRessource(request,response);
                    break;
                case "supprimer":
                    supprimerRessource(request,response);
                    break;
                case "afficher":
                    afficherRessource(request,response);
                    break;
                case "TrouverRessourceParid":
                    afficherRessourceParid(request,response);
                    break;
                default:
                    break;

            }
        } catch (Exception e) {
            throw new ServletException("Erreur dans la gestion des ressource", e);
        }
    }

    private void afficherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Ressource/ajouteRessource.jsp").forward(request,response);
    }
    private void ajouterRessource(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String Types   = request.getParameter("type");
        Double quantite = Double.valueOf(request.getParameter("quantite"));
        String fournisseur = request.getParameter("fournisseur");
        Ressource ressource = new Ressource(nom,Types,quantite,fournisseur);
        ressourceDAO.ajouterRessource(ressource);
        response.sendRedirect("ressource?action=afficher");
    }
    private void modifierRessource(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_RESS = Integer.parseInt(request.getParameter("id_RESS"));
        String nom = request.getParameter("nom");
        String Types   = request.getParameter("types");
        Double quantite = Double.valueOf(request.getParameter("quantite"));
        String fournisseur = request.getParameter("fournisseur");
        Ressource ressource = new Ressource(id_RESS,nom,Types,quantite,fournisseur);
        ressourceDAO.modifierRessource(ressource);
        response.sendRedirect("ressource?action=afficher");
    }
    private void supprimerRessource(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_RESS = Integer.parseInt(request.getParameter("id_RESS"));
        ressourceDAO.supprimerRessource(id_RESS);
        response.sendRedirect("ressource?action=afficher");
    }
    private void afficherRessource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Ressource> ressourceList = ressourceDAO.afficherRessource();

        System.out.println(ressourceList);
        request.setAttribute("ressourceList", ressourceList);
        request.getRequestDispatcher("/Ressource/afficherRessource.jsp").forward(request, response);
    }
    private void afficherRessourceParid(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id_RESS = Integer.parseInt(request.getParameter("id_RESS"));
        Ressource ressource = ressourceDAO.afficherRessourceParid(id_RESS);
        request.setAttribute("ressource", ressource);
        request.getRequestDispatcher("/Ressource/modifierRessource.jsp").forward(request,response);
    }
}

