package com.example.survey;

import java.sql.*;
import java.util.Calendar;

public class Write {

    final String url = "jdbc:mysql://localhost:3306/survey";
    final String userdb = "surveyEcit";
    final String psw = "123456";

    public void writeAnswers(String user, int surveyId, int[] questAnsw) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(this.url, this.userdb, this.psw);
        //quesry che inserisce nei sondaggi fatti l'id del sondaggio e chi l'ha fatto
        String queryInsertSurvey = "INSERT INTO submitted_survey  (id_survey, id_mail) " +
                "VALUES (?, ?);";
        PreparedStatement pstm1 = con.prepareStatement(queryInsertSurvey);
        pstm1.setInt(1, surveyId);
        pstm1.setString(2, user);
        pstm1.executeUpdate();
        pstm1.close();

        //seleziona l'id creato dal nuovo sondaggio fatto dall'utente
        String querySelectSurveyId = "SELECT id AS lastSubSurvey " +
                "FROM submitted_survey " +
                "WHERE id_survey = ? and id_mail = ?;";

        PreparedStatement pstm2 = con.prepareStatement(querySelectSurveyId);
        pstm2.setInt(1, surveyId);
        pstm2.setString(2, user);
        ResultSet rs = pstm2.executeQuery();
        int lastSubSurveyID = 0;
        while (rs.next()) {
            lastSubSurveyID = rs.getInt(1);
        }
        pstm2.close();
        if (lastSubSurveyID == 0) {
            System.out.println("non dovrei essere qui");
        }
        else{
            //inserimento delle risposte date al sondaggio fatto dall'utente
            PreparedStatement pstm3 = null;
            for (int j : questAnsw) {
                String queryInsertQA = "INSERT INTO submitted_answer  (id_submitted_survey, id_question_answer) " +
                                        "VALUES (?, ?);";
                pstm3 = con.prepareStatement(queryInsertQA);
                pstm3.setInt(1, lastSubSurveyID);
                pstm3.setInt(2, j);
                pstm3.executeUpdate();
            }
            assert pstm3 != null;
            pstm3.close();
            con.close();
        }
    }

    //String[] surveyParameters = {"user", "category", "name", "description"};
    //Calendar[] dates = {publish_date, ending_date};
    //String[][] question = {text1[], text2[],...}
    public void createSurvey(Survey sur,){

    }
}
