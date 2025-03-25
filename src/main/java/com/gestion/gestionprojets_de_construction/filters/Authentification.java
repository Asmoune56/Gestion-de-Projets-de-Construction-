package com.gestion.gestionprojets_de_construction.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*") // Apply to all requests
public class Authentification implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Get session, but don't create if it doesn't exist
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();


        boolean isLoggedIn = (session != null && session.getAttribute("admin") != null);
        boolean isLoginPage = uri.endsWith("login");
        boolean isResource = uri.startsWith(contextPath + "/css") || uri.startsWith(contextPath + "/js") || uri.startsWith(contextPath + "/images"); //Example resource folders


        if (isLoggedIn && isLoginPage) {
            // If already logged in and trying to access login page, redirect to index
            resp.sendRedirect(contextPath + "/index.jsp");
        } else if (isLoggedIn || isLoginPage || isResource) {
            // Allow access if logged in, is the login page, or is a resource
            chain.doFilter(request, response);
        } else {
            // Otherwise, redirect to login page
            resp.sendRedirect(contextPath + "/login");
        }
    }


}
