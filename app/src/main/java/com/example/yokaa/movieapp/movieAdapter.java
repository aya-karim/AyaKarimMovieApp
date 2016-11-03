package com.example.yokaa.movieapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yokaa on 10/21/16.
 */

public class movieAdapter extends BaseAdapter {

    private Context currentContext ;

    private  List<jasonMovieObj> MoviesBlaa;

    public  movieAdapter(Context c , List<jasonMovieObj> str)
    {
        this.currentContext=c;
        this.MoviesBlaa= str;
    }
    @Override
    public int getCount() {
        return MoviesBlaa.size();
    }

    @Override
    public Object getItem(int position) {
        return MoviesBlaa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)currentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.movie_list_item,parent,false);


        ImageView MovieImageView = (ImageView)convertView.findViewById(R.id.movieImageView);
        Picasso.with(currentContext).load(MoviesBlaa.get(position).imgPath).into(MovieImageView);
        Log.i("info", MoviesBlaa.get(position).imgPath);
        //TextView MovieTitle = (TextView)convertView.findViewById(R.id.movieNameTxtView);
        //MovieTitle.setText(MoviesBlaa.get(position).movieTitle);

        return convertView;
    }
}
