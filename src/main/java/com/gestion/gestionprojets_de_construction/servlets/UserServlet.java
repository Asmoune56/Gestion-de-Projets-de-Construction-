package com.gestion.gestionprojets_de_construction.servlets;

import com.gestion.gestionprojets_de_construction.daos.UserDao;
import com.gestion.gestionprojets_de_construction.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    // Gérer les requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "logout":
                logout(request, response);
                break;
            case "list":
                listAdmins(request, response);
                break;
            case "delete":
                deleteAdmin(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }

    // Gérer les requêtes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                login(request, response);
                break;
            case "add":
                addAdmin(request, response);
                break;
            case "update":
                updateAdmin(request, response);
                break;
            default:
                response.sendRedirect("dashboard.jsp");
                break;
        }
    }

    // Méthode pour gérer la connexion de l'utilisateur
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.getUser(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Email ou mot de passe incorrect !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // Méthode pour gérer la déconnexion de l'utilisateur
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    // Ajouter un administrateur
    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires !");
            request.getRequestDispatcher("add_admin.jsp").forward(request, response);
            return;
        }

        userDao.insertAdmin(email, password);
        response.sendRedirect("UserServlet?action=list");
    }

    // Modifier un administrateur
    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Tous les champs sont obligatoires !");
            request.getRequestDispatcher("edit_admin.jsp").forward(request, response);
            return;
        }

        userDao.updateAdmin(id, email, password);
        response.sendRedirect("UserServlet?action=list");
    }

    // Supprimer un administrateur
    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteAdmin(id);
        response.sendRedirect("UserServlet?action=list");
    }

    // Lister tous les administrateurs
    private void listAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> admins = userDao.getAllAdmins();
        request.setAttribute("admins", admins);
        request.getRequestDispatcher("list_admins.jsp").forward(request, response);
    }
}
