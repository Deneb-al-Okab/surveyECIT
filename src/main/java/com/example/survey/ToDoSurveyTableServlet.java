package com.example.survey;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ToDoSurveyTableServlet", value = "/ToDoSurveyTableServlet")
public class ToDoSurveyTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int step = Integer.parseInt(request.getParameter("step"));
        int currentPage = Integer.parseInt(request.getParameter("currentpage"));
        String username = request.getParameter("username");
        Read r = new Read();
        ArrayList<Survey> arrSurvey = null;
        try {
            arrSurvey = r.readSurveyToDo("root","12GaBGaL17!",currentPage,step,username);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        String json = gson.toJson(arrSurvey);

        PrintWriter out = response.getWriter();
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
