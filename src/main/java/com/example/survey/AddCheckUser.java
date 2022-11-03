package com.example.survey;

import java.sql.*;

public class AddCheckUser {


    /* legenda errorCode:
            0 = wrong password;
            1 = wrong user;
            2 = admin user;
            3 = normal user;
     */
    public int check(String userdb, String passworddb, String user, String password) throws ClassNotFoundException, SQLException {
        int errorCode;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey",
                userdb, passworddb);
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM users WHERE mail = ?");
        pstm.setString(1, user);
        ResultSet rs = pstm.executeQuery();
        if (!rs.isBeforeFirst()) {
            errorCode = 1;
            con.close();
            pstm.close();
            return errorCode;
        }

        String psw = "";
        int adm = 0;
        while(rs.next()){
            psw = rs.getString(2);
            adm = rs.getInt(3);
        }

        if (psw.equals(password)) {
            if (adm == 1) {
                con.close();
                pstm.close();
                errorCode = 2;
                return errorCode;
            } else {
                con.close();
                pstm.close();
                errorCode = 3;
                return errorCode;
            }
        }
        else {
            con.close();
            pstm.close();
            errorCode = 0;
            return errorCode;
        }
    }

    /*legenda errorCode:
            0 = utente inserito;
            1 = utente già esistente;
     */
    public int addUser(String userdb, String passworddb, String user, String password, String admin) throws ClassNotFoundException, SQLException {
        int errorCode;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey",
                userdb, passworddb);
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM users WHERE mail = ?");
        pstm.setString(1, user);
        ResultSet rs = pstm.executeQuery();
        if (rs.isBeforeFirst()) {
            errorCode = 1;
            con.close();
            pstm.close();
            return errorCode;
        }
        else{
            PreparedStatement stm = con.prepareStatement("INSERT INTO users VALUES (?,?,?);");
            stm.setString(1,user);
            stm.setString(2,password);
            stm.setInt(3, Integer.parseInt(admin));
            stm.executeUpdate();
            con.close();
            stm.close();

            errorCode = 0;
            return errorCode;
        }

    }

    public void setAdminStatus(String userdb, String passworddb, int status, String user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey",
                userdb, passworddb);
        PreparedStatement pstm = con.prepareStatement("UPDATE users SET is_admin =? WHERE mail = ?");
        pstm.setInt(1, status);
        pstm.setString(2, user);
        pstm.executeUpdate();
    }
}
