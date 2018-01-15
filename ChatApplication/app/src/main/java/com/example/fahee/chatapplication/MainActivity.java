package com.example.fahee.chatapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fahee.chatapplication.adapter.MoviesAdapter;
import com.example.fahee.chatapplication.api.Client;
import com.example.fahee.chatapplication.api.Service;
import com.example.fahee.chatapplication.model.Movie;
import com.example.fahee.chatapplication.model.MoviesResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<Movie> movieList;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;
    public static final String LOG_TAG = MoviesAdapter.class.getName();

    private FirebaseAuth mAuth;
   // private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //movie wala kam....

        mAuth = FirebaseAuth.getInstance();
        initViews();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.main_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);


        // Movie Wala Kam Yaha Se START Hai.......



        initViews();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this, "Movies Refreshed", Toast.LENGTH_LONG).show();
            }
        });
    }

    //movie kam end
       /* mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Chat");*/

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            sendToStart();
        }


    }

    private void sendToStart() {
        Intent startIntent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.main_logout_btn) {
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        //movie wala code......
        switch (item.getItemId()){
            case R.id.menu_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
    //movie wala kam yaha se start hai ......
    public Activity getActivity(){
        Context context = this;
        while (context instanceof ContextWrapper){
          if(context instanceof Activity){
              return (Activity) context;
          }
          context = ((ContextWrapper)context).getBaseContext();
        }

        return null;
    }
    public void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetches Movies....");
        pd.setCancelable(false);
        pd.show();

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        adapter = new MoviesAdapter(this,movieList);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJson();
    }
    public void loadJson(){

        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org",Toast.LENGTH_LONG).show();
                pd.dismiss();
                return;
            }
            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<MoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    List<Movie> movies = response.body().getResults();
                    recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(),movies));
                    recyclerView.smoothScrollToPosition(0);
                    if(swipeContainer.isRefreshing()){
                        swipeContainer.setRefreshing(false);
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {

                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this,"Error Fetching Data!",Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("Error",e.getMessage());
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }

}
