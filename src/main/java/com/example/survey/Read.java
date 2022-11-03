package com.example.survey;

import java.sql.*;
import java.util.ArrayList;

public class Read {
    public ArrayList<Question> readQuestions(String usr, String pwd, int idSurvey) throws SQLException, ClassNotFoundException {
        ArrayList<Question> questions = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("" +
                "jdbc:mysql://localhost/survey", usr, pwd);
        //CONTO IL NUMERO DI RIGHE
        int count = 0;
        PreparedStatement stmtC = con.prepareStatement("select count(question.id) as count " +
                "from  survey_table , survey_composition , question_answer " +
                "inner JOIN answer ON question_answer.id_answer = answer.id " +
                "inner join question on question_answer.id_question = question.id " +
                "where survey_table.id = survey_composition.id_survey and " +
                "survey_composition.id_question_answer = question_answer.id and " +
                "survey_table.id = ? ;");
        stmtC.setInt(1,idSurvey);
        ResultSet rsC = stmtC.executeQuery();
        while (rsC.next()){
             count = rsC.getInt(1);
        }
        stmtC.close();
        //PRENDO TUTTE LE DOMANDE E RISPOSTE
        PreparedStatement stmt = con.prepareStatement("select question.question, answer.answer, question.id " +
                "from  survey_table , survey_composition , question_answer " +
                "inner JOIN answer ON question_answer.id_answer = answer.id " +
                "inner join question on question_answer.id_question = question.id " +
                "where survey_table.id = survey_composition.id_survey and " +
                "survey_composition.id_question_answer = question_answer.id and " +
                "survey_table.id = ? " +
                "ORDER BY question.id asc; ");
        stmt.setInt(1,idSurvey);
        ResultSet rs = stmt.executeQuery();
        ArrayList<String> tempAnswers = new ArrayList<>();
        Question tempQuestion ;
        String textQuestion = "";
        String textAnswer;
        int idQuestion = 0;
        int idNext = 0;
        int loop = 1;
        while (rs.next()) {
             idNext= rs.getInt("id");

             if(idNext != idQuestion && idQuestion != 0 || loop == count){
                 if(loop == count){
                     textAnswer = rs.getString("answer");
                     tempAnswers.add(textAnswer);
                 }
                 tempQuestion = new Question(idQuestion,textQuestion,tempAnswers);
                 questions.add(tempQuestion);
                 tempAnswers = new ArrayList<>();
             }
            textAnswer = rs.getString("answer");
            tempAnswers.add(textAnswer);
            textQuestion = rs.getString("question");
            idQuestion = idNext;
            loop ++;

        }
        con.close();
        stmt.close();


        return questions;
    }
}
