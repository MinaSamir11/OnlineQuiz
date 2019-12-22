package com.fekrety.onlinequiz.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.fekrety.onlinequiz.R;

import java.util.ArrayList;

public class Result_table extends AppCompatActivity {




    ArrayList<String> unitarr = new ArrayList<>();
    ArrayList<String> Scorearr = new ArrayList<>();
    ArrayList<String> DateArr = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        TextView[] unit1  = new TextView[35];
        TextView[] score =new TextView[35];
        TextView[] status =new TextView[35];
        TextView[] Date =new TextView[35];
        TableRow[] row =new TableRow[35];

        ImageButton back = (ImageButton)findViewById(R.id.bu_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result_table.this,Drawablelist.class));
                finish();
            }
        });
        unitarr.add("unit 1 ");
        unitarr.add("unit 2 ");
        unitarr.add("Unit 3 ") ;
        unitarr.add("Exam 1 ") ;
        unitarr.add("unit 4 ");
        unitarr.add("unit 5");
        unitarr.add("unit 6");
        unitarr.add("Exam 2 ") ;
        unitarr.add("unit 7");
        unitarr.add("Unit 3 ") ;
        unitarr.add("Exam 1 ") ;
        unitarr.add("unit 4 ");
        unitarr.add("unit 5");
        unitarr.add("unit 6");
        unitarr.add("Exam 2 ") ;
        unitarr.add("unit 7");    unitarr.add("unit 7");
        unitarr.add("Unit 3 ") ;
        unitarr.add("Exam 1 ") ;
        unitarr.add("unit 4 ");
        unitarr.add("unit 5");
        unitarr.add("unit 6");
        unitarr.add("Exam 2 ") ;
        unitarr.add("unit 7");
        unitarr.add("Final ");

        Scorearr.add("80");
        Scorearr.add("90");
        Scorearr.add("40");
        Scorearr.add("60");
        Scorearr.add("50");
        Scorearr.add("90");
        Scorearr.add("100");
        Scorearr.add("20");
        Scorearr.add("45");
        Scorearr.add("10");
        Scorearr.add("40");
        Scorearr.add("60");
        Scorearr.add("50");
        Scorearr.add("90");
        Scorearr.add("100");
        Scorearr.add("20");
        Scorearr.add("45");
        Scorearr.add("10");
        Scorearr.add("40");
        Scorearr.add("60");
        Scorearr.add("50");
        Scorearr.add("90");
        Scorearr.add("100");
        Scorearr.add("20");
        Scorearr.add("45");

        DateArr.add("14-4-2019");
        DateArr.add("5-5-2019");
        DateArr.add("18-6-2019");
        DateArr.add("17-9-2019");
        DateArr.add("4-10-2019");
        DateArr.add("14-12-2019");
        DateArr.add("11-1-2020");
        DateArr.add("19-4-2020");
        DateArr.add("20-5-2020");
        DateArr.add("30-5-2020");
        DateArr.add("18-6-2019");
        DateArr.add("17-9-2019");
        DateArr.add("4-10-2019");
        DateArr.add("14-12-2019");
        DateArr.add("14-12-2019");
        DateArr.add("11-1-2020");
        DateArr.add("19-4-2020");
        DateArr.add("20-5-2020");
        DateArr.add("30-5-2020");
        DateArr.add("18-6-2019");
        DateArr.add("17-9-2019");
        DateArr.add("4-10-2019");
        DateArr.add("14-12-2019");
        DateArr.add("11-1-2020");
        DateArr.add("19-4-2020");
        DateArr.add("20-5-2020");


        TableLayout table = (TableLayout)findViewById(R.id.table_result);

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        params.setMargins(15,15,1,5);


        table.setColumnStretchable(0,true);
        table.setColumnStretchable(1,true);
        table.setColumnStretchable(2,true);
        table.setColumnStretchable(3,true);
        Typeface face = Typeface.create("sans-serif-condensed-light", Typeface.BOLD_ITALIC);


       for(int i = 0; i<unitarr.size(); i++)
       {
           unit1[i] = new TextView(this);
         //  unit1[i].setWidth(TableRow.LayoutParams.WRAP_CONTENT);
           unit1[i].setText(unitarr.get(i));
           unit1[i].setTypeface(face );
           unit1[i].setTextSize(20);
           unit1[i].setGravity(Gravity.CENTER);
           unit1[i].setTextColor(Color.parseColor("#000000"));

           unit1[i].setBackgroundColor(Color.parseColor("#FFFFFF"));


           score[i] = new TextView(this);
           score[i].setTypeface(face );
           score[i].setText(Scorearr.get(i)+"%");
           score[i].setTextSize(20);
           score[i].setGravity(Gravity.CENTER);
           score[i].setTextColor(Color.parseColor("#000000"));
           score[i].setBackgroundColor(Color.parseColor("#FFFFFF"));

           if(Integer.parseInt(Scorearr.get(i))<50) {
               //score[i].setBackgroundColor(Color.parseColor("#AA1010"));
               score[i].setTextColor(Color.parseColor("#AA1010"));
               status[i] = new TextView(this);
               status[i].setTypeface(face);
               status[i].setText("Failed");
               status[i].setTextSize(20);
               status[i].setGravity(Gravity.CENTER);
               status[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
               status[i].setTextColor(Color.parseColor("#AA1010"));
           }
           else{
               status[i] = new TextView(this);
               status[i].setTextColor(Color.parseColor("#62AA62"));
               status[i].setTypeface(face);
               status[i].setText("Pass");
               status[i].setTextSize(20);
               status[i].setGravity(Gravity.CENTER);
               status[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
           }
           Date[i] = new TextView(this);
           Date[i].setTypeface(face );


           Date[i].setText(DateArr.get(i));
           Date[i].setTextSize(20);
           Date[i].setGravity(Gravity.CENTER);
           Date[i].setTextColor(Color.parseColor("#000000"));

           Date[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
           row[i] = new TableRow(this);
           row[i].addView(unit1[i],params);
           row[i].addView(score[i],params);
           row[i].addView(status[i],params);
           row[i].addView(Date[i],params);

          table.addView(row[i]);
       }

        YoYo.with(Techniques.Landing).duration(1600).playOn(table);
    }
}