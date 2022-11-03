package com.example.survey;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "AddUserServlet", value = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        String isAdm = request.getParameter("admin");
        AddCheckUser add = new AddCheckUser();

        int error_code;
        try {
            error_code = add.addUser("root", "12GaBGaL17!", user, password, isAdm);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.print(error_code);
    }
}
