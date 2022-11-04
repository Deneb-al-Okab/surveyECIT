package com.example.survey;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

@WebServlet(name = "GetAnswersServlet", value = "/GetAnswersServlet")
public class GetAnswersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Read from request
        Gson gson = new Gson();
        StringBuilder buffer = new StringBuilder();
        Reader reader = request.getReader();
        String line;
        GetAnswerClass obj = gson.fromJson(reader, GetAnswerClass.class);
        //System.out.println(obj.getId_questionanswer());
        Write wr = new Write();
        wr.writeAnswers(obj.getUsername(),obj.getId_survey(),obj.getId_questionanswer());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
