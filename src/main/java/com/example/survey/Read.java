package com.example.survey;

import java.sql.*;
import java.util.ArrayList;

public class Read {
    public ArrayList<Question> readQuestions(String usr, String pwd, int idSurvey) throws SQLException, ClassNotFoundException {
        ArrayList<Question> questions = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("" +
                "jdbc:mysql://localhost/survey", usr, pwd);
      /*  PreparedStatement stmt = con.prepareStatement("select question.question, question.id" +
                "from  survey_table , survey_composition , question_answer , question, answer" +
                "where survey_table.id = survey_composition.id_survey and" +
                "survey_composition.id_question_answer = question_answer.id and" +
                "question_answer.id_answer = question.id" +
                "and survey_table.id = ?");*/
        // Prende tutte domande ripetendo nome
        PreparedStatement stmt = con.prepareStatement("select question.question, answer.answer, question.id " +
                "from  survey_table , survey_composition , question_answer " +
                "inner JOIN answer ON question_answer.id_answer = answer.id " +
                "inner join question on question_answer.id_question = question.id " +
                "where survey_table.id = survey_composition.id_survey and " +
                "survey_composition.id_question_answer = question_answer.id and " +
                "survey_table.id = ? ;");
        stmt.setInt(1,idSurvey);
        ResultSet rs = stmt.executeQuery();
        Question tempQuestion;
        String textQuestion = "";
        String textAnswer;
        ArrayList<String> tempAnswers = new ArrayList<>();
        int idQuestion = 0;
        int idNext = 0;
        while (rs.next()) {
             idNext= rs.getInt("id");
             textAnswer = rs.getString("answer");
             if (idNext != idQuestion){
                 tempQuestion = new Question(idQuestion,textQuestion,tempAnswers);
                 questions.add(tempQuestion);
                 tempAnswers.clear();
             }
            tempAnswers.add(textAnswer);
             idQuestion = idNext;
            textQuestion = rs.getString("question");
        }
        con.close();
        stmt.close();


        return questions;
    }
}
