package com.example.fahee.chatapplication.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fahee.chatapplication.DetailActivity;
import com.example.fahee.chatapplication.R;
import com.example.fahee.chatapplication.model.Movie;

import java.util.List;

/**
 * Created by fahee on 1/15/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mContext,List<Movie> movieList){
        this.mContext=mContext;
        this.movieList=movieList;
    }
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i ){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card,viewGroup,false);
        return new MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder viewHolder,int i){
        Movie movie = movieList.get(i);

        viewHolder.title.setText(movieList.get(i).getOrginialTitle());
        String vote = Double.toString(movieList.get(i).getVoteAverage());
        viewHolder.userrating.setText(vote);

        Glide.with(mContext).load(movieList.get(i).getPosterpath()).placeholder(R.drawable.load).into(viewHolder.thumbnail);


    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,userrating;
        public ImageView thumbnail;

        public MyViewHolder(final View view){
            super(view);
            title=(TextView)view.findViewById(R.id.title);
            userrating=(TextView)view.findViewById(R.id.userrating);
            thumbnail = (ImageView)view.findViewById(R.id.thumbnail);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view1){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("orginal_title",movieList.get(pos).getOrginialTitle());
                        intent.putExtra("poster_path",movieList.get(pos).getPosterpath());
                        intent.putExtra("overview",movieList.get(pos).getOverview());
                        intent.putExtra("vote_average",Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("release_date",movieList.get(pos).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(),"YOU CLICKED"+clickedDataItem.getOrginialTitle(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        public void bindView(Movie movie){

        }
    }

}