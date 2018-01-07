package com.example.fahee.timetabledailylife;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class AddTask extends AppCompatActivity {
   private FirebaseDatabase database;
    private DatabaseReference myRef;
    EditText editTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        database = FirebaseDatabase.getInstance();
    }

    public void addButtonClicked(View view){
        editTask=(EditText) findViewById(R.id.editTask);
        String name = editTask.getText().toString();

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyy h:mm a");
        String dateString = sdf.format(date);

        myRef=database.getInstance().getReference().child("Tasks");
        DatabaseReference newTasks = myRef.push();
        newTasks.child("name").setValue(name);
        newTasks.child("time").setValue(dateString);
    }
}
