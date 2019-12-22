package com.fekrety.onlinequiz.getdata;

import android.content.Context;

import  com.fekrety.onlinequiz.model.Units;
import  com.fekrety.onlinequiz.ui.ConnectDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetUnit {
    ConnectDatabase db = new ConnectDatabase();
    public ArrayList<Units> GetAllUnits(Context c) {        // method get product and retirive list of product
        ArrayList<Units> Units_data = new ArrayList<Units>();
        String selectQuery = "select * from Units ";
        Connection conn;
        conn = db.ConnectDB(c);
        Statement stmt ;
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                ResultSet t = stmt.executeQuery(selectQuery);
                t.next();
                do {
                    Units Units = new Units();
                    Units.setUnit_desc(t.getString(2));
                    Units.setBook_Url(t.getString(3));
                    Units.setFile_name(t.getString(4));
                    Units_data.add(Units);
                } while (t.next());
            } catch (SQLException ex) {

            }
        }else
        {
            Units_data.clear();
        }
        return Units_data;
    }
}
