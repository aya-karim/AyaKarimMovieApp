package com.example.yokaa.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.yokaa.movieapp.MovieDetails.DetailActivity;
import com.example.yokaa.movieapp.TabletView.DataListener;
import com.example.yokaa.movieapp.TabletView.TabletDetailFragment;

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
    public void setData(Bundle movieBundle) {

        if (!mIstTwoPain)
        {
            Intent i = new Intent(this, DetailActivity.class).putExtra("movie",movieBundle);
            i.putExtra("movieInfo", movieBundle);
            startActivity(i);

        }
        else {
            TabletDetailFragment Detail = new TabletDetailFragment();
            Detail.setArguments(movieBundle);
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction2.replace(R.id.FrameLayoutMain2,Detail);
            fragmentTransaction2.commit();
        }

    }
}
