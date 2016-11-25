package com.example.yokaa.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class MainList extends AppCompatActivity implements DataListener {

    boolean mIstTwoPain=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//            }
//        });

        main_fragment main = new main_fragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.FrameLayoutMain,main);
        fragmentTransaction.commit();



        if (findViewById(R.id.FrameLayoutMain2)!=null)
        {
            mIstTwoPain=true;
        }
        main.setmDataListener(this);
    }


    @Override
    public void setData(ArrayList<jasonMovieObj> jMoviesList , int index) {

        if (!mIstTwoPain)
        {
            Bundle movieBundle = new Bundle();

                    int movieIndex = index;
                    movieBundle.putString("movieTitle",jMoviesList.get(movieIndex).movieTitle);
                    movieBundle.putString("releaseDate",jMoviesList.get(movieIndex).releaseDate);
                    movieBundle.putString("overView",jMoviesList.get(movieIndex).overView);
                    movieBundle.putString("imgPath",jMoviesList.get(movieIndex).imgPath);
                    movieBundle.putInt("userRating",jMoviesList.get(movieIndex).userRating);
                    movieBundle.putInt("id",jMoviesList.get(movieIndex).id);
                    Intent i = new Intent(this, DetailActivity.class).putExtra("movie",movieBundle);


             i.putExtra("movieInfo", movieBundle);
            startActivity(i);

        }
        else {
            TabletDetailFragment Detail = new TabletDetailFragment();

            Bundle movieBundle = new Bundle();

            int movieIndex = index;
            movieBundle.putString("movieTitle",jMoviesList.get(movieIndex).movieTitle);
            movieBundle.putString("releaseDate",jMoviesList.get(movieIndex).releaseDate);
            movieBundle.putString("overView",jMoviesList.get(movieIndex).overView);
            movieBundle.putString("imgPath",jMoviesList.get(movieIndex).imgPath);
            movieBundle.putInt("userRating",jMoviesList.get(movieIndex).userRating);
            movieBundle.putInt("id",jMoviesList.get(movieIndex).id);


            Detail.setArguments(movieBundle);
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.FrameLayoutMain2,Detail);
            fragmentTransaction2.commit();
        }

    }
}
