package com.example.survey;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

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
        PreparedStatement stmt = con.prepareStatement("select question.question, answer.answer, question.id, question_answer.id as id_qa " +
                "from  survey_table , survey_composition , question_answer " +
                "inner JOIN answer ON question_answer.id_answer = answer.id " +
                "inner join question on question_answer.id_question = question.id " +
                "where survey_table.id = survey_composition.id_survey and " +
                "survey_composition.id_question_answer = question_answer.id and " +
                "survey_table.id = ? " +
                "ORDER BY question.id asc; ");
        stmt.setInt(1,idSurvey);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Answer> tempAnswers = new ArrayList<>();
        Question tempQuestion ;
        Answer tempAnswer;
        String textQuestion = "";
        String textAnswer ;
        int idQa;
        int idQuestion = 0;
        int idNext = 0;
        int loop = 1;
        while (rs.next()) {
             idNext= rs.getInt("id");

             if(idNext != idQuestion && idQuestion != 0 || loop == count){
                 if(loop == count){
                     textAnswer = rs.getString("answer");
                     idQa = rs.getInt("id_qa");
                     tempAnswers = new
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

    public ArrayList<Survey> readSurveyDone(String utentedb, String passworddb, int start, int step, String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey",
                utentedb, passworddb);
        String query = "SELECT survey_table.id, survey_table.id_mail, category.name, survey_table.name, survey_table.description, survey_table.publish_date, survey_table.ending_date " +
                "FROM survey_table " +
                "left join submitted_survey on submitted_survey.id_survey = survey_table.id " +
                "left join category on category.id = survey_table.id_category " +
                "WHERE submitted_survey.id_mail = ? " +
                "limit ?,?;";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, user);
        stm.setInt(2, start);
        stm.setInt(3, step);
        ResultSet rs = stm.executeQuery();

        ArrayList<Survey> surveysDone = new ArrayList<Survey>();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        while (rs.next()) {
            System.out.println("passo di qua");
            calendar1.setTime(rs.getDate(6));
            calendar2.setTime(rs.getDate(7));
            surveysDone.add(new Survey(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), calendar1,
                    calendar2));
        }
        con.close();
        stm.close();
        return surveysDone;
    }

    public ArrayList<Survey> readSurveyToDo(String utentedb, String passworddb, int start, int step, String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey",
                utentedb, passworddb);
        String queryToDo = "select survey_table.id, survey_table.id_mail, category.name, survey_table.name, survey_table.description, survey_table.publish_date, survey_table.ending_date " +
                "from survey_table " +
                "right join category on category.id = survey_table.id_category " +
                "where survey_table.id not in (" +
                "SELECT survey_table.id " +
                "FROM submitted_survey " +
                "left join survey_table on submitted_survey.id_survey = survey_table.id " +
                "where submitted_survey.id_mail = ?) " +
                "limit ?,?;";
        PreparedStatement pstm = con.prepareStatement(queryToDo);
        pstm.setString(1, user);
        pstm.setInt(2, start);
        pstm.setInt(3, step);
        ResultSet rst = pstm.executeQuery();
        System.out.println(pstm);

        ArrayList<Survey> surveysToDo = new ArrayList<Survey>();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        while (rst.next()) {
            calendar1.setTime(rst.getDate(6));
            calendar2.setTime(rst.getDate(7));
            surveysToDo.add(new Survey(rst.getInt(1), rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getString(5), calendar1,
                    calendar2));
        }
        con.close();
        pstm.close();
        return surveysToDo;
    }
}
