package com.fekrety.onlinequiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.MaterialEditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import  com.fekrety.onlinequiz.R;


public class Empty extends AppCompatActivity {
    Intent In = new Intent();
    Bundle b = new Bundle();
    Boolean Reset_Password=true,flag=true;
    LayoutInflater QuizDialog ;
    View ViewLayout ;
    AlertDialog.Builder Alertquiz;
    Button busubmit;
    public boolean Check_Quiz_Password(String Student_Password){
        if(b.getString("PasswordQuiz").equals(Student_Password))
        {
            return true;
        }
        return  false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
         b = getIntent().getExtras();
        QuizDialog = LayoutInflater.from(Empty.this);
        ViewLayout = QuizDialog.inflate(R.layout.dialog_quiz_password, null);
        Alertquiz = new AlertDialog.Builder(Empty.this).setView(ViewLayout);
        final MaterialEditText txt_password  = (MaterialEditText)ViewLayout.findViewById(R.id.txt_password);
        busubmit=(Button)ViewLayout.findViewById(R.id.bu_Submit_quiz_password);


        Alertquiz.setCancelable(false);

        Alertquiz.create();
        Alertquiz.show();
        busubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Check_Quiz_Password(txt_password.getText().toString()))
                {
                    startActivity(new Intent(Empty.this, MainQuiz.class));
                    finish();
                }
                else{
                    if(!flag){
                        if(txt_password.getText().length()!=0) {
                            txt_password.setError("Wrong Password");
                            int errorColor = getResources().getColor(R.color.material_red);
                            txt_password.setErrorColor(errorColor);
                        }
                        else {

                        }
                    }
                    if(flag){
                        flag=false;
                        if(txt_password.getText().length()==0){
                            txt_password.setError(" * Fill the Field");
                            int errorColor = getResources().getColor(R.color.black);
                            txt_password.setErrorColor(errorColor);
                        }
                    }
                }
            }
        });
        txt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( txt_password.getText().length()>0)
                {
                    txt_password.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if( txt_password.getText().length()==0)
                {
                    txt_password.setError(" * Fill the Field");
                    int errorColor = getResources().getColor(R.color.black);
                    txt_password.setErrorColor(errorColor);
                }
            }
        });

    }
}
