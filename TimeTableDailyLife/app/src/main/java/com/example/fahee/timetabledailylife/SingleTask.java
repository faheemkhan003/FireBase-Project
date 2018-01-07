package com.example.fahee.timetabledailylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SingleTask extends AppCompatActivity {

    private String task_key;
    private TextView singleTask;
    private  TextView singleTime;
    private DatabaseReference mDatabase;
    private Button deleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        task_key = getIntent().getExtras().getString("TaskId");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tasks");

        singleTask = (TextView) findViewById(R.id.singleTask);
        singleTime = (TextView) findViewById(R.id.singleTime);
        deleteData = (Button) findViewById(R.id.deleteData);
        mDatabase.child(task_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String task_title =(String) dataSnapshot.child("name").getValue();
                String task_time = (String) dataSnapshot.child("time").getValue();

                singleTime.setText(task_time);
                singleTask.setText(task_title);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           mDatabase.child(task_key).removeValue();
                Intent intent = new Intent(SingleTask.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
