package com.example.fahee.timetabledailylife;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mTastList;
    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTastList = (RecyclerView) findViewById(R.id.task_list);
        mTastList.setHasFixedSize(true);
        mTastList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tasks");

        TextView bannerDay = (TextView) findViewById(R.id.bannerDay);
        TextView bannerDate = (TextView) findViewById(R.id.bannerDate);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        bannerDay.setText(dayOfTheWeek);

        long date = System.currentTimeMillis();
        SimpleDateFormat sdff = new SimpleDateFormat("MMM MM dd, yyy h:mm a");
        String dateString = sdff.format(date);
        bannerDate.setText(dateString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TaskViewHolder(View itemView){

            super(itemView);
            mView=itemView;

        }
        public void setName(String name){
            TextView task_name = (TextView) mView.findViewById(R.id.taskName);
            task_name.setText(name);
        }
        public void setTime(String time){
            TextView task_time = (TextView) mView.findViewById(R.id.taskTime);
            task_time.setText(time);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Task,TaskViewHolder> FBRA = new FirebaseRecyclerAdapter<Task, TaskViewHolder>(
                Task.class,
                R.layout.task_row,
                TaskViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(TaskViewHolder viewHolder, Task model, int position) {
                final String task_key = getRef(position).getKey().toString();
              viewHolder.setName(model.getName());
                viewHolder.setTime(model.getTime());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleTaskActivity = new Intent(MainActivity.this,SingleTask.class);
                        singleTaskActivity.putExtra("TaskId",task_key);
                        startActivity(singleTaskActivity);
                    }
                });
            }
        };
            mTastList.setAdapter(FBRA);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }else if(id == R.id.addTask){
            Intent addintent = new Intent(MainActivity.this,AddTask.class);
            startActivity(addintent);
        }
        return super.onOptionsItemSelected(item);
    }
}
