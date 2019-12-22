package com.fekrety.onlinequiz.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import  com.fekrety.onlinequiz.R;
import  com.fekrety.onlinequiz.model.Studentlogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Drawablelist extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView location , level ;
    int index = 0;
    int[] imgs ;
    private static final long CountDowninMilles = 150000;   //10 min
    private CountDownTimer CountDownn;
    private boolean TimerRunning;
    private long timeLeft_in_Millis = CountDowninMilles;
    private at.grabner.circleprogress.CircleProgressView p1 ;
    private Studentlogin student  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startcounttime();

        getIncomingIntent();

        location = (TextView)findViewById(R.id.your_Class2);
        level = (TextView)findViewById(R.id.txtv_Level);
        location.setText(student.getLocation());
        level.setText(student.getLevel()+", secondary");


        imgs= new int[]{
                R.drawable.awlad,R.drawable.sss,R.drawable.kkkkkk,R.drawable.adad,
                R.drawable.kakkk,R.drawable.picb,R.drawable.kab,R.drawable.kakaa};



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Drawablelist.this, mediatorActivity.class);
                intent.putExtra("Chatroomid", student.getChatroom_id());
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    private long backPressedTime = 0;    // used by onBackPressed()
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press back again to exit",
                    Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up
            super.onBackPressed();       // bye
        }
    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("Studentinfo")){
            Intent i = getIntent();
            student = (Studentlogin) i.getSerializableExtra("Studentinfo");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Change_password) {
            return true;
        }
        else if(id == R.id.log_out)
        {
            signOut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Explore) {
            Intent intent = new Intent(this, units_explore.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else if (id == R.id.Results) {
            startActivity(new Intent(Drawablelist.this,Result_table.class));
        } else if (id == R.id.quiz) {
            Bundle b = new Bundle();
            b.putString("PasswordQuiz","123");
            Intent In = new Intent(Drawablelist.this,Empty.class);
            In.putExtras(b);
            startActivity(In);
            finish();
        } else if (id == R.id.CV) {

        } else if (id == R.id.Vision) {
            Intent intent = new Intent(Drawablelist.this, Missin_Vision.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        p1=(at.grabner.circleprogress.CircleProgressView)findViewById(R.id.prograssbar);
        p1.setTextTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        p1.setText(p1.getCurrentValue()+"%");
        p1.setValueAnimated(0, p1.getCurrentValue(),2300);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void startcounttime(){
        CountDownn = new CountDownTimer(timeLeft_in_Millis,5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft_in_Millis = millisUntilFinished;
                updateCountDownImage();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void updateCountDownImage(){
        ImageView img = (ImageView)findViewById(R.id.dynami_image);
        img.setImageResource(imgs[++index%imgs.length]);
        YoYo.with(Techniques.SlideInRight).duration(150).repeat(0).playOn(img);
    }
}
