package com.fekrety.onlinequiz.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import  com.fekrety.onlinequiz.R;

import java.util.ArrayList;


public class MainQuiz extends AppCompatActivity {
    private static final long Time = 600000;   //10 min
    private int size;
    private long timeLeft = Time;
    private static int x = 0;                        // variable to Go to needed Question
    private LayoutInflater Lay;   // create object from Layout
    private static View PromtView;
    private AlertDialog.Builder Dialog1;  // show layout in alert dialog
    private Dialog d1;
    private ArrayList<String> questionsss ;
    private CheckBox cb1;                // Choice
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private boolean flag = true;                     // set flag to Create Dialog one time only
    private Button Submit, logout;
    private com.spark.submitbutton.SubmitButton finishquiz;
    private ArrayList<Button> buttons = new ArrayList<>();
  int dam[] = new int[5];

    static private void increaseX() {
        x++;
    }
    static private void instanceX() {
        x = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_quizz);
        dam[0] =0 ;
        questionsss = new ArrayList<>();
        questionsss.add("The teacher asked me ........... received his e-mail");
        questionsss.add("Dan asked me whether I ......... again the following year");
        questionsss.add("I said that ali ........ be tired the next day");
        questionsss.add("I complained that it ........ rather late and that it was time for him to go to sleep ");
        questionsss.add("He suggested that ........ a doctor ");
        questionsss.add("The doctor said to me 'stop smoking'" + "\n" + "- the doctor advised me ........... smoking.");
        questionsss.add("He suggested that ............ a doctor .");
        questionsss.add("He told us that he ..... his bike home at the moment");
        questionsss.add("the teacher ....... osama where his homework was");
        questionsss.add("He admitted that he had arrived late");

        Lay = LayoutInflater.from(MainQuiz.this);                          // put Layout in Variable
        PromtView = Lay.inflate(R.layout.questions, null);    // put layout in variable View to deal with Alert dialog
        Dialog1 = new AlertDialog.Builder(MainQuiz.this).setView(PromtView);
        Submit = (Button) PromtView.findViewById(R.id.submit);                       // Instiallize Submit Button
        finishquiz = (com.spark.submitbutton.SubmitButton) PromtView.findViewById(R.id.Finishquiz);         // finish Quiz Button
        initChec_box();
        init_bu();
        buildLayoutQuestion(questionsss.get(0));                                                 // Build First Question manual
        size = questionsss.size();

        Start_quiz_timer();
    }      //// } on Create

    @Override
    public void onBackPressed() {                                                  //////// On Click Back  in Quiz
        final AlertDialog.Builder hint;                                                                          //Create Dialog While Quiting from Quiz and Set his Mark of the Exam to 0
        hint = new AlertDialog.Builder(MainQuiz.this);
        hint.setTitle("do you want to quit");
        hint.setMessage("Please note that you will get 0 in this exam");
        hint.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {                                                // this guy will get 0
                finish();                                                                                                     // finish activity Quiz
            }
        });

        hint.setNegativeButton("No", new DialogInterface.OnClickListener() {                           // 5aff
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        hint.create();
        hint.show();
    }

    private void buildLayoutQuestion(String s) {                                          ///////////// Set Text Question in Dialog
        TextView txtt;    //set question
        txtt = (TextView) MainQuiz.PromtView.findViewById(R.id.textView4);
        YoYo.with(Techniques.DropOut).duration(550).playOn(txtt);
        txtt.setText(s);
    }

    private void VisiblebuFinshquiz() {
        com.spark.submitbutton.SubmitButton finishquiz = (com.spark.submitbutton.SubmitButton) PromtView.findViewById(R.id.Finishquiz);
        finishquiz.setVisibility(View.VISIBLE);
    }
    private void InvisiblebuFinshquiz() {
        com.spark.submitbutton.SubmitButton finishquiz = (com.spark.submitbutton.SubmitButton) PromtView.findViewById(R.id.Finishquiz);
        finishquiz.setVisibility(View.INVISIBLE);
    }

    private void AddAll() {
        final LinearLayout rl = (LinearLayout) PromtView.findViewById(R.id.layout_bu);
        final LinearLayout rl_2 = (LinearLayout) PromtView.findViewById(R.id.layout_bu_row2);
        for (int i = 0; i < size; i++) {
            final Button btn = new Button(this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            btn.setText("" + (i + 1));
            btn.setId(i);
            final int final_i = i;
            btn.setBackgroundResource(R.drawable.button_question);
            btn.setTextSize(14);
            btn.setTextColor(getResources().getColor(R.color.black));
            p.setMargins(3, 4, 3, 4);
            p.weight = 1;
            btn.setLayoutParams(p);
            buttons.add(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x = final_i;
                    if (questionsss.size() - 1 == x) {
                        //   btnn.setTextColor(getResources().getColor(R.color.blue1));
                        VisiblebuFinshquiz();
                    } else {
                        InvisiblebuFinshquiz();
                    }
                    for (int i = 0; i < buttons.size(); i++) {
                        if (i == final_i) {
                            buttons.get(final_i).setBackgroundResource(R.drawable.button_questionii);   // orange
                        } else {
                            buttons.get(i).setBackgroundResource(R.drawable.button_question);   // blue
                            //Toast.makeText(MainQuiz.this, "Yes resource button "+ i + "i not equal green" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    buildLayoutQuestion(questionsss.get(final_i));
                }
            });
            if (i < 13) {
                rl.addView(btn);
            } else {
                rl_2.addView(btn);
            }
        }
    }
    private void Start_quiz_timer(){
        CountDownTimer CountDownn;
        final TextView CountDown = (TextView) findViewById(R.id.CountDown);                                 ///// Quiz Time   // set Time to Text
        CountDownn = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                CountDown.setText("" + (int) timeLeft / 60000 + ":" + timeLeft % 60000 / 1000);
            }

            @Override
            public void onFinish() {
            }
        }.start();

    }
    private void initChec_box(){
        cb1 = (CheckBox) PromtView.findViewById(R.id.checkBox);                   // installize Multiple Choice Buttons
        cb2 = (CheckBox) PromtView.findViewById(R.id.checkBox2);
        cb3 = (CheckBox) PromtView.findViewById(R.id.checkBox3);
        cb4 = (CheckBox) PromtView.findViewById(R.id.checkBox4);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                         //Multiple Choice 1
                if (cb1.isChecked()) {
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                }
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                         //Multiple Choice 2
                if (cb2.isChecked()) {
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                }
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                          //Multiple Choice 3
                if (cb3.isChecked()) {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb4.setChecked(false);
                }
            }
        });

        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                     //Multiple Choice 4
                if (cb4.isChecked()) {
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });
    }
    private  void init_bu(){
        finishquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                          //// Button  finish Quiz
                finishquiz.setEnabled(false);
            }
        });
        logout = findViewById(R.id.Log_Out);                             // Log out Button /// disable it while time is not equal to zero or Student is not Clicked on finish Button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainQuiz.this, Login.class));
                finish();
            }
        });


        Dialog1.setCancelable(false).setPositiveButton("Home", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {                                 /// set Button Dialog // Go to Home Quiz
                d1.cancel();
            }
        });


        final Button bu = findViewById(R.id.question);                         // Intiallize McQ Button
        bu.setOnClickListener(new View.OnClickListener() {                   /// on Ciick First Main Questions (MCQ) ..
            @Override
            public void onClick(View v) {
                if (!flag) {
                    buildLayoutQuestion(questionsss.get(x));
                    // Show Dialog & Question on the last Time Student is on it before Click Home
                    d1.show();
                }
                if (flag) {
                    AddAll();
                    /// Set Questions in Dialog and Open Buttons (Visible) Depend on Number of Questions
                }
                if (flag) {
                    d1 = Dialog1.create();                                                                         // create dialog first time only
                    flag = false;
                    d1.show();                                                                                         // show dialog question
                }
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                                                 // Submit Answer of Question
                if (questionsss.size() - 1 > x) {
                    buttons.get(x).setBackgroundResource(R.drawable.submitanswer);
                    increaseX();
                    buildLayoutQuestion(questionsss.get(x));
                    if (x == questionsss.size() - 1) {
                        VisiblebuFinshquiz();
                    }
                } else {
                    instanceX();
                    InvisiblebuFinshquiz();
                    d1.cancel();
                }
            }
        });
    }
}


