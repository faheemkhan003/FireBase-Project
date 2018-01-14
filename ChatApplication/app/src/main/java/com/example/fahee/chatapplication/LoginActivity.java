package com.example.fahee.chatapplication;
import com.example.fahee.chatapplication.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button mLogin_btn;
    private ProgressDialog mLoginProgressDialog;

    private FirebaseAuth mAuth;
  //  private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        /*mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Login");*/



        mLoginEmail= (TextInputLayout) findViewById(R.id.login_email);
        mLoginPassword= (TextInputLayout) findViewById(R.id.login_password);
        mLogin_btn=(Button) findViewById(R.id.login_btn);
        mLoginProgressDialog =new ProgressDialog(this);





        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=mLoginEmail.getEditText().getText().toString();
                String password=mLoginPassword.getEditText().getText().toString();

                if(!TextUtils.isEmpty(email)  || !TextUtils.isEmpty(password)){

                    mLoginProgressDialog.setTitle("Loggin In");
                    mLoginProgressDialog.setMessage("Please Wait Your Id Login ");
                    mLoginProgressDialog.setCanceledOnTouchOutside(false);
                    mLoginProgressDialog.show();

                    LoginUser(email,password);


                }
            }
        });

    }

    private void LoginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    mLoginProgressDialog.dismiss();
                    Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else{

                    mLoginProgressDialog.hide();
                    Toast.makeText(LoginActivity.this,"Cannot sign in.Please check the form and try again.",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
