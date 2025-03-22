package com.gestion.gestionprojets_de_construction.servlets;

import com.gestion.gestionprojets_de_construction.daos.UserDao;
import com.gestion.gestionprojets_de_construction.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User("rachid", "rachid@gmail.com", BCrypt.hashpw("12345", BCrypt.gensalt()));
        UserDao userDao = new UserDao();
        userDao.insertUser(user);

        System.out.println("yeees");
    }
}
