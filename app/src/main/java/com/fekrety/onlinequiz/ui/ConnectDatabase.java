package com.fekrety.onlinequiz.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
    private Connection con = null;
    public Connection ConnectDB(final Context c) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://SQL5041.site4now.net",
                    "DB_A49A16_minasamir11_admin",
                    "M0126158953");

        } catch (ClassNotFoundException e) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, "Not found", Toast.LENGTH_LONG).show();
                }
            }, 1000 );
            e.printStackTrace();

        } catch (SQLException e) {

            Handler handler = new Handler(Looper.getMainLooper());

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(c, " Connection failed no route to host, Try again ", Toast.LENGTH_LONG).show();
                }
            }, 1000 );
            e.printStackTrace();
        }
        return con;
        }
        public int RUNIUD (String s, Context c) {
            int x =0 ;
            try {
                Statement stmt = con.createStatement();
                x = stmt.executeUpdate(s);
               return x;
            } catch (SQLException e) {
                if (e.getErrorCode() == 2627) {
                    Toast.makeText(c, "user name  already exist  try  again   !!! :(", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(c, "Sorry error   :" + e.getMessage() + "\n"
                            + e.getErrorCode(), Toast.LENGTH_LONG).show();
                return x;
            }
        }
    }
