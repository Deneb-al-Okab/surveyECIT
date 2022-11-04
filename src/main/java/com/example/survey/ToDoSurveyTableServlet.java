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
        int counter=0;
        try {
            arrSurvey = r.readSurveyToDo(currentPage,step,username);
            counter = r.countSurveyToDo(username);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        SurveyList slist = new SurveyList(arrSurvey, counter);
        Gson gson = new Gson();
        String json = gson.toJson(slist);

        PrintWriter out = response.getWriter();
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
