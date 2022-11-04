package com.example.survey;

import java.sql.*;

public class Write {

    final String url = "jdbc:mysql://localhost:3306/survey";
    final String userdb = "surveyEcit";
    final String psw = "123456";

    /*public int writeAnswers(String user, int surveyId, int[] questAnsw) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(this.url, this.userdb, this.psw);
        String query = "INSERT INTO submitted_survey  (id_survey, id_mail) " +
                        "VALUES (?, ?);";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(2, user);
        stm.setInt(1, surveyId);
        ResultSet rs = stm.executeQuery();

    }*/
}
