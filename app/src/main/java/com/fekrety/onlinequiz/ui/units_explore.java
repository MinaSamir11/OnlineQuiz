package com.fekrety.onlinequiz.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import  com.fekrety.onlinequiz.R;
import  com.fekrety.onlinequiz.adapters.unit_adapter;
import  com.fekrety.onlinequiz.getdata.GetUnit;
import  com.fekrety.onlinequiz.internetcheck;
import  com.fekrety.onlinequiz.model.Units;
import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;

import java.io.IOException;
import java.util.ArrayList;

public class units_explore extends AppCompatActivity {

    private unit_adapter data;
    private GetUnit getunits =new GetUnit();
    private ListView listView;
    private ProgressBar mProgressBar;
    private TSnackbar snackBar;
    private TSnackbar snackBarwaring;
    private ImageButton back;
    private ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units_explore);
        mProgressBar = findViewById(R.id.progressBar2);
        listView = (ListView) findViewById(R.id.list_view);
        back = (ImageButton) findViewById(R.id.bu_backkk) ;
        viewGroup = (ViewGroup) findViewById(android.R.id.content).getRootView();//注意getRootView()最为重要，直接关系到TSnackBar的位置


        showDialog();
         snackBar = TSnackbar.make(viewGroup.findViewById(android.R.id.content), "Loading Please Wait...", TSnackbar.LENGTH_INDEFINITE, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
        snackBar.setActionTextColor(getResources().getColor(R.color.Black));
        snackBar.setPromptThemBackground(Prompt.SUCCESS);
        snackBar.addIconProgressLoading(0, true, false);
        snackBarwaring = TSnackbar.make(viewGroup.findViewById(android.R.id.content), "Can't Connect Right Now...", TSnackbar.LENGTH_INDEFINITE, TSnackbar.APPEAR_FROM_TOP_TO_DOWN);
       // snackBarwaring.setBackgroundColor(getResources().getColor(R.color.black));
        snackBarwaring.setPromptThemBackground(Prompt.WARNING);
        snackBarwaring.setActionTextColor(getResources().getColor(R.color.blue1));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(units_explore.this, Drawablelist.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public boolean isConnected() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { Log.e("ERROR", "IOException",e); }
        catch (InterruptedException e) { Log.e("ERROR", "InterruptedException",e); }

        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        /*
        new internetcheck(internet -> {
            if(internet) {
                showrecord showrecordd = new showrecord(this);
                new Thread(showrecordd).start();
            } else{
                hideDialog();
                snackBarwaring.setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog();
                        new internetcheck(internet -> {
                            if (internet) {
                                showrecord showrecordd = new showrecord(getApplicationContext());
                                new Thread(showrecordd).start();
                            }else {
                                hideDialog();
                                Toast.makeText(units_explore.this, "can't connect right now, Try again later", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                snackBarwaring.show();
            }
        });

         */
    }

    public static long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }

    private  void showDialog(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public  void hideDialog() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }


    class showrecord implements Runnable {
        private Context context;
        private ArrayList<Units> units;
        showrecord(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            snackBar.show();
              units = new ArrayList<>(getunits.GetAllUnits(context));
            if(units.isEmpty()){
                try {
                    units.clear();
                    Thread.sleep(50);
                    units = getunits.GetAllUnits(context);
                    if(!units.isEmpty()) {
                        data = new unit_adapter(context, units,viewGroup);
                        add();
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Stuff that updates the UI
                                hideDialog();
                                snackBar.dismiss();
                                snackBarwaring.show();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                data = new unit_adapter(context, units,viewGroup);
                add();
            }
        }
    }
    private void add(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                listView.setAdapter(data);
                hideDialog();
                snackBar.dismiss();
            }
        });
    }
}
