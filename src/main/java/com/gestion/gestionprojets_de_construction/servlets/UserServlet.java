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

@WebServlet("/UserServlet/*")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        switch (action) {
            case "login":
                login(request, response);
                break;
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

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

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

    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteAdmin(id);
        response.sendRedirect("UserServlet?action=list");
    }

    private void listAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> admins = userDao.getAllAdmins();
        request.setAttribute("admins", admins);
        request.getRequestDispatcher("list_admins.jsp").forward(request, response);
    }
}
