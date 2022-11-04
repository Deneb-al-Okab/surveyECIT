package com.example.survey;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

@WebServlet(name = "CreateSurveyServlet", value = "/CreateSurveyServlet")
public class CreateSurveyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read from request a json
        Reader reader = request.getReader();
        Gson gson = new Gson();
        Survey sur = gson.fromJson(reader,Survey.class);
        // CREO CALENDAR DALLE STRINGE DI yyyy-mm-dd HH:mm:ss
        try {
            sur.setCalendars();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
