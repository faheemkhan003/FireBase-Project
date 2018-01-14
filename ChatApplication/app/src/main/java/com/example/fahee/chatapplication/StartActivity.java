package com.example.fahee.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button mRegbtn;
    private Button mAleardyregbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mRegbtn  = (Button) findViewById(R.id.start_reg_btn);
        mAleardyregbtn=(Button)findViewById(R.id.alerdyhaveaccount_reg_btn);

        mRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_Intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(reg_Intent);
                finish();
            }
        });
        mAleardyregbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainpage = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(mainpage);
                finish();
            }
        });
    }
}
