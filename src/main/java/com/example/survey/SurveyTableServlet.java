package com.example.survey;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SurveyTableServlet", value = "/SurveyTableServlet")
public class SurveyTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int step = Integer.parseInt(request.getParameter("step"));
        int currentPage = Integer.parseInt(request.getParameter("currentpage"));

        ArrayList<Survey> arrSurvey = new ArrayList<>();



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
