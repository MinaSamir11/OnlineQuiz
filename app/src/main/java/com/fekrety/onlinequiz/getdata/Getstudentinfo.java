package com.fekrety.onlinequiz.getdata;

import android.content.Context;

import  com.fekrety.onlinequiz.model.Studentlogin;
import  com.fekrety.onlinequiz.ui.ConnectDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Getstudentinfo {
    ConnectDatabase db = new ConnectDatabase();

    public Studentlogin Getstudent(int s, Context c) {        // method get product and retirive list of product
      Studentlogin student = new Studentlogin() ;
        String selectQuery = "select * from Students where Student_ID = '" + s + "'";
        Connection conn;
        conn = db.ConnectDB(c);
        if(conn!=null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet t = stmt.executeQuery(selectQuery);
                t.next();
                do {
                    student = new Studentlogin();
                    student.setStudent_ID(t.getString(1));
                    student.setStudent_Name(t.getString(2));
                    student.setEmail(t.getString(3));
                    student.setPassword(t.getString(5));
                    student.setLocation(t.getString(6));
                    student.setLevel(t.getString(8));
                    student.setChatroom_id(t.getString(9));


                } while (t.next());

            } catch (SQLException ex) {
            }
        }
        return student;
    }

}
