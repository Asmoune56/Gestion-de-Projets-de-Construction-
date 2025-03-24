package com.gestion.gestionprojets_de_construction.servlets;


import com.gestion.gestionprojets_de_construction.daos.FournisseurDao;
import com.gestion.gestionprojets_de_construction.models.Fournisseur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/fournisseur/*")
public class FournisseurServlet extends HttpServlet {
    private final FournisseurDao fournisseurDao = new FournisseurDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println(action);
        switch (action) {
            case "/list":
                listFournisseurs(request, response);
                break;
            case "/edit-form":
                editFournisseurForm(request, response);
                break;
            case "/delete":
                deleteFournisseur(request, response);
                break;
            case "/add-form":
                addFournisseurForm(request, response);
                break;
            default:
                response.sendRedirect("fournisseur/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/create":
                createFournisseur(request, response);
                break;
            case "/update":
                updateFournisseur(request, response);
                break;
            default:
                response.sendRedirect("fournisseur/list");
        }
    }

    private void listFournisseurs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fournisseur> fournisseurs = fournisseurDao.selectAllFournisseurs();
        request.setAttribute("fournisseurs", fournisseurs);
        request.getRequestDispatcher("/views/fournisseurs/fournisseurs.jsp").forward(request, response);
    }

    private void createFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String adresse = request.getParameter("adresse");

            if (nom == null || email == null || telephone == null || adresse == null) {
                request.setAttribute("error", "Tous les champs sont obligatoires.");
                request.getRequestDispatcher("/views/fournisseurs/add_fournisseur.jsp").forward(request, response);
                return;
            }

            Fournisseur newFournisseur = new Fournisseur();
            newFournisseur.setNom(nom);
            newFournisseur.setEmail(email);
            newFournisseur.setTelephone(telephone);
            newFournisseur.setAdresse(adresse);

            fournisseurDao.insertFournisseur(newFournisseur);

            request.getSession().setAttribute("success", "Fournisseur ajouté avec succès !");
            response.sendRedirect(request.getContextPath() + "/fournisseur/list");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de l'ajout du fournisseur.");
            request.getRequestDispatcher("/views/fournisseurs/add_fournisseur.jsp").forward(request, response);
        }
    }



    private void updateFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String adresse = request.getParameter("adresse");

        Fournisseur updatedFournisseur = new Fournisseur();
        updatedFournisseur.setId(id);
        updatedFournisseur.setNom(nom);
        updatedFournisseur.setEmail(email);
        updatedFournisseur.setTelephone(telephone);
        updatedFournisseur.setAdresse(adresse);

        fournisseurDao.updateFournisseur(updatedFournisseur);
        response.sendRedirect("/fournisseur/list");
    }

    private void editFournisseurForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fournisseur fournisseur = fournisseurDao.selectAllFournisseurs().stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
        if (fournisseur != null) {
            request.setAttribute("fournisseur", fournisseur);
            request.getRequestDispatcher("/WEB-INF/views/fournisseurs/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("/fournisseur/list");
        }
    }

    private void deleteFournisseur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        fournisseurDao.deleteFournisseur(id);
        response.sendRedirect("/fournisseur/list");
    }
    private void addFournisseurForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/fournisseurs/add_fournisseur.jsp").forward(req, resp);
    }
}

