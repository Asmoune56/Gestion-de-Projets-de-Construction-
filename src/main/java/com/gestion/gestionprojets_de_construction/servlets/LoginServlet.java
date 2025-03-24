package com.gestion.gestionprojets_de_construction.servlets;

import com.gestion.gestionprojets_de_construction.daos.UserDao;
import com.gestion.gestionprojets_de_construction.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Veuillez remplir tous les champs !");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {

            User user = userDao.getUserByEmail(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);

                System.out.println("user logged in");


            } else {
                request.setAttribute("errorMessage", "Email ou mot de passe incorrect !");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String getRedirectPage(String role) {
        if ("admin".equalsIgnoreCase(role)) {
            return "adminDashboard.jsp";
        } else {
            return "dashboard.jsp";
        }
    }
}
