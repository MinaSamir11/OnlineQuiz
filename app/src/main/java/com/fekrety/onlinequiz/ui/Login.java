package com.fekrety.onlinequiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.MaterialEditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import  com.fekrety.onlinequiz.R;
import  com.fekrety.onlinequiz.UserClient;
import  com.fekrety.onlinequiz.getdata.Getstudentinfo;
import  com.fekrety.onlinequiz.model.Studentlogin;
import  com.fekrety.onlinequiz.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import net.cryptobrewery.androidprocessingbutton.ProcessButton;

import static android.text.TextUtils.isEmpty;

public class Login extends AppCompatActivity implements
        View.OnClickListener {
     private ProcessButton btnFail;
     private MaterialEditText ID ;
     private MaterialEditText Password ;
     private Getstudentinfo Student_info =new Getstudentinfo();
     private  Studentlogin Student;
    private static final String TAG = "LoginActivity";


    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar mProgressBar;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = findViewById(R.id.progressBar);

        ID = (MaterialEditText) findViewById(R.id.txt2);
        Password = (MaterialEditText) findViewById(R.id.txt);
        btnFail = findViewById(R.id.failure);
          hideDialog();
        setupFirebaseAuth();

        btnFail.setOnClickListener(this);

        btnFail.setProgressActivated(false);
        btnFail.setIntepolator(ProcessButton.interpolators.INTERPOLATOR_LINEAR);
        btnFail.setMultipleProgressColors(new String[]{"#EF9812", "#A323E1", "#ABCDEF", "#9ABEF1"});
        btnFail.setBtnBackgroundColor("#253031");
        btnFail.setBtnText("Sign in");
        btnFail.setFailureTxt("Failed !");
        btnFail.setBtnTextColor("#FFFFFF");
        btnFail.setSuccessTxt("Success");
        btnFail.setIndeterminate(true);
        ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ID.getText().toString().trim().length() != 0) {
                    btnFail.setBtnBackgroundColor("#253031");
                    btnFail.setBtnText("Sign in");
                    btnFail.setBtnTextColor("#FFFFFF");
                    ID.setError(null);
                    Student = null;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Password.getText().toString().trim().length() != 0) {
                    btnFail.setBtnBackgroundColor("#253031");
                    btnFail.setBtnText("Sign in");
                    btnFail.setBtnTextColor("#FFFFFF");
                    Password.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnFail.setOnBtnClickListener(new ProcessButton.onClickListener() {
            @Override
            public void onClick() {
                btnFail.playProgress();
                btnFail.setBtnText("signing in");
                btnFail.setBtnTextColor("#17130A");
                btnFail.setBtnBackgroundColor("#E4B860");
                if (ID.getText().toString().trim().length() == 0 || Password.getText().toString().trim().length() == 0) {
                    if (ID.getText().toString().trim().length() == 0) {
                        btnFail.setFailureTxt("Failed ! ");
                        btnFail.setButtonState(ProcessButton.state.FAILURE);
                        btnFail.stopProgress();
                        ID.setError("Enter your ID");
                        return;
                    } else if (Password.getText().toString().trim().length() == 0) {
                        btnFail.setFailureTxt("Failed ! ");
                        Password.setError("Enter Password");
                        btnFail.setButtonState(ProcessButton.state.FAILURE);
                        btnFail.stopProgress();
                        return;
                    }
                } else {
                    if (Student == null) {
                        int x = Integer.parseInt(ID.getText().toString());
                        ShowRecords(x);
                    }
                }
                if (Student != null) {
                        if (ID.getText().toString().trim().equals(Student.getStudent_ID()) && Password.getText().toString().trim().equals(Student.getPassword())) {
                            btnFail.setButtonState(ProcessButton.state.SUCCESS);
                            signIn();
                        }else{
                            btnFail.setFailureTxt("Wrong ID OR Password");
                            btnFail.setButtonState(ProcessButton.state.FAILURE);
                            btnFail.stopProgress();
                            Student = null;
                        }
                    }else {
                        btnFail.setFailureTxt("Wrong ID OR Password");
                        btnFail.setButtonState(ProcessButton.state.FAILURE);
                        btnFail.stopProgress();
                        Student = null;
                    }
            }
        });

        btnFail.setOnStartEndAnimationListener(new ProcessButton.onAnimationPhases() {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {
            }
        });

    }   //end on create
    private void ShowRecords(int x ){
         Student =   new Studentlogin();
        //Student = Student_info.Getstudent(x,getApplicationContext());
        Student.setStudent_ID("68432");
        Student.setStudent_Name("paula");
        Student.setEmail("paula@gmail.com");
        Student.setPassword("123456");
        Student.setLocation("Maadi");
        Student.setLevel("1");
        Student.setChatroom_id("rnju2kRV92xKOS0ryuHL");
    }

    /*
       ----------------------------- Firebase setup ---------------------------------
    */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(Login.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT).show();

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                            .setTimestampsInSnapshotsEnabled(true)
                            .build();
                    db.setFirestoreSettings(settings);

                    DocumentReference userRef = db.collection(getString(R.string.collection_users))
                            .document(user.getUid());

                    userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "onComplete: successfully set the user client.");
                                User user = task.getResult().toObject(User.class);
                                ((UserClient)(getApplicationContext())).setUser(user);
                            }
                        }
                    });
                    if(Student == null){
                        int x = Integer.parseInt(ID.getText().toString());
                        ShowRecords(x);
                    }
                    Intent intent = new Intent(Login.this, Drawablelist.class);
                    intent.putExtra("Studentinfo", Student);
                    startActivity(intent);
                    finish();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }



    private void showDialog(){
        mProgressBar.setVisibility(View.VISIBLE);

    }
    private void hideDialog(){
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        //
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn(){
        //check if the fields are filled out
        if(!isEmpty(Student.getEmail())
                && !isEmpty(Student.getPassword())){
            Log.d(TAG, "onClick: attempting to authenticate.");

            showDialog();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(Student.getEmail(),
                    Student.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            hideDialog();
                            FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
                            setupFirebaseAuth();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    hideDialog();
                }
            });
        }else{
            Toast.makeText(Login.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {

    }
}
