package com.gestion.gestionprojets_de_construction.servlets;



import com.gestion.gestionprojets_de_construction.models.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login")
public class login extends HttpServlet {
    private final HashMap<String, String> adminCredentials = new HashMap<>(); // Make it final

    public login() {
        adminCredentials.put("admin", "cvbnm ");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("admin") != null) {
            response.sendRedirect("dashboard.jsp");
            return;
        }
        request.getRequestDispatcher("/authentifiation/login.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String msg;

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            msg = "Username and password are required.";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/authentifiation/login.jsp").forward(request, response);
            return;
        }


        if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);

            HttpSession session = request.getSession();
            session.setAttribute("admin", admin); // Set the admin object in the session
            response.sendRedirect("index.jsp");
        } else {
            msg = "Invalid username or password.";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("/authentifiation/login.jsp").forward(request, response);
        }
    }
}
